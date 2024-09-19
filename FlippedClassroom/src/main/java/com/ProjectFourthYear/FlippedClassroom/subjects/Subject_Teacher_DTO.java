package com.ProjectFourthYear.FlippedClassroom.subjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Subject_Teacher_DTO{

    private String subject_id;
    private String subject_name;

    private String department;
    private int semester;
}
