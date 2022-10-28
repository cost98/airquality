package it.unipv.mapper;

import it.unipv.domain.MeasurementOnPostcodeOfStation;
import it.unipv.dto.MeasurementOnPostcodeOfStationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "jsr330")
public interface MeasurementOnPostcodeOfStationMapper {

    @Mapping(target = "postcode", source="postcode.postcode")
    @Mapping(target="param", source="param.name")
    @Mapping(target = "country", source="postcode.country.name")
    MeasurementOnPostcodeOfStationDto toDto(MeasurementOnPostcodeOfStation entity);

}




