package com.ProjectFourthYear.FlippedClassroom.teacher;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProjectFourthYear.FlippedClassroom.Materials.Material;
import com.ProjectFourthYear.FlippedClassroom.Materials.MaterialDTO;
// import com.ProjectFourthYear.FlippedClassroom.subjects.Subject_Student_DTO;
import com.ProjectFourthYear.FlippedClassroom.subjects.Subject_Teacher_DTO;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public List<Teacher> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @GetMapping("/{tid}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable String tid) {
        Optional<Teacher> Teacher = teacherService.getTeacherById(tid);
        if (Teacher.isPresent()) {
            return ResponseEntity.ok(Teacher.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/{teacherId}/subjects")
    public List<Subject_Teacher_DTO> getSubjects(@PathVariable String teacherId) {
        return teacherService.getSubjectsByTeacherId(teacherId);
    }
    @GetMapping("/{department}/{subid}/materials")
    public List<MaterialDTO> getMaterials(@PathVariable String department,@PathVariable String subid) {
        return teacherService.getMaterials(department,subid);
    }


    @PostMapping
    public Teacher addTeacher(@RequestBody Teacher teacher) {
        return teacherService.addTeacher(teacher);
    }

    @PostMapping("/{sub_id}/materials")
    public Material addMaterials(@PathVariable String sub_id, @RequestBody Material material) {
        Material newMaterial=teacherService.addMaterials(sub_id,material);
        return newMaterial;
    }
    

    @PutMapping("/{tid}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable String tid, @RequestBody Teacher teacherDetails) {
        Teacher updatedTeacher = teacherService.updateTeacher(tid, teacherDetails);
        if (updatedTeacher != null) {
            return ResponseEntity.ok(updatedTeacher);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{tid}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable String tid) {
        teacherService.deleteTeacher(tid);
        return ResponseEntity.noContent().build();
    }
}
