package com.ProjectFourthYear.FlippedClassroom.Assignments;

import java.time.LocalDateTime;

// import com.ProjectFourthYear.FlippedClassroom.subjects.Subject;
import com.ProjectFourthYear.FlippedClassroom.subjects.Subject_ID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long assignment_id;
    private String assignment_name;
    private int assignment_size;
    private String assignment_path;
    private LocalDateTime upload_time;
    private LocalDateTime last_time;
    private Subject_ID subject_ID;
    private int max_marks;
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
