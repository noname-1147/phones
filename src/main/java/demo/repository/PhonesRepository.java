package demo.repository;

import demo.domain.Phone;
import demo.enums.SortOrder;
import demo.requests.PhoneFilter;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.model.Sort;
import io.micronaut.data.model.jpa.criteria.impl.LiteralExpression;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.r2dbc.annotation.R2dbcRepository;
import io.micronaut.data.repository.jpa.criteria.PredicateSpecification;
import io.micronaut.data.repository.jpa.reactive.ReactorJpaSpecificationExecutor;
import io.micronaut.data.repository.reactive.ReactorCrudRepository;
import io.micronaut.data.runtime.criteria.RuntimeCriteriaBuilder;
import io.micronaut.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@R2dbcRepository(dialect = Dialect.POSTGRES)
@Transactional
public interface PhonesRepository extends ReactorCrudRepository<Phone, UUID>, ReactorJpaSpecificationExecutor<Phone> {
    @Query("UPDATE phones SET booked_by = :bookedBy, booked_at = now(), reserved = true WHERE id = :id and not reserved " +
            "RETURNING id, brand, model, reserved, booked_at, booked_by, network_technology, bands_2g, bands_3g, bands_4g, bands_5g, " +
            "created_date")
    Mono<Phone> book(Phone phone);

    @Query("UPDATE phones SET booked_by = null, booked_at = null, reserved = false WHERE id = :id and reserved and booked_by = :bookedBy " +
            "RETURNING id, brand, model, reserved, booked_at, booked_by, network_technology, bands_2g, bands_3g, bands_4g, bands_5g, " +
            "created_date")
    Mono<Phone> unBook(Phone phone);


    <E extends Phone> Mono<E> save(@NonNull Phone entity);

    Mono<Phone> findById(@NonNull UUID id);

    default Mono<Page<Phone>> findPhones(PhoneFilter filter) {
        PredicateSpecification<Phone> predicate = PredicateSpecification.where(null);
        if (filter.reserved() != null) {
            predicate = predicate.and(isReserved(filter.reserved()));
        }
        if (filter.bookedBy() != null) {
            predicate = predicate.and(bookedBy(filter.bookedBy()));
        }
        if (filter.bookedFromDate() != null) {
            predicate = predicate.and(bookedFromDate(filter.bookedFromDate()));
        }
        if (filter.bookedToDate() != null) {
            predicate = predicate.and(bookedToDate(filter.bookedToDate()));
        }
        if (filter.brand() != null) {
            predicate = predicate.and(brand(filter.brand()));
        }
        if (filter.model() != null) {
            predicate = predicate.and(model(filter.model()));
        }
        if (filter.networkTechnology() != null) {
            predicate = predicate.and(networkTechnology(filter.networkTechnology()));
        }
        if (filter.bands2g() != null) {
            predicate = predicate.and(bands2g(filter.bands2g()));
        }
        if (filter.bands3g() != null) {
            predicate = predicate.and(bands3g(filter.bands3g()));
        }
        if (filter.bands4g() != null) {
            predicate = predicate.and(bands4g(filter.bands4g()));
        }
        if (filter.bands5g() != null) {
            predicate = predicate.and(bands5g(filter.bands5g()));
        }
        return this.findAll(predicate, pageable(filter));

    }

    static PredicateSpecification<Phone> isReserved(boolean reserved) {
        return (root, criteriaBuilder) -> criteriaBuilder.equal(root.get("reserved"), reserved);
    }

    static PredicateSpecification<Phone> bookedBy(UUID bookedBy) {
        return (root, criteriaBuilder) -> criteriaBuilder.equal(root.get("bookedBy"), bookedBy);
    }

    static PredicateSpecification<Phone> bookedFromDate(LocalDateTime bookedFromDate) {
        return (root, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("bookedAt"), bookedFromDate);
    }

    static PredicateSpecification<Phone> bookedToDate(LocalDateTime bookedToDate) {
        return (root, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("bookedAt"), bookedToDate);
    }

    static PredicateSpecification<Phone> brand(String brand) {
        return (root, criteriaBuilder) -> ((RuntimeCriteriaBuilder) criteriaBuilder).ilikeString(root.get("brand"), new LiteralExpression<>(brand));
    }

    static PredicateSpecification<Phone> model(String model) {
        return (root, criteriaBuilder) -> ((RuntimeCriteriaBuilder) criteriaBuilder).ilikeString(root.get("model"), new LiteralExpression<>(model));
    }

    static PredicateSpecification<Phone> networkTechnology(String networkTechnology) {
        return (root, criteriaBuilder) -> ((RuntimeCriteriaBuilder) criteriaBuilder).ilikeString(root.get("networkTechnology"), new LiteralExpression<>(networkTechnology));
    }

    static PredicateSpecification<Phone> bands2g(String bands2g) {
        return (root, criteriaBuilder) -> ((RuntimeCriteriaBuilder) criteriaBuilder).ilikeString(root.get("bands2g"), new LiteralExpression<>(bands2g));
    }

    static PredicateSpecification<Phone> bands3g(String bands3g) {
        return (root, criteriaBuilder) -> ((RuntimeCriteriaBuilder) criteriaBuilder).ilikeString(root.get("bands3g"), new LiteralExpression<>(bands3g));
    }

    static PredicateSpecification<Phone> bands4g(String bands4g) {
        return (root, criteriaBuilder) -> ((RuntimeCriteriaBuilder) criteriaBuilder).ilikeString(root.get("bands4g"), new LiteralExpression<>(bands4g));
    }

    static PredicateSpecification<Phone> bands5g(String bands5g) {
        return (root, criteriaBuilder) -> ((RuntimeCriteriaBuilder) criteriaBuilder).ilikeString(root.get("bands5g"), new LiteralExpression<>(bands5g));
    }

    static Pageable pageable(PhoneFilter filter) {
        if(filter.sortField() == null) {
            return Pageable.from(filter.page(), filter.size());
        }
        SortOrder sortOrder = SortOrder.DESC == filter.sortOrder() ? filter.sortOrder() : SortOrder.ASC;
        Sort sort = Sort.of(sortOrder.getOrderFunction().apply(filter.sortField().getField()));
        return Pageable.from(filter.page(), filter.size(), sort);
    }


}
