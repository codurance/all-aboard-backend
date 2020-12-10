package com.codurance.allaboard.core.model.topic;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "topics")
public class Topic implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "t_id")
  private long id;

  @Column(nullable = false, name = "t_name")
  private String name;

  @Column(nullable = false, name = "t_description")
  @Type(type = "text")
  private String description;

  public Topic(long id, String name, String description) {
    this.id = id;
    this.name = name;
    this.description = description;
  }

  public Topic() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
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
