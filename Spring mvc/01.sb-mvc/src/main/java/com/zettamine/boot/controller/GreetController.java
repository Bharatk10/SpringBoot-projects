package com.zettamine.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GreetController {
	
	@GetMapping({"/greet","/greetings","/greets"})
	public ModelAndView getwelcomeMessage() {
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("message", "Good Morning");
		
		mav.setViewName("welcome");
		
		return mav;
		
	}
}
