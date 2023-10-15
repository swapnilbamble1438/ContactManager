package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class MyConfig extends WebSecurityConfigurerAdapter{
	
	@Bean
	public UserDetailsService getUserDetailsServicie()
	{
		return new UserDetailsServiceImpl();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider()
	{
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(this.getUserDetailsServicie());
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		
		return daoAuthenticationProvider;
		
	}

	
	///configure method...
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.authenticationProvider(authenticationProvider());
		
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
		.authorizeRequests()
		.antMatchers("/admin/**").hasRole("ADMIN")
		.antMatchers("/user/**").hasRole("USER")
		.antMatchers("/**").permitAll()
		.and().formLogin().loginPage("/signin")
		.loginProcessingUrl("/dologin")
		.defaultSuccessUrl("/user/")
		//.failureUrl("/signin")
		.and().csrf().disable();
	
	}
	
	
	
	
	
	

}
