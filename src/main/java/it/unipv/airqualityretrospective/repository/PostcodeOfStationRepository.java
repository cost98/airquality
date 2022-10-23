package it.unipv.airqualityretrospective.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import io.micronaut.data.jpa.repository.JpaSpecificationExecutor;
import it.unipv.airqualityretrospective.domain.Country;
import it.unipv.airqualityretrospective.domain.PostcodeOfStation;
import it.unipv.airqualityretrospective.domain.StationOnPostcode;

import java.util.List;


@Repository
public interface PostcodeOfStationRepository extends JpaSpecificationExecutor<PostcodeOfStation>, JpaRepository<PostcodeOfStation, Long> {

    List<PostcodeOfStation> findByCountryIdEqual(Integer id);
}