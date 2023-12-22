package demo.usecases;

import demo.domain.Phone;
import demo.domain.User;
import demo.ports.BookPhonePort;
import demo.repository.PhonesRepository;
import demo.repository.UsersRepository;
import demo.responses.PhoneItem;
import io.micronaut.transaction.annotation.Transactional;
import jakarta.inject.Singleton;
import jakarta.persistence.Tuple;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.UUID;

@Singleton
public class BookPhoneUseCase implements BookPhonePort {
    private final UsersRepository usersRepository;
    private final PhonesRepository phonesRepository;

    public BookPhoneUseCase(UsersRepository usersRepository, PhonesRepository phonesRepository) {
        this.usersRepository = usersRepository;
        this.phonesRepository = phonesRepository;
    }

    @Override
    @Transactional
    public Mono<PhoneItem> bookPhone(Principal principal, UUID phoneId) {
        return Mono.just(principal).map(Principal::getName).flatMap(usersRepository::findByUsername)
                .zipWith(Mono.just(phoneId).flatMap(phonesRepository::findById))
                .<Tuple2<User, Phone>>handle((tuple, sink) -> {
                    Phone phone = tuple.getT2();
                    if (phone.isReserved()) {
                        sink.error(new IllegalArgumentException("Phone is already booked"));
                    } else {
                        sink.next(tuple);
                        sink.complete();
                    }
                })
                .flatMap(tuple -> {
                    User user = tuple.getT1();
                    Phone phone = tuple.getT2();
                    phone.setBookedBy(user.getId());
                    phone.setReserved(true);
                    phone.setBookedAt(LocalDateTime.now());
                    return phonesRepository.book(phone).map(ph -> ph.toItem(user));
                })
                .switchIfEmpty(Mono.defer(() -> Mono.error(new IllegalArgumentException("Can't find an unbooked phone"))));
    }
}
