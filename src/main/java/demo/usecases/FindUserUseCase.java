package demo.usecases;

import demo.util.Util;
import demo.domain.User;
import demo.ports.FindUserPort;
import demo.repository.UsersRepository;
import demo.responses.UserItem;
import io.micronaut.transaction.annotation.Transactional;
import jakarta.inject.Singleton;
import reactor.core.publisher.Flux;

import java.util.Optional;

@Singleton
public class FindUserUseCase implements FindUserPort {
    private final UsersRepository repository;

    public FindUserUseCase(UsersRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Flux<UserItem> findUsers(Optional<String> username) {
        return username.map(Util::normalizeWildcard)
                .map(repository::findByUsernameLike)
                .orElseGet(repository::findAll)
                .map(User::toItem);
    }
}
