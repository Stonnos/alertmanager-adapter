package com.alertmanager.adapter;

import com.alertmanager.adapter.dto.AlertRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Cleanup;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit test for mapping alert request from json to java class model.
 *
 * @author Roman Batygin
 */
class AlertRequestTest {

    private static final String ALERT_JSON = "alert.json";

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testMapAlertRequest() throws IOException {
        @Cleanup InputStream inputStream = AlertRequestTest.class.getClassLoader().getResourceAsStream(ALERT_JSON);
        AlertRequest alertRequest = objectMapper.readValue(inputStream, AlertRequest.class);
        assertThat(alertRequest).isNotNull();
    }
}
