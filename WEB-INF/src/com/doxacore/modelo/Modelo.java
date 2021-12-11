package com.doxacore.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@MappedSuperclass
public class Modelo{
	
	private long orden; 
	
	@CreationTimestamp 
    @Column(updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date creado;
	@Column(updatable = false)
	private String creadoUser;
	
	@CreationTimestamp 
	@Temporal(TemporalType.TIMESTAMP)
	private Date modificacion;
	private String modificacionUser;
	
	public long getOrden() {
		return orden;
	}
	public void setOrden(long orden) {
		this.orden = orden;
	}
	public Date getCreado() {
		return creado;
	}
	public void setCreado(Date creado) {
		this.creado = creado;
	}
	public String getCreadoUser() {
		return creadoUser;
	}
	public void setCreadoUser(String creadoUser) {
		this.creadoUser = creadoUser;
	}
	public Date getModificacion() {
		return modificacion;
	}
	public void setModificacion(Date modificacion) {
		this.modificacion = modificacion;
	}
	public String getModificacionUser() {
		return modificacionUser;
	}
	public void setModificacionUser(String modificacionUser) {
		this.modificacionUser = modificacionUser;
	}
	
}
