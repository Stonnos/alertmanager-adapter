package com.alertmanager.adapter.controller;

import com.alertmanager.adapter.dto.AlertRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.inject.Inject;

import static com.alertmanager.adapter.TestHelperUtils.readAlertRequest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Unit tests for {@link AlertController} class.
 *
 * @author Roman Batygin
 */
@WebMvcTest(controllers = AlertController.class)
class AlertControllerTest {

    private static final String BASE_URL = "/alerts";
    private static final String TELEGRAM_REQUEST_URL = BASE_URL + "/telegram";

    @Inject
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testSendAlertRequestToTelegram() throws Exception {
        AlertRequest alertRequest = readAlertRequest();
        mockMvc.perform(post(TELEGRAM_REQUEST_URL)
                .content(objectMapper.writeValueAsString(alertRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
