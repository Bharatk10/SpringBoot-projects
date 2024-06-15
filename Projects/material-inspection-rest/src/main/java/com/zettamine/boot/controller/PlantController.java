package com.zettamine.boot.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zettamine.boot.constants.AppConstants;
import com.zettamine.boot.constants.ValidationConstants;
import com.zettamine.boot.entity.Plant;
import com.zettamine.boot.entity.Vendor;
import com.zettamine.boot.service.IPlantService;
import com.zettamine.boot.utility.SessionUtils;
import com.zettamine.boot.utility.StringUtilis;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/zettaInsp")
public class PlantController {

	private IPlantService plantService;

	public PlantController(IPlantService plantService) {
		super();
		this.plantService = plantService;
	}

	private static final Logger logger = LoggerFactory.getLogger(PlantController.class);
	
	@PostMapping("/plant")
	public ResponseEntity<?> addPlant(@Valid @RequestBody(required = false) Plant plant) {
		logger.debug("*** addPlantDetails() method execution started ***");

		if(plant == null) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ValidationConstants.PLANT_ERROR);
		}
		Plant plantObj = plantService.savePlant(plant);
		logger.debug("*** addPlantDetails() method execution completed ***");
		
		return ResponseEntity.status(HttpStatus.OK).body(plantObj);

	}
	
	@GetMapping("/plants")
	public ResponseEntity<?> getallPlants() {
		logger.debug("*** getAllPlants() method execution started ***");

		List<Plant> plantList = plantService.getAllPlants();

		if (plantList.isEmpty()) {
			logger.debug("*** getAllPlants() method execution completed ***");
			return ResponseEntity.ok(ValidationConstants.PLANT_LIST_MESSAGE);
		}
		logger.debug("*** getAllPlants() method execution completed ***");
		return ResponseEntity.ok(plantList);
	}
	
	@DeleteMapping("/plant/{id}")
	public ResponseEntity<?> deletePlant(@Valid @PathVariable("id") String id) {
		logger.debug("*** deletePlant() method execution started ***");
		id = StringUtilis.processString(id);
		 if (!id.matches(ValidationConstants.PLANT_ID_PATTERN)) {
		        logger.info("Invalid Plant Id format");
		        logger.debug("*** deletePlant() method execution completed ***");
		        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ValidationConstants.PLANT_ID_ERROR);
		    }
		
	
		Plant plantObj = plantService.editPlantStatus(id);

		if (plantObj!= null) {
			logger.debug("*** deletePlant() method execution completed ***");
			logger.info("plant deleted Successfully");
			return ResponseEntity.ok(AppConstants.PLANT_DELETE_MESSAGE);
		} else {

			logger.info("plant deleted Failed");
			logger.debug("*** deletePlant() method execution completed ***");
			String errorMessage = ValidationConstants.PLANT_NOT_PRESENT_ID_ERROR+id+"\n";
			return ResponseEntity.status(HttpStatus.GONE).body(errorMessage+AppConstants.PLANT_DELETE_ERROR);
		}

	}
	@GetMapping("/plant/{id}")
	public ResponseEntity<?> getPlantDetails(@Valid @PathVariable("id") String id) {
		logger.debug("*** getPlantDetails() method execution started ***");
		id = StringUtilis.processString(id);
		 if (!id.matches(ValidationConstants.PLANT_ID_PATTERN)) {
		        logger.info("Invalid Plant Id format");
		        logger.debug("*** getPlantDetails() method execution completed ***");
		        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ValidationConstants.PLANT_ID_ERROR);
		    }
		
	Plant plantObj = plantService.getPlantById(id);
	 
		if (plantObj!= null) {
			logger.debug("*** getPlantDetails() method execution completed ***");
			
			return ResponseEntity.ok(plantObj);
		} else {
			logger.debug("*** getPlantDetails() method execution completed ***");
			String errorMessage = ValidationConstants.PLANT_NOT_PRESENT_ID_ERROR+id;
			return ResponseEntity.status(HttpStatus.GONE).body(errorMessage);
		}

	}
	@PutMapping("/plant/{id}")
	public ResponseEntity<?> updatePlant(@Valid @PathVariable("id") String id,@RequestBody Plant plant) {
		logger.debug("*** updatePlant() method execution started ***");
		id = StringUtilis.processString(id);
		if (!plant.getPlantId().equals(id)) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(ValidationConstants.UPDATE_ID_ERROR);
		}
		
		 if (!id.matches(ValidationConstants.PLANT_ID_PATTERN)) {
		        logger.info("Invalid Plant Id format");
		        logger.debug("*** updatePlant() method execution completed ***");
		        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ValidationConstants.PLANT_ID_ERROR);
		    }
		
		Plant plantObj = plantService.updatePlant(plant);
		if(plantObj==null) {
			String errorMessage = ValidationConstants.PLANT_NOT_PRESENT_ID_ERROR+id+"\n";
			logger.debug("*** updatePlant() method execution completed ***");
			return ResponseEntity.status(HttpStatus.GONE).body(errorMessage+AppConstants.PLANT_UPDATE_ERROR);
		}
		logger.debug("*** updatePlant() method execution completed ***");

		return ResponseEntity.status(HttpStatus.OK).body(plantObj);

	}

	

	

	
}
