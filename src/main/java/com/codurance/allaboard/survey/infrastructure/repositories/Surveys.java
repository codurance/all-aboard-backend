package com.codurance.allaboard.survey.infrastructure.repositories;

import com.codurance.allaboard.survey.model.Survey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Surveys extends CrudRepository<Survey, Long> {

}
