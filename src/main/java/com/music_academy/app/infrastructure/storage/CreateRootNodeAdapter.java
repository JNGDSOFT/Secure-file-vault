package com.music_academy.app.infrastructure.storage;

import org.springframework.stereotype.Component;

import com.music_academy.app.application.port.out.CreateUserDirectoryNodeOutPort;
import com.music_academy.app.domain.model.Node;
import com.music_academy.app.infrastructure.mapper.NodeMapper;
import com.music_academy.app.infrastructure.persistance.SpringDataNodeRepository;
import com.music_academy.app.infrastructure.persistance.model.NodeEntity;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreateRootNodeAdapter implements CreateUserDirectoryNodeOutPort {
	private final SpringDataNodeRepository springDataNodeRepository;
	private final NodeMapper nodeMapper;

	@Override
	public void createRootNode(Node node) {
		NodeEntity nodeEntity = nodeMapper.mapNodeToEntity(node);
		springDataNodeRepository.save(nodeEntity);
	}

}
