package com.zettamine.boot.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.zettamine.boot.entity.InspectionLot;
import com.zettamine.boot.entity.Material;

public interface InspectionLotRepository extends JpaRepository<InspectionLot, Integer>{

	@Query("from InspectionLot order by lotId")
	List<InspectionLot> findAllOrderByLotId();

	List<InspectionLot> findByMaterial(Material material);

	List<InspectionLot> findByStartDateIsNotNullAndRemarksIsNullOrderByLotId();
	
	List<InspectionLot> findByStartDateIsNullOrderByLotId();

	List<InspectionLot> findByRemarksIsNotNullOrderByLotId();
	
	 @Query("SELECT i FROM InspectionLot i WHERE i.createdOn BETWEEN :fromDate AND :toDate order by i.lotId")
	 List<InspectionLot> findInspLotsByCreatedDateBetween(Date fromDate, Date toDate);

	List<InspectionLot> findByEndDateIsNotNullAndRemarksIsNullOrderByLotId();
	
	

}
