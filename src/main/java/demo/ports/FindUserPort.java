package demo.ports;

import demo.responses.UserItem;
import reactor.core.publisher.Flux;

import java.util.Optional;

public interface FindUserPort {
    Flux<UserItem> findUsers(Optional<String> username);
}
