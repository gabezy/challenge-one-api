package challenge.one.api.domain.topic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreateTopicDto(
        @NotBlank
        String title,
        @NotBlank
        String message,
        @NotBlank
        String author,
        @NotBlank
        String course
) {
}
