package com.dgh.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dgh.model.SingleSignonUserModel;
import com.dgh.service.MainService;



@Controller
@RequestMapping("/user")
public class UserController
{
	@Autowired
	MainService mainService;
	
	@RequestMapping(value = "/changepassword", method =  RequestMethod.GET)
	public ModelAndView changepassword(ModelMap model,HttpSession session) {
		return new ModelAndView("changepassword",model);
		
	}
	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public ModelAndView resetPassword(@ModelAttribute SingleSignonUserModel ssouserModel, BindingResult result,HttpSession session,ModelMap model) {
				
		System.out.println("in controller- reset password");
		SingleSignonUserModel loggedInUser=mainService.getLoggedInUserDetails(((SingleSignonUserModel)session.getAttribute("UserDetail")).getUsername());
		
		return new ModelAndView("changepassword");
		
	}
	
	
	@RequestMapping(value = "/forgotPassword", method =  RequestMethod.GET)
	public String forgotPassword(ModelMap model) {
		return "forgotPassword";
		
	}
	
	
}