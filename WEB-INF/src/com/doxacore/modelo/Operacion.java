package com.doxacore.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;


@Entity
@Table(name ="operaciones")
public class Operacion extends Modelo implements Serializable {
	
	private static final long serialVersionUID = 4339329983092294024L;

	@Id
	@Column(name ="operacionid")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long operacionid;
	
	@Column(unique=true)
	private String operacion;
	
	private String descripcion;
	
	@ManyToOne
	@JoinColumn(name = "moduloid")
	private Modulo modulo;
	
	@ColumnDefault("false")
	private boolean abreModulo;

	@Override
	public String getStringDatos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] getArrayObjectDatos() {
		// TODO Auto-generated method stub
		return null;
	}

	public Long getOperacionid() {
		return operacionid;
	}

	public void setOperacionid(Long operacionid) {
		this.operacionid = operacionid;
	}

	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Modulo getModulo() {
		return modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}

	public Boolean getAbreModulo() {
		return abreModulo;
	}

	public void setAbreModulo(Boolean abreModulo) {
		this.abreModulo = abreModulo;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
