package com.ProjectFourthYear.FlippedClassroom.Materials;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.core.io.Resource;
// import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

// import com.ProjectFourthYear.FlippedClassroom.subjects.Subject_ID;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class MaterialUploadController {

    private static final String UPLOAD_DIRECTORY = "uploads";

    @Autowired
    private MaterialService fileService; // Service to handle file path storage in the database

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,@RequestParam("subid") String subid,@RequestParam("department") String department) throws IOException {
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
        fileService.saveMaterialPath(targetPath.toFile(),subid,department);

        // Return a response indicating success
        return ResponseEntity.ok("File uploaded successfully: " + targetPath.toString());
    }
}
