package com.carrotcake.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.carrotcake.service.AuthUserDetailsService;

//import com.carrotcake.service.AuthUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Bean
	public AuthUserDetailsService userDetailService() {
		return new AuthUserDetailsService();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder(){		
	   return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bcEncrypt, AuthUserDetailsService userDetailService) throws Exception{
		return http
			   .getSharedObject(AuthenticationManagerBuilder.class)
			   .userDetailsService(userDetailService)
			   .passwordEncoder(bcEncrypt)
			   .and()
			   .build();
	}


	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		return http
		  .authorizeHttpRequests()
		  .requestMatchers(HttpMethod.GET,"/users/**").authenticated()
		  .requestMatchers(HttpMethod.GET).permitAll()
		  .requestMatchers(HttpMethod.POST,"/users/**").authenticated()
		  .requestMatchers(HttpMethod.POST).permitAll()
		  .requestMatchers(HttpMethod.PUT,"/users/**").authenticated()
		  .requestMatchers(HttpMethod.PUT).permitAll()
		  .requestMatchers(HttpMethod.DELETE,"/users/**").authenticated()
		  .requestMatchers(HttpMethod.DELETE).permitAll()
		  .anyRequest().authenticated()
		  .and()
		  .rememberMe().tokenValiditySeconds(86400).key("rememberMe-key")
		  .and()
		  .csrf().disable()
		  .cors().disable()
		  .formLogin().loginPage("/login/page")
		  .defaultSuccessUrl("/login/welcome")
		  .and()
		  .build();
	}
}
