package com.codurance.allaboard.core.model.topic;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "subtopics")
public class Subtopic implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "s_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "t_id")
    private Topic topic;

    @Column(nullable = false, name = "s_name")
    private String name;

    @OneToMany(fetch=FetchType.LAZY, mappedBy = "subtopic", cascade = CascadeType.ALL)
    private List<Resource> resources;

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

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }
}
