package com.ProjectFourthYear.FlippedClassroom.Chatroom;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatroomRepository extends JpaRepository<Chatroom,Long> {
    
    @Query(value = "Select username,chat from Chatroom where department=:department and subject_id=:subject_id order by time",nativeQuery = true)
    List<Object[]> getChats(@Param("subject_id") String subject_id,@Param("department") String department);

}
