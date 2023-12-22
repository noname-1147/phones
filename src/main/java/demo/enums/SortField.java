package demo.enums;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@Serdeable
public enum SortField {
    BOOKED_AT("bookedAt"), BRAND("brand"), MODEL("model"), NETWORK_TECHNOLOGY("networkTechnology");
    private final String field;

}
