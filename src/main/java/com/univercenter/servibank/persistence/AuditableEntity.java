package com.univercenter.servibank.persistence;

import java.util.Date;

import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;
import org.joda.time.DateTime;
import org.springframework.data.domain.Auditable;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.univercenter.servibank.persistence.model.Usuario;


/**
 * Clase abstracta base para las entidades a auditar. Contiene y audita los siguientes campos:
 * <ul>
 *  <li>{@code createdBy}
 *  <li>{@code createdDate}
 *  <li>{@code lastModifiedBy}
 *  <li>{@code lastModifiedDate}
 *  </ul>
 * 
 * @author David Aldana
 * @author <a href="http://www.univercenter.com">Univercenter</a>
 * @since 2017.03
 * 
 * @see AuditingEntityListener
 * @see AbstractPersistable
 * @see Auditable
 * @see Audited
 */
@Audited
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditableEntity extends AbstractPersistable<Long> implements Auditable<Usuario, Long> {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Usuario createdBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Usuario lastModifiedBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;

	public Usuario getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(final Usuario createdBy) {
		this.createdBy = createdBy;
	}

	public Usuario getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(final Usuario lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public DateTime getCreatedDate() {

		return null == createdDate ? null : new DateTime(createdDate);
	}

	public void setCreatedDate(final DateTime createdDate) {

		this.createdDate = null == createdDate ? null : createdDate.toDate();
	}

	public DateTime getLastModifiedDate() {

		return null == lastModifiedDate ? null : new DateTime(lastModifiedDate);
	}
	
	public void setLastModifiedDate(final DateTime lastModifiedDate) {

		this.lastModifiedDate = null == lastModifiedDate ? null : lastModifiedDate.toDate();
	}
}