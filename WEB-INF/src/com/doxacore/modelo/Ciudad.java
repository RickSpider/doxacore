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
	@JoinColumn(name = "paisid")
	private Pais pais;

	@Override
	public Object[] getArrayObjectDatos() {
		
		Object[] o = {this.ciudad};
		
		return o;
	}

	@Override
	public String getStringDatos() {
		return this.ciudadid + " " + this.ciudad + " " + this.pais.getPais();
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

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}
	

	
}
