package com.codurance.allaboard.core.unit;

import com.codurance.allaboard.core.actions.topic.FetchTopicById;
import com.codurance.allaboard.core.model.topic.Topic;
import com.codurance.allaboard.core.model.topic.Topics;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;

@Disabled
public class FetchTopicByIdShould {

    private static final long NONEXISTENT_TOPIC_ID = 9L;

    Topics topics;
    FetchTopicById fetchTopicById;

    @Test
    void not_find_not_existing_topic() {
        Optional<Topic> topic = fetchTopicById.execute(NONEXISTENT_TOPIC_ID);
        assertFalse(topic.isPresent());
    }
}
