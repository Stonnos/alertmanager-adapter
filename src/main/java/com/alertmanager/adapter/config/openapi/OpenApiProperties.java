package com.alertmanager.adapter.config.openapi;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

/**
 * Open api properties.
 *
 * @author Roman Batygin
 */
@Data
@Validated
@ConfigurationProperties(prefix = "open-api")
public class OpenApiProperties {

    private static final String DEFAULT_BASE_PATH = "/";

    /**
     * Project version
     */
    @NotEmpty
    private String projectVersion;

    /**
     * Api title
     */
    @NotEmpty
    private String title;

    /**
     * Api description
     */
    @NotEmpty
    private String description;

    /**
     * Api author full name
     */
    @NotEmpty
    private String author;

    /**
     * Api author email
     */
    @NotEmpty
    private String email;

    /**
     * Api base path
     */
    @NotEmpty
    private String basePath = DEFAULT_BASE_PATH;
}
