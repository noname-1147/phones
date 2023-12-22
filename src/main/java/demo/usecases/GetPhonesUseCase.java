package demo.usecases;

import demo.domain.Phone;
import demo.domain.User;
import demo.ports.GetPhonesPort;
import demo.repository.PhonesRepository;
import demo.repository.UsersRepository;
import demo.requests.PhoneFilter;
import demo.responses.PhoneItem;
import io.micronaut.data.model.Page;
import io.micronaut.transaction.annotation.Transactional;
import jakarta.inject.Singleton;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Singleton
public class GetPhonesUseCase implements GetPhonesPort {
    private final PhonesRepository phonesRepository;
    private final UsersRepository usersRepository;

    public GetPhonesUseCase(PhonesRepository phonesRepository, UsersRepository usersRepository) {
        this.phonesRepository = phonesRepository;
        this.usersRepository = usersRepository;
    }

    @Override
    @Transactional
    public Mono<Page<PhoneItem>> getPhones(final PhoneFilter filter) {
        return Mono.just(filter)
                .flatMap(phonesRepository::findPhones)
                .flatMap(phones -> {
                            final Set<UUID> userIds = phones.getContent().stream()
                                    .map(Phone::getBookedBy)
                                    .filter(Objects::nonNull)
                                    .collect(Collectors.toSet());
                            if (userIds.isEmpty()) {
                                return Mono.just(phones).zipWith(Mono.just(Collections.<UUID, User>emptyMap()));
                            }
                            return Mono.just(phones).zipWith(usersRepository.findByIdIn(userIds).collectList().map(list -> list.stream().collect(Collectors.toMap(User::getId, Function.identity()))));
                        }
                ).map(tuple -> tuple.getT1().map(phone -> phone.toItem(tuple.getT2().get(phone.getBookedBy()))));
    }
}
