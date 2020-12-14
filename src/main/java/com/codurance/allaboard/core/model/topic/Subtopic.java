package com.codurance.allaboard.core.model.topic;

import javax.persistence.*;

@Entity
@Table(name = "subtopics")
public class Subtopic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "s_id")
    private long id;

    @Column(nullable = false, name = "s_name")
    private String name;
}
