package com.config;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.model.Userr;

public class CustomUserDetails implements UserDetails {

	
	private Userr userr;
	
	
	public CustomUserDetails(Userr userr) {
		this.userr = userr;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

	SimpleGrantedAuthority simpleGrantedAuthority =	new SimpleGrantedAuthority(userr.getUrole());
		
		
		return List.of(simpleGrantedAuthority);
	}

	@Override
	public String getPassword() {

		return userr.getUpassword();
	}

	@Override
	public String getUsername() {

		return userr.getUemail();
	}

	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {

		return true;
	}

}
