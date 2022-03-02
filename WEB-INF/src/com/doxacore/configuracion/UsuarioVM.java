package com.doxacore.configuracion;

import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import com.doxacore.TemplateViewModel;
import com.doxacore.modelo.Usuario;

public class UsuarioVM extends TemplateViewModel {

	private List<Usuario> lUsuarios = null;
	private Usuario usuarioSelected = null;

	@Init(superclass = true)
	public void initUsuarioRolVM() {

		cargarUsuarios();

	}

	@AfterCompose(superclass = true)
	public void afterComposeUsuarioRolVM() {

	}

	private void cargarUsuarios() {

		this.lUsuarios = this.r.getAllObjectsByCondicionOrder(Usuario.class.getName(), null, "usuarioid asc");

	}

	// Seccion modal

	private Window modal;

	@Command
	public void modalUsuarioAgregar() {

		modalUsuario(-1);

	}

	@Command
	@NotifyChange("usuarioSelected")
	public void modalUsuario(@BindingParam("usuarioid") long usuarioid) {

		if (usuarioid != -1) {

			this.usuarioSelected = this.r.getObjectById(Usuario.class.getName(), usuarioid);

		} else {

			usuarioSelected = new Usuario();

		}

		modal = (Window) Executions.createComponents("/corezul/configuracion/usuarioModal.zul", this.mainComponent,
				null);
		Selectors.wireComponents(modal, this, false);
		modal.doModal();

	}

	@Command
	@NotifyChange("lUsuarios")
	public void guardar() {

		this.r.saveObject(usuarioSelected, getCurrentUser().getAccount());

		this.usuarioSelected = null;

		this.cargarUsuarios();

		this.modal.detach();

	}

	// Fin Seccion Modal
	
	@Command
	@NotifyChange("lUsuarios")
	public void borrarUsuario(@BindingParam("usuario") Usuario u) {
		
		if (this.mensajeSiNo("Borrar el Usuario?")) {
			
			this.r.deleteObject(u);
			
		}
		
		this.cargarUsuarios();
		
		
	}

	public List<Usuario> getlUsuarios() {
		return lUsuarios;
	}

	public void setlUsuarios(List<Usuario> lUsuarios) {
		this.lUsuarios = lUsuarios;
	}

	public Usuario getUsuarioSelected() {
		return usuarioSelected;
	}

	public void setUsuarioSelected(Usuario usuarioSelected) {
		this.usuarioSelected = usuarioSelected;
	}

}
