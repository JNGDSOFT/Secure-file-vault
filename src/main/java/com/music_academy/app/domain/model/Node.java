package com.music_academy.app.domain.model;

import java.time.Instant;
import java.util.UUID;

public record Node(UUID id, Node parentNode, User owner, Instant creationInstant, String name, NodeType nodeType,
		String nodeContentType, Integer size, String s3Key, String treePath) {
}
