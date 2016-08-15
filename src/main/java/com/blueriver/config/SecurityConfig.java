package com.blueriver.config;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		HashMap<String, String> users = Authentication.getUsers();
		for (String username : users.keySet()) {
			String password = users.get(username);
			auth.inMemoryAuthentication().withUser(username).password(password).roles("USER");
		}
	}

	//.csrf() is optional, enabled by default, if using WebSecurityConfigurerAdapter constructor
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/home/**").access("hasRole('ROLE_USER')")
			.antMatchers("/tenant/**").access("hasRole('ROLE_USER')")
			.antMatchers("/customer/**").access("hasRole('ROLE_USER')")
			.antMatchers("/product/**").access("hasRole('ROLE_USER')")
			.antMatchers("/order/**").access("hasRole('ROLE_USER')")
			.antMatchers("/orderspercustomer/**").access("hasRole('ROLE_USER')")
			.antMatchers("/resources/**", "/about").permitAll()
			.and().formLogin().loginPage("/login").failureUrl("/login?error").usernameParameter("username").passwordParameter("password")
			.and().logout().logoutSuccessUrl("/login?logout")
			.and().csrf().disable(); 
	}
}