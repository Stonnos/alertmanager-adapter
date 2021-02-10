package com.alertmanager.adapter.service;

import com.alertmanager.adapter.dto.AlertRequest;

/**
 * Interface to sent alert in specified channel.
 *
 * @author Roman Batygin
 */
public interface AlertService {

    /**
     * Sends alert message.
     *
     * @param alertRequest - alert request
     * @throws Exception in case of error
     */
    void sendAlert(AlertRequest alertRequest) throws Exception;
}
