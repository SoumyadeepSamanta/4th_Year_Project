package com.ProjectFourthYear.FlippedClassroom.teacher;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ProjectFourthYear.FlippedClassroom.Materials.Material;
import com.ProjectFourthYear.FlippedClassroom.Materials.MaterialDTO;
import com.ProjectFourthYear.FlippedClassroom.Materials.MaterialRepository;
import com.ProjectFourthYear.FlippedClassroom.subjects.SubjectRepository;
// import com.ProjectFourthYear.FlippedClassroom.subjects.Subject_Student_DTO;
import com.ProjectFourthYear.FlippedClassroom.subjects.Subject_Teacher_DTO;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;
    
    @Autowired
    private MaterialRepository materialRepository;

    @Autowired 
    private SubjectRepository subjectRepository;

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

    public Material addMaterials(String sub_id,Material material){
        return materialRepository.save(material);
    }

    public List<Subject_Teacher_DTO> getSubjectsByTeacherId(String teacherId) {
        List<Object[]> results = subjectRepository.findSubjectsByTeacherId(teacherId);
        
        return results.stream()
                .map(row -> new Subject_Teacher_DTO(
                        ((String) row[0]),              // subject id
                        (String) row[1],                // subject name
                        (String) row[2],                //department
                        (int) row[3]                 // semester
                ))
                .collect(Collectors.toList());
        // return subjectRepository.findSubjectsByStudentId(studentId);
    }

    public List<MaterialDTO> getMaterials(String department,String subid) {
        List<Object[]> results = materialRepository.findmaterials(subid,department);
        
        return results.stream()
                .map(row -> new MaterialDTO(
                        (String) row[0],              // subject id
                        (String) row[1],
                        (String) row[2]               // subject name
                    
                ))
                .collect(Collectors.toList());
        // return subjectRepository.findSubjectsByStudentId(studentId);
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
