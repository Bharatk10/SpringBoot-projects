package com.zettamine.boot.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
import com.zettamine.boot.entity.Vendor;
import com.zettamine.boot.service.IVendorService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/zettaInsp")
public class VendorController {

	private IVendorService vendorService;

	public VendorController(IVendorService vendorService) {
		super();
		this.vendorService = vendorService;
	}

	private static final Logger logger = LoggerFactory.getLogger(VendorController.class);

	@PostMapping("/vendor")
	public ResponseEntity<?> addVendor(@Valid @RequestBody Vendor vendor) {
		logger.debug("*** addVendorDetails() method execution started ***");

		if (vendor.getVendorId() != null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(ValidationConstants.SAVE_ID_ERROR);
		}
		Vendor vendorObj = vendorService.saveVendor(vendor);
		logger.debug("*** addVendorDetails() method execution completed ***");
		
		return ResponseEntity.status(HttpStatus.OK).body(vendorObj);

	}

	@GetMapping("/vendors")
	public ResponseEntity<?> getallVendors() {
		logger.debug("*** getAllVendors() method execution started ***");

		List<Vendor> vendorList = vendorService.getAllVendors();

		if (vendorList.isEmpty()) {
			logger.debug("*** getAllVendors() method execution completed ***");
			return ResponseEntity.ok(ValidationConstants.VENDOR_LIST_MESSAGE);
		}
		logger.debug("*** getAllVendors() method execution completed ***");
		return ResponseEntity.ok(vendorList);
	}

	@DeleteMapping("/vendor/{id}")
	public ResponseEntity<?> deleteVendor(@Valid @PathVariable("id") String id) {
		logger.debug("*** deleteVendor() method execution started ***");
		
		 if (!id.matches("\\d+")) {
		        logger.info("Invalid vendorId format");
		        logger.debug("*** deleteVendor() method execution completed ***");
		        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ValidationConstants.VENDOR_ID_ERROR);
		    }
		
	
		Vendor vendorObj = vendorService.editVendorStatus(Integer.parseInt(id));

		if (vendorObj!= null) {
			logger.debug("*** deleteVendor() method execution completed ***");
			logger.info("vendor deleted Successfully");
			return ResponseEntity.ok(AppConstants.VENDOR_DELETE_MESSAGE);
		} else {

			logger.info("vendor deleted Failed");
			logger.debug("*** deleteVendor() method execution completed ***");
			String errorMessage = ValidationConstants.VENDOR_NOT_PRESENT_ID_ERROR+id+"\n";
			return ResponseEntity.status(HttpStatus.GONE).body(errorMessage+AppConstants.VENDOR_DELETE_ERROR);
		}

	}
	
	@PutMapping("/vendor/{id}")
	public ResponseEntity<?> updateVendor(@Valid @PathVariable("id") String id,@RequestBody Vendor vendor) {
		logger.debug("*** updateVendor() method execution started ***");
		
		if (vendor.getVendorId() != Integer.parseInt(id)) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(ValidationConstants.UPDATE_ID_ERROR);
		}
		
		 if (!id.matches("\\d+")) {
		        logger.info("Invalid vendorId format");
		        logger.debug("*** updateVendor() method execution completed ***");
		        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ValidationConstants.VENDOR_ID_ERROR);
		    }
		
		Vendor vendorObj = vendorService.updateVendor(vendor);
		if(vendorObj==null) {
			String errorMessage = ValidationConstants.VENDOR_NOT_PRESENT_ID_ERROR+id+"\n";
			logger.debug("*** updateVendor() method execution completed ***");
			return ResponseEntity.status(HttpStatus.GONE).body(errorMessage+AppConstants.VENDOR_UPDATE_ERROR);
		}
		logger.debug("*** updateVendor() method execution completed ***");

		return ResponseEntity.status(HttpStatus.OK).body(vendorObj);

	}
	@GetMapping("/vendor/{id}")
	public ResponseEntity<?> getVendorDetails(@Valid @PathVariable("id") String id) {
		logger.debug("*** getVendorDetails() method execution started ***");
		
		 if (!id.matches("\\d+")) {
		        logger.info("Invalid vendorId format");
		        logger.debug("*** getVendorDetails() method execution completed ***");
		        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ValidationConstants.VENDOR_ID_ERROR);
		    }
		
	Vendor vendorObj = vendorService.getVendorById(Integer.parseInt(id));
	
		if (vendorObj!= null) {
			logger.debug("*** getVendorDetails() method execution completed ***");
			
			return ResponseEntity.ok(vendorObj);
		} else {
			logger.debug("*** getVendorDetails() method execution completed ***");
			String errorMessage = ValidationConstants.VENDOR_NOT_PRESENT_ID_ERROR+id;
			return ResponseEntity.status(HttpStatus.GONE).body(errorMessage);
		}

	}
	
}
