package it.unipv.airqualityretrospective.mapper;

import it.unipv.airqualityretrospective.domain.Country;
import it.unipv.airqualityretrospective.dto.CountryDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jsr330")
public interface CountryMapper {
    CountryDto toDto(Country entity);

}




