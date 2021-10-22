package com.alertmanager.adapter.controller.doc;

import lombok.experimental.UtilityClass;

/**
 * Api examples utility class.
 *
 * @author Roman Batygin
 */
@UtilityClass
public class ApiExamples {

    /**
     * Validation error response json
     */
    public static final String VALIDATION_ERROR_RESPONSE_JSON = "[{\"fieldName\": \"alerts[0].labels.alertName\", " +
            "\"code\": \"NotBlank\", \"errorMessage\": \"must not be blank\"}]";
}
