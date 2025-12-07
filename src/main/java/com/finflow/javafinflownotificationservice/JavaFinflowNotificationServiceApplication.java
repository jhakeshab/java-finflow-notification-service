package com.finflow.javafinflownotificationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class JavaFinflowNotificationServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(JavaFinflowNotificationServiceApplication.class, args);
    }
}

