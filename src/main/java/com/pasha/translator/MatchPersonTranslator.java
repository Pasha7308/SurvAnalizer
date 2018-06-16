package com.pasha.translator;

import com.pasha.entity.MatchPerson;
import com.pasha.entity.external.stats.ExtMatchStat;
import org.springframework.stereotype.Service;

@Service
public class MatchPersonTranslator {
    public MatchPerson extToLocal(ExtMatchStat stat) {
        MatchPerson matchPerson = new MatchPerson();
        matchPerson.setKills(stat.getKills());
        matchPerson.setDeaths(stat.getDies());
        matchPerson.setKd(stat.getKd());
        return matchPerson;
    }
}
