package com.music_academy.app.application.service;

import java.time.Instant;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.music_academy.app.application.port.in.CreateFolderUseCase;
import com.music_academy.app.application.port.out.GetRootNodeOutPort;
import com.music_academy.app.application.port.out.NodeRepositoryOutPort;
import com.music_academy.app.application.port.out.UserRepositoryOutPort;
import com.music_academy.app.domain.model.Node;
import com.music_academy.app.domain.model.NodeType;
import com.music_academy.app.domain.model.User;
import com.music_academy.app.infrastructure.exception.IllegalNodeType;
import com.music_academy.app.infrastructure.exception.StorageException;
import com.music_academy.app.infrastructure.exception.handler.IllegalFolderAccess;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateFolderService implements CreateFolderUseCase {

	private final NodeRepositoryOutPort nodeRepositoryOutPort;
	private final UserRepositoryOutPort userRepositoryOutPort;
	private final GetRootNodeOutPort getRootNodeOutPort;

	@Override
	public void createFolder(Long ownerUserId, UUID parentNodeUuid, String name) throws StorageException {
		User user = userRepositoryOutPort.getUserById(ownerUserId);

		Node node;

		if (parentNodeUuid != null) {
			node = nodeRepositoryOutPort.getNodeById(parentNodeUuid);
			if (!node.nodeType().equals(NodeType.DIRECTORY)) {
				throw new IllegalNodeType("Referenced parent node is not a folder");
			}
		} else {
			node = getRootNodeOutPort.getRootNode(user);
		}

		if (!user.id().equals(node.owner().id())) {
			throw new IllegalFolderAccess("cannot access to this folder because the user is not the owner");
		}

		nodeRepositoryOutPort.saveNode(new Node(UUID.randomUUID(), node, user, Instant.now(), name, NodeType.DIRECTORY,
				null, null, null, node.treePath() + "/" + name));
	}
}
