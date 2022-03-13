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

	private static final long serialVersionUID = 7196771350434502538L;

	@Id
	@Column(name ="ROLID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long rolid;
	
	private String rol;
	
	private String descripcion;

	public Long getRolid() {
		return rolid;
	}

	public void setRolid(Long rolid) {
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

	@Override
	public String getStringDatos() {
		return this.rolid + " " + this.rol + " " + this.descripcion;
	}

	@Override
	public Object[] getArrayObjectDatos() {
		
		Object[] o = {this.rol, this.descripcion};
		
		return o;
	}

	
	
}
