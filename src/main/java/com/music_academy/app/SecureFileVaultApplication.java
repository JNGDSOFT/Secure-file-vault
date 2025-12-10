package com.music_academy.app;

import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SecureFileVaultApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecureFileVaultApplication.class, args);
	}

}
