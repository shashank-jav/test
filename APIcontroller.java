package com.dgh.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dgh.model.SSOparameters;
import com.dgh.model.SingleSignonUserModel;
import com.dgh.service.MainService;

@RestController
@RequestMapping("/api")
public class APIcontroller {
	@Autowired
	MainService mainService;

	@RequestMapping(path = "/addssouser",consumes = "application/json", produces = "application/json", method = RequestMethod.POST)
	public ResponseEntity<String> addssouser(@RequestBody SSOparameters params,	HttpServletRequest req, ModelMap model) {

		
		SingleSignonUserModel ssouser=new SingleSignonUserModel();
		
		SingleSignonUserModel existinguser=mainService.ifemailalreadyregistered(params.getEmail());
		
		if (existinguser.getId()!=null)
		{	
				
		}
		else
		{	
				ssouser.setFirstName(params.getFirstname());
				ssouser.setLastName(params.getLastname());
				ssouser.setMobile(params.getMobile());
				ssouser.setUsername(params.getEmail());
				
			
				mainService.registerUser(ssouser);
		}
			
		
		return new ResponseEntity(HttpStatus.OK);

	}

}