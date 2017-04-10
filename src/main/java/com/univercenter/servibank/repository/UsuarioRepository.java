package com.univercenter.servibank.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.univercenter.servibank.persistence.model.Usuario;


/**
 * Repositorio para la entidad {@link Usuario}.
 * 
 * @author David Aldana
 * @author <a href="http://www.univercenter.com">Univercenter</a>
 * 
 * @since 2017.03
 * 
 * @see CrudRepository
 * 
 */
@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long>{
	
	public Usuario findByNombre(String nombre);
	//public Usuario findByNumeroDocumento(String numeroDocumento);
	
	//TODO optimizar esto para traer sólo los campos necesarios en un DTO.
	@Query("Select u from Usuario u join fetch u.roles r where u.numeroDocumento = :numeroDocumento")
	public Usuario findByNumeroDocumento(@Param("numeroDocumento") String numeroDocumento);
}