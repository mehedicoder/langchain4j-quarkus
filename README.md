# Quarkus AI Summarizer (LangChain4j + Groq)

This is a high-performance Java application built on the **Quarkus** framework, utilizing **LangChain4j** to integrate with the **Groq API**. It allows for dynamic text processing (summarization, translation, etc.) using state-of-the-art Llama 3 models.

## 🚀 Key Features

* **Quarkus Native**: Optimized for low memory footprint and fast startup.
* **Groq Inference**: Uses the `llama-3.3-70b-versatile` model for lightning-fast AI responses.
* **Declarative AI**: prompt engineering is handled via Java interfaces using `@RegisterAiService`.
* **Flexible REST API**: A single endpoint that handles diverse AI tasks via query parameters.

---

## 🛠 Tech Stack

| Component | Technology |
| :--- | :--- |
| **Runtime** | Quarkus 3.15.1 |
| **AI Framework** | LangChain4j (Quarkus Extension) |
| **LLM Provider** | Groq (OpenAI-compatible) |
| **Testing** | REST-Assured & JUnit 5 |

---

## ⚙️ Configuration

The application requires a Groq API Key. Set it in your environment to avoid hardcoding secrets:

```bash
# Set your API Key
export GROQ_API_KEY=your_gsk_api_key_here
