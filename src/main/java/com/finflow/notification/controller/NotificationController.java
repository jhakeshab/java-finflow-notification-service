package com.finflow.notification.controller;

import com.finflow.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notification")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @PostMapping("/send")
    public ResponseEntity<String> send(@RequestParam Long userId, @RequestParam String message, @RequestParam String type) {
        notificationService.sendManualNotification(userId, message, type);
        return ResponseEntity.ok("Notification sent");
    }

    @GetMapping("/preferences/{userId}")
    public ResponseEntity<String> getPreferences(@PathVariable Long userId) {
        // Return user prefs (fetched from Auth or DB if added)
        return ResponseEntity.ok("Email and SMS enabled");
    }
}