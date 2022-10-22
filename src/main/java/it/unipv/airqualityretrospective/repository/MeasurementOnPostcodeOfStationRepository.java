package it.unipv.airqualityretrospective.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import io.micronaut.data.jpa.repository.JpaSpecificationExecutor;
import io.micronaut.data.repository.PageableRepository;
import it.unipv.airqualityretrospective.domain.Country;
import it.unipv.airqualityretrospective.domain.MeasurementOnPostcodeOfStation;
import it.unipv.airqualityretrospective.domain.Param;


@Repository
public interface MeasurementOnPostcodeOfStationRepository extends JpaRepository<MeasurementOnPostcodeOfStation, Long>,JpaSpecificationExecutor<MeasurementOnPostcodeOfStation>, PageableRepository<MeasurementOnPostcodeOfStation, Long> {
}
