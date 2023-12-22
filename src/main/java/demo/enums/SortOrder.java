package demo.enums;

import io.micronaut.data.model.Sort;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;

@RequiredArgsConstructor
@Getter
@Serdeable
public enum SortOrder {
    ASC(Sort.Order::asc), DESC(Sort.Order::desc);
    private final Function<String, Sort.Order> orderFunction;
}
