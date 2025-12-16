package com.music_academy.app.infrastructure.storage;

import org.springframework.stereotype.Component;

import com.music_academy.app.application.port.out.CreateBucketDirectoryOutPort;
import com.music_academy.app.infrastructure.constant.S3Constants;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;

@RequiredArgsConstructor
@Component
public class CreateBucketDirectoryAdapter implements CreateBucketDirectoryOutPort {

	private final S3Client s3Client;

	@Override
	public void createDirectory(String pathPrefix, String name) {
		s3Client.putObject(b -> b.bucket(S3Constants.DEFAULT_BUCKET).key(pathPrefix + name), RequestBody.empty());
	}
}
