package com.alertmanager.adapter.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Alert label model.
 *
 * @author Roman Batygin
 */
@Data
@Schema(description = "Alert label model")
public class Labels {

    /**
     * Alert name
     */
    @JsonProperty("alertname")
    @Schema(description = "Alert name", example = "ServiceDown")
    @NotBlank
    private String alertName;

    /**
     * Instance name
     */
    @Schema(description = "Instance name", example = "app-service:8080")
    @NotBlank
    private String instance;

    /**
     * Job name
     */
    @Schema(description = "Job name", example = "app-service")
    private String job;

    /**
     * Alert severity
     */
    @Schema(description = "Alert severity", example = "critical")
    private String severity;
}
