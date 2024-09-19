package com.ProjectFourthYear.FlippedClassroom.Materials;

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
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long material_id;
    private String material_name;
    private int material_size;
    private String material_path;
    private LocalDateTime upload_time;
    private Subject_ID subject_ID;
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
