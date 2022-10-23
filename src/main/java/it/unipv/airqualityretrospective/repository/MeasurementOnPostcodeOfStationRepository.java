package it.unipv.airqualityretrospective.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import io.micronaut.data.jpa.repository.JpaSpecificationExecutor;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.repository.PageableRepository;
import it.unipv.airqualityretrospective.domain.Country;
import it.unipv.airqualityretrospective.domain.MeasurementOnPostcodeOfStation;
import it.unipv.airqualityretrospective.domain.Param;
import it.unipv.airqualityretrospective.domain.PostcodeOfStation;
import it.unipv.airqualityretrospective.dto.MeasurementOnPostcodeOfStationDto;

import java.util.Date;
import java.util.List;


@Repository
public interface MeasurementOnPostcodeOfStationRepository extends JpaRepository<MeasurementOnPostcodeOfStation, Long>,JpaSpecificationExecutor<MeasurementOnPostcodeOfStation>, PageableRepository<MeasurementOnPostcodeOfStation, Long> {
    Page<MeasurementOnPostcodeOfStation> findByTimestampAfterAndTimestampBeforeAndPostcodeInList(Date date, Date date2, List<PostcodeOfStation> listPostcode, Pageable pageable);
}
