package com.music_academy.app.domain.model;

import java.util.Set;

public record User(Long id, Set<Role> roles, String email, String password) {
}
