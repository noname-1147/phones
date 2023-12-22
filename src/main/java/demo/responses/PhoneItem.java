package demo.responses;


import io.micronaut.serde.annotation.Serdeable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Serdeable
@Builder
@Schema(description = "Phone data")
public record PhoneItem(
        @Schema(description = "Phone id") UUID id,
        @Schema(description = "Phone brand") String brand,
        @Schema(description = "Phone model") String model,
        @Schema(description = "Is phone booked") boolean reserved,
        @Schema(description = "Timestamp for when phone was booked") LocalDateTime bookedAt,
        @Schema(description = "Information for the user who booked the phone") UserItem bookedBy,
        @Schema(description = "Information about network technology") String networkTechnology,
        @Schema(description = "Information about 2G bands")String bands2g,
        @Schema(description = "Information about 3G bands")String bands3g,
        @Schema(description = "Information about 4G bands")String bands4g,
        @Schema(description = "Information about 5G bands")String bands5g) {
}
