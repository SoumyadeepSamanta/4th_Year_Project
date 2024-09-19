package com.ProjectFourthYear.FlippedClassroom.Materials;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

// import java.lang.String;
public interface MaterialRepository extends JpaRepository<Material,Long>{
    @Query(value="SELECT material_name,material_path,DATE_FORMAT(upload_time,'%Y-%m-%d  %H-%i-%s') FROM material WHERE department= :department and subid= :subid",nativeQuery = true)
    List<Object[]> findmaterials(@Param("department") String department , @Param("subid") String subid);
    
}
