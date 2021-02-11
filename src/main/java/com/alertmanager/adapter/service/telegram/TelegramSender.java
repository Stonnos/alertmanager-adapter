package com.alertmanager.adapter.service.telegram;

import com.alertmanager.adapter.config.TelegramConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.meta.updateshandlers.SentCallback;

/**
 * Service for communications with telegram API.
 *
 * @author Roman Batygin
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TelegramSender {

    private final TelegramConfig telegramConfig;
    private final TelegramLongPollingBot telegramLongPollingBot;

    /**
     * Sends message to telegram chat.
     *
     * @param message - message string
     */
    public void sendMessage(String message) throws TelegramApiException {
        if (log.isDebugEnabled()) {
            log.debug("Starting to send message [{}] to telegram", message);
        }
        SendMessage sendMessage = SendMessage.builder()
                .chatId(telegramConfig.getChatId())
                .text(message)
                .build();
        sendMessage.enableHtml(true);
        telegramLongPollingBot.executeAsync(sendMessage, new SentCallback<>() {
            @Override
            public void onResult(BotApiMethod<Message> method, Message response) {
               if (log.isDebugEnabled()) {
                   log.debug("Message [{}] has been sent to telegram with response: {}", message, response);
               }
            }

            @Override
            public void onError(BotApiMethod<Message> method, TelegramApiRequestException apiException) {
                log.error("Telegram api request exception for message [{}]: {}", message, apiException.getApiResponse());
            }

            @Override
            public void onException(BotApiMethod<Message> method, Exception exception) {
                log.error("Error exception for message [{}]: {}", message, exception.getMessage());
            }
        });
    }
}
