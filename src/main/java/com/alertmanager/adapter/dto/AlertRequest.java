package com.alertmanager.adapter.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

/**
 * Request model from alert manager web hook.
 *
 * @author Roman Batygin
 */
@Data
@Schema(description = "Alert request model")
public class AlertRequest {

    /**
     * Receiver name
     */
    @Schema(description = "Receiver name", example = "eca-admin")
    private String receiver;

    /**
     * Alert status
     */
    @Schema(description = "Alert status", example = "firing")
    private String status;

    /**
     * Alerts list
     */
    @Schema(description = "Alerts list")
    @Valid
    @NotEmpty
    private List<Alert> alerts;

    /**
     * Group labels
     */
    @Schema(description = "Group labels")
    private Labels groupLabels;

    /**
     * Common labels
     */
    @Schema(description = "Common labels")
    private Labels commonLabels;

    /**
     * Common annotations
     */
    @Schema(description = "Common annotations")
    private Annotations commonAnnotations;

    /**
     * Alert manager external url
     */
    @JsonProperty("externalURL")
    @Schema(description = "Alert manager external url", example = "http://alert-manager")
    private String externalUrl;

    /**
     * Version tag
     */
    @Schema(description = "Version tag", example = "4")
    private String version;

    /**
     * Group key
     */
    @Schema(description = "Group key")
    private String groupKey;
}
