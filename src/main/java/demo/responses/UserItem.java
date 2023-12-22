package demo.responses;

import io.micronaut.serde.annotation.Serdeable;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Serdeable
@Schema(description = "User data")
public record UserItem(@Schema(description = "User Id") UUID id, @Schema(description = "User name") String username) {
}
