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
@Table(name ="Ciudades")
public class Ciudad extends Modelo implements Serializable {

	private static final long serialVersionUID = 5225122407203295072L;
	
	@Id
	@Column(name ="ciudadid")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long ciudadid;
	
	private String ciudad;
	
	@ManyToOne
	@JoinColumn(name = "departamentoid")
	private Departamento departamento;

	@Override
	public Object[] getArrayObjectDatos() {
		
		Object[] o = {this.ciudad, this.departamento.getDepartamento(),  this.departamento.getPais().getPais()};
		
		return o;
	}

	@Override
	public String getStringDatos() {
		return this.ciudadid + " " + this.ciudad + " " + this.departamento.getDepartamento() + " " + this.departamento.getPais().getPais();
	}

	public Long getCiudadid() {
		return ciudadid;
	}

	public void setCiudadid(Long ciudadid) {
		this.ciudadid = ciudadid;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}


}
