package it.unipv.specifications;

import io.micronaut.core.util.StringUtils;
import io.micronaut.data.jpa.repository.criteria.Specification;
import it.unipv.domain.Country;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class CountrySpecifications {

    private CountrySpecifications() {
    }

    public static Specification<Country> filterByKeyword(final String keyword) {
        return (Root<Country> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.hasText(keyword)) {
                predicates.add(
                        builder.or(
                                builder.like(builder.upper(root.get("name")), "%" + keyword.toUpperCase() + "%")
                        )
                );
            }
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
