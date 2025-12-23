package com.music_academy.app.infrastructure.persistance;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.music_academy.app.infrastructure.persistance.model.NodeEntity;

public interface SpringDataNodeRepository extends JpaRepository<NodeEntity, UUID> {

	@Query("SELECT n FROM NodeEntity n WHERE n.owner.id = :userId AND n.parentNode IS NULL")
	Optional<NodeEntity> findRootNode(Long userId);
}
