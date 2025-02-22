package com.zettamine.boot.service;

import java.sql.Date;
import java.util.List;

import com.zettamine.boot.entity.InspectionActuals;
import com.zettamine.boot.entity.InspectionLot;
import com.zettamine.boot.entity.MaterialInspection;
import com.zettamine.boot.entity.User;
import com.zettamine.boot.models.InspLotModel;
import com.zettamine.boot.models.InspLotSearchCriteria;

public interface IInsptLotService {
	
	InspectionLot saveInspLotWithStatus(InspectionLot inspLot,String status);
	
	InspectionLot saveInspLot(InspectionLot inspLot);
	
	List<InspectionLot> getAllInspectionLots();
	
	InspectionLot getInspectionLotById(int id);

	List<InspLotModel> getAllInspectionModelData(List<InspectionLot> inspLots);
	
	Boolean getStatusOfInspectionActuals(int lotId,String matId);
	
	List<MaterialInspection> getMaterialInspectionList(List<MaterialInspection> matInsp,List<InspectionActuals> inspAct);
	
	Boolean generateInspLotResult(InspectionLot inspLot);
	
	InspectionLot saveInspLot(InspectionLot inspLot,Boolean result,User user);
	
	List<InspectionLot> getInspectionLotsWithStartDateNotNull();
	
	List<InspectionLot> getInspectionLotsWithStartDateNull();
	
	List<InspectionLot> getInspectionLotsWithRemarksNotNull();
	
	List<InspectionLot> getInspectionLotWithCreatedDate(InspLotSearchCriteria criteria);
	
	
	
}
