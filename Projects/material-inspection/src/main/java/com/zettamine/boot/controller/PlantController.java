package com.zettamine.boot.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zettamine.boot.constants.AppConstants;
import com.zettamine.boot.entity.Plant;
import com.zettamine.boot.service.IPlantService;
import com.zettamine.boot.utility.SessionUtils;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/zettaInsp")
public class PlantController {

	
	private IPlantService plantService;

	public PlantController(IPlantService plantService) {
		super();
		this.plantService = plantService;
	}
	private static final Logger logger = LoggerFactory.getLogger(PlantController.class);

	@GetMapping("/plant")
	public String getPlantPage(HttpSession session, Model model) {
		
		logger.debug("*** getPlantPage() method execution started ***");

		if (!SessionUtils.isUserLoggedIn(session)) {
            model.addAttribute("message", "Please Login");
            logger.error("*** the user verification failed ***");
			logger.error("*** the user redirect to login page ***");
            return AppConstants.LOGIN_VIEW;
        }

	 String userName = SessionUtils.getLoggedInUsername(session);
        model.addAttribute("userName", userName);
        logger.debug("*** getPlantPage() method execution completed ***");
		return "plant";
	}

	@GetMapping("/addPlant")
	public String getAddPlantPage(HttpSession session, Model model) {
		
		logger.debug("*** getAddPlantPage() method execution started ***");
		if (!SessionUtils.isUserLoggedIn(session)) {
            model.addAttribute("message", "Please Login");
            logger.error("*** the user verification failed ***");
			logger.error("*** the user redirect to login page ***");
            return AppConstants.LOGIN_VIEW;
        }

	 String userName = SessionUtils.getLoggedInUsername(session);
        model.addAttribute("userName", userName);
		model.addAttribute("plant", new Plant());
		logger.debug("*** getAddPlantPage() method execution completed ***");
		return AppConstants.ADDPLANT_VIEW;
	}

	@PostMapping("/submitPlant")
	public String addPlantDetails(HttpSession session, Plant plant, Model model) {
		logger.debug("*** getPlantDetails() method execution started ***");
		if (!SessionUtils.isUserLoggedIn(session)) {
            model.addAttribute("message", "Please Login");
            logger.error("*** the user verification failed ***");
			logger.error("*** the user redirect to login page ***");
            return AppConstants.LOGIN_VIEW;
        }

	 String userName = SessionUtils.getLoggedInUsername(session);
        model.addAttribute("userName", userName);
		Plant plantObj = plantService.savePlant(plant);
		
		if (plantObj == null) {
			
			model.addAttribute("failmessage", "Plant Added Failed already the plant id is exist.");
			 logger.debug("*** loading plant page for the add plant fail message ***");
			logger.debug("*** getPlantDetails() method execution completed ***");
			return AppConstants.PLANT_VIEW;
			
		} else {
			logger.debug("*** getPlantDetails() method execution completed ***");
			return "redirect:/zettaInsp/aPlant";
		}

	}
	@GetMapping("/aPlant")
	public String getPlantHomePage(HttpSession session,Model model) {
		logger.debug("*** method of PRG for double posting started ***");	
		if (!SessionUtils.isUserLoggedIn(session)) {
            model.addAttribute("message", "Please Login");
            logger.error("*** the user verification failed ***");
			logger.error("*** the user redirect to login page ***");
            return AppConstants.LOGIN_VIEW;
        }

	 String userName = SessionUtils.getLoggedInUsername(session);
        model.addAttribute("userName", userName);
		model.addAttribute("message", "Plant Added succesfully");
		 logger.debug("*** loading plant page for the success message ***");
		logger.debug("*** method of PRG for double posting completed ***");	
		return AppConstants.PLANT_VIEW;
		
	}

	@GetMapping("/plants")
	public String getAllPlants(Model model, HttpSession session) {
		logger.debug("*** getAllPlants() method execution started ***");
		if (!SessionUtils.isUserLoggedIn(session)) {
            model.addAttribute("message", "Please Login");
            logger.error("*** the user verification failed ***");
			logger.error("*** the user redirect to login page ***");
            return AppConstants.LOGIN_VIEW;
        }

	 String userName = SessionUtils.getLoggedInUsername(session);
        model.addAttribute("userName", userName);
		List<Plant> plantList = plantService.getAllPlants();
		model.addAttribute("plantList", plantList);
		logger.debug("*** getAllPlants() method execution started ***");
		return AppConstants.PLANT_VIEW;
	}

	@GetMapping("/updateplant/{id}")
	public String getUpdatePlantPage(@PathVariable("id") String id, Model model, HttpSession session) {
		logger.debug("*** getUpdatePlantPage() method execution started ***");
		if (!SessionUtils.isUserLoggedIn(session)) {
            model.addAttribute("message", "Please Login");
            logger.error("*** the user verification failed ***");
			logger.error("*** the user redirect to login page ***");
            return AppConstants.LOGIN_VIEW;
        }

	 String userName = SessionUtils.getLoggedInUsername(session);
        model.addAttribute("userName", userName);
		Plant plant = plantService.getPlantById(id);

		model.addAttribute("plant", plant);

		model.addAttribute("message", "Update Plant Form of Vendor Id: " + id);
		logger.debug("*** getUpdatePlantPage() method execution completed ***");
		return AppConstants.UPDATEPLANT_VIEW;

	}
	@PostMapping("/updatePlant")
	public String upadtePlantDetails(HttpSession session, Plant plant, Model model) {
		logger.debug("*** updatePlantDetails() method execution started ***");
		Plant plantObj = plantService.updatePlant(plant);
	

		if (!SessionUtils.isUserLoggedIn(session)) {
            model.addAttribute("message", "Please Login");
            logger.error("*** the user verification failed ***");
			logger.error("*** the user redirect to login page ***");
            return AppConstants.LOGIN_VIEW;
        }

	 String userName = SessionUtils.getLoggedInUsername(session);
        model.addAttribute("userName", userName);
		if (plantObj  == null) {
			model.addAttribute("failmessage", "Plant Update Failed");
			logger.debug("***  updatePlantDetails() method execution completed ***");
			return AppConstants.PLANT_VIEW;
			
			
		} else {
			logger.debug("***  updatePlantDetails() method execution completed ***");
			return "redirect:/zettaInsp/uPlant";
		}
	}
	
	@GetMapping("/uPlant")
	public String getplantHomePage(HttpSession session,Model model) {
		logger.debug("*** method of PRG for double posting started ***");	
		if (!SessionUtils.isUserLoggedIn(session)) {
            model.addAttribute("message", "Please Login");
            logger.error("*** the user verification failed ***");
			logger.error("*** the user redirect to login page ***");
            return AppConstants.LOGIN_VIEW;
        }

	 String userName = SessionUtils.getLoggedInUsername(session);
        model.addAttribute("userName", userName);
		model.addAttribute("message", "Plant Updated succesfully");
		 logger.debug("*** loading plant page for the success message ***");
		logger.debug("*** method of PRG for double posting completed ***");
		return AppConstants.PLANT_VIEW;
		
	}
	@GetMapping("/deleteplant/{id}")
	public String deletePlantDetails(@PathVariable("id") String id,HttpSession session, Model model,Plant plant) {
		logger.debug("*** deletePlantDetails() method execution started ***");
		Plant plantObj = plantService.editPlantStatus(id);
		if (!SessionUtils.isUserLoggedIn(session)) {
            model.addAttribute("message", "Please Login");
            logger.error("*** the user verification failed ***");
			logger.error("*** the user redirect to login page ***");
            return AppConstants.LOGIN_VIEW;
        }

	 String userName = SessionUtils.getLoggedInUsername(session);
        model.addAttribute("userName", userName);
		if (plantObj.getPlantId() != null) {
			model.addAttribute("message", "Plant Deleted succesfully");
			logger.debug("*** Plant Deleted succesfully ***");
			logger.debug("*** deletePlantDetails() method execution completed ***");
			return AppConstants.PLANT_VIEW;
		} else {
			model.addAttribute("message", "Plant Deleted Failed");
			logger.debug("*** Plant Deleted failed ***");
			logger.debug("*** deletePlantDetails() method execution completed ***");
			return AppConstants.PLANT_VIEW;
		}

	}
	

}
