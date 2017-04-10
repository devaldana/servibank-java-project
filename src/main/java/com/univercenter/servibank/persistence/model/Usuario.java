package com.univercenter.servibank.persistence.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.univercenter.servibank.persistence.AuditableEntity;
import com.univercenter.servibank.util.Global;



/**
 * Entidad persistente para la tabla <strong><code>usuarios</code></strong> de la base de datos.
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
@Table(name="usuarios")
public class Usuario extends AuditableEntity {
	
	private static final long serialVersionUID = 1L;

	@NotNull
	@NotBlank
	@Length(max=50)
	private String nombre;
	
	@NotNull
	@NotBlank
	@Length(max=50)
	private String apellido;

	@NotNull
	private boolean cambioClaveRequerido = true;

	@NotNull
	@NotBlank
	@Length(max=60)
	private String clave;

	@NotNull
	@NotBlank
	@Email
	@Length(max=60)
	private String correo;

	@NotNull
	private boolean correoConfirmado;

	@NotNull
	@Digits(integer=2, fraction=2)
	private float estatura;

	@NotNull
	private LocalDate fechaNacimiento;

	/**
	 * False por defecto, habilitado cuando confirme correo electronico
	 */
	@NotNull
	private boolean habilitado;

	@NotNull
	@NotBlank
	@Length(max=20)
	private String numeroDocumento;

	@NotNull
	@NotBlank
	@Length(min=10, max=10)
	@Pattern(regexp=Global.REGEXP_SOLO_NUMEROS)
	private String telefonoCelular;

	@NotNull
	@NotBlank
	@Length(min=7, max=10)
	@Pattern(regexp=Global.REGEXP_SOLO_NUMEROS)
	private String telefonoFijo;


	//bi-directional many-to-many association to Rol
	@NotNull
	@ManyToMany
	@JoinTable( name="usuarios_roles", joinColumns={ @JoinColumn(name="id_usuario", nullable=false) }, 
									   inverseJoinColumns={ @JoinColumn(name="id_rol", nullable=false) })
	private List<Rol> roles;

	public Usuario() {
	}
	
	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public boolean getCambioClaveRequerido() {
		return this.cambioClaveRequerido;
	}

	public void setCambioClaveRequerido(boolean cambioClaveRequerido) {
		this.cambioClaveRequerido = cambioClaveRequerido;
	}

	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public boolean getCorreoConfirmado() {
		return this.correoConfirmado;
	}

	public void setCorreoConfirmado(boolean correoConfirmado) {
		this.correoConfirmado = correoConfirmado;
	}

	public float getEstatura() {
		return this.estatura;
	}

	public void setEstatura(float estatura) {
		this.estatura = estatura;
	}

	public LocalDate getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public boolean isHabilitado() {
		return this.habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNumeroDocumento() {
		return this.numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getTelefonoCelular() {
		return this.telefonoCelular;
	}

	public void setTelefonoCelular(String telefonoCelular) {
		this.telefonoCelular = telefonoCelular;
	}

	public String getTelefonoFijo() {
		return this.telefonoFijo;
	}

	public void setTelefonoFijo(String telefonoFijo) {
		this.telefonoFijo = telefonoFijo;
	}

	public List<Rol> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

	@Transient
	public List<GrantedAuthority> getGrantedAuthorities() {
		
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		getRoles().forEach( rol -> grantedAuthorities.add(new SimpleGrantedAuthority(rol.getNombre())) );
		
		return grantedAuthorities;
	}

}