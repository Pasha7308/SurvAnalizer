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
        return matchCombined;
    }
}
