package it.unipv.airqualityretrospective.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import io.micronaut.data.jpa.repository.JpaSpecificationExecutor;
import io.micronaut.data.repository.PageableRepository;
import it.unipv.airqualityretrospective.domain.Country;
import it.unipv.airqualityretrospective.domain.StationOnPostcode;

@Repository
public interface StationOnPostcodeRepository extends JpaSpecificationExecutor<StationOnPostcode>, JpaRepository<StationOnPostcode, Long> {
}
