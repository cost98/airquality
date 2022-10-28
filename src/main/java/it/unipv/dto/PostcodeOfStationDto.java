package it.unipv.dto;

import it.unipv.domain.PostcodeOfStation;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A DTO for the {@link PostcodeOfStation} entity
 */
@Data
public class PostcodeOfStationDto implements Serializable {
    @NotNull
    private final String postcode;

    @NotNull
    private final double latitude;

    @NotNull
    private final double longitude;

    @NotNull
    private final String country;
}