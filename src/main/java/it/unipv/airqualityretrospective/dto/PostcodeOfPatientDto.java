package it.unipv.airqualityretrospective.dto;

import it.unipv.airqualityretrospective.domain.PostcodeOfPatient;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A DTO for the {@link PostcodeOfPatient} entity
 */
@Data
public class PostcodeOfPatientDto implements Serializable {
    @NotNull
    private final String postcode;
    @NotNull
    private final CountryDto country;
    private final String hashCode;
    private final Double latitude;
    private final Double longitude;
    private final PostcodeOfStationDto matchingPostCode;
    private final Double distance;
}