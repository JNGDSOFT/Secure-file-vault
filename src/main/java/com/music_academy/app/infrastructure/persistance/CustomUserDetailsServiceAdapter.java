package com.music_academy.app.infrastructure.persistance;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.music_academy.app.infrastructure.exception.NotFoundByNameException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CustomUserDetailsServiceAdapter implements UserDetailsService {
	private final SpringDataUserRepository springDataUserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return springDataUserRepository.findByEmail(username).orElseThrow(NotFoundByNameException::new);
	}
}
