package com.ProjectFourthYear.FlippedClassroom.Chatroom;

import java.time.LocalDateTime;

import jakarta.persistence.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity

public class Chatroom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long chat_id;
    private String username;
    private String department;
    private String Subject_ID;
    private String chat;
    private LocalDateTime time;
}
