package com.codurance.allaboard.core.model.topic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "resources")
public class Resource {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "r_id")
  private long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "s_id")
  private Subtopic subtopic;

  @Column(name = "r_label")
  private String label;

  @Column(name = "r_url")
  private String url;

  public Resource(Subtopic subtopic, String label, String url) {
    this.subtopic = subtopic;
    this.label = label;
    this.url = url;
  }

  public Resource() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Subtopic getSubtopic() {
    return subtopic;
  }

  public void setSubtopic(Subtopic subtopic) {
    this.subtopic = subtopic;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
