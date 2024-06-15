package com.zettamine.boot.security.configuration;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filter(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(request -> request.requestMatchers("/h2-console/**")
                .permitAll()
                .anyRequest()
                .authenticated());

        http.csrf(csrf -> csrf.disable());

        http.httpBasic(Customizer.withDefaults());

        http.headers(header -> header.frameOptions(frame -> frame.sameOrigin()));

        return http.build();
    }

    @Bean
    public DataSource dataSource() {
        
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url("jdbc:postgresql://localhost:5432/db_zettamine");
        dataSourceBuilder.username("postgres");
        dataSourceBuilder.password("Bharat@10");
        return dataSourceBuilder.build();
    }

    @Bean
    public UserDetailsManager manageUsers(PasswordEncoder passwordEncoder) {


		UserDetails user1 = User.builder().username("bharat").password("bharat123")
				.passwordEncoder(pwd -> getPasswordEncoder().encode(pwd)).authorities("ADMIN", "MANAGER").build();
		UserDetails user2 = User.builder().username("vik").password(getPasswordEncoder().encode("vik123"))
				.authorities("CUSTOMER").build();

		 JdbcUserDetailsManager users = new JdbcUserDetailsManager();
		 
		 
		 
		 users.setDataSource(dataSource());

		users.createUser(user1);
		users.createUser(user2);
		
		System.out.println(user2.getPassword());

		return users;
    }

    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
