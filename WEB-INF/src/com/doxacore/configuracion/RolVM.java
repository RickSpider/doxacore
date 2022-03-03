package com.doxacore.configuracion;

import java.util.List;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.util.Notification;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import com.doxacore.TemplateViewModel;
import com.doxacore.modelo.Rol;

public class RolVM extends TemplateViewModel{
	
	private List<Rol> lRoles;  
	private Rol rolSelected;
	
	@Init(superclass = true)
	public void initRolVM() {

		cargarRoles();

	}

	@AfterCompose(superclass = true)
	public void afterComposeRolVM() {

	}
	

	private void cargarRoles() {

		this.lRoles = this.reg.getAllObjectsByCondicionOrder(Rol.class.getName(), null, "Rolid asc");

	}
	
	//seccion modal
	
	private Window modal;

	@Command
	public void modalRolAgregar() {

		modalRol(-1);

	}

	@Command
	public void modalRol(@BindingParam("rolid") long rolid) {

		if (rolid != -1) {

			this.rolSelected = this.reg.getObjectById(Rol.class.getName(), rolid);

		} else {

			rolSelected = new Rol();

		}

		modal = (Window) Executions.createComponents("/corezul/configuracion/rolModal.zul", this.mainComponent,
				null);
		Selectors.wireComponents(modal, this, false);
		modal.doModal();

	}

	@Command
	@NotifyChange("lRoles")
	public void guardar() {

		this.reg.saveObject(rolSelected, getCurrentUser().getAccount());

		this.rolSelected = null;

		this.cargarRoles();

		this.modal.detach();
		
		Notification.show("El Rol fue agregado.");

	}

	
	//fin modal
	
	@Command
	public void borrarRolConfirmacion(@BindingParam("rol") Rol rol) {
		
		EventListener event = new EventListener () {

			@Override
			public void onEvent(Event evt) throws Exception {
				
				if (evt.getName().equals(Messagebox.ON_YES)) {
					
					borrarRol(rol);
					
				}
				
			}

		};
		
		this.mensajeEliminar("El rol sera eliminado. \n Continuar?", event);
	}
	
	
	private void borrarRol (Rol rol) {
		
		this.reg.deleteObject(rol);
		
		this.cargarRoles();
		
		BindUtils.postNotifyChange(null,null,this,"lRoles");
		
	}

	public List<Rol> getlRoles() {
		return lRoles;
	}

	public void setlRoles(List<Rol> lRoles) {
		this.lRoles = lRoles;
	}

	public Rol getRolSelected() {
		return rolSelected;
	}

	public void setRolSelected(Rol rolSelected) {
		this.rolSelected = rolSelected;
	}

	public Window getModal() {
		return modal;
	}

	public void setModal(Window modal) {
		this.modal = modal;
	}
	
	
	
	
}
