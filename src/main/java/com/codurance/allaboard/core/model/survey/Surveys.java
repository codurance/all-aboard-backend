package com.codurance.allaboard.core.model.survey;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Surveys extends CrudRepository<Survey, Long> {

}
