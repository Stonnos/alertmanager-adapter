package com.alertmanager.adapter.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Alert annotation model.
 *
 * @author Roman Batygin
 */
@Data
@Schema(description = "Alert annotation model")
public class Annotations {

    /**
     * Description
     */
    @Schema(description = "Description", example = "Service app-service:8080 has been down for more than 2 minutes.")
    @NotBlank
    private String description;

    /**
     * Summary
     */
    @Schema(description = "Summary", example = "Service app-service:8080 is unavailable.")
    @NotBlank
    private String summary;
}
