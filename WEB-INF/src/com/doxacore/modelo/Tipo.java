package com.doxacore.modelo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Tipo extends Modelo{

	@Id
	@Column(name ="tipoid")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long tipoid;
	
	private String tipo;
	
	private String descripcion;

	@Column(unique = true)
	private String sigla;
	
	@ManyToOne
	@JoinColumn(name = "tipotipoid")
	private TipoTipo tipotipo;
	
	public Long getTipoid() {
		return tipoid;
	}

	public void setTipoid(Long tipoid) {
		this.tipoid = tipoid;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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

	public TipoTipo getTipotipo() {
		return tipotipo;
	}

	public void setTipotipo(TipoTipo tipotipo) {
		this.tipotipo = tipotipo;
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