package it.unipv.airqualityretrospective.mapper;

import it.unipv.airqualityretrospective.domain.MeasurementOnPostcodeOfStation;
import it.unipv.airqualityretrospective.domain.StationOnPostcode;
import it.unipv.airqualityretrospective.dto.MeasurementOnPostcodeOfStationDto;
import it.unipv.airqualityretrospective.dto.StationOnPostcodeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "jsr330")
public interface StationOnPostcodeMapper {

    @Mapping(target = "postcode", source="postcode.postcode")
    @Mapping(target = "country", source="postcode.country.name")
    StationOnPostcodeDto toDto(StationOnPostcode entity);

}




