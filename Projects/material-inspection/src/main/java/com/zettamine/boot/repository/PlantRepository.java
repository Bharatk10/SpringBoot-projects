package com.zettamine.boot.repository;

import java.util.List;
import java.util.Optional;
import java.util.TreeSet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.zettamine.boot.entity.Plant;

public interface PlantRepository extends JpaRepository<Plant, String> {
	
	List<Plant> findByStatus(Character ch);
	
	@Query("select  p.plantName from Plant p where p.status='A'")
	TreeSet<String> getAllPlantName();

	Optional<Plant> findByPlantName(String plantName);


}
