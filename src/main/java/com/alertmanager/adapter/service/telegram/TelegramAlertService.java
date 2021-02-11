package com.alertmanager.adapter.service.telegram;

import com.alertmanager.adapter.dto.AlertRequest;
import com.alertmanager.adapter.service.AlertService;
import com.alertmanager.adapter.service.template.TemplateProcessorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Collections;
import java.util.Map;

/**
 * Implements service to sent alerts in telegram.
 *
 * @author Roman Batygin
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TelegramAlertService implements AlertService {

    private static final String ALERT_REQUEST_VARIABLE = "alertRequest";
    private static final String TELEGRAM_ALERT_TEMPLATE = "telegram-alert-template.ftlh";

    private final TemplateProcessorService templateProcessorService;
    private final TelegramSender telegramSender;

    @Override
    public void sendAlert(AlertRequest alertRequest) throws TelegramApiException {
        if (log.isDebugEnabled()) {
            log.debug("Starting to send alert {} to telegram", alertRequest);
        }
        Map<String, Object> templateData = Collections.singletonMap(ALERT_REQUEST_VARIABLE, alertRequest);
        String message = templateProcessorService.process(TELEGRAM_ALERT_TEMPLATE, templateData);
        telegramSender.sendMessage(message);
    }
}
