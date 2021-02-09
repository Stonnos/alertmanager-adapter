package com.alertmanager.adapter;

import com.alertmanager.adapter.dto.AlertRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test helper utility class.
 *
 * @author Roman Batygin
 */
@UtilityClass
public class TestHelperUtils {

    private static final String ALERT_JSON = "alert.json";

    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Reads alert request from file.
     *
     * @return alert request
     */
    @SneakyThrows
    public static AlertRequest readAlertRequest() {
        @Cleanup InputStream inputStream = TestHelperUtils.class.getClassLoader().getResourceAsStream(ALERT_JSON);
        AlertRequest alertRequest = objectMapper.readValue(inputStream, AlertRequest.class);
        assertThat(alertRequest).isNotNull();
        return alertRequest;
    }
}
