package com.music_academy.app.service;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.music_academy.app.application.port.out.NodeRepositoryOutPort;
import com.music_academy.app.application.port.out.UserRepositoryOutPort;
import com.music_academy.app.application.service.CreateFolderService;
import com.music_academy.app.domain.model.Node;
import com.music_academy.app.domain.model.NodeType;
import com.music_academy.app.domain.model.User;
import com.music_academy.app.infrastructure.exception.StorageException;

@ExtendWith(MockitoExtension.class)
public class CreateFolderServiceTest {

	@InjectMocks
	private CreateFolderService createFolderService;

	@Mock
	private NodeRepositoryOutPort nodeRepositoryOutPort;

	@Mock
	private UserRepositoryOutPort userRepositoryOutPort;

	@Test
	void createFolder_namesCorrectlyTreePath_whenFolderParamsAreValid() throws StorageException {

		UUID mockNodeUuid = UUID.randomUUID();

		User user = new User(1l, null, null, null);

		Node node = new Node(mockNodeUuid, null, user, null, null, NodeType.DIRECTORY, null, null, null, "");

		when(nodeRepositoryOutPort.getNodeById(mockNodeUuid)).thenReturn(node);
		when(userRepositoryOutPort.getUserById(1l)).thenReturn(user);
		when(nodeRepositoryOutPort.saveNode(any(Node.class))).thenAnswer(invocation -> invocation.getArgument(0));

		Node createdNode = createFolderService.createFolder(1l, mockNodeUuid, "FOLDER TEST");

		assertEquals("FOLDER TEST", createdNode.name());
		assertThat(createdNode.treePath(), Matchers.containsString(mockNodeUuid.toString()));
	}
}
