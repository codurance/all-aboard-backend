package com.codurance.allaboard.core.model.catalogue;

import com.codurance.allaboard.core.model.topic.Topic;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "catalogue")
public class LearningPath implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.TABLE)
  @Column(name = "lp_id")
  private Long id;

  @Column(nullable = false, name = "lp_name")
  private String name;

  @Type(type = "text")
  @Column(nullable = false, name = "lp_description")
  private String description;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(
      name = "catalogue_topic",
      joinColumns = @JoinColumn(name = "lp_id"),
      inverseJoinColumns = @JoinColumn(name = "t_id"))
  private List<Topic> topics;

  public LearningPath(long id, String name, String description, List<Topic> topics) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.topics = topics;
  }

  public LearningPath() {
  }

  public LearningPath(long id, String name, String description) {
    this.id = id;
    this.name = name;
    this.description = description;
  }

  public LearningPath(String name, String description) {
    this.name = name;
    this.description = description;
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

  public List<Topic> getTopics() {
    return topics;
  }

  public void setTopics(List<Topic> topics) {
    this.topics = topics;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
