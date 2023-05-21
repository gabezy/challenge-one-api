package challenge.one.api.domain.user;

import jakarta.validation.constraints.NotBlank;

public record UpdateUserDto(
        @NotBlank
        String username
) {
}
