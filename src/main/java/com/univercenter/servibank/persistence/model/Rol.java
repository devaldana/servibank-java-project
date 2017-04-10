package com.univercenter.servibank.persistence.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.univercenter.servibank.persistence.AuditableEntity;



/**
 * Entidad persistente para la tabla <strong><code>roles</code></strong> de la base de datos.
 * 
 * @author David Aldana
 * @author <a href="http://www.univercenter.com">Univercenter</a>
 * 
 * @since 2017.03
 * 
 * @see AuditableEntity
 * 
 */
@Entity
@Audited
@Table(name="roles")
public class Rol extends AuditableEntity {
	
	private static final long serialVersionUID = 1L;

	@Length(max=500)
	private String descripcion;

	@NotNull
	@NotBlank
	@Length(max=50)
	private String nombre;

	//bi-directional many-to-many association to Usuario
	@ManyToMany(mappedBy="roles")
	private List<Usuario> usuarios;

	public Rol() {
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
}