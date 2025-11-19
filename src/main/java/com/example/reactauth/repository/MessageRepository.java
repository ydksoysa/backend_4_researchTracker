package com.example.reactauth.repository;

import com.example.reactauth.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("SELECT m FROM Message m WHERE " +
            "(m.senderUsername = :user1 AND m.receiverUsername = :user2) OR " +
            "(m.senderUsername = :user2 AND m.receiverUsername = :user1) " +
            "ORDER BY m.timestamp ASC")
    List<Message> getConversation(@Param("user1") String user1, @Param("user2") String user2);
}
