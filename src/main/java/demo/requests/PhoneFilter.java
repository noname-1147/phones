package demo.requests;

import demo.enums.SortField;
import demo.enums.SortOrder;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

import static demo.util.Util.normalizeWildcard;

@Serdeable
@Builder
public record PhoneFilter(Boolean reserved,
                          LocalDateTime bookedFromDate,
                          LocalDateTime bookedToDate,
                          UUID bookedBy,
                          String brand,
                          String model,
                          String networkTechnology,
                          String bands2g,
                          String bands3g,
                          String bands4g,
                          String bands5g,
                          int page,
                          int size,
                          SortField sortField,
                          SortOrder sortOrder
) {

    public PhoneFilter(final Boolean reserved,
                       final LocalDateTime bookedFromDate,
                       final LocalDateTime bookedToDate,
                       final UUID bookedBy,
                       final String brand,
                       final String model,
                       final String networkTechnology,
                       final String bands2g,
                       final String bands3g,
                       final String bands4g,
                       final String bands5g,
                       final int page,
                       final int size,
                       final SortField sortField,
                       final SortOrder sortOrder
    ) {
        this.reserved = reserved;
        this.bookedFromDate = bookedFromDate;
        this.bookedToDate = bookedToDate;
        this.bookedBy = bookedBy;
        this.brand = normalizeWildcard(brand);
        this.model = normalizeWildcard(model);
        this.networkTechnology = normalizeWildcard(networkTechnology);
        this.bands2g = normalizeWildcard(bands2g);
        this.bands3g = normalizeWildcard(bands3g);
        this.bands4g = normalizeWildcard(bands4g);
        this.bands5g = normalizeWildcard(bands5g);
        this.page = page;
        this.size = size;
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }

}
