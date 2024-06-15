package com.zettamine.boot.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zettamine.boot.constants.AppConstants;
import com.zettamine.boot.constants.ValidationConstants;
import com.zettamine.boot.entity.Material;
import com.zettamine.boot.entity.MaterialInspection;
import com.zettamine.boot.models.MaterialSearchCriteria;
import com.zettamine.boot.service.IMaterialInspService;
import com.zettamine.boot.service.IMaterialService;
import com.zettamine.boot.utility.SessionUtils;
import com.zettamine.boot.utility.StringUtilis;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RestController
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

	@PostMapping("/material")
	public ResponseEntity<?> addMaterialDetails(@Valid @RequestBody(required = false) Material material) {
		logger.debug("*** getAddMaterialDetails() method execution started ***");

		String id = materialService.materialIdGenerator();
		System.out.println(id);
		Material materialObj = materialService.saveMaterialById(material, id);

		if (material == null) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ValidationConstants.MATERIAL_ERROR);
		}

		if (materialObj != null) {
			logger.debug("*** getAddMaterialDetails() method execution started ***");
			return ResponseEntity.status(HttpStatus.OK).body(materialObj);
		} else {

			logger.debug("*** getAddMaterialDetails() method execution completed ***");
			logger.debug("*** displaing the error messgage while adding material");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ValidationConstants.MATERIAL_SAVE_ERROR);
		}
	}

	@PutMapping("/material")
	public ResponseEntity<?> updateMaterialDetails(@Valid @RequestBody(required = false) Material material) {
		logger.debug("*** updateMaterialDetails() method execution started ***");

		if (material == null) {
			logger.debug("*** updateMaterialDetails() method execution completed ***");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ValidationConstants.MATERIAL_ERROR);
		}

		if (material.getMaterialId() == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(ValidationConstants.MATER_ID_ERROR);
		}

		String id = material.getMaterialId();

		if (!id.matches(ValidationConstants.MATERIAL_ID_PATTERN)) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ValidationConstants.MATERIAL_ID_ERROR);
		}

		Material matObj = materialService.getMaterialById(id);

		if (matObj == null) {
			return ResponseEntity.status(HttpStatus.GONE).body(ValidationConstants.MAT_ID_ERROR + " " + id);
		}

		Material materialObj = materialService.saveMaterialById(material, material.getMaterialId());

		if (materialObj != null) {
			logger.debug("*** updateMaterialDetails() method execution completed ***");
			return ResponseEntity.status(HttpStatus.OK).body(materialObj);
		} else {

			logger.debug("*** updateMaterialDetails() method execution completed ***");
			logger.debug("*** displaing the error messgage while adding material");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ValidationConstants.MATERIAL_UPDATE_ERROR);
		}
	}

	@GetMapping("/materials")
	public ResponseEntity<?> getAllMaterials() {
		logger.debug("*** getAllMaterials() method execution started ***");

		List<Material> materialList = materialService.getAllMaterials();

		if (materialList.isEmpty()) {
			logger.debug("*** getAllMaterials() method execution completed ***");
			return ResponseEntity.ok(ValidationConstants.MATERIAL_LIST_MESSAGE);
		}
		logger.debug("*** getAllMaterials() method execution completed ***");
		return ResponseEntity.ok(materialList);

	}

	@GetMapping("/material/{mId}")
	public ResponseEntity<?> getMaterialById(@PathVariable(required = false) String mId) {
		logger.debug("*** getMaterialById() method execution started ***");

		if (mId!=null) {
			mId = StringUtilis.processString(mId);
			if(mId.length()==0) {
				logger.debug("*** getMaterialById() method execution completed ***");
				return ResponseEntity.status(HttpStatus.CONFLICT).body(ValidationConstants.MATER_ID_ERROR);
			}
		}

		if (!mId.matches(ValidationConstants.MATERIAL_ID_PATTERN)) {
			logger.debug("*** getMaterialById() method execution completed ***");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ValidationConstants.MATERIAL_ID_ERROR);
		}

		Material matObj = materialService.getMaterialById(mId);

		if (matObj == null) {
			logger.debug("*** getMaterialById() method execution completed ***");
			return ResponseEntity.status(HttpStatus.GONE).body(ValidationConstants.MAT_ID_ERROR + " " + mId);
		}
		
		logger.debug("*** getMaterialById() method execution completed ***");
		return ResponseEntity.ok(matObj);

	}

	@PostMapping("/addcharateristics/{mId}")
	public ResponseEntity<?> getAddCharacteristics(@PathVariable("mId") String mId,@Valid @RequestBody MaterialInspection matInsp) {
		logger.debug("*** getAddCharacteristics() method execution started ***");
		
		if (mId!=null) {
			mId = StringUtilis.processString(mId);
			if(mId.length()==0) {
				logger.debug("*** getMaterialById() method execution completed ***");
				return ResponseEntity.status(HttpStatus.CONFLICT).body(ValidationConstants.MATER_ID_ERROR);
			}
		}
		if (!mId.matches(ValidationConstants.MATERIAL_ID_PATTERN)) {
			logger.debug("*** getMaterialById() method execution completed ***");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ValidationConstants.MATERIAL_ID_ERROR);
		}
		Material matObj = materialService.getMaterialById(mId);

		if (matObj == null) {
			logger.debug("*** getMaterialById() method execution completed ***");
			return ResponseEntity.status(HttpStatus.GONE).body(ValidationConstants.MAT_ID_ERROR + " " + mId);
		}
		
		matInsp.setMaterial(matObj);
		
		MaterialInspection matInspObj = matInspService.saveMaterialInsp(matInsp);
		
		if(matInspObj == null) {
			
			return ResponseEntity.status(HttpStatus.CONFLICT).body(ValidationConstants.MATERIAL_CHARACTERS_SAVE_ERROR);
		}
		
		logger.debug("*** getAddCharacteristics() method execution started ***");
		return ResponseEntity.ok(matInspObj);
	}
	@GetMapping("/materialcharacteristics/{chId}")
	public ResponseEntity<?> getMaterialCharcateristicsById(@PathVariable(required = false) String chId) {
		logger.debug("*** getMaterialCharcateristicsById() method execution started ***");

		if (chId!=null) {
			chId = StringUtilis.processString(chId);
			if(chId.length()==0) {
				logger.debug("*** getMaterialCharcateristicsById() method execution completed ***");
				return ResponseEntity.status(HttpStatus.CONFLICT).body(ValidationConstants.CHAN_ID_ERROR);
			}
		}

		if (!chId.matches(ValidationConstants.CHANNEL_ID_PATTERN)) {
			logger.debug("*** getMaterialCharcateristicsById() method execution completed ***");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ValidationConstants.CHANNEL_ID_ERROR);
		}
		
		Integer id = Integer.parseInt(chId);

	 MaterialInspection matInspObj = matInspService.getMaterialInspStatus(id);
		

		if (matInspObj == null) {
			logger.debug("*** getMaterialCharcateristicsById() method execution completed ***");
			return ResponseEntity.status(HttpStatus.GONE).body(ValidationConstants.MATCH_ID_ERROR + " " + chId);
		}
		
		logger.debug("*** getMaterialCharcateristicsById() method execution completed ***");
		return ResponseEntity.ok(matInspObj);

	}

	
	

	
}
