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


@Entity
@Table(name ="Departamentos")
public class Departamento extends Modelo implements Serializable{

	private static final long serialVersionUID = -2871086326920726533L;

	@Id
	@Column(name ="departamentoid")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long departamentoid;
	
	private String departamento;
	
	@ManyToOne
	@JoinColumn(name = "paisid")
	private Pais pais;
	
	
	public Long getDepartamentoid() {
		return departamentoid;
	}
	public void setDepartamentoid(Long departamentoid) {
		this.departamentoid = departamentoid;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	
	@Override
	public String getStringDatos() {
		
		return this.departamentoid + " " +this.departamento + " " + this.pais.getPais();
		
	}
	
	@Override
	public Object[] getArrayObjectDatos() {
		Object[] o = {this.departamento, this.pais.getPais()};
		return o;
	}
	public Pais getPais() {
		return pais;
	}
	public void setPais(Pais pais) {
		this.pais = pais;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
