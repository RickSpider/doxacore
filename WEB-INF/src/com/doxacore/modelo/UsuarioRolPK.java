package com.doxacore.modelo;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class UsuarioRolPK implements Serializable {

	@ManyToOne
	@JoinColumn(name="usuarioid")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="rolid")
	private Rol rol;
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Rol getRol() {
		return rol;
	}
	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
	
	
}
