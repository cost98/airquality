package it.unipv.airqualityretrospective.mapper;

import it.unipv.airqualityretrospective.domain.Country;
import it.unipv.airqualityretrospective.domain.MeasurementOnPostcodeOfStation;
import it.unipv.airqualityretrospective.dto.CountryDto;
import it.unipv.airqualityretrospective.dto.MeasurementOnPostcodeOfStationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "jsr330")
public interface MeasurementOnPostcodeOfStationMapper {

    @Mapping(target = "postcode", source="postcode.postcode")
    @Mapping(target="param", source="param.name")
    @Mapping(target = "country", source="postcode.country.name")
    MeasurementOnPostcodeOfStationDto toDto(MeasurementOnPostcodeOfStation entity);

}




