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
import com.zettamine.boot.entity.Material;
import com.zettamine.boot.entity.MaterialInspection;
import com.zettamine.boot.models.MaterialSearchCriteria;
import com.zettamine.boot.service.IMaterialInspService;
import com.zettamine.boot.service.IMaterialService;
import com.zettamine.boot.utility.SessionUtils;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/zettaInsp")
public class MaterialController {

	private IMaterialService materialService;

	private IMaterialInspService matInspService;

	public MaterialController(IMaterialService materialService, IMaterialInspService matInspService) {
		super();
		this.materialService = materialService;
		this.matInspService = matInspService;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(MaterialController.class);


	@GetMapping("/addMaterial/{mId}")
	public String getAddMaterialPage(@PathVariable("mId") String mId, HttpSession session, Model model) {
		logger.debug("*** getAddMaterialPage() method execution started ***");
		if (!SessionUtils.isUserLoggedIn(session)) {
			model.addAttribute("message", "Please Login");
			logger.error("*** the user verification failed ***");
			logger.error("*** the user redirect to login page ***");
			return AppConstants.LOGIN_VIEW;
		}

		String userName = SessionUtils.getLoggedInUsername(session);
		model.addAttribute("userName", userName);
		if (mId.equals("add")) {
			model.addAttribute("material", new Material());
			logger.debug("*** displaying add material form ***");
			model.addAttribute("message", "Add Material Form");
		} else {
			Material material = materialService.getMaterialById(mId);
			model.addAttribute("material", material);
			logger.debug("*** displaying update material form ***");
			model.addAttribute("message", "Update Material Form of "+mId);
		}
		logger.debug("*** getAddMaterialPage() method execution completed ***");
		return AppConstants.ADDMATERIAL_VIEW;
	}

	@PostMapping("/submitMaterial")
	public String addMaterialDetails(HttpSession session, Material material, Model model) {
		logger.debug("*** getAddMaterialDetails() method execution started ***");
		if (!SessionUtils.isUserLoggedIn(session)) {
			model.addAttribute("message", "Please Login");
			logger.error("*** the user verification failed ***");
			logger.error("*** the user redirect to login page ***");
			return AppConstants.LOGIN_VIEW;
		}

		String userName = SessionUtils.getLoggedInUsername(session);
		model.addAttribute("userName", userName);

		if (material.getMaterialId().isEmpty()) {

			String id = materialService.materialIdGenerator();
			Material materialObj = materialService.saveMaterialById(material, id);

			if (materialObj != null) {
				logger.debug("*** getAddMaterialDetails() method execution started ***");
				return "redirect:/zettaInsp/aMaterial";
			} else {
				model.addAttribute("failmessage", "Material Added Failed");
				logger.debug("*** getAddMaterialDetails() method execution completed ***");
		        logger.debug("*** displaing the error messgage while adding material");
				return AppConstants.MATERIAL_VIEW;
			}
		}
		Material matObj = materialService.saveMaterial(material);
		if (matObj != null) {
			logger.debug("*** getAddMaterialDetails() method execution started ***");
			return "redirect:/zettaInsp/uMaterial";
		} else {
			model.addAttribute("failmessage", "Material Updated Failed");
			logger.debug("*** getAddMaterialDetails() method execution started ***");
			  logger.debug("*** displaing the error messgage while updating material");
			return AppConstants.MATERIAL_VIEW;
		}
	}

	@GetMapping("/aMaterial")
	public String getVendorHomePage(HttpSession session, Model model) {
		logger.debug("*** method of PRG for double posting started ***");	
		if (!SessionUtils.isUserLoggedIn(session)) {
			model.addAttribute("message", "Please Login");
			logger.error("*** the user verification failed ***");
			logger.error("*** the user redirect to login page ***");
			return AppConstants.LOGIN_VIEW;
		}

		String userName = SessionUtils.getLoggedInUsername(session);
		model.addAttribute("userName", userName);
		model.addAttribute("message", "Material Added succesfully");
		 logger.debug("*** loading vendor page for the success message ***");
		  logger.debug("*** method of PGP for double completed ***");
		return AppConstants.MATERIAL_VIEW;

	}

	@GetMapping("/uMaterial")
	public String getMaterialHomePage(HttpSession session, Model model) {
		logger.debug("*** method of PRG for double posting started ***");	
		if (!SessionUtils.isUserLoggedIn(session)) {
			model.addAttribute("message", "Please Login");
			logger.error("*** the user verification failed ***");
			logger.error("*** the user redirect to login page ***");
			return AppConstants.LOGIN_VIEW;
		}

		String userName = SessionUtils.getLoggedInUsername(session);
		model.addAttribute("userName", userName);
		model.addAttribute("message", "Material Updated succesfully");
		 logger.debug("*** loading material page for the success message ***");
		  logger.debug("*** method of PGP for double completed ***");
		return AppConstants.MATERIAL_VIEW;

	}

	@GetMapping("/materials")
	public String getAllMaterials(Model model, HttpSession session) {
		logger.debug("*** getAllMaterials() method execution started ***");
		if (!SessionUtils.isUserLoggedIn(session)) {
			model.addAttribute("message", "Please Login");
			logger.error("*** the user verification failed ***");
			logger.error("*** the user redirect to login page ***");
			return AppConstants.LOGIN_VIEW;
		}

		String userName = SessionUtils.getLoggedInUsername(session);
		model.addAttribute("userName", userName);

		List<Material> materialList = materialService.getAllMaterials();
		model.addAttribute("materialList", materialList);
		logger.debug("*** getAllMaterials() method execution completed ***");
		return AppConstants.MATERIAL_VIEW;
	}

	@GetMapping("/addcharateristics/{mId}")
	public String getAddCharacteristics(@PathVariable("mId") String mId, HttpSession session, Model model) {
		logger.debug("*** getAddCharacteristics() method execution started ***");
		if (!SessionUtils.isUserLoggedIn(session)) {
			model.addAttribute("message", "Please Login");
			logger.error("*** the user verification failed ***");
			logger.error("*** the user redirect to login page ***");
			return AppConstants.LOGIN_VIEW;
		}

		String userName = SessionUtils.getLoggedInUsername(session);
		model.addAttribute("userName", userName);

		Material material = materialService.getMaterialById(mId);

		MaterialInspection matInsp = new MaterialInspection();
		matInsp.setMaterial(material);
		model.addAttribute("matInsp", matInsp);
		logger.debug("*** getAddCharacteristics() method execution started ***");
		return AppConstants.ADDMATR_PARAMETERS_VIEW;
	}

	@PostMapping("/submitMatInsp")
	public String getMatInspFormData(MaterialInspection matInsp, Model model, HttpSession session) {
		logger.debug("*** getMatInspFormData() method execution started ***");
		MaterialInspection matInspObj = matInspService.saveMaterialInsp(matInsp);

		if (!SessionUtils.isUserLoggedIn(session)) {
			model.addAttribute("message", "Please Login");
			logger.error("*** the user verification failed ***");
			logger.error("*** the user redirect to login page ***");
			return AppConstants.LOGIN_VIEW;
		}

		String userName = SessionUtils.getLoggedInUsername(session);
		model.addAttribute("userName", userName);

		if (matInspObj == null) {
			model.addAttribute("failmessage", "Material Characteristics added failed");
			logger.debug("*** adding material characteristics failed ***");
			logger.debug("*** getMatInspFormData() method execution started ***");
			
			return AppConstants.MATERIAL_VIEW;
		}

		String mId = matInspObj.getMaterial().getMaterialId();
		logger.debug("*** getMatInspFormData() method execution started ***");
		return "redirect:/zettaInsp/addMatInsp/" + mId;

	}

	@GetMapping("/addMatInsp/{mId}")
	public String getaddParametersPage(@PathVariable("mId") String mId, HttpSession session, Model model) {
		logger.debug("*** method of PRG for double posting started ***");	
		if (!SessionUtils.isUserLoggedIn(session)) {
			model.addAttribute("message", "Please Login");
			logger.error("*** the user verification failed ***");
			logger.error("*** the user redirect to login page ***");
			return AppConstants.LOGIN_VIEW;
		}

		String userName = SessionUtils.getLoggedInUsername(session);
		model.addAttribute("userName", userName);

		model.addAttribute("message", "Material charcaters added Successfully");
		Material material = materialService.getMaterialById(mId);
		MaterialInspection matInsp = new MaterialInspection();
		matInsp.setMaterial(material);
		model.addAttribute("matInsp", matInsp);
		logger.debug("*** method of PRG for double posting completed ***");	
		logger.debug("*** the form displays the same for adding new charcateristics ***");	
		return AppConstants.ADDMATR_PARAMETERS_VIEW;

	}
	@GetMapping("/viewmaterial/{mId}")
	public String getMaterialData(@PathVariable("mId") String mId, HttpSession session, Model model) {
		logger.debug("*** getMaterialData() method execution started ***");
		if (!SessionUtils.isUserLoggedIn(session)) {
			model.addAttribute("message", "Please Login");
			logger.error("*** the user verification failed ***");
			logger.error("*** the user redirect to login page ***");
			return AppConstants.LOGIN_VIEW;
		}

		String userName = SessionUtils.getLoggedInUsername(session);
		model.addAttribute("userName", userName);
		Material material = materialService.getMaterialById(mId);

		if(material == null) {
			model.addAttribute("failmessage", "the Material id "+mId+" is not in the list");
			logger.debug("*** the material details not available with the particular material id ***");
			logger.debug("*** getMaterialData() method execution completed ***");
			return AppConstants.MATERIAL_VIEW;
		}
		material = materialService.getFiletedMaterial(material);

		model.addAttribute("material", material);
		logger.debug("*** getMaterialData() method execution completed ***");
		return AppConstants.VIEWMATERIAL_VIEW;
	}
	@GetMapping("/searchMaterial")
	public String getMaterialSearchPage(Model model,MaterialSearchCriteria criteria,HttpSession session) {
		logger.debug("*** getMaterialSearchPage() method execution started ***");
		if (!SessionUtils.isUserLoggedIn(session)) {
            model.addAttribute("message", "Please Login");
            logger.error("*** the user verification failed ***");
			logger.error("*** the user redirect to login page ***");
            return AppConstants.LOGIN_VIEW;
        }
	 String userName = SessionUtils.getLoggedInUsername(session);
        model.addAttribute("userName", userName);

		model.addAttribute("search",true);
		model.addAttribute("criteria", criteria);
		logger.debug("*** getMaterialSearchPage() method execution completed ***");
		return AppConstants.SEARCHMATERIAL_VIEW;
	}
	@PostMapping("/getmatdetails")
	public String getMaterials(Model model,MaterialSearchCriteria criteria,HttpSession session) {
		logger.debug("*** getMaterials() method execution started ***");
		if (!SessionUtils.isUserLoggedIn(session)) {
            model.addAttribute("message", "Please Login");
            logger.error("*** the user verification failed ***");
			logger.error("*** the user redirect to login page ***");
            return AppConstants.LOGIN_VIEW;
        }
	 String userName = SessionUtils.getLoggedInUsername(session);
        model.addAttribute("userName", userName);
        
        if(!criteria.getMaterialId().isBlank()) {
        	
        	Material material = materialService.getMaterialById(criteria.getMaterialId());
        	material = materialService.getFiletedMaterial(material);

    		model.addAttribute("material", material);
    		logger.debug("*** getMaterials() method execution completed ***");
    		return AppConstants.VIEWMATERIAL_VIEW;
    
        }
        
      List<Material> materialList = materialService.getAllMaterialsBasedonSearchCriteria(criteria);
        
        model.addAttribute("materialList", materialList);
        
        if(materialList.size()==0) {
        	model.addAttribute("failmessage","No materials present for given search criteria");
        	logger.debug("*** the fail message ***");
        }
        logger.debug("*** getMaterials() method execution completed ***");
		return AppConstants.MATERIAL_VIEW;
		
	}

}
