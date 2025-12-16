package com.music_academy.app.infrastructure.config;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.music_academy.app.infrastructure.constant.S3Constants;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.S3Exception;

@Configuration
public class AwsS3Config {

	private final Logger logger = LoggerFactory.getLogger(AwsS3Config.class);

	@Value("${aws.accessKeyId}")
	private String accessKeyId;

	@Value("${aws.secretKey}")
	private String secretKey;

	@Value("${aws.urlS3}")
	private String s3Url;

	@Bean
	S3Client s3Client() {
		AwsCredentials credentials = AwsBasicCredentials.create(accessKeyId, secretKey);
		S3Client s3Client = S3Client.builder().credentialsProvider(StaticCredentialsProvider.create(credentials))
				.endpointOverride(URI.create("http://" + s3Url)).region(Region.of("LOCAL")).build();

		try {
			s3Client.headBucket(b -> b.bucket(S3Constants.DEFAULT_BUCKET));
			logger.info("There is already a bucket so the app is running normally");
		} catch (S3Exception s3Exception) {
			s3Client.createBucket(b -> b.bucket(S3Constants.DEFAULT_BUCKET));
			logger.info("ROOT Bucket was created because there was not any");
		}

		return s3Client;
	}
}
