package com.alertmanager.adapter.config.openapi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import java.util.Optional;

/**
 * Open api configuration.
 *
 * @author Roman Batygin
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(OpenApiProperties.class)
@RequiredArgsConstructor
public class OpenApi30Configuration {

    private final OpenApiProperties openApiProperties;

    /**
     * Creates open api bean.
     *
     * @return open api bean
     */
    @Bean
    public OpenAPI customOpenAPI() {
        log.info("Starting to init Open API configuration");
        return new OpenAPI()
                .addServersItem(new Server().url(openApiProperties.getBasePath()))
                .info(apiInfo());
    }

    @Bean
    public OpenApiCustomiser openApiCustomiser() {
        return openApi -> {
        };
    }

    private void addAlertRequestExample(OpenAPI openAPI) {
        if (!CollectionUtils.isEmpty(openAPI.getPaths())) {
            openAPI.getPaths().forEach((method, pathItem) -> {
                if (pathItem.getPost() != null) {

                }
            });
        }
    }

    private Info apiInfo() {
        return new Info()
                .title(openApiProperties.getTitle())
                .version(openApiProperties.getProjectVersion())
                .description(openApiProperties.getDescription())
                .contact(new Contact()
                        .name(openApiProperties.getAuthor())
                        .email(openApiProperties.getEmail()));
    }
}
