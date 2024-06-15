package com.zettamine.boot.service;

import java.util.List;

import com.zettamine.boot.entity.InspectionActuals;
import com.zettamine.boot.models.InspActualModel;

public interface IInspActualService {
	
	InspectionActuals saveInspectionActuals(InspectionActuals inspAct);
	
	InspActualModel getInspActualModelFromInspActual(InspectionActuals inspAct);
	
	List<InspActualModel> getInspActualsModelFromInspActuals(List<InspectionActuals> inspActs);
	
	InspectionActuals editInspectionactual(InspActualModel inspActModel,Integer lotId);



}
