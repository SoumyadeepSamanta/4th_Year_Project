package com.ProjectFourthYear.FlippedClassroom.subjects;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SubjectRepository extends JpaRepository<Subject, String>{

    @Query(value = "SELECT s.subid,s.name,s.department,t.name,st.sid From  subject s, teacher_subjects tp, student st, teacher t where  s.semester=st.semester and s.department=st.department and  tp.subjects_subid=s.subid and tp.department=s.department and t.tid=tp.teacher_tid and st.sid = :studentId", nativeQuery = true)
    List<Object[]> findSubjectsAndTeacherByStudentId(@Param("studentId") String studentId);

    
    @Query(value = "SELECT s.subid,s.name,s.department,s.semester,t.name FROM teacher_subjects ts inner join subject s on ts.subjects_subid=s.subid and ts.department=s.department inner join teacher t on t.tid=ts.teacher_tid  WHERE teacher_tid = :teacherId", nativeQuery = true)
    List<Object[]> findSubjectsByTeacherId(@Param("teacherId") String teacherId);

}
