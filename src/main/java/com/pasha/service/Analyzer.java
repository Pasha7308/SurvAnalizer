package com.pasha.service;

import com.pasha.entity.MatchCombined;
import com.pasha.entity.MatchPerson;
import com.pasha.entity.MatchType;
import com.pasha.entity.analytics.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class Analyzer {

    @Autowired private MatchCombinedService matchCombinedService;
    @Autowired private MatchPersonService matchPersonService;


    public void analyze() {
        List<MatchCombined> matches = matchCombinedService.findAll();

        Person pasha = new Person();
        Person daniil = new Person();
        for (MatchCombined match : matches) {
            MatchPerson pashaMatch = match.getPasha();
            MatchPerson daniilMatch = match.getDaniil();
            boolean group = (pashaMatch != null) && (daniilMatch != null);

            if (group) {
                pasha.getGroup().addMatch(pashaMatch.getKills(), pashaMatch.getDeaths());
                daniil.getGroup().addMatch(daniilMatch.getKills(), daniilMatch.getDeaths());
            } else if (pashaMatch != null) {
                pasha.getSolo().addMatch(pashaMatch.getKills(), pashaMatch.getDeaths());
            } else {
                daniil.getSolo().addMatch(daniilMatch.getKills(), daniilMatch.getDeaths());
            }
        }

        pasha.divide();
        daniil.divide();

        MatchCombined matchCombined = new MatchCombined(99999998, LocalDateTime.now());
        matchCombined.setMatchType(MatchType.analyticsSolo);
        matchCombined.setPasha(matchPersonService.save(new MatchPerson(pasha.getKills(true), pasha.getDeaths(true), pasha.getKDs(true))));
        matchCombined.setDaniil(matchPersonService.save(new MatchPerson(daniil.getKills(true), daniil.getDeaths(true), daniil.getKDs(true))));
        matchCombinedService.save(matchCombined);

        matchCombined = new MatchCombined(99999999, LocalDateTime.now());
        matchCombined.setMatchType(MatchType.analyticsGroup);
        matchCombined.setPasha(matchPersonService.save(new MatchPerson(pasha.getKills(false), pasha.getDeaths(false), pasha.getKDs(false))));
        matchCombined.setDaniil(matchPersonService.save(new MatchPerson(daniil.getKills(false), daniil.getDeaths(false), daniil.getKDs(false))));
        matchCombinedService.save(matchCombined);
    }
}
