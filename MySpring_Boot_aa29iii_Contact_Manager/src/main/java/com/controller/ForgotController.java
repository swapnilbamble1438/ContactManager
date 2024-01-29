package com.controller;

import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dao.UserrRepository;
import com.helper.Message;
import com.model.Email;
import com.model.Userr;
import com.service.EmailService;

@Controller
public class ForgotController {
	
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private UserrRepository userRepo;

	
	// for generating otp 
	Random random =	new Random();
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	//for password form handler
	@RequestMapping("/forgot")
	public String forgot()
	{
		
		return "forgot";
	}
	
	
	@PostMapping("/enterotp")
	public String sendotp(@RequestParam("mail") String mail,HttpSession session)
	{
		System.out.println(mail);
		
		if(userRepo.getUserrByUserrName(mail) != null)
		{
			
		
		//generating otp of 4 digit
		
		int otp = random.nextInt((9999-100) + 1) + 10;
				
				
				;
		
//		String message = "Your OTP is: "+ otp;
		String message =""
				+ "<div style='border:1px solid #e2e2e2; padding:20px'>"
				+ "<h2>"
				+ "Your OTP is: "
				+ "<b>" + otp
				+ "</b>"
				+ "</h2>"
				+ "</div>";
		System.out.println("==========================================================================================================================================");
		System.out.println("OTP: " + otp);
		System.out.println("==========================================================================================================================================");

		
		//write code for sending otp to email...
		
		Email email = new Email();
		email.setTo(mail);
		email.setMessage(message);
		email.setSubject("Please Verify OTP");
		
		emailService.sendEmail(email);
		
		
		// sending otp and email for verifying
		session.setAttribute("myotp", otp);
		session.setAttribute("mail", mail);
		
	
		return "enterotp";
		}
		else {
			session.setAttribute("message",new Message("Not a registered EmailId, Please enter correct EmailId !!","danger"));
			return "forgot";
		}
	}
	
	
	//verify otp
	@PostMapping("/verifyotp")
	public String verifyotp(@RequestParam("otp") int otp,HttpSession session)
	{
		int myotp = (int)session.getAttribute("myotp");
		String mail = (String)session.getAttribute("mail");
		
		if(myotp == otp)
		{
			Userr userr = userRepo.getUserrByUserrName(mail);
			
			if(userr == null)
			{
				session.setAttribute("message",new Message("Not a registered EmailId, Please enter correct EmailId !!","danger"));
				return "forgot";
			}
			else
			{
				
				return "enternewpassword";
			}
			
			
		}
		else {
			
			session.setAttribute("message",new Message("You have entered wrong OTP !","danger"));

			return "enterotp";
		}
		
		
		
	}
	
	
	// change password
	@PostMapping("/changepassword")
	public String changePassword(@RequestParam("newpassword") String newpassword,
			HttpSession session)
	{
		String mail = (String)session.getAttribute("mail");
		Userr userr = userRepo.getUserrByUserrName(mail);
		
		userr.setUpassword(bCryptPasswordEncoder.encode(newpassword));
		userRepo.save(userr);
		
	//	session.setAttribute("message",new Message("Your Password has been changed successfully !","success"));

		
		
		return "redirect:/signin?changed=Your Password has been changed successfully...";
	}
	
	
}
