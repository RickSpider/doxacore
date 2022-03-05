package com.doxacore.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name ="roles")
public class Rol extends Modelo implements Serializable{

	
	@Id
	@Column(name ="ROLID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long rolid;
	
	private String rol;
	
	private String descripcion;

	public long getRolid() {
		return rolid;
	}

	public void setRolid(long rolid) {
		this.rolid = rolid;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
	
}
