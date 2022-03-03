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
	
	protected Register reg;
	protected Component mainComponent;
	

	@Init(superclass = true)
	public void initTemplateViewModel(@ContextParam(ContextType.VIEW) Component view) {
		
		this.reg = new Register();
		this.mainComponent = view;
		
	}
	
	@AfterCompose(superclass = true)
	public void afterComposeTemplateViewModel () {
		
		
	}
	
	protected Usuario getCurrentUser () {
		
		UsuarioCredencial usuarioCredencial = (UsuarioCredencial) Sessions.getCurrent().getAttribute("userCredential");
		
		Usuario currentUser = this.reg.getObjectByColumnString(Usuario.class.getName(), "account", usuarioCredencial.getAccount());
		
		return currentUser;
		
	}
	
	public void mensajeInfo(String texto) {
		org.zkoss.zul.Messagebox.Button b = Messagebox.show(texto, "Información",
				new Messagebox.Button[] { Messagebox.Button.OK }, Messagebox.INFORMATION, null);
	}
	
	public void mensajeSiNo(String texto, String titulo, EventListener event ) {

		int b = Messagebox.show(texto, titulo,
				Messagebox.YES | Messagebox.NO, Messagebox.QUESTION, event);

	
	}

	
	public void mensajeAgregar(String texto, EventListener event) {

		mensajeSiNo(texto, "Agregar",  event);
		
	}
	
	
	public void mensajeEliminar(String texto, EventListener event) {

		mensajeSiNo(texto, "Eliminar", event);
		
	}
	
	public void mensajeError(String texto) {
		org.zkoss.zul.Messagebox.Button b = Messagebox.show(texto, "Error",
				new Messagebox.Button[] { Messagebox.Button.OK }, Messagebox.ERROR, null);
	}
	
}
