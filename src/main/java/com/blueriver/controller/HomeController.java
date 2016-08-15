package com.blueriver.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.blueriver.beans.RepositoryUtilityImpl;
import com.blueriver.config.Authentication;

@Controller
public class HomeController {
	public final static String BUILD_PROP_FILE = "build.properties";
	
	@RequestMapping(value={"/"}, method=RequestMethod.GET)
	public String rootPage() {
//		return "forward:home"; // "forward" is different than "redirect"
		return "redirect:home"; // "forward" is different than "redirect"
	}
	
	@RequestMapping(value={"home"}, method=RequestMethod.GET)
	public String homePage(Model m) {
		m.addAttribute("tenant", TenantController.getTenantResponse());
		return "HomePage";
	}
	
	@RequestMapping(value = { "about" }, method = RequestMethod.GET)
	public ModelAndView aboutPage() {
		ModelAndView model = new ModelAndView();
		model.addObject("buildDate", getBuildDate());
		model.setViewName("AboutPage");
		return model;
	}

	@RequestMapping(value = { "users" }, method = RequestMethod.GET)
	public ModelAndView usersPage() {
		ModelAndView model = new ModelAndView();
		model.addObject("usersList", getUsersList());
		model.setViewName("UsersPage");
		return model;
	}

	/*
	 * TODO: This method needs to change to obtain the tenant's logo from
	 * its own database.
	 */
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public ModelAndView login(
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		
		model.setViewName("Login");
		return model;
	}
	
	private List<String> getUsersList() {
		List<String> usersList = new ArrayList<String>();
		HashMap<String, String> users = Authentication.getUsers();
		for (String username : users.keySet()) {
			String password = users.get(username);
			usersList.add(username + ", " + password);
		}
		return usersList;
	}
	
	private String getBuildDate() {
		Properties props = new Properties();
		try {
			props.load(RepositoryUtilityImpl.class.getClassLoader().getResourceAsStream("/"+BUILD_PROP_FILE));
		} 
		catch (IOException e) {
			System.out.println("Error trying to read the Properties file "+BUILD_PROP_FILE);
			e.printStackTrace();
		}

		// compose jdb.url 
		String buildDate = props.getProperty("build.date");
		return buildDate;
	}
	
}
