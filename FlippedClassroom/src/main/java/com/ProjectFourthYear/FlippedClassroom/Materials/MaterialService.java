package com.ProjectFourthYear.FlippedClassroom.Materials;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.autoconfigure.ssl.SslProperties.Bundles.Watch.File;
import org.springframework.stereotype.Service;

import com.ProjectFourthYear.FlippedClassroom.subjects.Subject_ID;

import java.io.File;

@Service
public class MaterialService {

    @Autowired
    private MaterialRepository MaterialRepository; // Repository for database operations

    public void saveMaterialPath(File file,String subid,String department) {
        Material MaterialEntity = new Material();
        MaterialEntity.setMaterial_name(file.getName());
        MaterialEntity.setMaterial_size((int) file.length());
        MaterialEntity.setMaterial_path(file.getAbsolutePath());
        MaterialEntity.setUpload_time(LocalDateTime.now());
        MaterialEntity.setSubject_ID(new Subject_ID(subid,department));
        MaterialRepository.save(MaterialEntity);
    }
}
