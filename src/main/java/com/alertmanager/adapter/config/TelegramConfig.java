package com.alertmanager.adapter.config;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;
/**
 * Telegram config.
 *
 * @author Roman Batygin
 */
@Validated
@Data
@ConfigurationProperties("telegram")
public class TelegramConfig {

    private static final int MAX_ALERT_BATCH_SIZE = 5;
    private static final int MAX_NUM_THREADS = 10;

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
     * Threads number for async message sending
     */
    @NotNull
    @Min(1)
    @Max(MAX_NUM_THREADS)
    private Integer numThreads;

    /**
     * Alerts batch size
     */
    @NotNull
    @Min(1)
    @Max(MAX_ALERT_BATCH_SIZE)
    private Integer alertBatchSize;
}
