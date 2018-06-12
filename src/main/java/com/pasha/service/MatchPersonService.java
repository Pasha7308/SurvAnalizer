package com.pasha.service;

import com.pasha.entity.MatchPerson;
import com.pasha.repository.MatchPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class MatchPersonService {
    private final MatchPersonRepository repository;

    @Autowired
    public MatchPersonService(MatchPersonRepository repository) {
        this.repository = repository;
    }

    public MatchPerson save(MatchPerson matchPerson) {
        return repository.save(matchPerson);
    }
}
