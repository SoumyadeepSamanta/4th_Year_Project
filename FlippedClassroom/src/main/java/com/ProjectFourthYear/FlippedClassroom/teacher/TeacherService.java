package com.ProjectFourthYear.FlippedClassroom.teacher;

import java.io.IOException;
// import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ProjectFourthYear.FlippedClassroom.Assignments.Assignment;
import com.ProjectFourthYear.FlippedClassroom.Assignments.AssignmentDTO;
import com.ProjectFourthYear.FlippedClassroom.Assignments.AssignmentRepository;
// import com.ProjectFourthYear.FlippedClassroom.Helper.ExcelHelperStudent;
import com.ProjectFourthYear.FlippedClassroom.Helper.ExcelHelperTeacher;
import com.ProjectFourthYear.FlippedClassroom.Materials.Material;
import com.ProjectFourthYear.FlippedClassroom.Materials.MaterialDTO;
import com.ProjectFourthYear.FlippedClassroom.Materials.MaterialRepository;
import com.ProjectFourthYear.FlippedClassroom.Submissions.SubmissionDTO;
import com.ProjectFourthYear.FlippedClassroom.Submissions.SubmissionRepository;
// import com.ProjectFourthYear.FlippedClassroom.student.Student;
// import com.ProjectFourthYear.FlippedClassroom.student.StudentDTO;
import com.ProjectFourthYear.FlippedClassroom.subjects.SubjectRepository;
// import com.ProjectFourthYear.FlippedClassroom.subjects.Subject_Student_DTO;
import com.ProjectFourthYear.FlippedClassroom.subjects.Subject_Teacher_DTO;

import jakarta.transaction.Transactional;
// import jakarta.validation.constraints.Null;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;
    
    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired 
    private SubjectRepository subjectRepository;

    @Autowired
    private SubmissionRepository submissionRepository;

    // @Autowired
    // priv

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Optional<Teacher> getTeacherById(String sid) {
        return teacherRepository.findById(sid);
    }

    public Teacher addTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public void save(MultipartFile file) {
        try {
          List<Teacher> teachers = ExcelHelperTeacher.excelToTeachers(file.getInputStream());
          teacherRepository.saveAll(teachers);
        } catch (IOException e) {
          throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    public Material addMaterials(String sub_id,Material material){
        return materialRepository.save(material);
    }

    public Assignment addAssignments(String sub_id,Assignment assignment){
        return assignmentRepository.save(assignment);
    }

    public List<Subject_Teacher_DTO> getSubjectsByTeacherId(String teacherId) {
        List<Object[]> results = subjectRepository.findSubjectsByTeacherId(teacherId);
        
        return results.stream()
                .map(row -> new Subject_Teacher_DTO(
                        (String) row[0],              // subject id
                        (String) row[1],                // subject name
                        (String) row[2],                //department
                        (int) row[3],                   // semester
                        (String) row[4]                 // teacher name
                ))
                .collect(Collectors.toList());
        // return subjectRepository.findSubjectsByStudentId(studentId);
    }

    public List<MaterialDTO> getMaterials(String department,String subid) {
        List<Object[]> results = materialRepository.findmaterials(subid,department);
        
        return results.stream()
                .map(row -> new MaterialDTO(
                        (Long)  row[0],
                        (String) row[1],              // subject id
                        (String) row[2],
                        (String) row[3]               // subject name
                    
                ))
                .collect(Collectors.toList());
        // return subjectRepository.findSubjectsByStudentId(studentId);
    }

    

    public List<AssignmentDTO> getAssignments(String department,String subid) {
        List<Object[]> results = assignmentRepository.findassignments(subid,department);
        
        return results.stream()
                .map(row -> new AssignmentDTO(
                    (Long) row[0],
                    (String) row[1],              // subject id
                    (String) row[2],
                    (String) row[3],
                    (String) row[4],
                    (Integer) row[5]             // subject name
                    
                ))
                .collect(Collectors.toList());
        // return subjectRepository.findSubjectsByStudentId(studentId);
    }
    public List<SubmissionDTO> getSubmission(Long ass_id){
        List<Object[]> results=submissionRepository.findsubmission(ass_id);
        return results.stream()
                .map(row->new SubmissionDTO(
                    (String) row[0],
                    (String) row[1],              // subject id
                    (String) row[2],
                    (String) row[3],
                    (Integer) row[4],
                    (Long) row[5]  
                ))
                .collect(Collectors.toList());
    }

    public List<String> getStudentsofaSubject(String teacher_id,String subject_id,String department){
        return teacherRepository.retreiveStudentofaSubject(teacher_id, subject_id,department);
        
    }
    
    public List<TeacherDTO> checkPassword(String teacher_id){
        List<Object[]> results=teacherRepository.checkPassword(teacher_id);

        return results.stream()
                .map(row -> new TeacherDTO(
                    
                    (String) row[0]
                ))
                .collect(Collectors.toList());
    }
    @Transactional
    public int updateMarks_obtained(Long assignment_id,Long submission_id,int marks){
        return submissionRepository.updateMarks(submission_id,marks);
    } 

    public Teacher updateTeacher(String tid, Teacher teacherDetails) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(tid);
        if (optionalTeacher.isPresent()) {
            Teacher existingTeacher = optionalTeacher.get();
            existingTeacher.setName(teacherDetails.getName());
            existingTeacher.setEmail(teacherDetails.getEmail());
            existingTeacher.setPhone_number(teacherDetails.getPhone_number());
            existingTeacher.setBirthdate(teacherDetails.getBirthdate());
            existingTeacher.setPassword(teacherDetails.getPassword());
    
            return teacherRepository.save(existingTeacher);
        } else {
            return null; // Handle this case in the controller
        }
        // return teacherDetails;
    }

    public void deleteTeacher(String sid) {
        teacherRepository.deleteById(sid);
    }

    

    
}
