package com.markdown.mailable.services;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);
}
