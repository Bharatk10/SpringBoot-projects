package com.zettamine.boot.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.zettamine.boot.constants.AppConstants;
import com.zettamine.boot.constants.ValidationConstants;
import com.zettamine.boot.entity.InspectionActuals;
import com.zettamine.boot.entity.InspectionLot;
import com.zettamine.boot.entity.Material;
import com.zettamine.boot.entity.MaterialInspection;
import com.zettamine.boot.entity.Plant;
import com.zettamine.boot.entity.User;
import com.zettamine.boot.entity.Vendor;
import com.zettamine.boot.exception.DateViolationException;
import com.zettamine.boot.models.InspApproveModel;
import com.zettamine.boot.models.InspLotDisplay;
import com.zettamine.boot.models.InspLotModel;
import com.zettamine.boot.models.InspLotSearchCriteria;
import com.zettamine.boot.repository.InspectionActualsRepo;
import com.zettamine.boot.repository.InspectionLotRepository;
import com.zettamine.boot.repository.MaterialRepository;
import com.zettamine.boot.repository.PlantRepository;
import com.zettamine.boot.repository.VendorRepository;
import com.zettamine.boot.utility.StringUtilis;

import jakarta.persistence.EntityManager;

@Service
public class InspLotServiceImpl implements IInsptLotService {
	
	private InspectionLotRepository inspLotRepo;

	private MaterialRepository matRepo;

	private VendorRepository vendorRepo;

	private PlantRepository plantRepo;

	@Autowired
	private InspectionActualsRepo inspActRepo;

	public InspLotServiceImpl(InspectionLotRepository inspLotRepo, MaterialRepository matRepo,
			VendorRepository vendorRepo, PlantRepository plantRepo) {
		super();
		this.inspLotRepo = inspLotRepo;
		this.matRepo = matRepo;
		this.vendorRepo = vendorRepo;
		this.plantRepo = plantRepo;
	}

	@Override
	public InspectionLot saveInspLotWithStartDate(InspectionLot inspLot, String startDate) {

		InspectionLot inspLotObj = null;

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	    LocalDate localDate = LocalDate.parse(startDate, formatter);
	    Date sqlDate = Date.valueOf(localDate);

	    Date today = Date.valueOf(LocalDate.now());

	  
	    if (inspLot.getCreatedOn().before(sqlDate)) {
	    	
	    	if(!sqlDate.after(today)) {
	    		
	    		inspLot.setStartDate(sqlDate);
	 	        inspLotObj = inspLotRepo.save(inspLot);
	    	}
	    	else {
		    	throw new DateViolationException(ValidationConstants.START_DATE_TODAY_ERROR);
		    }
	       
	    }
	    else {
	    	throw new DateViolationException(ValidationConstants.START_DATE_BEFORE_ERROR+inspLot.getCreatedOn()+")");
	    }

		return inspLotObj;
	}

	@Override
	public List<InspectionLot> getAllInspectionLots() {

		List<InspectionLot> inspLots = inspLotRepo.findAllOrderByLotId();
		return inspLots;
	}

	@Override
	public InspectionLot getInspectionLotById(int id) {

		InspectionLot inspLot = null;
		inspLotRepo.flush();
		
		Optional<InspectionLot> optInspLot = inspLotRepo.findById(id);

		if (optInspLot.isPresent()) {
			
			inspLot = optInspLot.get();
		}
		return inspLot;
	}

	@Override
	public InspectionLot saveInspLot(InspectionLot inspLot) {
		InspectionLot inspLotObj = null;

		if (inspLot.getResult() != null) {
			inspLot.setResult(StringUtilis.processString(inspLot.getResult()));
		}
		if (inspLot.getRemarks() != null) {
			inspLot.setRemarks(StringUtilis.processSentance(inspLot.getRemarks()));
		}
		inspLotObj = inspLotRepo.save(inspLot);
		return inspLotObj;
	}

	@Override
	public Boolean getStatusOfInspectionActuals(int lotId, String matId) {

		Optional<InspectionLot> inspLot = inspLotRepo.findById(lotId);

		long count;

		List<Integer> ids;

		if (inspLot.isPresent()) {

			ids = inspLot.get().getMaterial().getMatInsp().stream().map(obj -> obj.getChannelId())
					.collect(Collectors.toList());

			count = inspLot.get().getMaterial().getMatInsp().stream().count();

			Integer actCount = inspActRepo.getInspActualsByLotIdAndChannelId(lotId, ids);

			if (actCount == count) {
				return true;
			}
		}

		return false;

	}

	@Override
	public List<MaterialInspection> getMaterialInspectionList(List<MaterialInspection> matInspList,
			List<InspectionActuals> inspActList) {

		List<MaterialInspection> result = new ArrayList<>();

		if (inspActList.isEmpty()) {

			return matInspList;
		}

		for (MaterialInspection materialInspection : matInspList) {
			boolean found = false;
			for (InspectionActuals inspectionActual : inspActList) {
				if (materialInspection.getChannelId().equals(inspectionActual.getMatInsp().getChannelId())) {
					found = true;
					break;
				}
			}
			if (!found) {
				result.add(materialInspection);
			}
		}

		return result;
	}

	@Override
	public Boolean generateInspLotResult(InspectionLot inspLot) {

		List<InspectionActuals> inspActuals = inspLot.getInspectionActuals();

		for (InspectionActuals inspActual : inspActuals) {

			MaterialInspection matInsp = inspActual.getMatInsp();

			Float actualMax = inspActual.getMaxMesurment();
			Float actualMin = inspActual.getMinMesurment();

			Float charMax = matInsp.getUpperTollerance();
			Float charMin = matInsp.getLowerTollerance();

			if (actualMax > charMax || actualMax < charMin) {
				return false;
			}
			if (actualMin < charMin || actualMin > charMax) {
				return false;
			}
		}
		return true;

	}

	@Override
	public InspectionLot saveInspLot(InspectionLot inspLot, Boolean result, User user) {

		InspectionLot inspectionLot = null;

		LocalDate date = LocalDate.now();
		Date sqlDate = Date.valueOf(date);
		inspLot.setEndDate(sqlDate);

		if (result) {
			inspLot.setResult("PASS");
			inspLot.setRemarks("ALL THE TEST CASES ARE PASSED");
			inspLot.setUser(user);
			inspectionLot = inspLotRepo.save(inspLot);
		} else {

			inspLot.setResult("FAIL");
			inspectionLot = inspLotRepo.save(inspLot);
		}

		return inspectionLot;
	}

	@Override
	public List<InspectionLot> getInspectionLotsWithStartDateNotNull() {
		return inspLotRepo.findByStartDateIsNotNullAndRemarksIsNullOrderByLotId();
	}

	@Override
	public List<InspectionLot> getInspectionLotsWithRemarksNotNull() {

		return inspLotRepo.findByRemarksIsNotNullOrderByLotId();
	}

	@Override
	public List<InspectionLot> getInspectionLotWithCreatedDate(InspLotSearchCriteria criteria) {

		List<InspectionLot> inspLots = inspLotRepo.findInspLotsByCreatedDateBetween(criteria.getFromDate(),
				criteria.getToDate());

		System.out.println(criteria);

		if (!criteria.getStatus().isEmpty()) {

			if (criteria.getStatus().equals("UNDER INSPECTION")) {
				inspLots = inspLots.stream()
						.filter(inspLot -> inspLot.getStartDate() != null && inspLot.getResult() == null).toList();
			} else if (criteria.getStatus().equals("INSPECTION NOT STARTED")) {
				inspLots = inspLots.stream().filter(inspLot -> inspLot.getStartDate() == null).toList();
			} else {
				inspLots = inspLots.stream().filter(inspLot -> inspLot.getResult() != null)
						.filter(inspLot -> inspLot.getResult().equals(criteria.getStatus())).toList();
			}

		}
		if (!criteria.getMaterialId().isBlank()) {
			inspLots = inspLots.stream().filter(inspLot -> inspLot.getMaterial().getMaterialId()
					.equals(StringUtilis.processString(criteria.getMaterialId()))).toList();
		}
		if (!criteria.getPlantId().isBlank()) {
			inspLots = inspLots.stream().filter(inspLot -> inspLot.getPlant().getPlantId()
					.equals(StringUtilis.processString(criteria.getPlantId()))).toList();
		}
		if (criteria.getVendorId() != null) {

			String vendorIdStr = StringUtilis.processString(criteria.getVendorId().toString());

			inspLots = inspLots.stream()
					.filter(inspLot -> inspLot.getVendor().getVendorId().equals(Integer.parseInt(vendorIdStr)))
					.toList();
		}
		return inspLots;
	}

	@Override
	public List<InspectionLot> getInspectionLotsWithStartDateNull() {

		List<InspectionLot> inspLot = inspLotRepo.findByStartDateIsNullOrderByLotId();
		return inspLot;
	}

	@Override
	public InspectionLot saveInspectionLotByLotModel(InspLotModel inspLotModel) {

		HashMap<String, String> conflicts = new HashMap<>();

		String vendorName = inspLotModel.getVendorName();
		String plantName = inspLotModel.getPlantName();
		String materialName = inspLotModel.getMaterialName();

		Optional<Vendor> optVendor = vendorRepo.findByVendorName(StringUtilis.processSentance(vendorName));

		if (optVendor.isEmpty()) {
			conflicts.put(vendorName, AppConstants.VENDOR_NAME_ERROR);
		}

		Optional<Plant> optPlant = plantRepo.findByPlantName(StringUtilis.processSentance(plantName));

		if (optPlant.isEmpty()) {
			conflicts.put(plantName, AppConstants.PLANT_NAME_ERROR);
		}

		Optional<Material> optMaterial = matRepo.findByDesc(StringUtilis.processSentance(materialName));

		if (optMaterial.isEmpty()) {
			conflicts.put(materialName, AppConstants.MATERIAL_NAME_ERROR);
		}

		if (conflicts.size() > 0) {
			throw new DataIntegrityViolationException(conflicts.toString());
		}

		InspectionLot inspLot = new InspectionLot();

		inspLot.setVendor(optVendor.get());
		inspLot.setPlant(optPlant.get());
		inspLot.setMaterial(optMaterial.get());

		LocalDate today = LocalDate.now();
		Date sqlDate = Date.valueOf(today);
		inspLot.setCreatedOn(sqlDate);
		if (inspLotModel.getStartDate()) {
			inspLot.setStartDate(sqlDate);
		}

		inspLotRepo.save(inspLot);

		return inspLot;
	}

	

	@Override
	public List<InspLotDisplay> getInspLotDisplays(List<InspectionLot> inspLots) {
		
		List<InspLotDisplay> insplotDisplays = new ArrayList<>();
		
		
		
		for (InspectionLot inspLot : inspLots) {
			
			InspLotDisplay inspDisplay = new InspLotDisplay();
			
			inspDisplay.setLotId(inspLot.getLotId());
			inspDisplay.setVendorName(inspLot.getVendor().getVendorName());
			inspDisplay.setPlantName(inspLot.getPlant().getPlantName());
			inspDisplay.setMaterialName(inspLot.getMaterial().getDesc());
			
			inspDisplay.setCreatedOn(inspLot.getCreatedOn());
			inspDisplay.setStartDate(inspLot.getStartDate() == null ? AppConstants.EMPTY_DATA : inspLot.getStartDate().toString());
			inspDisplay.setEndDate(inspLot.getEndDate() == null ?  AppConstants.EMPTY_DATA : inspLot.getEndDate().toString());
			inspDisplay.setResult(inspLot.getResult() == null ?  AppConstants.EMPTY_DATA :inspLot.getResult());
			inspDisplay.setRemarks(inspLot.getRemarks() == null ?  AppConstants.EMPTY_DATA :inspLot.getRemarks());
			inspDisplay.setUserName(inspLot.getUser() == null ? AppConstants.EMPTY_DATA : inspLot.getUser().getUserName());
			
			insplotDisplays.add(inspDisplay);
		}
		return insplotDisplays;
	}

	@Override
	public List<InspectionLot> getAllInspectionLotsMarkForApproval() {
		
		return inspLotRepo.findByEndDateIsNotNullAndRemarksIsNullOrderByLotId();
	}

	@Override
	public Boolean getStatusOfChannelDesc(String channelDesc, List<MaterialInspection> matInsp) {
		
		
		long count = matInsp.stream().filter(matInspObj->matInspObj.getChannelDescription().equals(channelDesc)).count();
		
		if(count ==1)
			return true;
		return false;
	}

	@Override
	public InspLotDisplay getInspLotDisplay(InspectionLot inspLot) {
		
		InspLotDisplay inspDisplay = new InspLotDisplay();
		
		inspDisplay.setLotId(inspLot.getLotId());
		inspDisplay.setVendorName(inspLot.getVendor().getVendorName());
		inspDisplay.setPlantName(inspLot.getPlant().getPlantName());
		inspDisplay.setMaterialName(inspLot.getMaterial().getDesc());
		
		inspDisplay.setCreatedOn(inspLot.getCreatedOn());
		inspDisplay.setStartDate(inspLot.getStartDate() == null ? AppConstants.EMPTY_DATA : inspLot.getStartDate().toString());
		inspDisplay.setEndDate(inspLot.getEndDate() == null ?  AppConstants.EMPTY_DATA : inspLot.getEndDate().toString());
		inspDisplay.setResult(inspLot.getResult() == null ?  AppConstants.EMPTY_DATA :inspLot.getResult());
		inspDisplay.setRemarks(inspLot.getRemarks() == null ?  AppConstants.EMPTY_DATA :inspLot.getRemarks());
		inspDisplay.setUserName(inspLot.getUser() == null ? AppConstants.EMPTY_DATA : inspLot.getUser().getUserName());
		
		return inspDisplay;
	}

	@Override
	public InspectionLot updateInspectionLot(InspApproveModel inspModel,InspectionLot inspLot) {
		
		inspLot.setResult(inspModel.getResult());
		inspLot.setRemarks(inspModel.getRemarks());
		try {
			InspectionLot inspLotObj = inspLotRepo.save(inspLot);
			
			return inspLotObj;
		}
		catch(Exception ex) {	
			return null;
		}
	}
	@Override
	public Boolean dateComparision(Date fromDate, Date toDate) {
		long differenceInMillis = Math.abs(toDate.getTime() - fromDate.getTime());

		long differenceInDays = differenceInMillis / (1000 * 60 * 60 * 24);

		if (differenceInDays <= 90) {
			return true;
		} else {
			return false;
		}
	}
}
