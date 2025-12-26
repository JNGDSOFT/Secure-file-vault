package com.music_academy.app.application.port.out;

import java.util.UUID;

import com.music_academy.app.domain.model.Node;

public interface NodeRepositoryOutPort {
	Node saveNode(Node node);

	Node getNodeById(UUID uuid);
}
