package com.doxacore.vm;

import org.zkoss.bind.annotation.Command;

import com.doxacore.login.UsusarioDAO;
import com.doxacore.modelo.Usuarios;

public class testVM {
	
	private Usuarios user = new Usuarios();
	private UsusarioDAO ud = new UsusarioDAO();

	
	@Command
	public void guardar() {
		
		ud.saveOrUpdate(user.getAccount(), user.getPassword());
		
	}

	public Usuarios getUser() {
		return user;
	}

	public void setUser(Usuarios user) {
		this.user = user;
	}

	public UsusarioDAO getUd() {
		return ud;
	}

	public void setUd(UsusarioDAO ud) {
		this.ud = ud;
	}
	
}
