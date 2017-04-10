package com.univercenter.servibank.config.security;

import org.springframework.security.core.userdetails.User;

import com.univercenter.servibank.persistence.model.Usuario;



/**
 * Clase que extiende la funcionalidad de {@link User}
 * para guardar el atributo {@link Usuario usuario} que representa la entidad del dominio que
 * contiene los datos del usuario que inicia sesión.
 * 
 * @author David Aldana
 * @author <a href="http://www.univercenter.com">Univercenter</a>
 * 
 * @since 2017.03
 * 
 * @see User
 * 
 */
public class UserDetails extends User {

	/*
	 * Serial Version UID por defecto: 1L
	 */
	private static final long serialVersionUID = 1L;
	
	/*
	 * Atributo que representa el usuario en sesión.
	 */
	private final Usuario usuario;

	/**
	 * Constructor parametrizado con el usuario que inicia sesión.
	 * @param usuario Usuario en sesión.
	 */
	public UserDetails(final Usuario usuario) {
		
		/*
		 * accountNonExpired fijado a true porque no se maneja expiración de cuentas.
		 * credentialsNonExpired fijado a true porque no se maneja expiración de cuentas.
		 * accountNonLocked set to true if the account is not locked
		 */
		super(usuario.getId().toString(), usuario.getClave(), usuario.isHabilitado(), true, true, true, usuario.getGrantedAuthorities());
		this.usuario = usuario;
	}

	/**
	 * Devuelve el {@link Usuario usuario} en sesión.
	 * @return usuario en sesión.
	 */
	public Usuario getUsuario() {
		return usuario;
	}
}