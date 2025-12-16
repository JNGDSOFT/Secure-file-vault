package com.music_academy.app.domain.model;

import java.time.LocalDateTime;

public record Node(Long id, Node parentNode, User owner, LocalDateTime creationDateTime, String name, NodeType nodeType,
		Integer size, String s3Key) {
	public Node {
		creationDateTime = LocalDateTime.now();
	}
}
