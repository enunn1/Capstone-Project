package org.ethannunn.gamereviews.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/")
public class MainController {
	
	@GetMapping(path="/")
	public String showHomePage() {
		return "index";
	}
	
	@GetMapping(path="/about")
	public String showAboutPage() {
		return "about";
	}

	
	@GetMapping(path="/login")
	public String showLoginPage() {
		return "login";
	}

}
