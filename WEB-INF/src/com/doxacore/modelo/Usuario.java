package com.doxacore.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

/**
 * User entity
 */

@Entity
@Table(name = "usuarios")
public class Usuario extends Modelo implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "USUARIOID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private long usuarioid;

	@Column(name = "account")
	private String account;
	private String fullName;
	private String password;
	private String email;
	
	@ColumnDefault("false")
	private boolean activo = false;

	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Usuario(String account, String password, String fullName, String email) {
		super();
		this.account = account;
		this.password = password;
		this.fullName = fullName;
		this.email = email;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {

		this.account = account;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		return true;
	}

	public static Usuario clone(Usuario user) {
		try {
			return (Usuario) user.clone();
		} catch (CloneNotSupportedException e) {
			// not possible
		}
		return null;
	}

	public long getUsuarioid() {
		return usuarioid;
	}

	public void setUsuarioid(long usuarioid) {
		this.usuarioid = usuarioid;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

}
