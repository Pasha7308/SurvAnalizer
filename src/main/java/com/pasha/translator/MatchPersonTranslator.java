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
        matchPerson.setArtefactUses(stat.getArtefactUses());
        matchPerson.setBoxesBringed(stat.getBoxesBringed());
        matchPerson.setPointCaptures(stat.getPointCaptures());
        matchPerson.setArtefactKills(stat.getArtefactKills());
        matchPerson.setMeleeKills(stat.getMeleeKills());
        matchPerson.setGrenadeKills(stat.getGrenadeKills());
        matchPerson.setHeadshots(stat.getHeadshots());
        matchPerson.setScore(stat.getScore());
        matchPerson.setPlace(stat.getPlace());
        matchPerson.setLevel(stat.getLevel());
        return matchPerson;
    }
}
