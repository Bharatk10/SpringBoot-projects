package com.zettamine.boot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.zettamine.boot.constants.ValidationConstants;
import com.zettamine.boot.entity.InspectionActuals;
import com.zettamine.boot.entity.InspectionLot;
import com.zettamine.boot.exception.NoResourcesFoundException;
import com.zettamine.boot.models.InspActualModel;
import com.zettamine.boot.repository.InspectionActualsRepo;
import com.zettamine.boot.repository.InspectionLotRepository;
import com.zettamine.boot.utility.StringUtilis;

@Service
public class InspActualServiceImpl implements IInspActualService {

	private InspectionActualsRepo inspActRepo;
	private InspectionLotRepository inspRepo;

	public InspActualServiceImpl(InspectionActualsRepo inspActRepo,InspectionLotRepository inspRepo) {
		super();
		this.inspActRepo = inspActRepo;
		this.inspRepo = inspRepo;
	}

	@Override
	public InspectionActuals saveInspectionActuals(InspectionActuals inspAct) {
		
		InspectionLot inspectionLot = inspAct.getInspectionLot();
		

		InspectionActuals inspActObj = null;
		try {
			inspActObj = inspActRepo.save(inspAct);
			inspActRepo.flush();
			
		}catch (Exception ex) {
			
			ex.printStackTrace();
		}

		return inspActObj;
	}

	@Override
	public InspActualModel getInspActualModelFromInspActual(InspectionActuals inspAct) {
		
		InspActualModel inspActModel = new InspActualModel();
		
		inspActModel.setLotId(inspAct.getInspectionLot().getLotId());
		inspActModel.setChannelDesc(inspAct.getMatInsp().getChannelDescription());
		inspActModel.setLowerTollerance(inspAct.getMatInsp().getLowerTollerance());
		inspActModel.setMinMeasurement(inspAct.getMinMesurment());
		inspActModel.setUpperTollerance(inspAct.getMatInsp().getUpperTollerance());
		inspActModel.setMaxMeasurement(inspAct.getMaxMesurment());
		inspActModel.setUms(inspAct.getMatInsp().getUms());
		
		return inspActModel;
	}

	@Override
	public List<InspActualModel> getInspActualsModelFromInspActuals(List<InspectionActuals> inspActs) {
		
		List<InspActualModel> inspActModels = new ArrayList<>();
		
		for (InspectionActuals inspAct : inspActs) {
			
			InspActualModel inspActModel = new InspActualModel();
			
			inspActModel.setLotId(inspAct.getInspectionLot().getLotId());
			inspActModel.setChannelDesc(inspAct.getMatInsp().getChannelDescription());
			inspActModel.setLowerTollerance(inspAct.getMatInsp().getLowerTollerance());
			inspActModel.setMinMeasurement(inspAct.getMinMesurment());
			inspActModel.setUpperTollerance(inspAct.getMatInsp().getUpperTollerance());
			inspActModel.setMaxMeasurement(inspAct.getMaxMesurment());
			inspActModel.setUms(inspAct.getMatInsp().getUms());
			
			inspActModels.add(inspActModel);
		}
		return inspActModels;
	}

	@Override
	public InspectionActuals editInspectionactual(InspActualModel inspActModel,Integer lotId) {
		
		  Optional<InspectionLot> OptinspLot = inspRepo.findById(lotId);
		  InspectionLot inspLot = null;
		  if(OptinspLot.isPresent()) {
			  inspLot = OptinspLot.get();
		  }
		  
		  String channelDesc = StringUtilis.processSentance(inspActModel.getChannelDesc());
		  
		  Optional<InspectionActuals> optInspActual = inspLot.getInspectionActuals().stream().filter(inspAct->inspAct.getMatInsp().getChannelDescription().equals(channelDesc))
		  .findFirst();
		  
		  if(optInspActual.isEmpty()) {
			  
			  String errorMessage = String.format(ValidationConstants.VIEW_ACTUALS_ERROR, lotId.toString());
			  
			  throw new NoResourcesFoundException(errorMessage);
		  }
		  
		  InspectionActuals inspAct = optInspActual.get();
		  
		  inspAct.setMaxMesurment(inspActModel.getMaxMeasurement());
		  inspAct.setMinMesurment(inspActModel.getMinMeasurement());
		  
		  InspectionActuals inspActObj = inspActRepo.save(inspAct);
		
	
		return inspActObj;
	}

}
