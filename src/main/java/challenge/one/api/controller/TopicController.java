package challenge.one.api.controller;

import challenge.one.api.domain.service.TopicService;
import challenge.one.api.domain.topic.CreateTopicDto;
import challenge.one.api.domain.topic.DetailsTopicDto;
import challenge.one.api.domain.repository.TopicRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<DetailsTopicDto> create (@RequestBody @Valid CreateTopicDto data, UriComponentsBuilder uriBuilder) {
        var topic = service.createTopic(data);
        topicRepository.save(topic);
        var uri = uriBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetailsTopicDto(topic));
    }

    @GetMapping
    public ResponseEntity<Page<DetailsTopicDto>> list (
            @PageableDefault(size = 10, sort = {"createdAt"}, direction = Sort.Direction.ASC) Pageable pageable,
            @RequestParam(required = false) String course, @RequestParam(required = false) String year
            ) {
        Page<DetailsTopicDto> page = service.searchTopicWithParams(pageable, course, year);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailsTopicDto> detail (@PathVariable String id) {
        var topic = topicRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetailsTopicDto(topic));
    }

}
