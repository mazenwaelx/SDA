package com.cairouniv.fci.travel.agency.Notification_Module;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class NotificationTemplate {
    private String templateId;
    private String subject;
    private String content;
    private List<String> languages;
    private List<String> channels; // e.g., Email, SMS

    public NotificationTemplate(String templateId, String subject, String content, List<String> languages, List<String> channels) {
        this.templateId = templateId;
        this.subject = subject;
        this.content = content;
        this.languages = new ArrayList<>(languages);
        this.channels = new ArrayList<>(channels);
    }

    public String fillPlaceholders(Map<String, String> placeholders) {
        String filledContent = content;
        for (Map.Entry<String, String> entry : placeholders.entrySet()) {
            filledContent = filledContent.replace("{" + entry.getKey() + "}", entry.getValue());
        }
        return filledContent;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public List<String> getChannels() {
        return channels;
    }

    public void setChannels(List<String> channels) {
        this.channels = channels;
    }
}