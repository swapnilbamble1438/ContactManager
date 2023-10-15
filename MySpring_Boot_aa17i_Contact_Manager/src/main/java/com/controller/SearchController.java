package com.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.dao.ContactRepository;
import com.dao.UserrRepository;
import com.model.Contact;
import com.model.Userr;

@RestController
public class SearchController {
	
	@Autowired
	private UserrRepository userrRepo;
	
	@Autowired
	private ContactRepository contRepo;
	
	
	// search handler
	@GetMapping("/search/{keyword}")
	public ResponseEntity<?> search(@PathVariable("keyword") String keyword,Principal p)
	{
		
		
		Userr userr = userrRepo.getUserrByUserrName(p.getName());
		
	List<Contact> contacts = contRepo.findByUidAndKeyword(userr.getUid(),keyword);
		
		return ResponseEntity.ok(contacts);
	
	}
	

}
