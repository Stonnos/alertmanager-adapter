package com.alertmanager.adapter.service.template.impl;

import com.alertmanager.adapter.exception.TemplateProcessingException;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link FreemarkerTemplateProcessorService} class.
 *
 * @author Roman Batygin
 */
@ExtendWith(MockitoExtension.class)
class FreemarkerTemplateProcessorServiceTest {

    private static final String TEST_TEMPLATE = "testTemplate";

    @Mock
    private Configuration configuration;
    @InjectMocks
    private FreemarkerTemplateProcessorService templateProcessorService;

    @Test
    void testThrowTemplateProcessingException() throws IOException {
        when(configuration.getTemplate(TEST_TEMPLATE)).thenThrow(new IOException());
        assertThrows(TemplateProcessingException.class, () -> templateProcessorService.process(TEST_TEMPLATE,
                Collections.emptyMap()));
    }

    @Test
    void testProcessTemplate() throws IOException {
        Template template = mock(Template.class);
        when(configuration.getTemplate(TEST_TEMPLATE)).thenReturn(template);
        String message = templateProcessorService.process(TEST_TEMPLATE, Collections.emptyMap());
        assertThat(message).isNotNull();
    }
}
