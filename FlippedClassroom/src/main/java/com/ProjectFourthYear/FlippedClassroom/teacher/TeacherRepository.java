package com.ProjectFourthYear.FlippedClassroom.teacher;

import java.util.List;

// import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, String> {
	//check this
    @Query(value = "SELECT s.name from student s inner join student_subjects ss on s.sid=ss.student_sid inner join teacher_subjects ts on ss.subjects_subid=ts.subjects_subid and ss.department=ts.department where ts.teacher_tid=:teacher_id and ts.subjects_subid=:subject_id and ts.department=:department",nativeQuery = true)
    List<String> retreiveStudentofaSubject(@Param("teacher_id") String teacher_id,@Param("subject_id") String subject_id,@Param("department") String department);

    @Query(value="Select password from teacher where tid=:teacher_id",nativeQuery = true)
    List<Object[]> checkPassword(@Param("teacher_id") String teacher_id);

}
