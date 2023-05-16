package challenge.one.api.domain.user;

import java.time.LocalDateTime;

public record DetailsUserDto(String id, String username, LocalDateTime createdAt) {
    public DetailsUserDto(User user) {
        this(user.getId(), user.getUsername(), user.getCreatedAt());
    }
}
