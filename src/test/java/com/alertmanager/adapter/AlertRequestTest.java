package com.alertmanager.adapter;

import com.alertmanager.adapter.dto.AlertRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Cleanup;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

class AlertRequestTest {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testMapAlertRequest() throws IOException {
        @Cleanup InputStream inputStream = AlertRequestTest.class.getClassLoader().getResourceAsStream("alert.json");
        AlertRequest alertRequest = objectMapper.readValue(inputStream, AlertRequest.class);
        assertThat(alertRequest).isNotNull();
    }
}
