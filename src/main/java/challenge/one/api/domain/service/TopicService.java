package challenge.one.api.domain.service;

import challenge.one.api.domain.repository.TopicRepository;
import challenge.one.api.domain.topic.CreateTopicDto;
import challenge.one.api.domain.topic.DetailsTopicDto;
import challenge.one.api.domain.topic.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.time.Year;

@Service
public class TopicService {
    @Autowired
    private TopicRepository topicRepository;

    public Topic createTopic(CreateTopicDto data) {
        if (hasTopicWithTheSameTitleAndMessage(data.title(), data.message())) {
            throw new RuntimeException();
        }
        return new Topic(data);
    }

    public boolean hasTopicWithTheSameTitleAndMessage(String title, String message) {
        Topic topic = topicRepository.findTopicByTitleAndMessage(title, message);
        return topic != null;
    }

    public Page<DetailsTopicDto> searchTopicWithParams (Pageable pageable, String course, String yearStr) {
        if ((course == null || course.isEmpty()) && (yearStr == null || yearStr.isEmpty())) {
            return topicRepository.findAllByStatusEqualsPosted(pageable).map(DetailsTopicDto::new);
        }
        // TODO -> refactor function (conversion course to lower case duplicated)
        System.out.println(yearStr);
        if (yearStr != null) {
            int year = Integer.parseInt(yearStr);
            if (course != null) {
                course = course.toLowerCase();
                return topicRepository.findAllByCourseAndCreatedAtYearAndStatusEqualsPosted(pageable, course, year).map(DetailsTopicDto::new);
            }
            return topicRepository.findAllByYearAndStatusEqualsPosted(pageable, year).map(DetailsTopicDto::new);
        }
        course = course.toLowerCase();
        return topicRepository.findAllByCourseAndStatusEqualsPosted(pageable, course).map(DetailsTopicDto::new);
    }
}
