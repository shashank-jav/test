package com.dgh.controller;
import java.io.Serializable;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dgh.model.SingleSignonUserModel;
import com.dgh.service.MainService;


@Controller
public class RegistrationController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	MainService mainService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    binder.setAutoGrowCollectionLimit(768);
	}
	
	
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register(ModelMap model) {
		
		return new ModelAndView("/register", model);
	}
	
	
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(ModelMap model, @RequestParam(required=false) String error) {
		
		return new ModelAndView("/index", model);
	}
	
	
	@RequestMapping(value = "/saveUser",method = RequestMethod.POST)
	public ModelAndView saveUser(@ModelAttribute SingleSignonUserModel registrationForm, BindingResult result,ModelMap model,HttpSession session) {
		
		if (result.hasErrors())
		{
			System.out.println(result.toString());
		}
		else  
		{ 
			boolean emailUniqueOrNot=mainService.checkIfEmailUnique(registrationForm.getUsername());
			if(!emailUniqueOrNot)
			{
				Integer userid=mainService.registerUser(registrationForm);
			
				if (userid!=0)
				{	
					model.addAttribute("successMsg", "Form successfully submitted.Check registered email for credentials.");
				
				}
				else
				{
					 model.addAttribute("errorMsg", "Your form submission contains errors.");
					
				}
			}
		
			else
			{
				model.addAttribute("errorMsg", "This email id is already registered with us.Choose a unique email id");
			}
		}
		 return new ModelAndView("register");
	}
	
}
