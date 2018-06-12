package com.pasha.service;

import com.pasha.entity.MatchCombined;

import java.util.List;

public interface MatchCombinedServiceInt {

    MatchCombined save(MatchCombined contact);

    void deleteAll();

    List<MatchCombined> findAll();

}
