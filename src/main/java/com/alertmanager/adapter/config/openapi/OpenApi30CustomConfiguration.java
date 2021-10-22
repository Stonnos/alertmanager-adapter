package com.alertmanager.adapter.config.openapi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.CollectionUtils;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * Open api custom configuration.
 *
 * @author Roman Batygin
 */
@Slf4j
@Configuration
public class OpenApi30CustomConfiguration {

    private static final String ALERT_EXAMPLE_JSON = "alert-example.json";

    private final PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

    /**
     * Creates open api customizer bean.
     *
     * @return open api customizer bean
     */
    @Bean
    public OpenApiCustomiser openApiCustomiser() {
        return this::addAlertRequestExample;
    }

    private void addAlertRequestExample(OpenAPI openAPI) {
        if (!CollectionUtils.isEmpty(openAPI.getPaths())) {
            String alertRequestJson = readAlertRequestExample();
            openAPI.getPaths().forEach((method, pathItem) -> {
                if (Optional.ofNullable(pathItem.getPost()).map(Operation::getRequestBody).isPresent() &&
                        !CollectionUtils.isEmpty(pathItem.getPost().getRequestBody().getContent())) {
                    var mediaType = pathItem.getPost().getRequestBody().getContent().values().iterator().next();
                    mediaType.setExample(alertRequestJson);
                }
            });
        }
    }

    private String readAlertRequestExample() {
        try {
            log.info("Starting to read example from file [{}]", ALERT_EXAMPLE_JSON);
            var resource = resolver.getResource(ALERT_EXAMPLE_JSON);
            @Cleanup var inputStream = resource.getInputStream();
            @Cleanup var reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            String exampleJson = FileCopyUtils.copyToString(reader);
            log.info("Example has been read from file [{}]", ALERT_EXAMPLE_JSON);
            return exampleJson;
        } catch (IOException ex) {
            log.error("There was an error while read example from file [{}]: {}", ALERT_EXAMPLE_JSON, ex.getMessage());
            return null;
        }
    }
}
