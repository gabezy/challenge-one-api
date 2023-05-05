package challenge.one.api.domain.repository;

import challenge.one.api.domain.topic.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, String> {
    Topic findTopicByTitleAndMessage(String title, String message);
}
