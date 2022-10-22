package it.unipv.airqualityretrospective.dto;

import io.micronaut.core.annotation.Introspected;
import it.unipv.airqualityretrospective.domain.Country;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

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