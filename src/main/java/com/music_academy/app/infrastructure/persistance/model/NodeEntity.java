package com.music_academy.app.infrastructure.persistance.model;

import java.time.Instant;
import java.util.UUID;

import com.music_academy.app.domain.model.NodeType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "nodes")
public class NodeEntity {

	@Id
	private UUID id;

	@ManyToOne
	@JoinColumn(name = "id_parent_node")
	private NodeEntity parentNode;

	@ManyToOne
	@JoinColumn(name = "owner_id")
	private UserEntity owner;

	private Instant createdAt;

	private String name;

	@Enumerated(EnumType.STRING)
	private NodeType nodeType;

	private String nodeContentType;

	private Long size;

	@Column(unique = true)
	private String s3Key;

	private String treePath;

}
