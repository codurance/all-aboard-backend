package com.codurance.allaboard.core.model.topic;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Resources extends CrudRepository<Resource, Long> {

}
