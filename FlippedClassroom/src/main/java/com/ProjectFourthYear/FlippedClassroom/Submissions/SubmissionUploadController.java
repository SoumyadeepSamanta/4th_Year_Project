package com.ProjectFourthYear.FlippedClassroom.Submissions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

// import com.ProjectFourthYear.FlippedClassroom.Submissions.SubmissionService;

@RestController
public class SubmissionUploadController {
    
    private static final String UPLOAD_DIRECTORY = "upload_submissions";

    @Autowired
    private SubmissionService fileService; // Service to handle file path storage in the database

    @PostMapping("/upload_sub")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,@RequestParam("student_id") String student_id,@RequestParam("department") String department,@RequestParam("subid") String subid ,@RequestParam("assignment_id") Long assignment_id) throws IOException {
        // Create the upload directory if it does not exist
        File uploadDir = new File(UPLOAD_DIRECTORY);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // Define the path for the file to be saved
        Path targetPath = Paths.get(UPLOAD_DIRECTORY + File.separator + file.getOriginalFilename()+"_"+subid+"_"+department+'_'+file.getContentType().replace('/', '.'));

        // Save the file to the upload directory
        Files.copy(file.getInputStream(), targetPath);

        // Save the file path to the database
        fileService.saveSubmissionPath(targetPath.toFile(),student_id,assignment_id,subid,department);

        // Return a response indicating success
        return ResponseEntity.ok("File uploaded successfully: " + targetPath.toString());
    }
}

