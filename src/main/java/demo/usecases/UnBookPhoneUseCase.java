package demo.usecases;

import demo.domain.Phone;
import demo.domain.User;
import demo.ports.UnBookPhonePort;
import demo.repository.PhonesRepository;
import demo.repository.UsersRepository;
import demo.responses.PhoneItem;
import io.micronaut.transaction.annotation.Transactional;
import jakarta.inject.Singleton;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.security.Principal;
import java.util.UUID;

@Singleton
public class UnBookPhoneUseCase implements UnBookPhonePort {
    private final UsersRepository usersRepository;
    private final PhonesRepository phonesRepository;

    public UnBookPhoneUseCase(UsersRepository usersRepository, PhonesRepository phonesRepository) {
        this.usersRepository = usersRepository;
        this.phonesRepository = phonesRepository;
    }

    @Override
    @Transactional
    public Mono<PhoneItem> unBookPhone(final Principal principal, final UUID phoneId) {
        return Mono.just(principal).map(Principal::getName).flatMap(usersRepository::findByUsername)
                .zipWith(Mono.just(phoneId).flatMap(phonesRepository::findById))
                .<Phone>handle((tuple, sink) -> {
                    User user = tuple.getT1();
                    Phone phone = tuple.getT2();
                    if (phone.isReserved()) {
                        if (user.getId().equals(phone.getBookedBy())) {
                            phone.setBookedAt(null);
                            phone.setReserved(false);
                            sink.next(phone);
                            sink.complete();
                        } else {
                            sink.error(new IllegalArgumentException("Phone is booked by another user"));
                        }
                    } else {
                        sink.error(new IllegalArgumentException("Phone is already unbooked"));
                    }
                })
                .flatMap(phonesRepository::unBook)
                .map(ph -> ph.toItem(null))
                .switchIfEmpty(Mono.defer(() -> Mono.error(new IllegalArgumentException("Can't find a booked phone"))));
    }
}
