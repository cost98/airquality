package it.unipv.mapper;

import it.unipv.domain.Country;
import it.unipv.dto.CountryDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jsr330")
public interface CountryMapper {
    CountryDto toDto(Country entity);

}




