package com.zettamine.boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zettamine.boot.entity.InspectionActuals;

public interface InspectionActualsRepo extends JpaRepository<InspectionActuals, Integer> {
	
	@Query("SELECT COUNT(ia) FROM InspectionActuals ia WHERE ia.inspectionLot = :lotId AND ia.matInsp IN :ids")
	Integer getInspActualsByLotIdAndChannelId(@Param("lotId") Integer lotId, @Param("ids") List<Integer> ids);


}
