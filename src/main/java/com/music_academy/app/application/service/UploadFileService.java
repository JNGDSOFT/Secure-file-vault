package com.music_academy.app.application.service;

import java.io.IOException;
import java.time.Instant;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.music_academy.app.application.port.in.UploadFileUseCase;
import com.music_academy.app.application.port.out.FilesBucketOutPort;
import com.music_academy.app.application.port.out.GetRootNodeOutPort;
import com.music_academy.app.application.port.out.NodeRepositoryOutPort;
import com.music_academy.app.application.port.out.UserRepositoryOutPort;
import com.music_academy.app.domain.model.Node;
import com.music_academy.app.domain.model.NodeType;
import com.music_academy.app.domain.model.User;
import com.music_academy.app.infrastructure.exception.IllegalNodeType;
import com.music_academy.app.infrastructure.exception.StorageException;
import com.music_academy.app.infrastructure.exception.handler.IllegalFolderAccess;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UploadFileService implements UploadFileUseCase {

	private final UserRepositoryOutPort userRepositoryOutPort;
	private final FilesBucketOutPort filesBucketOutPort;
	private final GetRootNodeOutPort getRootNodeOutPort;
	private final NodeRepositoryOutPort nodeRepositoryOutPort;

	@Override
	public void uploadFile(Long userId, UUID parentNodeId, MultipartFile multipartFile)
			throws StorageException, IOException {

		User user = userRepositoryOutPort.getUserById(userId);

		Node targetNode;

		if (parentNodeId != null) {
			targetNode = nodeRepositoryOutPort.getNodeById(parentNodeId);

			try {
				if (!targetNode.nodeType().equals(NodeType.DIRECTORY)) {
					throw new IllegalNodeType(
							"The node type is incorrect, it must be a directory to be used as a container");
				}
				if (!targetNode.owner().id().equals(userId)) {
					throw new IllegalFolderAccess("This user doesn´t own this folder");
				}
			} catch (IllegalNodeType a) {
				throw new StorageException("There was an error while saving a file", a);
			} catch (IllegalFolderAccess b) {
				throw new StorageException("There was an error while saving a file", b);
			}
		} else {
			targetNode = getRootNodeOutPort.getRootNode(user);
		}

		UUID uuid = UUID.randomUUID();

		Node node = new Node(uuid, targetNode, user, Instant.now(), multipartFile.getName(), NodeType.FILE,
				multipartFile.getContentType(), multipartFile.getSize(), uuid.toString(), targetNode.treePath());

		nodeRepositoryOutPort.saveNode(node);

		filesBucketOutPort.createFile(multipartFile.getBytes(), node);
	}
}
