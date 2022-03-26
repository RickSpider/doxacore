package com.doxacore.modelo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class TipoTipo extends Modelo{
	
	@Id
	@Column(name ="tipotipoid")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long tipotipoid;
	private String tipotipo;
	private String descripcion;
	
	@Column(unique = true)
	private String sigla;
	
	public Long getTipotipoid() {
		return tipotipoid;
	}
	public void setTipotipoid(Long tipotipoid) {
		this.tipotipoid = tipotipoid;
	}
	public String getTipotipo() {
		return tipotipo;
	}
	public void setTipotipo(String tipotipo) {
		this.tipotipo = tipotipo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
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
	
	
	

}
