package it.unipv.airqualityretrospective.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import it.unipv.airqualityretrospective.domain.MeasurementOnPostcodeOfStation;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

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