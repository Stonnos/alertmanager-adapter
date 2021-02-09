package com.alertmanager.adapter.controller;

import com.alertmanager.adapter.dto.AlertRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller to receive requests from alert manager.
 *
 * @author Roman Batygin
 */
@Slf4j
@RestController
@RequestMapping("/alerts")
@RequiredArgsConstructor
public class AlertController {

    /**
     * Received request from alert manager for sending to telegram.
     *
     * @param alertRequest - alert request
     */
    @PostMapping(value = "/telegram")
    public void sendAlertToTelegram(@RequestBody AlertRequest alertRequest) {
        if (log.isDebugEnabled()) {
            log.debug("Received alert request for sending to telegram: {}", alertRequest);
        }
    }
}
