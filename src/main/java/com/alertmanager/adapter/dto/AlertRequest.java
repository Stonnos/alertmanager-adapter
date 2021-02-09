package com.alertmanager.adapter.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Request model from alert manager web hook.
 *
 * @author Roman Batygin
 */
@Data
public class AlertRequest {

    private String receiver;

    private String status;

    private List<Alert> alerts;

    private Labels groupLabels;

    private Labels commonLabels;

    private Annotations commonAnnotations;

    @JsonProperty("externalURL")
    private String externalUrl;

    private String version;

    private String groupKey;
}
