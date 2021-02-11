package com.alertmanager.adapter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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

    private static final int MAX_BATCH_SIZE = 5;
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
     * Batch size for messages
     */
    @NotNull
    @Min(1)
    @Max(MAX_BATCH_SIZE)
    private Integer batchSize;
}
