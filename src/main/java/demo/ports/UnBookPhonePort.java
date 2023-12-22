package demo.ports;

import demo.responses.PhoneItem;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.UUID;

public interface UnBookPhonePort {
    Mono<PhoneItem> unBookPhone(Principal principal, UUID phoneId);
}
