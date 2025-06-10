package com.ProjectFourthYear.FlippedClassroom.Chatroom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class ChatroomDTO {
    private String username;
    private String chat;
}
