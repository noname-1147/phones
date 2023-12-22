package demo.controllers;

import demo.domain.Phone;
import demo.domain.User;
import demo.enums.SortField;
import demo.enums.SortOrder;
import demo.ports.BookPhonePort;
import demo.ports.GetPhonesPort;
import demo.ports.UnBookPhonePort;
import demo.repository.PhonesRepository;
import demo.repository.UsersRepository;
import demo.requests.PhoneFilter;
import demo.responses.PhoneItem;
import io.micronaut.context.annotation.Value;
import io.micronaut.data.model.Page;
import io.micronaut.http.annotation.*;
import io.micronaut.http.hateoas.JsonError;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/phones")
public class PhonesController {
    private static final int DEFAULT_PAGE_SIZE = 20;

    private final GetPhonesPort getPhonesPort;
    private final BookPhonePort bookPhonePort;
    private final UnBookPhonePort unBookPhonePort;

    public PhonesController(GetPhonesPort getPhonesPort, BookPhonePort bookPhonePort, UnBookPhonePort unBookPhonePort) {
        this.getPhonesPort = getPhonesPort;
        this.bookPhonePort = bookPhonePort;
        this.unBookPhonePort = unBookPhonePort;
    }


    @Operation(summary = "List filtered phone entries")
    @Get(uri = "/", produces = "application/json")
    public Publisher<Page<PhoneItem>> index(@QueryValue @Parameter(description = "Is phone booked") Optional<Boolean> reserved,
                                            @QueryValue @Parameter(description = "Booked minimum date") Optional<LocalDateTime> bookedFromDate,
                                            @QueryValue @Parameter(description = "Booked maximum date") Optional<LocalDateTime> bookedToDate,
                                            @QueryValue @Parameter(description = "Booked by user with id") Optional<UUID> bookedBy,
                                            @QueryValue @Parameter(description = "Brand wildcard") Optional<String> brand,
                                            @QueryValue @Parameter(description = "Model wildcard") Optional<String> model,
                                            @QueryValue @Parameter(description = "Network technology wildcard") Optional<String> networkTechnology,
                                            @QueryValue @Parameter(description = "2G bands wildcard") Optional<String> bands2g,
                                            @QueryValue @Parameter(description = "3G bands wildcard") Optional<String> bands3g,
                                            @QueryValue @Parameter(description = "4G bands wildcard") Optional<String> bands4g,
                                            @QueryValue @Parameter(description = "5G bands wildcard") Optional<String> bands5g,
                                            @QueryValue @Parameter(description = "Current page") Optional<Integer> page,
                                            @QueryValue @Parameter(description = "Page size") Optional<Integer> size,
                                            @QueryValue @Parameter(description = "Field to sort by") Optional<SortField> sortField,
                                            @QueryValue @Parameter(description = "Order to sort by") Optional<SortOrder> sortOrder) {
        PhoneFilter filter = PhoneFilter.builder()
                .reserved(reserved.orElse(null))
                .bookedFromDate(bookedFromDate.orElse(null))
                .bookedToDate(bookedToDate.orElse(null))
                .bookedBy(bookedBy.orElse(null))
                .brand(brand.orElse(null))
                .model(model.orElse(null))
                .networkTechnology(networkTechnology.orElse(null))
                .bands2g(bands2g.orElse(null))
                .bands3g(bands3g.orElse(null))
                .bands4g(bands4g.orElse(null))
                .bands5g(bands5g.orElse(null))
                .page(page.orElse(0))
                .size(size.orElse(DEFAULT_PAGE_SIZE))
                .sortField(sortField.orElse(null))
                .sortOrder(sortOrder.orElse(null))
                .build();
        return getPhonesPort.getPhones(filter);
    }

    @Operation(summary = "Book the phone", responses =
            {@ApiResponse(description = "Successful response with phone updated data", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PhoneItem.class))}),
                    @ApiResponse(description = "Unable to book the phone", responseCode = "400", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = JsonError.class))})})
    @Post(uri = "/{phoneId}/book", produces = "application/json")
    public Publisher<PhoneItem> bookPhone(Principal principal, @Parameter(description = "Phone Id") UUID phoneId) {
        return bookPhonePort.bookPhone(principal, phoneId);
    }

    @Operation(summary = "Return the phone", responses =
            {@ApiResponse(description = "Successful response with phone updated data", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PhoneItem.class))}),
                    @ApiResponse(description = "Unable to return the phone", responseCode = "400", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = JsonError.class))})})
    @Post(uri = "/{phoneId}/unBook", produces = "application/json")
    public Publisher<PhoneItem> unBookPhone(Principal principal, @Parameter(description = "Phone Id") UUID phoneId) {
        return unBookPhonePort.unBookPhone(principal, phoneId);
    }
}