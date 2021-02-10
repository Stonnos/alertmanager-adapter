package com.alertmanager.adapter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Telegram config.
 *
 * @author Roman Batygin
 */
@Validated
@Data
@ConfigurationProperties("telegram")
public class TelegramConfig {

    /**
     * Telegram api base url
     */
    @NotBlank
    private String baseUrl;

    /**
     * Telegram bot token
     */
    @NotBlank
    private String token;

    /**
     * Telegram bot user name
     */
    @NotBlank
    private String botUserName;

    /**
     * Chat id
     */
    @NotBlank
    private String chatId;

    /**
     * Max. threads for async method execution
     */
    @NotNull
    private Integer maxThreads;
}
