package com.ProjectFourthYear.FlippedClassroom.Chatroom;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chatroom")
public class ChatroomController {

    @Autowired
    private ChatroomService chatroomservice;
    
    @GetMapping("{subject_id}/{department}")
    public List<ChatroomDTO> getChats(@PathVariable String subject_id,@PathVariable String department){
        return chatroomservice.getChats(subject_id,department);
    }

    @PostMapping("/{username}/{subject_id}/{department}")
    public void addChat(@PathVariable String username,@PathVariable String subject_id,@PathVariable String department,@RequestParam String chat){
        chatroomservice.addChat(username,subject_id,department,chat);
        
    }
}
