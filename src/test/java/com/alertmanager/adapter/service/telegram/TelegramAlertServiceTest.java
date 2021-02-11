package com.alertmanager.adapter.service.telegram;

import com.alertmanager.adapter.config.TelegramConfig;
import com.alertmanager.adapter.dto.AlertRequest;
import com.alertmanager.adapter.service.template.TemplateProcessorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static com.alertmanager.adapter.TestHelperUtils.readAlertRequest;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link TelegramAlertService} class.
 *
 * @author Roman Batygin
 */
@ExtendWith(MockitoExtension.class)
class TelegramAlertServiceTest {

    private static final String EXPECTED_MESSAGE = "message";
    private static final int BATCH_SIZE = 4;

    @Mock
    private TelegramConfig telegramConfig;
    @Mock
    private TemplateProcessorService templateProcessorService;
    @Mock
    private TelegramSender telegramSender;
    @InjectMocks
    private TelegramAlertService telegramAlertService;

    @Test
    void testSendAlert() throws TelegramApiException {
        AlertRequest alertRequest = readAlertRequest();
        when(telegramConfig.getBatchSize()).thenReturn(BATCH_SIZE);
        when(templateProcessorService.process(anyString(), anyMap())).thenReturn(EXPECTED_MESSAGE);
        telegramAlertService.sendAlert(alertRequest);
        verify(telegramSender, times(4)).sendMessage(EXPECTED_MESSAGE);
    }

}
