package org.quarkusllm;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@QuarkusTest
class TextProcessingServiceIT {

    @Inject
    TextProcessingService textProcessingService; // The actual interface used by your app

    @InjectMock
    TextProcessingService mockSummarizer; // Quarkus automatically swaps 'summarizerService' with this mock

    @Test
    void testDirectTextSummarization() {
        // 1. Define inputs and expected mock output
        String directText = "The quick brown fox jumps over the lazy dog.";
        String mockResponse = "A fast fox leaped.";
        String role = "Professional Summarizer";
        String action = "summarize";
        String language = "English";

        // 2. Setup the mock behavior
        // This ensures that when the app calls the service with these 4 params, it gets our mock string
        when(mockSummarizer.process(directText, role, action, language))
                .thenReturn(mockResponse);

        // 3. Execute the call on the INJECTED service
        String result = textProcessingService.process(directText, role, action, language);

        // 4. Verify the result
        assertEquals(mockResponse, result);
    }
}