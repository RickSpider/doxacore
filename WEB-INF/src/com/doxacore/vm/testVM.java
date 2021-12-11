package com.doxacore.vm;

import org.zkoss.bind.annotation.Command;

import com.doxacore.login.UsusarioDAO;
import com.doxacore.modelo.Usuario;

public class testVM {
	
	private Usuario user = new Usuario();
	private UsusarioDAO ud = new UsusarioDAO();

	
	@Command
	public void guardar() {
		
		ud.saveOrUpdate(user.getAccount(), user.getPassword());
		
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public UsusarioDAO getUd() {
		return ud;
	}

	public void setUd(UsusarioDAO ud) {
		this.ud = ud;
	}
	
}
