package challenge.one.api.domain.service;

import challenge.one.api.domain.repository.TopicRepository;
import challenge.one.api.domain.topic.CreateTopicDto;
import challenge.one.api.domain.topic.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {
    @Autowired
    private TopicRepository topicRepository;

    public Topic createTopic(CreateTopicDto data) {
        Topic topic = topicRepository.findTopicByTitleAndMessage(data.title(), data.message());
        if (topic != null) {
            throw new RuntimeException();
        }
        return new Topic(data);
    }
}
