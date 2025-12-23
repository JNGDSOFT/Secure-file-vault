package com.music_academy.app.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public record Node(UUID id, Node parentNode, User owner, LocalDateTime creationDateTime, String name, NodeType nodeType,
		String nodeContentType, Integer size, String s3Key, String treePath) {
	public Node {
		creationDateTime = LocalDateTime.now();
	}
}
