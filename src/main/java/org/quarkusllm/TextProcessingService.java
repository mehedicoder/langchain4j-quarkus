package org.quarkusllm;

import io.quarkiverse.langchain4j.RegisterAiService;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import jakarta.enterprise.context.ApplicationScoped;

@RegisterAiService
@ApplicationScoped
public interface TextProcessingService {

    @SystemMessage("You are a helpful assistant acting as a {role}.")
    @UserMessage("""
        {action} in {language}:
        
        {text}
        """)
    String process(String text, String role, String action, String language);
}