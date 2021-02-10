package com.alertmanager.adapter.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

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
    private String description;

    /**
     * Summary
     */
    @Schema(description = "Summary")
    private String summary;
}
