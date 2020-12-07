package com.codurance.allaboard.core.model.catalogue;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "catalogue")
public class LearningPath implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="lp_id")
    private Long id;

    @Column(nullable = false, name="lp_name")
    private String name;

    @Type(type="text")
    @Column(nullable = false, name="lp_description")
    private String description;

    public LearningPath(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public LearningPath() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
