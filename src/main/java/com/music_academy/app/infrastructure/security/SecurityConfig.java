package com.music_academy.app.infrastructure.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.music_academy.app.application.port.out.UserRepositoryOutPort;
import com.music_academy.app.application.service.UserService;
import com.music_academy.app.domain.model.User;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.formLogin(httpform -> {
			httpform.loginPage("/login").permitAll();
		}).authorizeHttpRequests(registry -> {
			registry.requestMatchers("/signup").permitAll();
			registry.anyRequest().authenticated();
		});
		return httpSecurity.build();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	UserDetailsService userDetailsService(UserRepositoryOutPort repositoryOutPort) {
		return new UserService(repositoryOutPort);
	}
}
