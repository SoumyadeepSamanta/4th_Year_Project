package com.ProjectFourthYear.FlippedClassroom.teacher;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ProjectFourthYear.FlippedClassroom.Assignments.Assignment;
import com.ProjectFourthYear.FlippedClassroom.Assignments.AssignmentDTO;
import com.ProjectFourthYear.FlippedClassroom.Helper.ExcelHelperStudent;
import com.ProjectFourthYear.FlippedClassroom.Helper.ExcelHelperTeacher;
import com.ProjectFourthYear.FlippedClassroom.Materials.Material;
import com.ProjectFourthYear.FlippedClassroom.Materials.MaterialDTO;
import com.ProjectFourthYear.FlippedClassroom.Submissions.SubmissionDTO;
import com.ProjectFourthYear.FlippedClassroom.message.ResponseMessage;
// import com.ProjectFourthYear.FlippedClassroom.subjects.Subject_Student_DTO;
import com.ProjectFourthYear.FlippedClassroom.subjects.Subject_Teacher_DTO;
import org.springframework.web.bind.annotation.RequestParam;


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

    @GetMapping("/{department}/{subid}/assignments")
    public List<AssignmentDTO> getAssignments(@PathVariable String department,@PathVariable String subid) {
        return teacherService.getAssignments(department,subid);
    }

    @GetMapping("/{ass_id}/submissions")
    public List<SubmissionDTO> getsubmission(@PathVariable Long ass_id) {
        return teacherService.getSubmission(ass_id);
    }
    
    @GetMapping("/{teacher_id}/{subject_id}/{department}/students")
    public List<String> getStudents(@PathVariable String teacher_id,@PathVariable String subject_id,@PathVariable String department){
        return teacherService.getStudentsofaSubject(teacher_id, subject_id,department);
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (file == null || file.isEmpty()) {
            message = "No file uploaded!";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
        }

        if (!ExcelHelperTeacher.hasExcelFormat(file)) {
            message = "Please upload an Excel file!";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
        }

        try {
            teacherService.save(file);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!"+e;
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @PostMapping("/authenticate")
    public List<TeacherDTO> getPassword(@RequestParam String teacher_id){
        return teacherService.checkPassword(teacher_id);
    } 

    public String getMethodName(@RequestParam String param) {
        return new String();
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

    @PostMapping("/{sub_id}/assignments")
    public Assignment addAssignments(@PathVariable String sub_id, @RequestBody Assignment assignment) {
        Assignment newAssignments=teacherService.addAssignments(sub_id,assignment);
        return newAssignments;
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
    @PutMapping("/{assignment_id}/{submission_id}/marks")
    public int updateMarks_obtained(@PathVariable Long assignment_id,@PathVariable Long submission_id, @RequestParam int marks) {
       return teacherService.updateMarks_obtained(assignment_id, submission_id, marks);
        
        
    }

    @DeleteMapping("/{tid}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable String tid) {
        teacherService.deleteTeacher(tid);
        return ResponseEntity.noContent().build();
    }
}
