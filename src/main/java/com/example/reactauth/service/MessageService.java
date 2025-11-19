package com.example.reactauth.service;

import com.example.reactauth.model.Message;
import com.example.reactauth.repository.MessageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    // Save a new message
    @Transactional
    public Message sendMessage(String senderUsername, String receiverUsername, String content) {
        // Input validation
        if (senderUsername == null || senderUsername.trim().isEmpty()) {
            throw new IllegalArgumentException("Sender username cannot be null or empty");
        }
        if (receiverUsername == null || receiverUsername.trim().isEmpty()) {
            throw new IllegalArgumentException("Receiver username cannot be null or empty");
        }
        if (content == null || content.trim().isEmpty()) {
            throw new IllegalArgumentException("Message content cannot be null or empty");
        }

        System.out.println("📩 MessageService: Sending message");
        System.out.println("   From: " + senderUsername);
        System.out.println("   To: " + receiverUsername);
        System.out.println("   Content: " + content);

        Message msg = new Message();
        msg.setSenderUsername(senderUsername.trim());
        msg.setReceiverUsername(receiverUsername.trim());
        msg.setContent(content.trim());
        msg.setTimestamp(LocalDateTime.now());
        msg.setRead(false);

        try {
            Message savedMessage = messageRepository.save(msg);
            System.out.println("✅ Message saved successfully with ID: " + savedMessage.getId());
            return savedMessage;
        } catch (Exception e) {
            System.err.println("❌ Error saving message: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to save message: " + e.getMessage(), e);
        }
    }

    // Get conversation between two users
    @Transactional(readOnly = true)
    public List<Message> getConversation(String user1, String user2) {
        if (user1 == null || user1.trim().isEmpty() || user2 == null || user2.trim().isEmpty()) {
            throw new IllegalArgumentException("Both usernames must be provided");
        }

        System.out.println("📖 MessageService: Fetching conversation between: " + user1 + " and " + user2);

        try {
            List<Message> messages = messageRepository.getConversation(user1.trim(), user2.trim());
            System.out.println("✅ Found " + messages.size() + " messages");
            return messages;
        } catch (Exception e) {
            System.err.println("❌ Error fetching conversation: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to fetch conversation: " + e.getMessage(), e);
        }
    }
}
