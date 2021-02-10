package com.alertmanager.adapter.service.telegram;

import com.alertmanager.adapter.config.TelegramConfig;
import com.alertmanager.adapter.dto.AlertRequest;
import com.alertmanager.adapter.service.template.TemplateProcessorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.updateshandlers.SentCallback;

import java.util.Map;

import static com.alertmanager.adapter.TestHelperUtils.readAlertRequest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link TelegramSender} class.
 *
 * @author Roman Batygin
 */
@ExtendWith(MockitoExtension.class)
class TelegramSenderTest {

    private static final String TEST_CHAT = "testChat";
    private static final String TEST_MESSAGE = "message";
    private static final String HTML_PARSE_MODE = "html";

    @Mock
    private TelegramConfig telegramConfig;
    @Mock
    private TelegramLongPollingBot telegramLongPollingBot;
    @InjectMocks
    private TelegramSender telegramSender;

    @Captor
    private ArgumentCaptor<SendMessage> sendMessageArgumentCaptor;

    @Test
    void testSendMessage() throws TelegramApiException {
        when(telegramConfig.getChatId()).thenReturn(TEST_CHAT);
        telegramSender.sendMessage(TEST_MESSAGE);
        verify(telegramLongPollingBot).executeAsync(sendMessageArgumentCaptor.capture(), any(SentCallback.class));
        SendMessage sendMessage = sendMessageArgumentCaptor.getValue();
        assertThat(sendMessage).isNotNull();
        assertThat(sendMessage.getChatId()).isEqualTo(TEST_CHAT);
        assertThat(sendMessage.getText()).isEqualTo(TEST_MESSAGE);
        assertThat(sendMessage.getParseMode()).isEqualTo(HTML_PARSE_MODE);
    }

}
