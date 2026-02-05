package org.quarkusllm;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/ai")
public class SummarizerResource {

    @Inject
    SummarizerService summarizer;

    /**
     * Accepts text in the request body and processes it based on query params.
     * * Example Call:
     * POST /ai/action?role=CEO&action=summarize&language=French
     * Body: "Large amount of text here..."
     */
    @POST
    @Path("/action")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String doAction(
            String textContent,
            @QueryParam("role") @DefaultValue("Expert Assistant") String role,
            @QueryParam("action") @DefaultValue("summarize") String action,
            @QueryParam("language") @DefaultValue("English") String language) {

        // Validating input
        if (textContent == null || textContent.isBlank()) {
            return "Error: No text provided to process.";
        }

        try {
            // Passing all data directly to your AI service
            return summarizer.summarize(textContent, role, action, language);
        } catch (Exception e) {
            return "Error calling AI Service: " + e.getMessage();
        }
    }
}