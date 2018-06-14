package com.pasha.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.pasha.entity.MatchCombined;

import java.util.List;

@Transactional(propagation = Propagation.MANDATORY)
public interface MatchCombinedRepository extends CrudRepository<MatchCombined, Long> {

    List<MatchCombined> findAll();
    MatchCombined findByExtId(Integer extId);
}
