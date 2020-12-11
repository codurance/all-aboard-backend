package com.codurance.allaboard.web.controllers.topic;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopicController {

    @GetMapping("topic/{id}")
    public ResponseEntity<String> fetchTopicsById() {
        return ResponseEntity.ok("");
    }
}
