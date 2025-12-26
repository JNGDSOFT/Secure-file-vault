package com.music_academy.app.infrastructure.storage;

import org.springframework.stereotype.Component;

import com.music_academy.app.application.port.out.GetRootNodeOutPort;
import com.music_academy.app.domain.model.Node;
import com.music_academy.app.domain.model.User;
import com.music_academy.app.infrastructure.exception.NotFoundByIdException;
import com.music_academy.app.infrastructure.mapper.NodeMapper;
import com.music_academy.app.infrastructure.persistance.SpringDataNodeRepository;
import com.music_academy.app.infrastructure.persistance.model.NodeEntity;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GetRootNodeAdapter implements GetRootNodeOutPort {

	private final SpringDataNodeRepository springDataNodeRepository;
	private final NodeMapper nodeMapper;

	@Override
	public Node getRootNode(User user) {
		NodeEntity nodeEntity = springDataNodeRepository.findRootNode(user.id())
				.orElseThrow(NotFoundByIdException::new);
		return nodeMapper.mapEntityToNode(nodeEntity);
	}
}
