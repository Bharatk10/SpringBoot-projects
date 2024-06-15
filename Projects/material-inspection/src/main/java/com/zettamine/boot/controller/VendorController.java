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
import com.zettamine.boot.entity.State;
import com.zettamine.boot.entity.Vendor;
import com.zettamine.boot.service.IVendorService;
import com.zettamine.boot.utility.SessionUtils;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/zettaInsp")
public class VendorController {

	private IVendorService vendorService;

	public VendorController(IVendorService vendorService) {
		super();
		this.vendorService = vendorService;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(VendorController.class);

	@GetMapping("/vendor")
	public String getVendorPage(HttpSession session, Model model) {
		
		logger.debug("*** getVendorPage() method execution started ***");

		if (!SessionUtils.isUserLoggedIn(session)) {
			model.addAttribute("message", "Please Login");
			logger.error("*** the user verification failed ***");
			logger.error("*** the user redirect to login page ***");
			return AppConstants.LOGIN_VIEW;
		}

		String userName = SessionUtils.getLoggedInUsername(session);
		logger.info("*** the user is verified ***");
		model.addAttribute("userName", userName);
		logger.info("*** the user is redirect to vendor home page ***");
		logger.debug("*** getVendorPage() method execution completed ***");
		return AppConstants.VENDOR_VIEW;
	}

	@GetMapping("/addVendor/{id}")
	public String getAddVendorPage(@PathVariable("id") String id, HttpSession session, Model model) {
		logger.debug("*** getAddVendorPage() method execution started ***");
		if (!SessionUtils.isUserLoggedIn(session)) {
			model.addAttribute("message", "Please Login");
			logger.error("*** the user verification failed ***");
			logger.error("*** the user redirect to login page ***");
			return AppConstants.LOGIN_VIEW;
		}

		String userName = SessionUtils.getLoggedInUsername(session);
		logger.info("*** the user is verified ***");
		model.addAttribute("userName", userName);
		if (id.equals("add")) {
			logger.info("*** the user is redirect to add vendor page ***");
			model.addAttribute("vendor", new Vendor());
			model.addAttribute("states", State.getAllStates());
			model.addAttribute("message", "Add Vendor Form");
		} else {
			logger.info("*** the user is redirect to update vendor page ***");
			Vendor vendor = vendorService.getVendorById(Integer.parseInt(id));
			model.addAttribute("message", "Update Vendor Form of Vendor Id: " + id);
			model.addAttribute("states", State.getAllStates());
			model.addAttribute("vendor", vendor);

		}
		logger.debug("*** getAddVendorPage() method execution completed ***");
		return AppConstants.ADDVENDOR_VIEW;
	}

	@PostMapping("/submitVendor")
	public String addVendorDetails(HttpSession session, Vendor vendor, Model model) {
		logger.debug("*** addVendorDetails() method execution started ***");
		if (!SessionUtils.isUserLoggedIn(session)) {
			model.addAttribute("message", "Please Login");
			logger.error("*** the user verification failed ***");
			logger.error("*** the user redirect to login page ***");
			return AppConstants.LOGIN_VIEW;
		}
		String userName = SessionUtils.getLoggedInUsername(session);
		model.addAttribute("userName", userName);

		System.out.println(vendor);
		if (vendor.getVendorId() == null) {
			Vendor vendorObj = vendorService.saveVendor(vendor);

			if (vendorObj!= null) {
				logger.debug("*** addVendorDetails() method execution completed ***");
				return "redirect:/zettaInsp/aVendor";
			} else {
				model.addAttribute("failmessage", "Vendor Added Failed Already the vendor details are present");
				logger.debug("*** loading vendor page for the failure  message while adding new vendor***");
				logger.debug("*** addVendorDetails() method execution completed ***");
				return AppConstants.VENDOR_VIEW;
			}

		} else {
			Vendor vendorObj = vendorService.saveVendor(vendor);
			if (vendorObj!= null) {
				logger.debug("*** addVendorDetails() method execution completed ***");
				return "redirect:/zettaInsp/uVendor";
			}

			else {
				model.addAttribute("message", "Vendor update Failed");
				logger.debug("*** addVendorDetails() method execution completed ***");
				logger.debug("*** loading vendor page for the failure  message while updating the vendor ***");
				return AppConstants.VENDOR_VIEW;
			}
		}

	}

	@GetMapping("/aVendor")
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
		model.addAttribute("message", "Vendor Added succesfully");
		 logger.debug("*** loading vendor page for the success message ***");
		  logger.debug("*** method of PGP for double completed ***");
		return AppConstants.VENDOR_VIEW;
	}

	@GetMapping("/vendors")
	public String getallVendors(Model model, HttpSession session) {
		logger.debug("*** getAllVendors() method execution started ***");
		if (!SessionUtils.isUserLoggedIn(session)) {
			model.addAttribute("message", "Please Login");
			logger.error("*** the user verification failed ***");
			logger.error("*** the user redirect to login page ***");
			return AppConstants.LOGIN_VIEW;
		}

		String userName = SessionUtils.getLoggedInUsername(session);
		model.addAttribute("userName", userName);
		List<Vendor> vendorList = vendorService.getAllVendors();
		model.addAttribute("vendorList", vendorList);
		logger.debug("*** getAllVendors() method execution completed ***");
		return AppConstants.VENDOR_VIEW;
	}

	@GetMapping("/uVendor")
	public String getvendorHomePage(HttpSession session, Model model) {
		logger.debug("*** method of PRG for double posting started ***");	

		if (!SessionUtils.isUserLoggedIn(session)) {
			model.addAttribute("message", "Please Login");
			logger.error("*** the user verification failed ***");
			logger.error("*** the user redirect to login page ***");
			return AppConstants.LOGIN_VIEW;
		}

		String userName = SessionUtils.getLoggedInUsername(session);
		model.addAttribute("userName", userName);
		model.addAttribute("message", "Vendor Updated succesfully");
		 logger.debug("*** loading vendor page for the success message ***");
		  logger.debug("*** method of PGP for double completed ***");
		return AppConstants.VENDOR_VIEW;

	}

	@GetMapping("/deletevendor/{id}")
	public String deleteDetails(@PathVariable("id") int id, HttpSession session, Model model, Vendor vendor) {
		logger.debug("*** deleteVendor() method execution started ***");
		Vendor vendorObj = vendorService.editVendorStatus(id);
		if (!SessionUtils.isUserLoggedIn(session)) {
			model.addAttribute("message", "Please Login");
			logger.error("*** the user verification failed ***");
			logger.error("*** the user redirect to login page ***");
			return AppConstants.LOGIN_VIEW;
		}
		String userName = SessionUtils.getLoggedInUsername(session);
		model.addAttribute("userName", userName);
		if (vendorObj.getVendorId() != null) {
			model.addAttribute("message", "Vendor Deleted succesfully");
			logger.debug("*** deleteVendor() method execution completed ***");
			logger.info("vendor deleted Successfully");
			return AppConstants.VENDOR_VIEW;
		} else {
			model.addAttribute("message", "Vendor Deleted Failed");
			logger.info("vendor deleted Failed");
			logger.debug("*** deleteVendor() method execution completed ***");
			return AppConstants.VENDOR_VIEW;
		}

	}

}
