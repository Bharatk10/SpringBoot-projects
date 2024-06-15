package com.zettamine.boot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zettamine.boot.constants.AppConstants;
import com.zettamine.boot.entity.Material;
import com.zettamine.boot.entity.MaterialInspection;
import com.zettamine.boot.entity.Plant;
import com.zettamine.boot.service.IMaterialInspService;
import com.zettamine.boot.utility.SessionUtils;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/zettaInsp")
public class MaterialInspController {

	private IMaterialInspService matInspService;

	public MaterialInspController(IMaterialInspService matInspService) {
		super();
		this.matInspService = matInspService;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(MaterialInspController.class);
	
	@GetMapping("/deletematInsp/{id}")
	public String deleteDetails(@PathVariable("id") int id, HttpSession session, Model model,
			MaterialInspection MaterialInspection) {
		

		MaterialInspection matInspObj = matInspService.editMaterialInspStatusBtId(id);

	

		String userName = SessionUtils.getLoggedInUsername(session);
		model.addAttribute("userName", userName);

		Material material = matInspObj.getMaterial();

		return "redirect:/zettaInsp/viewmaterial/" + material.getMaterialId();

	}

}
