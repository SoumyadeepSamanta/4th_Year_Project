package com.ProjectFourthYear.FlippedClassroom.Assignments;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.autoconfigure.ssl.SslProperties.Bundles.Watch.File;
import org.springframework.stereotype.Service;

import com.ProjectFourthYear.FlippedClassroom.subjects.Subject_ID;

import java.io.File;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentRepository AssignmentRepository; // Repository for database operations

    public void saveAssignmentPath(File file,String subid,String department,Integer max_marks,LocalDateTime last_date) {
        Assignment AssignmentEntity = new Assignment();
        AssignmentEntity.setAssignment_name(file.getName());
        AssignmentEntity.setAssignment_size((int) file.length());
        AssignmentEntity.setAssignment_path(file.getAbsolutePath());
        AssignmentEntity.setUpload_time(LocalDateTime.now());
        AssignmentEntity.setSubject_ID(new Subject_ID(subid,department));
        AssignmentEntity.setMax_marks(max_marks);
        AssignmentEntity.setLast_time(last_date);
        AssignmentRepository.save(AssignmentEntity);
    }
}
