package demo.domain;

import demo.responses.PhoneItem;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.MappedProperty;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Serdeable
@MappedEntity("phones")
@Data
@NoArgsConstructor
public class Phone {
    @Id UUID id;
    @NotBlank String brand;
    @NotBlank String model;
    boolean reserved;
    LocalDateTime bookedAt;
    UUID bookedBy;
    @NotBlank String networkTechnology;
    @MappedProperty("bands_2g")
    String bands2g;
    @MappedProperty("bands_3g")
    String bands3g;
    @MappedProperty("bands_4g")
    String bands4g;
    @MappedProperty("bands_5g")
    String bands5g;
    LocalDateTime createdDate;

    public PhoneItem toItem(User user) {
        return PhoneItem.builder()
                .id(id)
                .brand(brand)
                .model(model)
                .reserved(reserved)
                .bookedAt(bookedAt)
                .bookedBy(user == null ? null : user.toItem())
                .networkTechnology(networkTechnology)
                .bands2g(bands2g)
                .bands3g(bands3g)
                .bands4g(bands4g)
                .bands5g(bands5g)
                .build();
    }
}
