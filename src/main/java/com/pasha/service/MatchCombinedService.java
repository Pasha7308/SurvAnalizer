package com.pasha.service;

import com.pasha.entity.MatchCombined;

import java.util.List;

public interface MatchCombinedService {

    MatchCombined save(MatchCombined contact);

    void deleteAll();

    List<MatchCombined> findAll();

}
