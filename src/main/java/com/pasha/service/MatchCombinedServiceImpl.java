package com.pasha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pasha.entity.MatchCombined;
import com.pasha.repository.MatchCombinedRepository;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MatchCombinedServiceImpl implements MatchCombinedService {

    private final MatchCombinedRepository repository;

    @Autowired
    public MatchCombinedServiceImpl(MatchCombinedRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void generateTestData() {
        MatchCombined matchCombined = save(new MatchCombined(
                (int)Math.round(Math.random()*100), (int)Math.round(Math.random()*100), Math.random()*2.0,
                (int)Math.round(Math.random()*100), (int)Math.round(Math.random()*100), Math.random()*2.0));

        matchCombined = save(new MatchCombined(
                (int)Math.round(Math.random()*100), (int)Math.round(Math.random()*100), Math.random()*2.0,
                (int)Math.round(Math.random()*100), (int)Math.round(Math.random()*100), Math.random()*2.0));
//        save(new MatchCombined("Иван Иванов", "+123456789", "ivan@ivan.ov"));
//        save(new MatchCombined("Петр Петров", "+987654321", "petr@pe.tr"));
    }

    @Override
    public MatchCombined save(MatchCombined contact) {
        return repository.save(contact);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public List<MatchCombined> findAll() {
        return repository.findAll();
    }
}
