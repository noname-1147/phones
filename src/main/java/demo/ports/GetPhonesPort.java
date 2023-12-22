package demo.ports;

import demo.requests.PhoneFilter;
import demo.responses.PhoneItem;
import io.micronaut.data.model.Page;
import reactor.core.publisher.Mono;

public interface GetPhonesPort {
    Mono<Page<PhoneItem>> getPhones(PhoneFilter filter);
}
