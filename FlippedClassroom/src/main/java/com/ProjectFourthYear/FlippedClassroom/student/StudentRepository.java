package com.ProjectFourthYear.FlippedClassroom.student;

import java.util.List;

// import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
	@Query(value = "SELECT password from student where sid=:student_id",nativeQuery = true)
    List<Object[]> checkPassword(@Param("student_id") String student_id);
}
