package com.doxacore.vm;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import com.doxacore.TemplateViewModel;
import com.doxacore.modelo.Usuario;

public class testVM extends TemplateViewModel{
	
	private Usuario user;
	private Usuario userLogin;


	 @Init
	 public void init() {
		 
		 user = new Usuario();

		 
	 }
	
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
