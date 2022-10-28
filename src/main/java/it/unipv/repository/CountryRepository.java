package it.unipv.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import io.micronaut.data.jpa.repository.JpaSpecificationExecutor;
import io.micronaut.data.repository.PageableRepository;
import it.unipv.domain.Country;

@Repository
public interface CountryRepository extends JpaSpecificationExecutor<Country>, PageableRepository<Country, Integer>,JpaRepository<Country, Integer> {
    Country findByNameLike(String country);
}
