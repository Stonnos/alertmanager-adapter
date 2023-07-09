package com.alertmanager.adapter.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Alert model.
 *
 * @author Roman Batygin
 */
@Data
@Schema(description = "Alert model")
public class Alert {

    /**
     * Alert status
     */
    @Schema(description = "Alert status", example = "firing")
    @NotBlank
    private String status;

    /**
     * Labels
     */
    @Schema(description = "Labels")
    @Valid
    @NotNull
    private Labels labels;

    /**
     * Annotations
     */
    @Schema(description = "Annotations")
    private Annotations annotations;

    /**
     * Alert starts at
     */
    @Schema(description = "Alert starts at")
    private String startsAt;

    /**
     * Alert ends at
     */
    @Schema(description = "Alert ends at")
    private String endsAt;

    /**
     * Alert url in alert manager
     */
    @JsonProperty("generatorURL")
    @Schema(description = "Alert url in alert manager")
    private String generatorUrl;

    /**
     * Alert fingerprint
     */
    @JsonProperty("fingerprint")
    @Schema(description = "Alert fingerprint")
    private String fingerPrint;
}
