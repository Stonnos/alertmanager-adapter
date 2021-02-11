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
    @Schema(description = "Description")
    @NotBlank
    private String description;

    /**
     * Summary
     */
    @Schema(description = "Summary")
    @NotBlank
    private String summary;
}
