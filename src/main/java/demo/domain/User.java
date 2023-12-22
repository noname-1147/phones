package demo.domain;

import demo.responses.UserItem;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Serdeable
@MappedEntity("users")
@NoArgsConstructor
@Data
public class User {
    @Id private UUID id;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    public UserItem toItem() {
        return new UserItem(id, username);
    }
}
