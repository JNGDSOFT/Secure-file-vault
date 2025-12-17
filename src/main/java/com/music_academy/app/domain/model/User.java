package com.music_academy.app.domain.model;

import java.util.List;

public record User(Long id, List<Role> roles, String email, String password) {
}
