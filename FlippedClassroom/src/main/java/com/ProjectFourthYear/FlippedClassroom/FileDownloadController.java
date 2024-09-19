package com.ProjectFourthYear.FlippedClassroom;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class FileDownloadController {

    // Specify the directory where files should be saved before downloading
    private static final String DOWNLOAD_DIRECTORY = "downloads";

    @GetMapping("/download")
    public ResponseEntity<Resource> downloadFile(@RequestParam String filePath) throws IOException {
        // Create a file object from the provided path
        File sourceFile = new File(filePath);

        // Check if the file exists
        if (!sourceFile.exists()) {
            return ResponseEntity.notFound().build();
        }

        // Create the download directory if it does not exist
        File downloadDir = new File(DOWNLOAD_DIRECTORY);
        if (!downloadDir.exists()) {
            downloadDir.mkdirs();
        }

        // Define the path for the file to be saved
        Path targetPath = Paths.get(DOWNLOAD_DIRECTORY + sourceFile.getName());

        // Copy the file to the download directory
        Files.copy(sourceFile.toPath(), new FileOutputStream(targetPath.toFile()));

        // Prepare the file for download
        InputStreamResource resource = new InputStreamResource(new FileInputStream(targetPath.toFile()));

        // Set headers to indicate file type and attachment
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + sourceFile.getName());

        // Return the file as a response entity
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(targetPath.toFile().length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}