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
import com.doxacore.modelo.Usuario;

public class UsuarioVM extends TemplateViewModel {

	private List<Usuario> lUsuarios = null;
	private Usuario usuarioSelected = null;

	@Init(superclass = true)
	public void initUsuarioVM() {

		cargarUsuarios();

	}

	@AfterCompose(superclass = true)
	public void afterComposeUsuarioVM() {

	}

	private void cargarUsuarios() {

		this.lUsuarios = this.reg.getAllObjectsByCondicionOrder(Usuario.class.getName(), null, "usuarioid asc");

	}

	// Seccion modal

	private Window modal;
	private boolean editar = false;

	@Command
	public void modalUsuarioAgregar() {

		modalUsuario(-1);

	}

	@Command
	public void modalUsuario(@BindingParam("usuarioid") long usuarioid) {

		if (usuarioid != -1) {

			this.usuarioSelected = this.reg.getObjectById(Usuario.class.getName(), usuarioid);
			this.editar = true;

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

		this.reg.saveObject(usuarioSelected, getCurrentUser().getAccount());

		this.usuarioSelected = null;

		this.cargarUsuarios();

		this.modal.detach();

		if (editar) {

			Notification.show("El Usuario fue Actualizado.");
			this.editar=false;

		} else {

			Notification.show("El Usuario fue agregado.");
		}

		

	}

	// Fin Seccion Modal

	@Command
	public void borrarUsuarioConfirmacion(@BindingParam("usuario") Usuario u) {

		EventListener event = new EventListener() {

			@Override
			public void onEvent(Event evt) throws Exception {

				if (evt.getName().equals(Messagebox.ON_YES)) {

					borrarUsuario(u);

				}

			}

		};

		this.mensajeEliminar("El usuario sera eliminado. \n Continuar?", event);
	}

	private void borrarUsuario(Usuario u) {

		this.reg.deleteObject(u);

		this.cargarUsuarios();

		BindUtils.postNotifyChange(null, null, this, "lUsuarios");

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

	public boolean isEditar() {
		return editar;
	}

	public void setEditar(boolean editar) {
		this.editar = editar;
	}

}
