package com.music_academy.app.infrastructure.persistance.model;

import java.time.LocalDateTime;

import com.music_academy.app.domain.model.NodeType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_parent_node")
	private NodeEntity parentNode;

	@ManyToOne
	@JoinColumn(name = "id_user")
	private UserEntity owner;

	private LocalDateTime creationDateTime;

	private String name;

	@Enumerated(EnumType.STRING)
	private NodeType nodeType;

	private Long size;

	@Column(unique = true)
	private String s3Key;
}
