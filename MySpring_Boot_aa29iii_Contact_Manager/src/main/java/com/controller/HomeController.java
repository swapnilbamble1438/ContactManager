package com.controller;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dao.UserrRepository;
import com.helper.Message;
import com.model.Userr;



@Controller
public class HomeController {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	@Autowired
	private UserrRepository userrRepository;
	
	
		@RequestMapping("/")
		public String home(Model model)
		{
			model.addAttribute("title","Home - Smart Contact Manager");
			return "home";
		}
		
		

		@RequestMapping("/about")
		public String about(Model model)
		{
			model.addAttribute("title","About - Smart Contact Manager");
			return "about";
		}
		

		@RequestMapping("/signin")
		public String signin(Model model)
		{
			model.addAttribute("title","Signin - Smart Contact Manager");
			return "signin";
		}
		
		@RequestMapping("/signup")
		public String signup(Model model)
		{
			model.addAttribute("title","Signup - Smart Contact Manager");
			model.addAttribute("userr",new Userr());
			
			
			return "signup";
		}
		
		
		// handler for registering user   //Note: BindingResult should be always declare next to @ModelAttribute 
		@RequestMapping(value="/do_signup",method = RequestMethod.POST)
		public String signupUser(@Valid @ModelAttribute("userr") Userr userr,BindingResult r,Model model, @RequestParam(value="agreement",defaultValue= "false")boolean agreement,HttpSession session)
		{
			try {
				if(!agreement)
				{
					System.out.println("You have not agreed the Terms and Conditions");
					throw new Exception("You have not agreed the Terms and Conditions");
				}
				
				if(r.hasErrors())
				{
					model.addAttribute("userr",userr);
					System.out.println(r);
					return "signup";
				}
				
				
				if(userrRepository.getUserrByUserrName(userr.getUemail()) != null)
				{
					System.out.println("Same Email Id is already registered. Try using different Email Id !");
					throw new Exception("Same Email Id is already registered. Try using different Email Id !");
				}
					
				
				userr.setUrole("ROLE_USER");
				userr.setUenabled(true);
				userr.setUimage("default.png");
				userr.setUpassword(passwordEncoder.encode(userr.getUpassword()));
				
				
				
				System.out.println("Agreement " + agreement);
				System.out.println("Userr " + userr);
				
			Userr result = this.userrRepository.save(userr);
				
				// it means it will return the data on the same page
				model.addAttribute("userr",new Userr());
				
				session.setAttribute("message",new Message("Successfully registered !!","alert-success"));
				return "signup";
				
				
			}catch (Exception e)
			{
				e.printStackTrace();
				model.addAttribute("userr",userr);
				session.setAttribute("message",new Message("Something went wrong !!"+ e.getMessage(),"alert-danger"));
				return "signup";
			}
			
			
		}
		
		

}
