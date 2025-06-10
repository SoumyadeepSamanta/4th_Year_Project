package com.ProjectFourthYear.FlippedClassroom.Assignments;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

// import java.lang.String;
@Repository
public interface AssignmentRepository extends JpaRepository<Assignment,Long>{
    @Query(value="SELECT assignment_id,assignment_name,assignment_path,DATE_FORMAT(upload_time,'%Y-%m-%d  %H-%i-%s'),DATE_FORMAT(last_time,'%Y-%m-%d  %H-%i-%s'),max_marks  FROM assignment WHERE department= :department and subid= :subid",nativeQuery = true)
    List<Object[]> findassignments(@Param("department") String department , @Param("subid") String subid);
        
}
