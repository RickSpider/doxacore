package com.doxacore.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="Paises")
public class Pais extends Modelo implements Serializable {

	private static final long serialVersionUID = -3376215750324572203L;

	@Id
	@Column(name ="paisid")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long paisid;
	private String pais;
	private String gentilicio;
	
	@Override
	public Object[] getArrayObjectDatos() {

		Object[] o = {this.pais, this.gentilicio};
		
		return o;
	}

	@Override
	public String getStringDatos() {
		return this.paisid + " " + this.pais;
	}

	public Long getPaisid() {
		return paisid;
	}

	public void setPaisid(Long paisid) {
		this.paisid = paisid;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getGentilicio() {
		return gentilicio;
	}

	public void setGentilicio(String gentilicio) {
		this.gentilicio = gentilicio;
	}

	
	
	

}
