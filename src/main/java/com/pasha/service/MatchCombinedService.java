package com.pasha.service;

import com.pasha.entity.MatchCombined;
import com.pasha.entity.MatchPerson;
import com.pasha.repository.MatchCombinedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class MatchCombinedService {

    private MatchCombinedRepository repository;
    private MatchPersonService matchPersonService;

    @Autowired
    public MatchCombinedService(MatchCombinedRepository repository, MatchPersonService matchPersonService) {
        this.repository = repository;
        this.matchPersonService = matchPersonService;
    }

    /*@PostConstruct
    public void generateTestData() {
        MatchCombined matchCombined = save(new MatchCombined(1, LocalDateTime.of(2018, 1, 1, 12, 0),
                matchPersonService.save(new MatchPerson((int)Math.round(Math.random()*100), (int)Math.round(Math.random()*100), Math.random()*2.0)),
                matchPersonService.save(new MatchPerson((int)Math.round(Math.random()*100), (int)Math.round(Math.random()*100), Math.random()*2.0))));

        matchCombined = save(new MatchCombined(2, LocalDateTime.of(2018, 1, 1, 12, 0),
                matchPersonService.save(new MatchPerson((int)Math.round(Math.random()*100), (int)Math.round(Math.random()*100), Math.random()*2.0)),
                matchPersonService.save(new MatchPerson((int)Math.round(Math.random()*100), (int)Math.round(Math.random()*100), Math.random()*2.0))));
    }*/

    public MatchCombined save(MatchCombined matchCombined) {
        return repository.save(matchCombined);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public List<MatchCombined> findAll() {
        return repository.findAll();
    }

    public MatchCombined findByExtId(Integer extId) {
        return repository.findByExtId(extId);
    }
}
