package com.univercenter.servibank.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.univercenter.servibank.persistence.model.Usuario;
import com.univercenter.servibank.services.UsuarioService;


/**
 * Servicio que implementa la interfaz  {@link org.springframework.security.core.userdetails.UserDetailsService UserDetailsService}<p>
 * 
 * Este componente se usa para la autenticación de los usuarios.
 * 
 * @author David Aldana
 * @author <a href="http://www.univercenter.com">Univercenter</a>
 * 
 * @since 2017.03
 *
 * @see UserDetails
 * @see WebSecurityConfig
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	private final UsuarioService usuarioService;
	
	@Autowired
	public UserDetailsServiceImpl(final UsuarioService usuarioService) {
		
		this.usuarioService = usuarioService;
	}
	
	/**
	 * Encuentra el usuario que cumpla con el criterio de <code>username</code>.
	 *
	 * @param username el identificador del usuario que se está buscando.
	 *
	 * @return un usuario con toda su respectiva información (nunca <code>null</code>)
	 *
	 * @throws UsernameNotFoundException si no se encuentra al usuario o si el usuario no tiene privilegios.
	 */
	@Override
	public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(final String numeroDocumento) throws UsernameNotFoundException {

		Usuario usuario = usuarioService.buscarPorNumeroDocumento(numeroDocumento);

		if ( usuario == null ) throw new UsernameNotFoundException("Usuario o clave inválida");
		
		return new UserDetails(usuario);
	}
}
