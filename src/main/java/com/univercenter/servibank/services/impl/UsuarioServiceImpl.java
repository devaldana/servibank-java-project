package com.univercenter.servibank.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.univercenter.servibank.persistence.model.Usuario;
import com.univercenter.servibank.repository.UsuarioRepository;
import com.univercenter.servibank.services.UsuarioService;


/**
 * Servicio transaccional para la entidad {@link Usuario}.
 *
 * @author David Aldana
 * @author <a href="http://www.univercenter.com">Univercenter</a>
 *
 * @since 2017.03
 *
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

	
	private final UsuarioRepository usuarioRepository;
	
	@Autowired
	public UsuarioServiceImpl(final UsuarioRepository usuarioRepository) {
		
		this.usuarioRepository = usuarioRepository;
	}
	
	@Override
	public Usuario guardar(final Usuario entity) {

		return usuarioRepository.save(entity);
	}

	@Override
	public Usuario buscar(final Long id) {

		return usuarioRepository.findOne(id);
	}

	@Override
	public boolean eliminar(final Long id) {

		usuarioRepository.delete(id);
		
		return !usuarioRepository.exists(id);
	}

	@Override
	public Usuario buscarPorNombre(String nombre) {

		return usuarioRepository.findByNombre(nombre);
	}

	@Override
	public Usuario buscarPorNumeroDocumento(String numeroDocumento) {

		return usuarioRepository.findByNumeroDocumento(numeroDocumento);
	}

}
