package com.doxacore;


import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Messagebox;

import com.doxacore.login.UsuarioCredencial;
import com.doxacore.modelo.Usuario;
import com.doxacore.util.Register;

public abstract class TemplateViewModel {
	
	protected Register r;
	protected Component mainComponent;
	

	@Init(superclass = true)
	public void initTemplateViewModel(@ContextParam(ContextType.VIEW) Component view) {
		
		this.r = new Register();
		this.mainComponent = view;
		
	}
	
	@AfterCompose(superclass = true)
	public void afterComposeTemplateViewModel () {
		
		
	}
	
	protected Usuario getCurrentUser () {
		
		UsuarioCredencial usuarioCredencial = (UsuarioCredencial) Sessions.getCurrent().getAttribute("userCredential");
		
		Usuario currentUser = this.r.getObjectByColumnString(Usuario.class.getName(), "account", usuarioCredencial.getAccount());
		
		return currentUser;
		
	}
	
	protected boolean mensajeSiNo(String mensaje) {

		int mb = Messagebox.show(mensaje, "Mensaje de Confirmación", Messagebox.YES | Messagebox.NO, Messagebox.QUESTION);
		
		if (mb == Messagebox.YES) {
			
			return true;
			
		}
		
		return false;
		
	}
	
}
