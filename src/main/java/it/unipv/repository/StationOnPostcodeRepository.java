package it.unipv.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import io.micronaut.data.jpa.repository.JpaSpecificationExecutor;
import it.unipv.domain.StationOnPostcode;

@Repository
public interface StationOnPostcodeRepository extends JpaSpecificationExecutor<StationOnPostcode>, JpaRepository<StationOnPostcode, Long> {
}
