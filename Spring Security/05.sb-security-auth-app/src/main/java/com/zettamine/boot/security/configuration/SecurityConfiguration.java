package com.zettamine.boot.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfiguration {
	
	@Autowired
	PasswordEncoder encoder;
	
	
	@Bean
	public SecurityFilterChain filter(HttpSecurity http) throws Exception {
		
		return http.authorizeHttpRequests(request->request.requestMatchers("/home","/care").permitAll()
				
				/*
				 * .requestMatchers("/admin").hasRole("ADMIN")
				 * .requestMatchers("/accounts").hasAnyRole("ADMIN","MANAGER")
				 * .requestMatchers("/loans").hasAnyRole("ADMIN","MANAGER","CLERK")
				 * .requestMatchers("/deposits").hasAnyRole("ADMIN","MANAGER","PO")
				 * .requestMatchers("/customer/balance").hasAnyRole("CUSTOMER")
				 */
				 
				.anyRequest().authenticated())
				
				
		.csrf(csrf->csrf.disable())
		
		.formLogin(Customizer.withDefaults())
		
		.httpBasic(Customizer.withDefaults()).build();
			
	}
	
	@Bean
	public UserDetailsService manageUsers() {
		
		UserDetails user1 = User.builder().username("admin")
				   			.password(encoder.encode("1234"))
				   			.roles("ADMIN")
				   			.build();
		
		UserDetails user2 = User.builder().username("vinay")
	   			.password(encoder.encode("1234"))
	   			.roles("CLERK")
	   			.build();
		
		UserDetails user3 = User.builder().username("bharat")
	   			.password(encoder.encode("1234"))
	   			.roles("MANAGER")
	   			.build();
		UserDetails user4 = User.builder().username("kavya")
	   			.password(encoder.encode("1234"))
	   			.roles("PO")
	   			.build();
		UserDetails user5 = User.builder().username("rekha")
	   			.password(encoder.encode("1234"))
	   			.roles("CUSTOMER")
	   			.build();
		
		UserDetailsManager users = new InMemoryUserDetailsManager(user1,user2,user3,user4,user5);
		
		return users;
	}
	
	
}
