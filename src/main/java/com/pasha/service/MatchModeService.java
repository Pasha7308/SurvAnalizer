package com.pasha.service;

import com.pasha.entity.MatchMode;
import com.pasha.repository.MatchModeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MatchModeService {
    @Autowired private MatchModeRepository repository;

    public MatchMode save(MatchMode matchMode) {
        return repository.save(matchMode);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public List<MatchMode> findAll() {
        return repository.findAllByOrderByNameAsc();
    }

    public MatchMode findByName(String name) {
        return repository.findByName(name);
    }
}
