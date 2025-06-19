package com.ProjectFourthYear.FlippedClassroom.subjects;

import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

// import com.ProjectFourthYear.FlippedClassroom.Helper.ExcelHelperStudent;
import com.ProjectFourthYear.FlippedClassroom.Helper.ExcelHelperSubject;
// import com.ProjectFourthYear.FlippedClassroom.student.Student;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    public void save(MultipartFile file) {
        try {
          List<Subject> subjects = ExcelHelperSubject.excelToTutorials(file.getInputStream());
          subjectRepository.saveAll(subjects);
        } catch (IOException e) {
          throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
}
