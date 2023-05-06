package challenge.one.api.domain.topic;

public record UpdateTopicDto(
        String title,
        String message,
        String author,
        String course
) {
}
