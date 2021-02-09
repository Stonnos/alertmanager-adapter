package com.alertmanager.adapter.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Alert {

    private String status;

    private Labels labels;

    private Annotations annotations;

    private String startsAt;

    private String endsAt;

    @JsonProperty("generatorURL")
    private String generatorUrl;

    @JsonProperty("fingerprint")
    private String fingerPrint;
}
