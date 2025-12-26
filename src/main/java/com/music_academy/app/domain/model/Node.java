package com.music_academy.app.domain.model;

import java.time.Instant;
import java.util.UUID;

public record Node(UUID id, Node parentNode, User owner, Instant createdAt, String name, NodeType nodeType,
		String nodeContentType, Long size, String s3Key, String treePath) {
}
