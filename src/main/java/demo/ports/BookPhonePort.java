package demo.ports;

import demo.responses.PhoneItem;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.UUID;

public interface BookPhonePort {

    Mono<PhoneItem> bookPhone(Principal principal, UUID phoneId);
}
