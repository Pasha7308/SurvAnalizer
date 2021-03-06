package com.pasha.translator;

import com.pasha.entity.MatchCombined;
import com.pasha.entity.MatchMode;
import com.pasha.entity.external.stats.ExtMatchStat;
import com.pasha.service.DateService;
import com.pasha.service.MatchModeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchCombinedTranslator {
    @Autowired private DateService dateService;
    @Autowired private MatchModeService matchModeService;

    public MatchCombined extToLocal(ExtMatchStat stat) {
        MatchCombined matchCombined = new MatchCombined();
        matchCombined.setExtId(stat.getMatch().getId());
        matchCombined.setMatchDate(dateService.jodaDateToLocalDate(stat.getDate()));
        matchCombined.setBattlefield(stat.getBattlefield().getTitle());
        matchCombined.setWeather(stat.getWeather().getTitle());
        matchCombined.setTeam(stat.getTeam());
        matchCombined.setRating_match(stat.getRating_match());
        matchCombined.setVictory(stat.getVictory());
        matchCombined.setLevel(stat.getLevel());

        String modeName = stat.getMode().getTitle();
        MatchMode mode = matchModeService.findByName(modeName);
        if (mode == null) {
            mode = matchModeService.save(new MatchMode(modeName));
        }
        matchCombined.setMode(mode);

        return matchCombined;
    }
}
