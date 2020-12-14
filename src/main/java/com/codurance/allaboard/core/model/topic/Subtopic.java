package com.codurance.allaboard.core.model.topic;

import javax.persistence.*;

@Entity
@Table(name = "subtopics")
public class Subtopic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "s_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "t_id")
    private Topic topic;

    @Column(nullable = false, name = "s_name")
    private String name;

    public Subtopic(String name) {
        this.name = name;
    }

    public Subtopic() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
