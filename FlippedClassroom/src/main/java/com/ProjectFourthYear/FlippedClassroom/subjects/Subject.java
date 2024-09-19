package com.ProjectFourthYear.FlippedClassroom.subjects;

import java.util.List;

import com.ProjectFourthYear.FlippedClassroom.Materials.Material;

import java.util.ArrayList;

import jakarta.persistence.CascadeType;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.JoinColumn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Subject {

    @EmbeddedId
    private Subject_ID subject_id;

    private String name;
    private int semester;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumns({
        @JoinColumn(name = "subject_id", referencedColumnName = "subid"),
        @JoinColumn(name = "department", referencedColumnName = "department")
    })
    private List<Material> materials = new ArrayList<>();
}
