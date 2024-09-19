package com.ProjectFourthYear.FlippedClassroom.teacher;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ProjectFourthYear.FlippedClassroom.subjects.Subject;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
// import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Teacher {
	
	@Id
	private String tid;
	private String name;
	private String email;
	private String department;
	private Long phone_number;
	private LocalDate birthdate;
	private String password;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(
		name = "teacher_subjects",
		
		joinColumns=@JoinColumn(name="teacher_tid"),
		
		inverseJoinColumns={
			@JoinColumn(name="subjects_subid",referencedColumnName = "subid"),
		 	@JoinColumn(name="department",referencedColumnName = "department")
		}

	)
	private List<Subject> subjects=new ArrayList<>();
	
	
		
	

}
