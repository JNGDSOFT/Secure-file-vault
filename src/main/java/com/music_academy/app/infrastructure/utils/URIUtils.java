package com.music_academy.app.infrastructure.utils;

import java.net.URI;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class URIUtils {

	private URIUtils() {

	}

	/**
	 * Obtiene la URI del elemento recien creado basandose en el path actual
	 * 
	 * @param id Id del elemento
	 * @return URI con el path de consulta del elemento en el servicio
	 */
	public static URI getCreatedElementURI(String pathPrefix, Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path(pathPrefix + "/{id}").buildAndExpand(id).toUri();
	}
}
