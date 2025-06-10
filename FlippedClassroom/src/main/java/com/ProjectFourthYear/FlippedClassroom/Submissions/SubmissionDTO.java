package com.ProjectFourthYear.FlippedClassroom.Submissions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString


public class SubmissionDTO {
    // private Integer 
    private String student_id;
    private String submission_name;
    private String submission_path;
    private String upload_time;
    private Integer marks_obtained;
    private Long submission_id;
}
