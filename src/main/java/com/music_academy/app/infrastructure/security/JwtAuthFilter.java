package com.music_academy.app.infrastructure.security;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.music_academy.app.application.port.out.TokenBlacklistRepositoryOutPort;
import com.music_academy.app.infrastructure.exception.handler.AuthenticationExceptionHandler;
import com.music_academy.app.infrastructure.utils.ServletUtils;

import io.jsonwebtoken.Claims;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
	private final JwtUtil jwtUtil;
	private final TokenBlacklistRepositoryOutPort tokenBlacklistRepositoryOutPort;
	private final UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response,
			@Nonnull FilterChain filterChain) throws ServletException, IOException {

		Optional<String> servletToken = ServletUtils.extractToken(request);

		if (servletToken.isEmpty()) {
			filterChain.doFilter(request, response);
			return;
		}

		final String token = servletToken.get();

		try {
			final Claims claims = jwtUtil.getClaims(token);

			if (tokenBlacklistRepositoryOutPort.isTokenInBlacklist(UUID.fromString(claims.get("jti", String.class)))) {
				throw new BadCredentialsException("");
			}

			final String username = claims.getSubject();

			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

				@SuppressWarnings("unchecked")
				Collection<String> roles = claims.get("roles", Collection.class);

				Set<SimpleGrantedAuthority> authorities = roles.stream().map(SimpleGrantedAuthority::new)
						.collect(Collectors.toSet());

				UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, authorities);

				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(authenticationToken);

			}

			filterChain.doFilter(request, response);
		} catch (Exception e) {
			// TODO implement the handling of this in 401 instead of internal 500 error
			throw new RuntimeException("Token failure");
		}
	}
}
