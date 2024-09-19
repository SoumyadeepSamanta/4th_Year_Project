package com.ProjectFourthYear.FlippedClassroom.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProjectFourthYear.FlippedClassroom.student.Student;
import com.ProjectFourthYear.FlippedClassroom.student.StudentRepository;
import com.ProjectFourthYear.FlippedClassroom.subjects.Subject;
import com.ProjectFourthYear.FlippedClassroom.subjects.SubjectRepository;
import com.ProjectFourthYear.FlippedClassroom.teacher.Teacher;
import com.ProjectFourthYear.FlippedClassroom.teacher.TeacherRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;
    
    @Autowired
    private SubjectRepository subjectRepository;
    
    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/teachers")
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        List<Teacher> teachers = teacherRepository.findAll();
        return ResponseEntity.ok(teachers);
    }
    
    @GetMapping("/subjects")
    public ResponseEntity<List<Subject>> getAllSubjects() {
        List<Subject> subjects = subjectRepository.findAll();
        return ResponseEntity.ok(subjects);
    }

    @PostMapping("/register/students")
    public ResponseEntity<Student> saveStudent(@Valid @RequestBody Student student) {
        Student savedStudent = studentRepository.save(student);
        return ResponseEntity.ok(savedStudent);
    }

    @PostMapping("/register/teachers")
    public ResponseEntity<Teacher> saveTeacher(@Valid @RequestBody Teacher teacher) {
        Teacher savedTeacher = teacherRepository.save(teacher);
        return ResponseEntity.ok(savedTeacher);
    }
    
    @PostMapping("/register/subjects")
    public ResponseEntity<Subject> saveSubject(@Valid @RequestBody Subject subject) {
        Subject savedSubject = subjectRepository.save(subject);
        return ResponseEntity.ok(savedSubject);
    }
    
    @PutMapping("/update/students/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable String id, @RequestBody Student studentDetails) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            student.setEmail(studentDetails.getEmail());
            student.setPhone_number(studentDetails.getPhone_number());
            Student updatedStudent = studentRepository.save(student);
            return ResponseEntity.ok(updatedStudent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/teachers/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable String id, @RequestBody Teacher teacherDetails) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(id);
        if (optionalTeacher.isPresent()) {
            Teacher teacher = optionalTeacher.get();
            teacher.setEmail(teacherDetails.getEmail());
            teacher.setPhone_number(teacherDetails.getPhone_number());
            Teacher updatedTeacher = teacherRepository.save(teacher);
            return ResponseEntity.ok(updatedTeacher);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable String id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            return ResponseEntity.ok(optionalStudent.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/teachers/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable String id) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(id);
        if (optionalTeacher.isPresent()) {
            return ResponseEntity.ok(optionalTeacher.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    
}
