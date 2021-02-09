package com.alertmanager.adapter.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Labels {

    @JsonProperty("alertname")
    private String alertName;

    private String instance;

    private String job;

    private String severity;
}
