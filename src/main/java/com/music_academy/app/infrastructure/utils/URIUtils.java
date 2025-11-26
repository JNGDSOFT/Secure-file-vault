package com.music_academy.app.infrastructure.utils;

import java.net.URI;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class URIUtils {

	/**
	 * Obtiene la URI del elemento recien creado basandose en el path actual
	 * 
	 * @param id Id del elemento
	 * @return URI con el path de consulta del elemento en el servicio
	 */
	public static URI getCreatedElementURI(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}
}
