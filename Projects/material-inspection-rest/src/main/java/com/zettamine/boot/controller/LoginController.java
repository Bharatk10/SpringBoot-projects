package com.zettamine.boot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zettamine.boot.constants.AppConstants;
import com.zettamine.boot.entity.User;
import com.zettamine.boot.service.IUserService;
import com.zettamine.boot.utility.SessionUtils;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/zettaInsp")
public class LoginController {

	private IUserService userService;

	public LoginController(IUserService userService) {
		super();
		this.userService = userService;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@PostMapping("/login")
	public String verifyUser(HttpServletRequest request, @RequestParam("userName") String userName,
			@RequestParam("password") String password,Model model) {
		logger.debug("*** verifyUser() method execution started ***");
		Boolean status = userService.validateUserCredintials(userName, password);

		if (status) {
			HttpSession session = request.getSession();
			User user = userService.getUserDetailsByUserName(userName);
			session.setAttribute("user", user);
			logger.info("*** the user is verified ***");
			return "redirect:home";
		}
		model.addAttribute("message","Incorrect Login credntials");
		logger.error("*** the user verification failed ***");
		logger.debug("*** verifyUser() method execution completed ***");
		return AppConstants.LOGIN_VIEW;
	}

	@GetMapping(value = { "/home", "" })
	public String getWelcomePage(HttpSession session,Model model ) {

		logger.debug("*** getWelcomePage() method execution started ***");
		 
		 if (!SessionUtils.isUserLoggedIn(session)) {
	            model.addAttribute("message", "Please Login");
	            logger.error("*** Session expired redirect to login page ***");
	            return AppConstants.LOGIN_VIEW;
	        }

		 String userName = SessionUtils.getLoggedInUsername(session);
	        model.addAttribute("userName", userName);
	        logger.debug("*** getWelcomePage() executed successfully ***");
			logger.debug("*** getWelcomePage() method execution completed ***");
		return "home";
	}
	@GetMapping(value= "/logout")
	public String logout(HttpServletRequest request) {
		logger.debug("*** logout() method execution started ***");
		HttpSession session = request.getSession(false);

	    if (session != null) {
	    	logger.info("*** the user is logout the session is null ***");
	    	
	        session.invalidate(); 
	    }
	    logger.debug("*** logout() method execution completed ***");
		return "redirect:/";
	}

}
