package com.pasha.repository;

import com.pasha.entity.MatchCombined;
import com.pasha.entity.MatchMode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(propagation = Propagation.MANDATORY)
public interface MatchModeRepository extends CrudRepository<MatchMode, Long> {
    List<MatchMode> findAll();
    List<MatchMode> findAllByOrderByNameAsc();
    MatchMode findByName(String name);
}
