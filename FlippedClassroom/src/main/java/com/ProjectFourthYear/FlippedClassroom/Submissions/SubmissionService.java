package com.ProjectFourthYear.FlippedClassroom.Submissions;

import java.io.File;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// import com.ProjectFourthYear.FlippedClassroom.Submissions.SubmissionRepository;
import com.ProjectFourthYear.FlippedClassroom.subjects.Subject_ID;

// import jakarta.validation.constraints.Null;
@Service
public class SubmissionService {
    @Autowired
    private SubmissionRepository SubmissionRepository;

    public void saveSubmissionPath(File file,String student_id,Long assignment_id,String subid,String department){
        Submissions SubmissionEntity = new Submissions();
        SubmissionEntity.setStudent_id(student_id);
        SubmissionEntity.setSubmission_name(file.getName());
        SubmissionEntity.setSubmission_size((int) file.length());
        SubmissionEntity.setSubmission_path(file.getAbsolutePath());
        SubmissionEntity.setUpload_time(LocalDateTime.now());
        SubmissionEntity.setSubject_ID(new Subject_ID(subid,department));
        SubmissionEntity.setMarks_obtained(-1);
        SubmissionEntity.setAssignment_id(assignment_id);
        SubmissionRepository.save(SubmissionEntity);
    }
}
