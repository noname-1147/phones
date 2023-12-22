package demo.repository;


import demo.domain.User;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.r2dbc.annotation.R2dbcRepository;
import io.micronaut.data.repository.reactive.ReactorCrudRepository;
import io.micronaut.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;
import java.util.UUID;

@R2dbcRepository(dialect = Dialect.POSTGRES)
@Transactional
public interface UsersRepository extends ReactorCrudRepository<User, UUID> {
    Mono<User> findByUsername(String username);

    Flux<User> findByUsernameLike(String username);
    Flux<User> findByIdIn(Set<UUID> ids);
}
