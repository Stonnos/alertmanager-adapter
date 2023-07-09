package com.alertmanager.adapter.controller;

import com.alertmanager.adapter.dto.AlertRequest;
import com.alertmanager.adapter.dto.ValidationErrorDto;
import com.alertmanager.adapter.service.AlertService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.alertmanager.adapter.controller.doc.ApiExamples.VALIDATION_ERROR_RESPONSE_JSON;

/**
 * Rest controller to receive requests from alert manager.
 *
 * @author Roman Batygin
 */
@Slf4j
@Tag(name = "API for sending alerts from alert manager to specified channels")
@RestController
@RequestMapping("/alerts")
@RequiredArgsConstructor
public class AlertController {

    private final ObjectMapper objectMapper;
    private final AlertService alertService;

    /**
     * Received request from alert manager for sending to telegram.
     *
     * @param alertRequest - alert request
     * @throws Exception in case of error
     */
    @Operation(summary = "Send alert to telegram",
            description = "Send alert to telegram",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200"),
                    @ApiResponse(description = "Bad request", responseCode = "400",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = {
                                            @ExampleObject(value = VALIDATION_ERROR_RESPONSE_JSON),
                                    },
                                    array = @ArraySchema(schema = @Schema(implementation = ValidationErrorDto.class))
                            )
                    )
            })
    @PostMapping(value = "/telegram")
    public void sendAlertToTelegram(@Valid @RequestBody AlertRequest alertRequest) throws Exception {
        log.info("Received alert request for sending to telegram: {}", objectMapper.writeValueAsString(alertRequest));
        alertService.sendAlert(alertRequest);
    }
}
