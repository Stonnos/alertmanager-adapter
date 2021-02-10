package com.alertmanager.adapter.service.telegram;

import com.alertmanager.adapter.dto.AlertRequest;
import com.alertmanager.adapter.service.template.TemplateProcessorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Map;

import static com.alertmanager.adapter.TestHelperUtils.readAlertRequest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link TelegramAlertService} class.
 *
 * @author Roman Batygin
 */
@ExtendWith(MockitoExtension.class)
class TelegramAlertServiceTest {

    private static final String EXPECTED_TEMPLATE_CODE = "telegram-alert-template.ftlh";
    private static final String EXPECTED_ALERT_REQUEST_VARIABLE = "alertRequest";
    private static final String EXPECTED_MESSAGE = "message";

    @Mock
    private TemplateProcessorService templateProcessorService;
    @Mock
    private TelegramSender telegramSender;
    @InjectMocks
    private TelegramAlertService telegramAlertService;

    @Captor
    private ArgumentCaptor<String> templateCodeCaptor;
    @Captor
    private ArgumentCaptor<Map<String, Object>> variablesMapCaptor;

    @Test
    void testSendAlert() throws TelegramApiException {
        AlertRequest alertRequest = readAlertRequest();
        when(templateProcessorService.process(anyString(), anyMap())).thenReturn(EXPECTED_MESSAGE);
        telegramAlertService.sendAlert(alertRequest);
        verify(templateProcessorService).process(templateCodeCaptor.capture(), variablesMapCaptor.capture());
        assertThat(templateCodeCaptor.getValue()).isEqualTo(EXPECTED_TEMPLATE_CODE);
        assertThat(variablesMapCaptor.getValue()).containsEntry(EXPECTED_ALERT_REQUEST_VARIABLE, alertRequest);
        verify(telegramSender).sendMessage(EXPECTED_MESSAGE);
    }

}
