package com.alertmanager.adapter.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Telegram configuration class.
 *
 * @author Roman Batygin
 */
@Configuration
@EnableConfigurationProperties(TelegramConfig.class)
public class TelegramConfiguration {

    /**
     * Creates telegram bot client bean.
     *
     * @param telegramConfig - telegram config
     * @return telegram bot client bean
     */
    @Bean
    public TelegramLongPollingBot telegramLongPollingBot(TelegramConfig telegramConfig) {
        TelegramLongPollingBot telegramLongPollingBot = new TelegramLongPollingBot() {
            @Override
            public String getBotToken() {
                return telegramConfig.getToken();
            }

            @Override
            public void onUpdateReceived(Update update) {
                //Empty implementation
            }

            @Override
            public String getBotUsername() {
                return telegramConfig.getBotUserName();
            }
        };
        telegramLongPollingBot.getOptions().setBaseUrl(telegramConfig.getBaseUrl());
        telegramLongPollingBot.getOptions().setMaxThreads(telegramConfig.getMaxThreads());
        return telegramLongPollingBot;
    }
}
