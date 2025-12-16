package com.music_academy.app.infrastructure.storage;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.music_academy.app.application.port.out.CreateUserDirectoryNodeOutPort;
import com.music_academy.app.application.port.out.UserRepositoryOutPort;
import com.music_academy.app.domain.model.Node;
import com.music_academy.app.domain.model.NodeType;
import com.music_academy.app.domain.model.User;
import com.music_academy.app.infrastructure.exception.NotFoundByIdException;
import com.music_academy.app.infrastructure.mapper.NodeMapper;
import com.music_academy.app.infrastructure.persistance.SpringDataNodeRepository;
import com.music_academy.app.infrastructure.persistance.model.NodeEntity;
import com.music_academy.app.infrastructure.persistance.model.UserEntity;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreateNodeAdapter implements CreateUserDirectoryNodeOutPort {

	private final SpringDataNodeRepository springDataNodeRepository;
	private final NodeMapper nodeMapper;

	@Override
	public void createNode(Node node) {
		NodeEntity nodeEntity = nodeMapper.mapNodeToEntity(node);
		springDataNodeRepository.save(nodeEntity);
	}

	@Override
	public Node getRootNode(User user) {
		NodeEntity nodeEntity = springDataNodeRepository.findRootNode(user.id())
				.orElseThrow(NotFoundByIdException::new);
		return nodeMapper.mapEntityToNode(nodeEntity);
	}
}
