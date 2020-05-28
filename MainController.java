package com.dgh.controller;

import java.io.FileReader;
import java.io.Serializable;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.dgh.model.SingleSignonUserModel;
import com.dgh.service.MainService;
import com.opencsv.CSVReader;

@Controller

public class MainController implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Autowired
	MainService mainService;
	
	@RequestMapping(value = "/forgotPassword", method = RequestMethod.GET)
	public String forgotPassword(ModelMap model) {
		return "forgotPassword";
	}
	
	@RequestMapping(value = "/sendPasswordResetLink",method = RequestMethod.POST)
	public ModelAndView sendPasswordResetLink(@ModelAttribute SingleSignonUserModel sendLinkForm, BindingResult result,ModelMap model) {
		
		if (result.hasErrors()) {
			System.out.println(result.toString());
		} else  { 
			System.out.println("in main controller:"+sendLinkForm.getUsername());
			boolean test=mainService.sendPasswordResetLink(sendLinkForm.getUsername());
		
			if (test)
			{	
				model.addAttribute("successMsg", "Password Reset Link sent successfully. ");
				
			
			
			}
			else
			{
				 model.addAttribute("errorMsg", "Password Reset link could not be sent. Error encountered.");
				
			}
		}
		 return new ModelAndView("forgotPassword");
	}
	
	@RequestMapping(value = "/changePassword/{username}/{recoveryToken}",method = RequestMethod.GET)
	public ModelAndView changePassword(@PathVariable("username")String username,@PathVariable("recoveryToken")String recoveryToken,@ModelAttribute SingleSignonUserModel changePassword,BindingResult result,HttpSession session,ModelMap model){
		
		
		if (result.hasErrors()) {
			System.out.println(result.toString());
		} else  {
			
			
					
			System.out.println("Hello there!");
			session.setAttribute("username", username);
			session.setAttribute("recoveryToken", recoveryToken);
		
		}
		return new ModelAndView("changeforgotpassword");
	}
	
	  @RequestMapping(value = "/resetPassword",method = RequestMethod.POST)
	public ModelAndView resetPassword(@ModelAttribute SingleSignonUserModel ssouserModel,BindingResult result,HttpSession session,ModelMap model){
		
		
		if (result.hasErrors()) {
			System.out.println(result.toString());
		} else  {
			
			if(mainService.checkIfUserRequestedPasswordRecovery(session.getAttribute("username").toString()));
				
				ssouserModel.setUsername(session.getAttribute("username").toString());
			
					if(mainService.changePassword(ssouserModel,session))
						model.addAttribute("successMsg", "Password Changed successfully. ");
					else
						 model.addAttribute("errorMsg", "Password could not be changed. Error encountered.");
					
			
		}
		return new ModelAndView("changeforgotpassword");
	}
	
	  @RequestMapping(value = "/adduser", method = RequestMethod.GET)
		public ModelAndView adduser(ModelMap model, HttpSession session) {
			
			return new ModelAndView("adduser");
		}
	  
	  @RequestMapping(value = "/upload-csv-file", method = RequestMethod.POST)
	    public String uploadCSVFile(@RequestParam("file") MultipartFile file, Model model) {

	        // validate file
	        if (file.isEmpty()) {
	            model.addAttribute("message", "Please select a CSV file to upload.");
	            model.addAttribute("status", false);
	        } else {


	             
	            	CSVReader reader = null;  
	            	try  
	            	{  
	            	reader = new CSVReader(new FileReader("F:\\users.csv"));    
	            	String [] nextLine;  
	            	//read one line at a time  
	            	while ((nextLine = reader.readNext()) != null)  
	            	{  
	            	for(String token : nextLine)  
	            	{  
	            	System.out.println(token);  
	            	}  
	            	System.out.print("\n");  
	            	}
	            	
	            } catch (Exception ex) {
	                model.addAttribute("message", "An error occurred while processing the CSV file.");
	                model.addAttribute("status", false);
	                
	                
	                    final String uri = "http://localhost:8080/springrest/error";
	                    RestTemplate restTemplate = new RestTemplate();
	                    String result = restTemplate.getForObject(uri, String.class);

	                    System.out.println(result);
	                     
	                
	            }
	        }

	        return "file-upload-status";
	    }
	    
		
		@RequestMapping(value = "/submituser",method = RequestMethod.POST)
		public ModelAndView submituser(@ModelAttribute SingleSignonUserModel ssouserModel, BindingResult result,ModelMap model,HttpSession session) {
			
			if (result.hasErrors())
			{
				System.out.println(result.toString());
			}
			else  
			{ 
				boolean emailUniqueOrNot=mainService.checkIfEmailUnique(ssouserModel.getUsername());
				if(!emailUniqueOrNot)
				{
					Integer userid=mainService.registerUser(ssouserModel);
				
					if (userid!=0)
					{	
						model.addAttribute("successMsg", "Form successfully submitted. An email with credentials has been sent to the user.");
					
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
			
			return new ModelAndView("dashboardadmin");
		}

		@RequestMapping(value = "/sendcredentials", method = RequestMethod.GET)
		public ModelAndView sendcredentials(ModelMap model, HttpSession session) {
			
			
			return new ModelAndView("sendcredentials");
		}
}
