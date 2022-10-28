package it.unipv.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import it.unipv.domain.MeasurementOnPostcodeOfStation;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link MeasurementOnPostcodeOfStation} entity
 */
@Data
public class MeasurementOnPostcodeOfStationDto implements Serializable {
    @NotNull
    private final Long id;

    @NotNull
    private final Integer measurement;
    
    @NotNull
    private final String postcode;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private final Date timestamp;
    @NotNull
    private final String param;

    @NotNull
    private final String country;

    @Override
    public String toString() {
        return "MeasurementOnPostcodeOfStationDto{" +
                "id=" + id +
                ", measurement=" + measurement +
                ", postcode='" + postcode + '\'' +
                ", timestamp=" + timestamp +
                ", param='" + param + '\'' +
                ", country='" + country + '\'' +
                '}'+"\n";
    }
}