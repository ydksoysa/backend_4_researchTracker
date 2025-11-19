package com.example.reactauth.controller;

import com.example.reactauth.model.Message;
import com.example.reactauth.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/messages")
@CrossOrigin(origins = "*")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    // Get all messages between two users
    @GetMapping("/conversation/{user1}/{user2}")
    public ResponseEntity<List<Message>> getConversation(
            @PathVariable String user1,
            @PathVariable String user2
    ) {
        System.out.println("🔍 Fetching conversation between: " + user1 + " and " + user2);
        List<Message> messages = messageService.getConversation(user1, user2);
        System.out.println("✅ Found " + messages.size() + " messages");
        return ResponseEntity.ok(messages);
    }

    // Send a new message
    @PostMapping
    public ResponseEntity<Message> sendMessage(@RequestBody Map<String, String> request) {
        String sender = request.get("senderUsername");
        String receiver = request.get("receiverUsername");
        String content = request.get("content");

        System.out.println("📩 Attempting to send message:");
        System.out.println("   Sender: " + sender);
        System.out.println("   Receiver: " + receiver);
        System.out.println("   Content: " + content);

        if (sender == null || receiver == null || content == null) {
            System.out.println("❌ Missing required fields!");
            return ResponseEntity.badRequest().build();
        }

        try {
            Message savedMessage = messageService.sendMessage(sender, receiver, content);
            System.out.println("✅ Message saved with ID: " + savedMessage.getId());
            return ResponseEntity.ok(savedMessage);
        } catch (Exception e) {
            System.out.println("❌ Error saving message: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
