package com.doxacore.modelo;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="UsuariosRoles")
public class UsuarioRol extends Modelo implements Serializable{
	
	@EmbeddedId
	private UsuarioRolPK usuariorolpk;

	public UsuarioRolPK getUsuariorolpk() {
		return usuariorolpk;
	}

	public void setUsuariorolpk(UsuarioRolPK usuariorolpk) {
		this.usuariorolpk = usuariorolpk;
	}

}
