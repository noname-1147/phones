package demo.security;

import at.favre.lib.crypto.bcrypt.BCrypt;
import demo.domain.User;
import demo.repository.UsersRepository;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import jakarta.inject.Singleton;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

@Singleton
public class AuthenticationProviderUserPassword implements AuthenticationProvider<HttpRequest<?>> {
    private final UsersRepository usersRepository;
    private final BCrypt.Verifyer verifyer;

    public AuthenticationProviderUserPassword(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
        this.verifyer = BCrypt.verifyer();
    }

    @Override
    public Publisher<AuthenticationResponse> authenticate(@Nullable final HttpRequest<?> httpRequest, final AuthenticationRequest<?, ?> authenticationRequest) {
        return Mono.justOrEmpty(authenticationRequest.getIdentity()).zipWith(Mono.justOrEmpty(authenticationRequest.getSecret()))
                .flatMap(t -> usersRepository.findByUsername(t.getT1().toString()).zipWith(Mono.just(t.getT2())))
                .handle((t, sink) -> {
                            final User user = t.getT1();
                            final String password = t.getT2().toString();
                            final BCrypt.Result result = verifyer.verify(password.toCharArray(), user.getPassword().toCharArray());
                            if (result.verified) {
                                sink.next(AuthenticationResponse.success(user.getUsername()));
                                sink.complete();
                            } else {
                                sink.error(AuthenticationResponse.exception());
                            }
                        }
                );
    }
}
