package com.zettamine.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WelcomeController {
	
	@GetMapping("/welcome")
	public ModelAndView getwelcomeMessage() {
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("message", "Welcome to Zettamine");
		
		mav.setViewName("welcome");
		
		return mav;
		
	}
}
