package com.zettamine.boot.repository;



import java.util.List;
import java.util.TreeSet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.zettamine.boot.entity.MaterialInspection;

public interface MatInspRepository extends JpaRepository<MaterialInspection, Integer> {

	
	@Query("select m.channelId from MaterialInspection m where m.material =:id")
	TreeSet<Integer> findChannelIdsByMaterialId(String id);
	
	@Query(value ="select * from mat_isp_ch m where m.material_id =:id and m.status = 'A'",nativeQuery = true)
	List<MaterialInspection> findMaterialInspectionByMaterialId(String id);
}
