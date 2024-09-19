package com.ProjectFourthYear.FlippedClassroom.student;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import com.ProjectFourthYear.FlippedClassroom.subjects.Subject;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
// import jakarta.persistence.JoinColumns;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Student {
    
    @Id
    private String sid;
    private LocalDate birthdate;
    private String department;
    @Email(message = "Invalid email")
    private String email;
    private String name;
    private String password;
    private Long phone_number;
    private int semester;
    private int year;
    
    
    
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
        name = "student_subjects",
        joinColumns = @JoinColumn(name = "student_sid", referencedColumnName = "sid"),
        inverseJoinColumns = {
            @JoinColumn(name = "subjects_subid", referencedColumnName = "subid"),
            @JoinColumn(name = "department", referencedColumnName = "department")
        }
    )
    private List<Subject> subjects = new ArrayList<>();
}
