package com.codurance.allaboard.web.views;

import java.io.Serializable;
import java.util.List;

public class TopicWithSubtopicsView  extends TopicView implements Serializable {

    private List<SubtopicDetailView> subtopics;

    public TopicWithSubtopicsView(List<SubtopicDetailView> subtopics) {
        this.subtopics = subtopics;
    }

    public TopicWithSubtopicsView() {
    }

    public List<SubtopicDetailView> getSubtopics() {
        return subtopics;
    }
}
