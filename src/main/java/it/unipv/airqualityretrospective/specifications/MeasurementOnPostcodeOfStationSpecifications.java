package it.unipv.airqualityretrospective.specifications;

import io.micronaut.core.util.StringUtils;
import io.micronaut.data.jpa.repository.criteria.Specification;
import it.unipv.airqualityretrospective.domain.Country;
import it.unipv.airqualityretrospective.domain.MeasurementOnPostcodeOfStation;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class MeasurementOnPostcodeOfStationSpecifications {

    private MeasurementOnPostcodeOfStationSpecifications() {
    }

    public static Specification<MeasurementOnPostcodeOfStation> filterByKeyword(final String keyword) {
        return (Root<MeasurementOnPostcodeOfStation> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.hasText(keyword)) {
                predicates.add(
                        builder.or(
                                builder.like(builder.upper(root.get("postcode")), "%" + keyword.toUpperCase() + "%")
                        )
                );
            }
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
