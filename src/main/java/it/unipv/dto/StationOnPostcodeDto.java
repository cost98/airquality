package it.unipv.dto;

import it.unipv.domain.MeasurementOnPostcodeOfStation;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A DTO for the {@link MeasurementOnPostcodeOfStation} entity
 */
@Data
public class StationOnPostcodeDto implements Serializable {
    @NotNull
    private final Long id;

    @NotNull
    private final String stationCode;

    @NotNull
    private final double latitude;

    @NotNull
    private final double longitude;

    @NotNull
    private final String postcode;

    @NotNull
    private final String country;
}