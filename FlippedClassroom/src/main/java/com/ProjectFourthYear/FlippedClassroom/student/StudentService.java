package com.ProjectFourthYear.FlippedClassroom.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ProjectFourthYear.FlippedClassroom.Materials.MaterialDTO;
import com.ProjectFourthYear.FlippedClassroom.Materials.MaterialRepository;
// import com.ProjectFourthYear.FlippedClassroom.subjects.Subject;
// import com.ProjectFourthYear.FlippedClassroom.subjects.SubjectDTO;
import com.ProjectFourthYear.FlippedClassroom.subjects.SubjectRepository;
import com.ProjectFourthYear.FlippedClassroom.subjects.Subject_Student_DTO;

import java.io.IOException;
// import java.sql.Timestamp;
// import java.time.LocalDateTime;
import java.util.ArrayList;
// import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
// import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
// import java.util.ExcelHelper;
import com.ProjectFourthYear.FlippedClassroom.Helper.ExcelHelper;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;
    
    @Autowired
    private MaterialRepository materialRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    public void save(MultipartFile file) {
        try {
          List<Student> students = ExcelHelper.excelToTutorials(file.getInputStream());
          studentRepository.saveAll(students);
        } catch (IOException e) {
          throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    public List<Subject_Student_DTO> getSubjectsAndTeacherByStudentId(String studentId) {
        List<Object[]> results = subjectRepository.findSubjectsAndTeacherByStudentId(studentId);
        List<Subject_Student_DTO> subjectDetailsList = new ArrayList<>();
    
        // Iterate over the results
        Iterator<Object[]> iterator = results.iterator();
        while (iterator.hasNext()) {
            Object[] row = iterator.next();
            
            // Create DTO and populate fields
            Subject_Student_DTO dto = new Subject_Student_DTO(
                (String) row[0], // subject ID
                (String) row[1], // subject name
                (String) row[2],  // teacher name
                (String) row[3]
            );
            
            // Add DTO to the list
            subjectDetailsList.add(dto);
        }
    
        return subjectDetailsList;
    }
    
    public List<MaterialDTO> getMaterials(String department,String subid) {
        List<Object[]> results = materialRepository.findmaterials(subid,department);
        
        return results.stream()
                .map(row -> new MaterialDTO(
                        (String) row[0],              // subject id
                        (String) row[1],
                        (String) row[2]              // subject name
                    
                ))
                .collect(Collectors.toList());
        // return subjectRepository.findSubjectsByStudentId(studentId);
    }

    public Optional<Student> getStudentById(String sid) {
        return studentRepository.findById(sid);
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(String sid, Student studentDetails) {
        Optional<Student> optionalStudent = studentRepository.findById(sid);
        if (optionalStudent.isPresent()) {
            Student existingStudent = optionalStudent.get();
            existingStudent.setName(studentDetails.getName());
            existingStudent.setDepartment(studentDetails.getDepartment());
            existingStudent.setYear(studentDetails.getYear());
            existingStudent.setSemester(studentDetails.getSemester());
            existingStudent.setEmail(studentDetails.getEmail());
            existingStudent.setPhone_number(studentDetails.getPhone_number());
            existingStudent.setBirthdate(studentDetails.getBirthdate());
            existingStudent.setPassword(studentDetails.getPassword());
            return studentRepository.save(existingStudent);
        } else {
            return null; // Handle this case in the controller
        }
    }

    public void deleteStudent(String sid) {
        studentRepository.deleteById(sid);
    }
}
