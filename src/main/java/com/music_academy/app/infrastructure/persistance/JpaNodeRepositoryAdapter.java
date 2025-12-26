package com.music_academy.app.infrastructure.persistance;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.music_academy.app.application.port.out.NodeRepositoryOutPort;
import com.music_academy.app.domain.model.Node;
import com.music_academy.app.infrastructure.exception.NotFoundByIdException;
import com.music_academy.app.infrastructure.mapper.NodeMapper;
import com.music_academy.app.infrastructure.persistance.model.NodeEntity;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JpaNodeRepositoryAdapter implements NodeRepositoryOutPort {

	private final SpringDataNodeRepository springDataNodeRepository;
	private final NodeMapper nodeMapper;

	@Override
	public Node getNodeById(UUID uuid) {

		NodeEntity nodeEntity = springDataNodeRepository.findById(uuid).orElseThrow(NotFoundByIdException::new);

		Node node = nodeMapper.mapEntityToNode(nodeEntity);

		return node;
	}

	@Override
	public Node saveNode(Node node) {
		NodeEntity nodeEntity = nodeMapper.mapNodeToEntity(node);
		NodeEntity savedEntity = springDataNodeRepository.save(nodeEntity);
		return nodeMapper.mapEntityToNode(savedEntity);
	}
}
