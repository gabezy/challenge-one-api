package challenge.one.api.domain.topic;

import java.time.LocalDateTime;

public record DetailsTopicDto(
        String id, String title, String message, LocalDateTime createdAt,
        String Status, String author, String course
) {
    public DetailsTopicDto(Topic topic) {
        this(
                topic.getId(), topic.getTitle(), topic.getMessage(), topic.getCreatedAt(),
                topic.getStatus(), topic.getAuthor(), topic.getCourse()
        );
    }
}
