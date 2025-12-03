package com.music_academy.app.domain.model;

import java.time.LocalDateTime;

public record Node(Long id, Long parentId, Long userId, LocalDateTime creationDateTime, String name, NodeType nodeType,
		Integer size, String s3Key) {

}
