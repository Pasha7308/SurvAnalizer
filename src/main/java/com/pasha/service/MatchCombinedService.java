package com.pasha.service;

import com.pasha.entity.MatchCombined;
import com.pasha.repository.MatchCombinedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MatchCombinedService {
    @Autowired private MatchCombinedRepository repository;

    public MatchCombined save(MatchCombined matchCombined) {
        return repository.save(matchCombined);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public List<MatchCombined> findAll() {
        return repository.findAll();
    }

    public List<MatchCombined> findAllByOrderByExtIdDesc() {
        return repository.findAllByOrderByExtIdDesc();
    }

    public MatchCombined findByExtId(Integer extId) {
        return repository.findByExtId(extId);
    }
}
