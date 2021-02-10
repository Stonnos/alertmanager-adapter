package com.alertmanager.adapter.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

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
    @Schema(description = "Alert name")
    private String alertName;

    /**
     * Instance name
     */
    @Schema(description = "Instance name")
    private String instance;

    /**
     * Job name
     */
    @Schema(description = "Job name")
    private String job;

    /**
     * Alert severity
     */
    @Schema(description = "Alert severity")
    private String severity;
}
