package it.unipv.dto;

import it.unipv.domain.Param;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A DTO for the {@link Param} entity
 */
@Data
public class ParamDto implements Serializable {
    @NotNull
    private final String name;
    @NotNull
    private final String description;
}