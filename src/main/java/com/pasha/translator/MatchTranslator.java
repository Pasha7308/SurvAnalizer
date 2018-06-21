package com.pasha.translator;

import com.pasha.entity.MatchCombined;
import com.pasha.entity.external.stats.ExtMatchStat;
import com.pasha.service.DateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchTranslator {
    @Autowired private DateService dateService;

    public MatchCombined extToLocal(ExtMatchStat stat) {
        MatchCombined matchCombined = new MatchCombined();
        matchCombined.setExtId(stat.getMatch().getId());
        matchCombined.setMatchDate(dateService.jodaDateToLocalDate(stat.getDate()));
        matchCombined.setBattlefield(stat.getBattlefield().getTitle());
        matchCombined.setMode(stat.getMode().getTitle());
        matchCombined.setWeather(stat.getWeather().getTitle());
        matchCombined.setTeam(stat.getTeam());
        matchCombined.setRating_match(stat.getRating_match());
        matchCombined.setVictory(stat.getVictory());
        matchCombined.setLevel(stat.getLevel());
        return matchCombined;
    }
}
