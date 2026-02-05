package org.quarkusllm;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.InjectMock;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@QuarkusTest
class SummarizerResourceIT {

    @InjectMock
    SummarizerService mockSummarizer;

    @Test
    void testSummarizeEndpoint() {
        // 1. Setup the mock behavior
        // Note: we now match 4 anyString() arguments to match your SummarizerService interface
        String expectedSummary = "This is a mock summary from Llama 3.";
        when(mockSummarizer.summarize(anyString(), anyString(), anyString(), anyString()))
                .thenReturn(expectedSummary);

        // 2. Call the REST endpoint using POST
        given()
                .contentType(ContentType.TEXT) // Inform the server we are sending plain text
                .queryParam("role", "Expert Editor")
                .queryParam("action", "summarize")
                .queryParam("language", "English")
                .body("This is the long input text that would normally come from a file.")
                .when()
                .post("/ai/action") // Changed from .get() to .post()
                .then()
                .statusCode(200)
                .body(is(expectedSummary));
    }
}