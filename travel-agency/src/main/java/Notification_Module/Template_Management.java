package Notification_Module;

import java.util.List;
import java.util.Map;

class NotificationTemplate {
    private String templateId;
    private String subject;
    private String content;
    private List<String> placeholders; // e.g., {x}, {y}
    private List<String> supportedLanguages;
    private List<String> languages;

    // Methods
    public void addTemplate(String subject, String content, List<String> placeholders, List<String> languages) {
        this.subject = subject;
        this.content = content;
        this.placeholders = placeholders;
        this.languages = languages;
    }

    // Method to get the content of the template based on language and placeholder values
    public String getContent(String language, Map<String, String> placeholderValues) {
        if (!languages.contains(language)) {
            throw new IllegalArgumentException("Language not supported.");
        }
        return language;
    }
}


