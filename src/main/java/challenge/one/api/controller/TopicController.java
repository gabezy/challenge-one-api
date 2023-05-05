package challenge.one.api.controller;

import challenge.one.api.domain.service.TopicService;
import challenge.one.api.domain.topic.CreateTopicDto;
import challenge.one.api.domain.topic.DetailsTopic;
import challenge.one.api.domain.topic.Topic;
import challenge.one.api.domain.repository.TopicRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private TopicService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DetailsTopic> create (@RequestBody @Valid CreateTopicDto data, UriComponentsBuilder uriBuilder) {
        var topic = service.createTopic(data);
        topicRepository.save(topic);
        var uri = uriBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetailsTopic(topic));
    }

}
