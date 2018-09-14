package com.kristijangeorgiev.auth.configuration;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 
 * @author Kristijan Georgiev
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		
//		http.authorizeRequests().antMatchers("/searcher/**").permitAll();
		
//		http.csrf().disable().exceptionHandling()				
//				.and().authorizeRequests()
//				.antMatchers("/searcher/**").permitAll();
		 http
         .csrf().disable()
         .anonymous().disable()
         .authorizeRequests()
         .antMatchers(HttpMethod.OPTIONS).permitAll()
         .filterSecurityInterceptorOncePerRequest(true)
         // when restricting access to 'Roles' you must remove the "ROLE_" part role
         // for "ROLE_USER" use only "USER"
         //.antMatchers("/api/hello").access("hasAnyRole('USER')")
         .antMatchers("/searcher/**").authenticated()
         .antMatchers("/index-configger/**").hasAnyRole("USER", "ADMIN")
         //.antMatchers("/api/admin").hasRole("ADMIN")
         // use the full name when specifying authority access
         //.antMatchers("/api/registerUser").hasAuthority("ROLE_REGISTER")
         // restricting all access to /api/** to authenticated users
         .antMatchers("/api/**").authenticated();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

}