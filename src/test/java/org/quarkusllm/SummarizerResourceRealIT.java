package org.quarkusllm;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
class SummarizerResourceRealIT {

    @Test
    void testRealGroqCallWithFileContent() throws IOException {
        // 1. Load the file from src/test/resources/test_something.txt
        String fileName = "test_something.txt";
        String fileContent;

        try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName)) {
            assertNotNull(is, "Make sure " + fileName + " exists in src/test/resources!");
            fileContent = new String(is.readAllBytes(), StandardCharsets.UTF_8);
        }

        // 2. Perform the REAL request
        // RestAssured will call your running Quarkus app,
        // which will use the Groq settings from your test properties.
        String response = given()
                .contentType(ContentType.TEXT)
                .queryParam("role", "Expert Researcher")
                .queryParam("action", "summarize in 2 sentences")
                .queryParam("language", "English")
                .body(fileContent)
                .when()
                .post("/ai/action")
                .then()
                .statusCode(200)
                .body(notNullValue())
                .extract().asString();

        // 3. Print the real result so you can see what Groq said!
        System.out.println("--- REAL GROQ RESPONSE ---");
        System.out.println(response);
        System.out.println("--------------------------");
    }
}
