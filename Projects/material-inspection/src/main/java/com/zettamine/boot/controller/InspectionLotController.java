package com.zettamine.boot.controller;

import java.util.List;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zettamine.boot.constants.AppConstants;
import com.zettamine.boot.entity.InspectionActuals;
import com.zettamine.boot.entity.InspectionLot;
import com.zettamine.boot.entity.Material;
import com.zettamine.boot.entity.MaterialInspection;
import com.zettamine.boot.entity.Plant;
import com.zettamine.boot.entity.User;
import com.zettamine.boot.entity.Vendor;
import com.zettamine.boot.models.InspLotSearchCriteria;
import com.zettamine.boot.models.VendorNames;
import com.zettamine.boot.service.IInspActualService;
import com.zettamine.boot.service.IInsptLotService;
import com.zettamine.boot.service.IMaterialInspService;
import com.zettamine.boot.service.IMaterialService;
import com.zettamine.boot.service.IPlantService;
import com.zettamine.boot.service.IVendorService;
import com.zettamine.boot.utility.SessionUtils;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/zettaInsp")
public class InspectionLotController {

	private IInsptLotService insplotService;
	private IMaterialService materialService;
	private IVendorService vendorService;
	private IPlantService plantService;
	private IInspActualService inspActService;
	private IMaterialInspService matInspService;

	public InspectionLotController(IInsptLotService insplotService, IMaterialService materialService,
			IVendorService vendorService, IPlantService plantService, IInspActualService inspActService,
			IMaterialInspService matInspService) {
		super();
		this.insplotService = insplotService;
		this.materialService = materialService;
		this.vendorService = vendorService;
		this.plantService = plantService;
		this.inspActService = inspActService;
		this.matInspService = matInspService;
	}

	private static final Logger logger = LoggerFactory.getLogger(InspectionLotController.class);

	@GetMapping("/inspLot")
	public String getMaterialInspectionPage(HttpSession session, Model model) {
		logger.debug("*** getMaterialInspectionPage() method execution started ***");
		if (!SessionUtils.isUserLoggedIn(session)) {
			model.addAttribute("message", "Please Login");
			logger.error("*** the user verification failed ***");
			logger.error("*** the user redirect to login page ***");
			logger.debug("*** getMaterialInspectionPage() method execution completed ***");
			return AppConstants.LOGIN_VIEW;
		}

		String userName = SessionUtils.getLoggedInUsername(session);
		model.addAttribute("userName", userName);
		logger.debug("*** getMaterialInspectionPage() method execution completed ***");
		return AppConstants.INSPECTIONLOT_VIEW;
	}

	@GetMapping("/inspection")
	public String getInspectionPage(HttpSession session, Model model) {
		logger.debug("*** getInspectionPage() method execution started ***");
		if (!SessionUtils.isUserLoggedIn(session)) {
			model.addAttribute("message", "Please Login");
			logger.error("*** the user verification failed ***");
			logger.error("*** the user redirect to login page ***");
			logger.debug("*** getInspectionPage() method execution completed ***");
			return AppConstants.LOGIN_VIEW;
		}

		String userName = SessionUtils.getLoggedInUsername(session);
		model.addAttribute("userName", userName);
		logger.debug("*** getInspectionPage() method execution completed ***");
		return AppConstants.INSPECTION_VIEW;
	}

	@GetMapping("/addInspLot")
	public String getInspLotPage(HttpSession session, Model model) {
		logger.debug("*** getInspLotPage() method execution started ***");
		if (!SessionUtils.isUserLoggedIn(session)) {
			model.addAttribute("message", "Please Login");
			logger.error("*** the user verification failed ***");
			logger.error("*** the user redirect to login page ***");
			logger.debug("*** getInspLotPage() method execution completed ***");
			return AppConstants.LOGIN_VIEW;
		}

		String userName = SessionUtils.getLoggedInUsername(session);

		TreeSet<String> materialNames = materialService.getAllMaterialNames();
		TreeSet<String> VendorNames = vendorService.getAllVendorNames();
		TreeSet<String> plantNames = plantService.getAllPlantNames();

		model.addAttribute("userName", userName);
		model.addAttribute("materialNameList", materialNames);
		model.addAttribute("vendorNameList", VendorNames);
		model.addAttribute("plantNameList", plantNames);
		model.addAttribute("inspLot", new InspectionLot());
		logger.debug("*** getInspLotPage() method execution completed ***");
		return AppConstants.ADDINSPLOT_VIEW;
	}

	@PostMapping("/submitinspLot")
	public String addInspLot(InspectionLot InspLot, Model model, @RequestParam("materialName") String matrialName,
			@RequestParam("vendorName") String vendorName, @RequestParam("plantName") String plantName,
			@RequestParam("startInspection") String status, HttpSession session) {
		logger.debug("*** addInspLot() method execution started ***");
		if (!SessionUtils.isUserLoggedIn(session)) {
			model.addAttribute("message", "Please Login");
			logger.error("*** the user verification failed ***");
			logger.error("*** the user redirect to login page ***");
			return AppConstants.LOGIN_VIEW;
		}

		String userName = SessionUtils.getLoggedInUsername(session);
		model.addAttribute("userName", userName);

		Material material = materialService.getMaterialByName(matrialName);
		InspLot.setMaterial(material);
		Vendor vendor = vendorService.getVendorByName(vendorName);
		InspLot.setVendor(vendor);

		Plant plant = plantService.getPantByName(plantName);
		InspLot.setPlant(plant);
		InspectionLot inspLotObj = insplotService.saveInspLotWithStatus(InspLot, status);

		if (inspLotObj.getLotId() != null) {
			
			logger.debug("*** addInspLot() method execution completed ***");
			return "redirect:/zettaInsp/aInspLot";
		}
		logger.debug("*** addInspLot() method execution completed ***");
		return AppConstants.INSPECTIONLOT_VIEW;

	}

	@GetMapping("/aInspLot")
	public String getInspHomePage(HttpSession session, Model model) {
		logger.debug("*** method of PRG for double posting started ***");	
		if (!SessionUtils.isUserLoggedIn(session)) {
			model.addAttribute("message", "Please Login");
			logger.error("*** the user verification failed ***");
			logger.error("*** the user redirect to login page ***");
			logger.debug("*** method of PRG for double posting completed ***");
			return AppConstants.LOGIN_VIEW;
		}

		String userName = SessionUtils.getLoggedInUsername(session);
		model.addAttribute("userName", userName);
		model.addAttribute("message", "Inspection Lot Added succesfully");
		logger.debug("*** inspection added successfully ***");
		logger.debug("*** method of PRG for double posting completed ***");
		return AppConstants.INSPECTIONLOT_VIEW;

	}

	@GetMapping("/insplots")
	public String getAllLotsNotStarted(HttpSession session, Model model) {
		logger.debug("*** getAllLotsNotStarted() method execution started ***");
		if (!SessionUtils.isUserLoggedIn(session)) {
			model.addAttribute("message", "Please Login");
			logger.error("*** the user verification failed ***");
			logger.error("*** the user redirect to login page ***");
			logger.debug("*** getAllLotsNotStarted() method execution completed ***");
			return AppConstants.LOGIN_VIEW;
		}

		String userName = SessionUtils.getLoggedInUsername(session);

		List<InspectionLot> inspLots = insplotService.getInspectionLotsWithStartDateNull();

		model.addAttribute("userName", userName);
		model.addAttribute("inspLots", inspLots);
		logger.debug("*** getAllLotsNotStarted() method execution completed ***");
		return AppConstants.VIEW_INSPT_VIEW;
	}

	@GetMapping("/editinspLot/{id}")
	public String getViewInspLotPage(HttpSession session, Model model, @PathVariable("id") Integer id) {
		logger.debug("*** getViewInspLotPage() method execution started ***");
		if (!SessionUtils.isUserLoggedIn(session)) {
			model.addAttribute("message", "Please Login");
			logger.error("*** the user verification failed ***");
			logger.error("*** the user redirect to login page ***");
			logger.debug("*** getViewInspLotPage() method execution completed ***");
			return AppConstants.LOGIN_VIEW;
		}

		String userName = SessionUtils.getLoggedInUsername(session);
		model.addAttribute("userName", userName);

		InspectionLot inspLot = insplotService.getInspectionLotById(id);

		if (inspLot.getStartDate() != null) {

			if (inspLot.getResult() == null) {

				model.addAttribute("failmessage", "Lot inspection started.You can't edit Lot Details");
				logger.debug("fail message while starting the inspection , reason (the inspection has started)");
				logger.debug("*** getViewInspLotPage() method execution completed ***");
				return AppConstants.VIEW_INSPT_VIEW;
			} else if (inspLot.getResult().equalsIgnoreCase("Pass")) {
				model.addAttribute("failmessage", "Lot inspection completed.You can't edit Lot Details");
				logger.debug("fail message while starting the inspection , reason (the inspection has started)");
				logger.debug("*** getViewInspLotPage() method execution completed ***");
				return AppConstants.VIEW_INSPT_VIEW;
			} else {
				model.addAttribute("inspLot", inspLot);
				
				logger.debug("*** getViewInspLotPage() method execution completed ***");
				return AppConstants.UPDATEINSPLOT_VIEW;
			}
		}
		if (inspLot.getMaterial().getMatInsp().size() == 0) {

			model.addAttribute("failmessage", "Before starting Inspection add Material Characteristics for material "
					+ inspLot.getMaterial().getDesc());
			logger.debug("fail message while starting the inspection, reason (add characteristics for the material) ");
			logger.debug("*** getViewInspLotPage() method execution completed ***");
			return AppConstants.VIEW_INSPT_VIEW;
		}

		model.addAttribute("inspLot", inspLot);

		return AppConstants.UPDATEINSPLOT_VIEW;
	}

	@PostMapping("/submitInsplot")
	public String updateInspLot(HttpSession session, InspectionLot inspLot, Model model) {
		logger.debug("*** updateInspLot() method execution started ***");
		if (!SessionUtils.isUserLoggedIn(session)) {
			model.addAttribute("message", "Please Login");
			logger.error("*** the user verification failed ***");
			logger.error("*** the user redirect to login page ***");
			logger.debug("*** updateInspLot() method execution completed ***");
			return AppConstants.LOGIN_VIEW;
		}

		String userName = SessionUtils.getLoggedInUsername(session);
		model.addAttribute("userName", userName);

		User user = (User) session.getAttribute("user");

		InspectionLot saveInspLot = insplotService.saveInspLot(inspLot);

		model.addAttribute("userName", user.getUserName());
		if (saveInspLot.getLotId() != null) {

			model.addAttribute("message", "Lot updated Successfully");
			logger.debug("*** lot updated successfully ***");
			logger.debug("*** updateInspLot() method execution completed ***");
			return AppConstants.INSPECTIONLOT_VIEW;
		}

		model.addAttribute("message", "Lot not updated");
		logger.debug("*** lot updated failed ***");
		logger.debug("*** updateInspLot() method execution completed ***");
		return AppConstants.INSPECTIONLOT_VIEW;
	}

	@GetMapping("/addinspactuals/{lotId}")
	public String addInspectActuals(@PathVariable("lotId") Integer lotId, HttpSession session, Model model) {
		logger.debug("*** addInspectActuals() method execution started ***");
		if (!SessionUtils.isUserLoggedIn(session)) {
			model.addAttribute("message", "Please Login");
			logger.error("*** the user verification failed ***");
			logger.error("*** the user redirect to login page ***");
			logger.debug("*** addInspectActuals() method execution completed ***");
			return AppConstants.LOGIN_VIEW;
		}

		String userName = SessionUtils.getLoggedInUsername(session);
		model.addAttribute("userName", userName);

		InspectionLot inspLot = insplotService.getInspectionLotById(lotId);

		if (inspLot.getStartDate() != null) {
			String mId = inspLot.getMaterial().getMaterialId();
			List<MaterialInspection> matInspList = matInspService.getAllCharcateristicsById(mId);
			List<InspectionActuals> inspActList = inspLot.getInspectionActuals();

			if (matInspList.isEmpty()) {

				model.addAttribute("failmessage", "The Inspection Actuals has not added.");
				logger.debug("*** addInspectActuals() method execution completed ***");
				logger.debug("*** add inspection actuals fail message ***");
				return AppConstants.INSPECTION_VIEW;
			}

			matInspList = insplotService.getMaterialInspectionList(matInspList, inspActList);
			if (matInspList.size() == 0 || inspLot.getResult() != null) {
				model.addAttribute("failmessage", "The Inspection Actuals are addded.");
				logger.debug("*** add inspection actuals fail message ***");
				logger.debug("*** addInspectActuals() method execution completed ***");
				return AppConstants.INSPECTION_VIEW;
			}
			model.addAttribute("inspLot", inspLot);
			model.addAttribute("inspActual", new InspectionActuals());
			model.addAttribute("matInspList", matInspList);
			model.addAttribute("addactual", true);
			logger.debug("*** addInspectActuals() method execution completed ***");
			return AppConstants.ADDACTUALS_VIEW;

		}

		model.addAttribute("failmessage", "The inspection has not started yet");
		logger.debug("*** The inspection has not started yet ***");
		logger.debug("*** addInspectActuals() method execution completed ***");
		return AppConstants.INSPECTION_VIEW;

	}
///
	@PostMapping("/submitmatact")
	public String addActuals(@RequestParam("lotId") Integer lotId, @RequestParam("materialInsp") Integer matInspId,
			@RequestParam("maxMesurment") float maxMeasurement, @RequestParam("minMesurment") float minMeasurement,
			Model model, HttpSession session) {
		logger.debug("*** addActuals() method execution started ***");
		if (!SessionUtils.isUserLoggedIn(session)) {
			model.addAttribute("message", "Please Login");
			logger.error("*** the user verification failed ***");
			logger.error("*** the user redirect to login page ***");
			logger.debug("*** addActuals() method execution completed ***");
			return AppConstants.LOGIN_VIEW;
		}

		String userName = SessionUtils.getLoggedInUsername(session);
		model.addAttribute("userName", userName);

		InspectionLot inspLot = insplotService.getInspectionLotById(lotId);

		MaterialInspection matInsp = matInspService.getMaterialInspStatus(matInspId);

		InspectionActuals inspActual = new InspectionActuals(inspLot, matInsp, maxMeasurement, minMeasurement);
		InspectionActuals inspActualObj = inspActService.saveInspectionActuals(inspActual);

		if (inspActualObj == null) {
			model.addAttribute("message", "Inspection actual added failed");
			logger.debug("*** addActuals() method execution completed ***");
			return AppConstants.INSPECTION_VIEW;
		}
		String mId = inspLot.getMaterial().getMaterialId();
		List<MaterialInspection> matInspList = matInspService.getAllCharcateristicsById(mId);
		List<InspectionActuals> inspActList = inspLot.getInspectionActuals();

		matInspList = insplotService.getMaterialInspectionList(matInspList, inspActList);
		if (matInspList.size() == 0) {
			logger.debug("*** checking if all the actuals are inpected ***");
			Boolean result = insplotService.generateInspLotResult(inspLot);
			User user = (User) session.getAttribute("user");
			
			logger.debug("*** saving the inspection lot based on the result ***");
			insplotService.saveInspLot(inspLot, result, user);

		}
		logger.debug("*** addActuals() method execution completed ***");
		return "redirect:/zettaInsp/addactuals";
	}

	@GetMapping("/addactuals")
	public String addInspectActual(HttpSession session, Model model) {
		logger.debug("*** addInspectActuals() method execution started ***");
		if (!SessionUtils.isUserLoggedIn(session)) {
			model.addAttribute("message", "Please Login");
			logger.error("*** the user verification failed ***");
			logger.error("*** the user redirect to login page ***");
			logger.debug("*** addInspectActuals() method execution completed ***");
			return AppConstants.LOGIN_VIEW;
		}

		String userName = SessionUtils.getLoggedInUsername(session);
		model.addAttribute("userName", userName);

		model.addAttribute("message", "Inspection actual added successfully");
		logger.debug("*** inspection actual added successfully***");
		logger.debug("*** addInspectActuals() method execution completed ***");
		return AppConstants.INSPECTION_VIEW;

	}

	@GetMapping("/inspProgress")
	public String getAllInspectlots(HttpSession session, Model model) {
		logger.debug("*** getAllInspectlots() method execution started ***");
		if (!SessionUtils.isUserLoggedIn(session)) {
			model.addAttribute("message", "Please Login");
			logger.error("*** the user verification failed ***");
			logger.error("*** the user redirect to login page ***");
			logger.debug("*** getAllInspectlots() method execution completed ***");
			return AppConstants.LOGIN_VIEW;
		}

		String userName = SessionUtils.getLoggedInUsername(session);
		model.addAttribute("userName", userName);

		List<InspectionLot> inspLots = insplotService.getInspectionLotsWithStartDateNotNull();

		model.addAttribute("inspLots", inspLots);
		logger.debug("*** getAllInspectlots() method execution completed ***");
		return AppConstants.VIEW_INSPT_VIEW;
	}

	@GetMapping("/inspcompleted")
	public String getAllInspectcompletedLots(HttpSession session, Model model) {
		logger.debug("*** getAllInspectcompletedLots() method execution started ***");
		if (!SessionUtils.isUserLoggedIn(session)) {
			model.addAttribute("message", "Please Login");
			logger.error("*** the user verification failed ***");
			logger.error("*** the user redirect to login page ***");
			logger.debug("*** getAllInspectcompletedLots() method execution completed ***");
			return AppConstants.LOGIN_VIEW;
		}

		String userName = SessionUtils.getLoggedInUsername(session);
		model.addAttribute("userName", userName);

		List<InspectionLot> inspLots = insplotService.getInspectionLotsWithRemarksNotNull();

		model.addAttribute("inspLots", inspLots);
		logger.debug("*** getAllInspectcompletedLots() method execution completed ***");
		return AppConstants.VIEW_INSPT_VIEW;
	}

	@GetMapping("/Allinsplots")
	public String getAllInspectLots(HttpSession session, Model model) {
		logger.debug("*** getAllInspectLots() method execution started ***");
		if (!SessionUtils.isUserLoggedIn(session)) {
			model.addAttribute("message", "Please Login");
			logger.error("*** the user verification failed ***");
			logger.error("*** the user redirect to login page ***");
			logger.debug("*** getAllInspectLots() method execution completed ***");
			return AppConstants.LOGIN_VIEW;
		}

		String userName = SessionUtils.getLoggedInUsername(session);
		model.addAttribute("userName", userName);

		List<InspectionLot> inspLots = insplotService.getAllInspectionLots();

		model.addAttribute("inspLots", inspLots);
		logger.debug("*** getAllInspectLots() method execution completed ***");
		return AppConstants.VIEW_INSPT_VIEW;
	}

	@GetMapping("/viewActuals/{lotId}")
	public String getActuals(@PathVariable Integer lotId, Model model, HttpSession session) {
		logger.debug("*** getActuals() method execution started ***");
		if (!SessionUtils.isUserLoggedIn(session)) {
			model.addAttribute("message", "Please Login");
			logger.error("*** the user verification failed ***");
			logger.error("*** the user redirect to login page ***");
			logger.debug("*** getActuals() method execution completed ***");
			return AppConstants.LOGIN_VIEW;
		}

		String userName = SessionUtils.getLoggedInUsername(session);
		model.addAttribute("userName", userName);

		InspectionLot inspLot = insplotService.getInspectionLotById(lotId);

		if (inspLot == null) {
			model.addAttribute("failmessage",
					"The inspection LotId " + lotId + " you have entered is InValid.Please Enter correct lot Id");
			logger.debug("*** fail message the lot Id enetered is invalid ***");
			logger.debug("*** getActuals() method execution completed ***");
			return AppConstants.INSPECTION_VIEW;
		}

		if (inspLot.getStartDate() == null) {
			model.addAttribute("failmessage", "The inspection is not started for inspection lotId: " + lotId + ".");
			logger.debug("*** fail message the inspection not started for the lot Id ***");
			logger.debug("*** getActuals() method execution completed ***");
			return AppConstants.INSPECTION_VIEW;
		}

		if (inspLot.getInspectionActuals().size() == 0 && inspLot.getResult() == null) {
			model.addAttribute("failmessage",
					"The inspection LotId " + lotId + " you have entered is InValid for view actuals.Please Try Again");
			logger.debug("*** fail message inspection actuals are not added for the lot Id ***");
			logger.debug("*** getActuals() method execution completed ***");
			return AppConstants.INSPECTION_VIEW;
		}

		model.addAttribute("inspLot", inspLot);
		model.addAttribute("displaylot", true);
		logger.debug("*** getActuals() method execution completed ***");
		return AppConstants.ADDACTUALS_VIEW;
	}

	@GetMapping("/approve/{lotId}")
	public String approveLot(@PathVariable Integer lotId, Model model, HttpSession session) {
		logger.debug("*** approveLot() method execution started ***");
		if (!SessionUtils.isUserLoggedIn(session)) {
			model.addAttribute("message", "Please Login");
			logger.error("*** the user verification failed ***");
			logger.error("** the user redirect to login page ***");
			logger.debug("*** approveLot() method execution completed ***");
			return AppConstants.LOGIN_VIEW;
		}

		String userName = SessionUtils.getLoggedInUsername(session);
		model.addAttribute("userName", userName);

		InspectionLot inspLot = insplotService.getInspectionLotById(lotId);

		if (inspLot == null) {
			model.addAttribute("failmessage",
					"The inspection LotId " + lotId + " you have entered is InValid.Please Enter correct lot Id");
			logger.info("*** fail message the log id is invalid ***");
			logger.debug("*** approveLot() method execution completed ***");
			return AppConstants.INSPECTION_VIEW;
		}

		if (inspLot.getStartDate() == null) {
			model.addAttribute("failmessage", "The inspection is not started for inspection lotId: " + lotId + ".");
			logger.info("*** fail message the inspectionnot started for the log id ***");
			logger.debug("*** approveLot() method execution completed ***");
			return AppConstants.INSPECTION_VIEW;
		}

		if (inspLot.getEndDate() == null || inspLot.getRemarks() != null) {
			model.addAttribute("failmessage",
					"The inspection LotId " + lotId + " you have entered is InValid for approval.Please Try Again");
			logger.info("*** fail message the log id is invalid for the approval***");
			logger.debug("*** approveLot() method execution completed ***");
			return AppConstants.INSPECTION_VIEW;
		}

		model.addAttribute("inspLot", inspLot);
		model.addAttribute("displaylot", true);
		model.addAttribute("approve", true);
		logger.debug("*** approveLot() method execution completed ***");
		return AppConstants.ADDACTUALS_VIEW;
	}

	@GetMapping("/viewresult/{lotId}")
	public String viewstatusOfLot(@PathVariable Integer lotId, Model model, HttpSession session) {
		logger.debug("*** viewstatusOfLot() method execution started ***");
		if (!SessionUtils.isUserLoggedIn(session)) {
			model.addAttribute("message", "Please Login");
			logger.error("*** the user verification failed ***");
			logger.error("*** the user redirect to login page ***");
			logger.debug("*** viewstatusOfLot() method execution completed ***");
			return AppConstants.LOGIN_VIEW;
		}

		String userName = SessionUtils.getLoggedInUsername(session);
		model.addAttribute("userName", userName);

		InspectionLot inspLot = insplotService.getInspectionLotById(lotId);

		if (inspLot == null) {
			model.addAttribute("failmessage",
					"The inspection LotId " + lotId + " you have entered is InValid.Please Enter correct lot Id");
			logger.info("*** the entered log id is invalid ***");
			logger.debug("*** viewstatusOfLot() method execution completed ***");
			return AppConstants.INSPECTION_VIEW;
		}
		if (inspLot.getStartDate() == null) {
			model.addAttribute("failmessage", "The inspection is not started for inspection lotId: " + lotId + ".");
			logger.info("*** the inspection is not started for the lot id ***");
			logger.debug("*** viewstatusOfLot() method execution completed ***");
			return AppConstants.INSPECTION_VIEW;
		}
		if (inspLot.getRemarks() == null) {
			model.addAttribute("failmessage",
					"The inspection LotId " + lotId + " you have entered is InValid for view result.Please Try Again");
			logger.info("*** the inspection not completed to view the result ***");
			logger.debug("*** viewstatusOfLot() method execution completed ***");
			return AppConstants.INSPECTION_VIEW;
		}

		model.addAttribute("inspLot", inspLot);
		model.addAttribute("displaylot", true);
		model.addAttribute("result", true);
		logger.debug("*** viewstatusOfLot() method execution completed ***");
		return AppConstants.ADDACTUALS_VIEW;
	}

	@PostMapping("/submitlot")
	public String submitLot(InspectionLot inspLot, HttpSession session, Model model) {
		logger.debug("*** submitLot() method execution started ***");
		if (!SessionUtils.isUserLoggedIn(session)) {
			model.addAttribute("message", "Please Login");
			logger.error("*** the user verification failed ***");
			logger.error("*** the user redirect to login page ***");
			logger.debug("*** submitLot() method execution completed ***");
			return AppConstants.LOGIN_VIEW;
		}

		String userName = SessionUtils.getLoggedInUsername(session);
		model.addAttribute("userName", userName);

		User user = (User) session.getAttribute("user");

		inspLot.setUser(user);

		System.out.println(inspLot);

		InspectionLot inspLotObj = insplotService.saveInspLot(inspLot);
		if (inspLotObj.getRemarks() != null) {
			model.addAttribute("message", "InspectionLot updated  successfully");
			logger.info("*** the inspection lot updated successfully ***");
			logger.debug("*** submitLot() method execution completed ***");
			return AppConstants.INSPECTION_VIEW;

		}
		model.addAttribute("message", "InspectionLot updated  failed");
		logger.info("*** the inspection lot updation failed ***");
		logger.debug("*** submitLot() method execution completed ***");
		return AppConstants.INSPECTION_VIEW;
	}

	@GetMapping("/searchLot")
	public String getSearchLotPage(Model model, HttpSession session) {
		logger.debug("*** getSearchLotPage() method execution started ***");
		if (!SessionUtils.isUserLoggedIn(session)) {
			model.addAttribute("message", "Please Login");
			logger.error("*** the user verification failed ***");
			logger.error("*** the user redirect to login page ***");
			logger.debug("*** getSearchLotPage() method execution completed ***");
			return AppConstants.LOGIN_VIEW;
		}

		String userName = SessionUtils.getLoggedInUsername(session);
		model.addAttribute("userName", userName);

		model.addAttribute("search", true);
		logger.debug("*** getSearchLotPage() method execution completed ***");
		return AppConstants.INSPECTIONLOT_VIEW;

	}

	@GetMapping("/searchbylotid")
	public String getSearchByIdPage(HttpSession session, Model model) {
		logger.debug("*** getSearchByIdPage() method execution started ***");
		if (!SessionUtils.isUserLoggedIn(session)) {
			model.addAttribute("message", "Please Login");
			logger.error("*** the user verification failed ***");
			logger.error("*** the user redirect to login page ***");
			logger.debug("*** getSearchByIdPage() method execution completed ***");
			return AppConstants.LOGIN_VIEW;
		}

		String userName = SessionUtils.getLoggedInUsername(session);
		model.addAttribute("userName", userName);

		model.addAttribute("searchById", true);
		logger.debug("*** getSearchByIdPage() method execution completed ***");
		return AppConstants.SEARCHLOT_VIEW;
	}

	@GetMapping("/search")
	public String getSearchPage(Model model, InspLotSearchCriteria criteria, HttpSession session) {
		logger.debug("*** getSearchPage() method execution started ***");
		if (!SessionUtils.isUserLoggedIn(session)) {
			model.addAttribute("message", "Please Login");
			logger.error("*** the user verification failed ***");
			logger.error("*** the user redirect to login page ***");
			logger.debug("*** getSearchPage() method execution completed ***");
			return AppConstants.LOGIN_VIEW;
		}

		String userName = SessionUtils.getLoggedInUsername(session);
		model.addAttribute("userName", userName);

		model.addAttribute("search", true);
		model.addAttribute("criteria", criteria);
		logger.debug("*** getSearchPage() method execution completed ***");
		return AppConstants.SEARCHLOT_VIEW;
	}

	@PostMapping("/getinsplot")
	public String getLotDetails(Model model, @RequestParam("lotId") Integer lotId, HttpSession session) {
		logger.debug("*** getLotDetails() method execution started ***");
		if (!SessionUtils.isUserLoggedIn(session)) {
			model.addAttribute("message", "Please Login");
			logger.error("*** the user verification failed ***");
			logger.error("*** the user redirect to login page ***");
			logger.debug("*** getLotDetails() method execution completed ***");
			return AppConstants.LOGIN_VIEW;
		}

		String userName = SessionUtils.getLoggedInUsername(session);
		model.addAttribute("userName", userName);

		InspectionLot inspLot = insplotService.getInspectionLotById(lotId);

		if (inspLot == null) {
			model.addAttribute("failmessage",
					"The inspection LotId " + lotId + " you have entered is InValid.Please Try Again");
			logger.debug("*** getLotDetails() method execution completed ***");
			logger.info("*** entered lot id is invalid ***");
			return AppConstants.INSPECTION_VIEW;
		}
		if (inspLot.getResult() != null) {
			model.addAttribute("result", true);
			model.addAttribute("displaylot", true);
		}
		if (inspLot.getInspectionActuals().size() > 0) {

			model.addAttribute("displaylot", true);
		}
		model.addAttribute("inspLot", inspLot);
		logger.debug("*** getLotDetails() method execution completed ***");
		return AppConstants.ADDACTUALS_VIEW;
	}

	@PostMapping("/getinsplots")
	public String getLotsDetails(Model model, InspLotSearchCriteria criteria, HttpSession session) {
		logger.debug("*** getLotsDetails() method execution started ***");
		List<InspectionLot> inspLots = insplotService.getInspectionLotWithCreatedDate(criteria);

		if (!SessionUtils.isUserLoggedIn(session)) {
			model.addAttribute("message", "Please Login");
			logger.error("*** the user verification failed ***");
			logger.error("*** the user redirect to login page ***");
			logger.debug("*** getLotsDetails() method execution completed ***");
			return AppConstants.LOGIN_VIEW;
		}

		String userName = SessionUtils.getLoggedInUsername(session);
		model.addAttribute("userName", userName);

		model.addAttribute("inspLots", inspLots);
		if (inspLots.size() == 0) {
			model.addAttribute("failmessage", "Based on that search criteria no Inspection Lots are present");
			logger.info("*** based on the search criteria no inspection lots are present ***");
		}
		logger.debug("*** getLotsDetails() method execution completed ***");
		return AppConstants.VIEW_INSPT_VIEW;
	}
}
