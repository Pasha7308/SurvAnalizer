package com.pasha.repository;

import com.pasha.entity.MatchPerson;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(propagation = Propagation.MANDATORY)
public interface MatchPersonRepository extends CrudRepository<MatchPerson, Long> {

    List<MatchPerson> findAll();


}
