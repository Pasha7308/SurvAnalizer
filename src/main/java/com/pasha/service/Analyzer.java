package com.pasha.service;

import com.pasha.entity.MatchCombined;
import com.pasha.entity.MatchPerson;
import com.pasha.entity.MatchType;
import com.pasha.entity.analytics.Person;
import com.pasha.entity.analytics.ShowClass;
import com.pasha.entity.analytics.ShowClassDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class Analyzer {

    @Autowired private MatchCombinedService matchCombinedService;

    static public void initTable(ShowClassDesc showClassDesc) {
        showClassDesc.add("Описание", "description", 100);
        showClassDesc.add("pMatches", "pmatch", 20);
        showClassDesc.add("pK", "pk", 20);
        showClassDesc.add("pD", "pd", 20);
        showClassDesc.add("pKD", "pkd", 20);
        showClassDesc.add("dMatches", "dmatch", 20);
        showClassDesc.add("dK", "dk", 20);
        showClassDesc.add("dD", "dd", 20);
        showClassDesc.add("dKD", "dkd", 20);
    }

    public void analyze(List<ShowClass> show) {
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

        show.add(new ShowClass("Соло",
                String.valueOf(pasha.getMatchCount(true)),
                pasha.getKills(true), pasha.getDeaths(true), pasha.getKDs(true),
                String.valueOf(daniil.getMatchCount(true)),
                daniil.getKills(true), daniil.getDeaths(true), daniil.getKDs(true)));

        show.add(new ShowClass("Группа",
                String.valueOf(pasha.getMatchCount(false)),
                pasha.getKills(false), pasha.getDeaths(false), pasha.getKDs(false),
                String.valueOf(daniil.getMatchCount(false)),
                daniil.getKills(false), daniil.getDeaths(false), daniil.getKDs(false)));

    }
}
