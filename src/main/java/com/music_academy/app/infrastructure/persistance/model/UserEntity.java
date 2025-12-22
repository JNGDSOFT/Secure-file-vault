package com.music_academy.app.infrastructure.persistance.model;

import java.util.List;
import java.util.Set;
import java.util.Collection;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.music_academy.app.domain.model.Role;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Entity
@Getter
@Setter
@Table(name = "users")
public class UserEntity implements UserDetails, CredentialsContainer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String email;

	private String password;

	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "id_user", referencedColumnName = "id", nullable = false))
	@Column(name = "role_name", length = 50, nullable = false)
	@Enumerated(EnumType.STRING)
	private Set<Role> roles;

	@Override
	public void eraseCredentials() {
		this.password = null;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles.stream().map(r -> new SimpleGrantedAuthority(r.name())).toList();
	}

	@Override
	public String getUsername() {
		return email;
	}
}
