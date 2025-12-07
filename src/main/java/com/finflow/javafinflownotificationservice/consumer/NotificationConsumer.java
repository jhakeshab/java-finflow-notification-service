package com.finflow.javafinflownotificationservice.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationConsumer {

    @KafkaListener(topics = "user.created")
    public void handleUserCreated(String event) {
        System.out.println("[Notification] Welcome email sent for event: " + event);
    }

    @KafkaListener(topics = "user.kyc_updated")
    public void handleKycUpdated(String event) {
        System.out.println("[Notification] KYC status alert sent for event: " + event);
    }

    @KafkaListener(topics = "payment.completed")
    public void handlePaymentCompleted(String event) {
        System.out.println("[Notification] Payment confirmation sent for event: " + event);
    }

    @KafkaListener(topics = "payment.failed")
    public void handlePaymentFailed(String event) {
        System.out.println("[Notification] Payment failure alert sent for event: " + event);
    }
}

