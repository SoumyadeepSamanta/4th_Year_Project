package com.ProjectFourthYear.FlippedClassroom.Assignments;

// import java.time.LocalDateTime;
// import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class AssignmentDTO {

     // Timestamp timestamp = getTimestamp("your_column_name");

// Convert java.sql.Timestamp to java.time.LocalDateTime
    // LocalDateTime localDateTime = timestamp.toLocalDateTime();
    // private String material_id;
    // private String Subject_id
    private Long assignment_id;
    private String assignment_name;
    // private int material_size;
    private String assignment_path;
    private String upload_time;
    private String last_time;
    private Integer max_marks;
    
    // private String subjectId;
    
}
