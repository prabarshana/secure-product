package com.isanka.product.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.isanka.product.security.service.UserSecurityService;

@Configuration
@EnableWebSecurity
public class ProductSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserSecurityService userService;

	@Bean
	public AuthenticationProvider loginUser() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userService);
		return provider;

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//http.csrf().disable()
	http
		.authorizeRequests().antMatchers("/login").permitAll().anyRequest().authenticated()
		.and()
		.formLogin().loginPage("/login").permitAll()
		.successForwardUrl("/menu")
		//.failureForwardUrl("/errorlogin")
		.and()
		.logout().logoutUrl("/logout").permitAll()
		.logoutSuccessUrl("/home")
		.clearAuthentication(true);
	
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/","/home");
	}
	
	

}
