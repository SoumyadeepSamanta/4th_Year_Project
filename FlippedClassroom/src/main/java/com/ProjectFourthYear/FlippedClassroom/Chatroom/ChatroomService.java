package com.ProjectFourthYear.FlippedClassroom.Chatroom;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatroomService {

    @Autowired
    private ChatroomRepository chatroomRepository;

    public List<ChatroomDTO> getChats(String subject_id,String department){
        List<Object[]> results = chatroomRepository.getChats(subject_id,department);
        
        return results.stream()
                .map(row-> new ChatroomDTO(
                    (String) row[0],
                    (String) row[1]
                ))
                .collect(Collectors.toList());
    }

    public void addChat(String username,String subject_id,String department,String chat){
        Chatroom ChatroomEntity=new Chatroom();
        ChatroomEntity.setChat(chat);
        ChatroomEntity.setDepartment(department);
        ChatroomEntity.setSubject_ID(subject_id);
        ChatroomEntity.setUsername(username);
        ChatroomEntity.setTime(LocalDateTime.now());
        chatroomRepository.save(ChatroomEntity);
    }
    
}
