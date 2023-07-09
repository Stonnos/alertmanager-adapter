package com.alertmanager.adapter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Alert manager adapter main class.
 *
 * @author Roman Batygin
 */
@SpringBootApplication
public class AlertManagerAdapterApplication {

    /**
     * Runs application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(AlertManagerAdapterApplication.class, args);
    }

}
