package com.ProjectFourthYear.FlippedClassroom.subjects;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SubjectRepository extends JpaRepository<Subject, String>{

    @Query(value = "SELECT s.subid,s.name,s.department,t.name FROM student_subjects ss inner join subject s on ss.subjects_subid=s.subid and ss.department=s.department inner join student st on ss.student_sid=st.sid inner join teacher_subjects ts on ss.subjects_subid=ts.subjects_subid and ss.department=ts.department inner join teacher t on ts.teacher_tid=t.tid  WHERE student_sid = :studentId", nativeQuery = true)
    List<Object[]> findSubjectsAndTeacherByStudentId(@Param("studentId") String studentId);
    
    @Query(value = "SELECT s.subid,s.name,s.department,s.semester FROM teacher_subjects ts inner join subject s on ts.subjects_subid=s.subid and ts.department=s.department  WHERE teacher_tid = :teacherId", nativeQuery = true)
    List<Object[]> findSubjectsByTeacherId(@Param("teacherId") String teacherId);

}
