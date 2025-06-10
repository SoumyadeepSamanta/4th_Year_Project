package com.ProjectFourthYear.FlippedClassroom.Submissions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

// import jakarta.transaction.Transactional;
@Repository
public interface SubmissionRepository extends JpaRepository<Submissions,Long>{
    @Query(value="SELECT s.student_id,s.submission_name,s.submission_path,DATE_FORMAT(s.upload_time,'%Y-%m-%d  %H-%i-%s'),s.marks_obtained,s.submission_id  from  submissions s WHERE s.assignment_id=:ass_id ",nativeQuery = true)
    List<Object[]> findsubmission(@Param("ass_id") Long ass_id);
    
    @Modifying
    @Transactional
    @Query(value = "UPDATE submissions SET marks_obtained = :marks WHERE submission_id = :sub_id ", nativeQuery = true)
    int updateMarks(@Param("sub_id") Long submissionId, @Param("marks") int marks);
}
