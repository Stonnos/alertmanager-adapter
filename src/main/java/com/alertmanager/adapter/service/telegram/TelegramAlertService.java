package com.alertmanager.adapter.service.telegram;

import com.alertmanager.adapter.dto.AlertRequest;
import com.alertmanager.adapter.service.AlertService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Implements service to sent alerts in telegram.
 *
 * @author Roman Batygin
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TelegramAlertService implements AlertService {

    private final TelegramSender telegramSender;

    @Override
    public void sendAlert(AlertRequest alertRequest) throws TelegramApiException {
        telegramSender.sendMessage("Hello!");
    }
}
