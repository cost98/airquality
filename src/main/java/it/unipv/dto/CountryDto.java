package it.unipv.dto;

import io.micronaut.core.annotation.Introspected;
import it.unipv.domain.Country;
import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * A DTO for the {@link Country} entity
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Introspected
public class CountryDto {

    @NotNull
    private String name;

    @NotNull
    private String description;
}