package it.unipv.mapper;

import it.unipv.domain.StationOnPostcode;
import it.unipv.dto.StationOnPostcodeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "jsr330")
public interface StationOnPostcodeMapper {

    @Mapping(target = "postcode", source="postcode.postcode")
    @Mapping(target = "country", source="postcode.country.name")
    StationOnPostcodeDto toDto(StationOnPostcode entity);

}




