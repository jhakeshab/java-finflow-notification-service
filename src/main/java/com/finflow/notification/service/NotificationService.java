package com.finflow.notification.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final JavaMailSender mailSender;
    private final RestTemplate restTemplate;  // ← Will be injected
    private final ObjectMapper objectMapper;

    private final String AUTH_URL = "http://localhost:9001/api/auth";

    @KafkaListener(topics = "payment.completed")
    public void handlePaymentCompleted(String message) throws Exception {
        Map payment = objectMapper.readValue(message, Map.class);
        Long userId = ((Number) payment.get("fromUserId")).longValue();
        Map user = restTemplate.getForObject(AUTH_URL + "/user/" + userId, Map.class);
        String email = (String) user.get("email");

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);
        msg.setSubject("Payment Completed");
        msg.setText("Your payment of " + payment.get("amount") + " " + payment.get("currency") + " was successful.");
        mailSender.send(msg);
        // Similar for SMS via Twilio if added
    }

    @KafkaListener(topics = "user.kyc_updated")
    public void handleKycUpdated(String message) throws Exception {
        // Similar logic for KYC notifications
    }

    public void sendManualNotification(Long userId, String message, String type) {
        // Fetch user, send based on type (email/sms/push)
    }
}