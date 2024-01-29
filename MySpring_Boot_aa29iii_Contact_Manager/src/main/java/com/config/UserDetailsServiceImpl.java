package com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.dao.UserrRepository;
import com.model.Userr;

public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserrRepository userrRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		
		//fetching user from 
		
	Userr userr = userrRepository.getUserrByUserrName(username);

		if(userr == null)
		{
			throw new UsernameNotFoundException("Could not found user !!");
			
		}
		CustomUserDetails customUserDetails = new CustomUserDetails(userr);
		
		return customUserDetails;
	}

}
