package org.quarkusllm;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@QuarkusTest
class TextProcessingServiceLogicTest {

    @InjectMock
    TextProcessingService mockSummarizer;

    @Test
    void testLogic() {
        // Define your params clearly to avoid swap errors
        String text = "Input text";
        String role = "CEO";
        String action = "Summarize";
        String language = "German";
        String expected = "Zusammenfassung";

        // 1. Setup: Order must match SummarizerService (text, role, action, language)
        when(mockSummarizer.process(text, role, action, language))
                .thenReturn(expected);

        // 2. Execution: Order must be IDENTICAL to the setup above
        String result = mockSummarizer.process(text, role, action, language);

        // 3. Assertion: Using assertEquals is cleaner than the 'assert' keyword
        assertEquals(expected, result, "The mock should return the German translation");
    }
}