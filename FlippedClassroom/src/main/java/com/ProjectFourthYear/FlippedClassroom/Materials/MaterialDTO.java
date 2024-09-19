package com.ProjectFourthYear.FlippedClassroom.Materials;

import java.time.LocalDateTime;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString


public class MaterialDTO {
    // Timestamp timestamp = getTimestamp("your_column_name");

// Convert java.sql.Timestamp to java.time.LocalDateTime
    // LocalDateTime localDateTime = timestamp.toLocalDateTime();
    // private String material_id;
    // private String Subject_id
    private String material_name;
    // private int material_size;
    private String material_path;
    private String upload_time;
    // private String subjectId;
}
