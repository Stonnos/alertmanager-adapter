package com.alertmanager.adapter.config.openapi;

import com.alertmanager.adapter.dto.AlertRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
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
    private final ObjectMapper objectMapper = new ObjectMapper();

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
            var alertRequest = readAlertRequestExample();
            openAPI.getPaths().forEach((method, pathItem) -> {
                if (Optional.ofNullable(pathItem.getPost()).map(Operation::getRequestBody).isPresent() &&
                        !CollectionUtils.isEmpty(pathItem.getPost().getRequestBody().getContent())) {
                    var mediaType = pathItem.getPost().getRequestBody().getContent().values().iterator().next();
                    mediaType.setExample(alertRequest);
                }
            });
        }
    }

    private AlertRequest readAlertRequestExample() {
        try {
            log.info("Starting to read example from file [{}]", ALERT_EXAMPLE_JSON);
            var resource = resolver.getResource(ALERT_EXAMPLE_JSON);
            @Cleanup var inputStream = resource.getInputStream();
            var alertRequest = objectMapper.readValue(inputStream, AlertRequest.class);
            log.info("Example has been read from file [{}]", ALERT_EXAMPLE_JSON);
            return alertRequest;
        } catch (IOException ex) {
            log.error("There was an error while read example from file [{}]: {}", ALERT_EXAMPLE_JSON, ex.getMessage());
            return null;
        }
    }
}
