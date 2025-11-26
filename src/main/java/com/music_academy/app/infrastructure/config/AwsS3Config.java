package com.music_academy.app.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class AwsS3Config {

	@Value("${aws.accessKey}")
	private String accessKeyId;

	@Value("${aws.secretKey}")
	private String secretKey;

	@Bean
	S3Client s3Client() {
		Region region = Region.of("LOCAL");
		AwsCredentials credentials = AwsBasicCredentials.create(accessKeyId, secretKey);

		S3Client s3Client = S3Client.builder().region(region)
				.credentialsProvider(StaticCredentialsProvider.create(credentials)).build();

		return s3Client;
	}
}
