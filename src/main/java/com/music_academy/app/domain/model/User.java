package com.music_academy.app.domain.model;

public record User(Long id, Role role, String email, String password) {
}
