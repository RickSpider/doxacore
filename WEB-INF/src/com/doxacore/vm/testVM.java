package com.doxacore.vm;

import org.zkoss.bind.annotation.Command;

import com.doxacore.login.UsusarioDAO;
import com.doxacore.modelo.Register;
import com.doxacore.modelo.Usuario;

public class testVM {
	
	private Usuario user = new Usuario();
	//private UsusarioDAO ud = new UsusarioDAO();
	private Register r = new Register();

	
	@Command
	public void guardar() {
		
		r.saveObject(user, "sys");
		
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}


	
}
