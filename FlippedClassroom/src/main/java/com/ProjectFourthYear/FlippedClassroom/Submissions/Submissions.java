package com.ProjectFourthYear.FlippedClassroom.Submissions;

import java.time.LocalDateTime;

import com.ProjectFourthYear.FlippedClassroom.subjects.Subject_ID;
// import com.ProjectFourthYear.FlippedClassroom.Assignments.Assignment;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Submissions {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long submission_id;
    
    private String student_id;
    private String submission_name;
    private int submission_size;
    private String submission_path;
    private LocalDateTime upload_time;
    private Subject_ID subject_ID;
    // private int max_marks;
    private int marks_obtained;

    // @ManyToOne(cascade = CascadeType.PERSIST)
    // @JoinColumn(name="assignment_id",referencedColumnName = "assignment_id")
    private Long assignment_id;
    // @ManyToOne(cascade = CascadeType.PERSIST)
    // @JoinColumns({
    //     @JoinColumn(name = "subject_id", referencedColumnName = "subid"),
    //     @JoinColumn(name = "department", referencedColumnName = "department")
    // })
    // private Subject Subject;
    
    // private String sub_id;

    // @ManyToOne
	// @JoinColumn(name="subid")
	// private Subject subject;

}
