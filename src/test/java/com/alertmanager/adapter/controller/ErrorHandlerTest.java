package com.alertmanager.adapter.controller;

import com.alertmanager.adapter.dto.ValidationErrorDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link ErrorHandler} class.
 *
 * @author Roman Batygin
 */
@ExtendWith(MockitoExtension.class)
class ErrorHandlerTest {

    private static final String LABELS_ALERT_NAME = "alerts[0].labels.alertname";
    private static final String ERROR_MESSAGE = "message";

    @Mock
    private MethodArgumentNotValidException methodArgumentNotValidException;
    @Mock
    private BindingResult bindingResult;

    private ErrorHandler errorHandler = new ErrorHandler();

    @Test
    void testHandleMethodArgumentNotValid() {
        mockMethodArgumentNotValid();
        var errorResponse = errorHandler.handleMethodArgumentNotValid(methodArgumentNotValidException);
        assertResponse(errorResponse, null, LABELS_ALERT_NAME, ERROR_MESSAGE);
    }

    private void mockMethodArgumentNotValid() {
        when(methodArgumentNotValidException.getBindingResult()).thenReturn(bindingResult);
        var fieldError = new FieldError(Object.class.getSimpleName(), LABELS_ALERT_NAME, ERROR_MESSAGE);
        when(bindingResult.getAllErrors()).thenReturn(Collections.singletonList(fieldError));
    }

    private void assertResponse(ResponseEntity<List<ValidationErrorDto>> errorResponse, String expectedCode,
                                String expectedField, String expectedErrorMessage) {
        assertThat(errorResponse).isNotNull();
        assertThat(errorResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(errorResponse.getBody()).hasSize(1);
        var validationErrorDto = errorResponse.getBody().iterator().next();
        assertThat(validationErrorDto.getCode()).isEqualTo(expectedCode);
        assertThat(validationErrorDto.getFieldName()).isEqualTo(expectedField);
        assertThat(validationErrorDto.getErrorMessage()).isEqualTo(expectedErrorMessage);
    }
}
