package com.music_academy.app.infrastructure.storage;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.music_academy.app.application.port.out.CreateUserDirectoryNodeOutPort;
import com.music_academy.app.application.port.out.FilesBucketOutPort;
import com.music_academy.app.application.port.out.UserRepositoryOutPort;
import com.music_academy.app.domain.model.Node;
import com.music_academy.app.domain.model.NodeType;
import com.music_academy.app.domain.model.User;
import com.music_academy.app.infrastructure.constant.BucketConstants;
import com.music_academy.app.infrastructure.exception.NotFoundByIdException;
import com.music_academy.app.infrastructure.exception.StorageException;
import com.music_academy.app.infrastructure.mapper.NodeMapper;
import com.music_academy.app.infrastructure.persistance.SpringDataNodeRepository;
import com.music_academy.app.infrastructure.persistance.model.NodeEntity;
import com.music_academy.app.infrastructure.persistance.model.UserEntity;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Component
@RequiredArgsConstructor
public class CreateNodeAdapter implements FilesBucketOutPort {
	private final S3Client s3Client;
	private final NodeMapper nodeMapper;

	@Override
	public void createFile(byte[] binary, Node node) throws IOException, StorageException {
		try {
			PutObjectRequest putObjectRequest = PutObjectRequest.builder().bucket(BucketConstants.DEFAULT_BUCKET)
					.key(node.s3Key()).build();

			s3Client.putObject(putObjectRequest, RequestBody.fromBytes(binary));
		} catch (Exception e) {
			throw new StorageException(e.getMessage());
		}
	}
}
