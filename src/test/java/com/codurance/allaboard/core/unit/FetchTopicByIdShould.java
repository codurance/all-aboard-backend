package com.codurance.allaboard.core.unit;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import com.codurance.allaboard.core.actions.topic.FetchTopicById;
import com.codurance.allaboard.core.model.topic.Topics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FetchTopicByIdShould {

    private static final long NONEXISTENT_TOPIC_ID = 9L;

    FetchTopicById fetchTopicById;

    @Mock
    private Topics topics;


    @BeforeEach
    void setUp() {
        fetchTopicById = new FetchTopicById(topics);
    }

    @Test
    void query_the_repository_for_a_topic_by_id() {
        fetchTopicById.execute(NONEXISTENT_TOPIC_ID);

        verify(topics, atLeastOnce()).findById(NONEXISTENT_TOPIC_ID);
    }
}
