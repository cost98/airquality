package it.unipv.airqualityretrospective.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.core.convert.format.Format;
import it.unipv.airqualityretrospective.domain.MeasurementOnPostcodeOfStation;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
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
}