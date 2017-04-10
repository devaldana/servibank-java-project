package com.univercenter.servibank.services;

import com.univercenter.servibank.persistence.model.Usuario;

/**
 * Definición del servicio para la entidad {@link Usuario}.
 *
 * @author David Aldana
 * @author <a href="http://www.univercenter.com">Univercenter</a>
 *
 * @since 2017.03
 *
 */
public interface UsuarioService extends CrudService<Usuario, Long> {

	/**
	 * Busca un {@link Usuario usuario} con el nombre pasado por parámetro.
	 * 
	 * @param nombre del usuario a encontrar.
	 * @return el usuario con el nombre dado o <code>null</code> si no se encuentra.
	 */
	Usuario buscarPorNombre(String nombre);

	/**
	 * Busca un {@link Usuario usuario} con el número de documento pasado por parámetro.
	 * 
	 * @param numeroDocumento del usuario a encontrar.
	 * @return el usuario con el número de documento dado o <code>null</code> si no se encuentra.
	 */
	Usuario buscarPorNumeroDocumento(String numeroDocumento);

}
