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
import com.doxacore.modelo.Modulo;
import com.doxacore.modelo.Operacion;
import com.doxacore.util.Params;

public class ModuloVM extends TemplateViewModel {

	private List<Modulo> lModulos;
	private List<Modulo> lModulosOri;
	private Modulo moduloSelected;
	private Modulo moduloSelectedOperacion;
	private List<Operacion> lOperacionesModulos;
	private Operacion operacionSelected;
	
	private boolean opCrearModulo;
	private boolean opEditarModulo;
	private boolean opBorrarModulo;

	@Init(superclass = true)
	public void initModuloVM() {

		cargarModulos();
		inicializarFiltros();

	}

	@AfterCompose(superclass = true)
	public void afterComposeModuloVM() {

	}
	
	@Override
	protected void inicializarOperaciones() {
		
		this.opCrearModulo = this.operacionHabilitada(Params.OP_CREAR_MODULO);
		this.opEditarModulo = this.operacionHabilitada(Params.OP_EDITAR_MODULO);
		this.opBorrarModulo = this.operacionHabilitada(Params.OP_BORRAR_MODULO);
		
	}

	private void cargarModulos() {

		this.lModulos = this.reg.getAllObjectsByCondicionOrder(Modulo.class.getName(), null, "moduloid asc");
		this.lModulosOri = this.lModulos;
	}

	// seccion filtro

	private String filtroColumns[];

	private void inicializarFiltros() {

		this.filtroColumns = new String[2]; // se debe de iniciar el filtro deacuerdo a la cantidad declarada en el
											// modelo

		for (int i = 0; i < this.filtroColumns.length; i++) {

			this.filtroColumns[i] = "";

		}

	}

	@Command
	@NotifyChange("lModulos")
	public void filtrarModulo() {

		this.lModulos = this.filtrarLT(this.filtroColumns, this.lModulosOri);

	}

	// fin Seccion filtro

	// seccion modal

	private Window modal;
	private boolean editar = false;

	@Command
	public void modalModuloAgregar() {
		
		if (!this.opCrearModulo) 
			return;

		this.editar = false;
		modalModulo(-1);

	}

	@Command
	public void modalModulo(@BindingParam("moduloid") long moduloid) {

		if (moduloid != -1) {
			
			if (!this.opEditarModulo)
				return;

			this.moduloSelected = this.reg.getObjectById(Modulo.class.getName(), moduloid);
			this.editar = true;

		} else {
			
			moduloSelected = new Modulo();

		}

		modal = (Window) Executions.createComponents("/doxacore/zul/configuracion/moduloModal.zul", this.mainComponent,
				null);
		Selectors.wireComponents(modal, this, false);
		modal.doModal();

	}

	@Command
	@NotifyChange("lModulos")
	public void guardar() {

		this.reg.saveObject(moduloSelected, getCurrentUser().getAccount());

		this.moduloSelected = null;

		this.cargarModulos();

		this.modal.detach();

		if (editar) {

			Notification.show("El Modulo fue Actualizado.");
			this.editar = false;
		} else {

			Notification.show("El Modulo fue agregado.");
		}

	}

	// fin modal

	@Command
	public void borrarModuloConfirmacion(@BindingParam("modulo") Modulo modulo) {
		
		if(!this.opBorrarModulo)
			return;

		EventListener event = new EventListener() {

			@Override
			public void onEvent(Event evt) throws Exception {

				if (evt.getName().equals(Messagebox.ON_YES)) {

					borrarModulo(modulo);

				}

			}

		};

		this.mensajeEliminar("El Modulo sera eliminado. \n Continuar?", event);
	}

	private void borrarModulo(Modulo modulo) {

		this.reg.deleteObject(modulo);

		this.cargarModulos();

		BindUtils.postNotifyChange(null, null, this, "lModulos");

	}

	// Seccion Operacion

	@Command
	@NotifyChange("lOperacionesModulos")
	public void refrescarOperaciones(@BindingParam("modulo") Modulo modulo) {

		this.moduloSelectedOperacion = modulo;
		this.lOperacionesModulos = this.reg.getAllObjectsByCondicionOrder(Operacion.class.getName(),
				"moduloid = " + modulo.getModuloid(), "operacionid asc");

	}
	
	@Command
	public void modalOperacionAgregar() {

		if (this.moduloSelectedOperacion == null) {
			
			this.mensajeInfo("Seleccione un modulo.");
			return;
		}
		
		modalOperacion(-1);

	}
	
	@Command
	public void modalOperacion(@BindingParam("operacionid") long operacionid) {

		if (operacionid != -1) {

			this.operacionSelected = this.reg.getObjectById(Operacion.class.getName(), operacionid);
			this.editar = true;

		} else {

			this.operacionSelected = new Operacion();
			this.operacionSelected.setModulo(this.moduloSelectedOperacion);

		}

		modal = (Window) Executions.createComponents("/doxacore/zul/configuracion/operacionModal.zul", this.mainComponent,
				null);
		Selectors.wireComponents(modal, this, false);
		modal.doModal();

	}
	
	@Command
	@NotifyChange("lOperacionesModulos")
	public void guardarOperacion() {
		
		this.save(operacionSelected);

		this.operacionSelected = null;

		this.refrescarOperaciones(this.moduloSelectedOperacion);

		this.modal.detach();

		if (editar) {

			Notification.show("La Operación fue Actualizada.");
			this.editar = false;
		} else {

			Notification.show("La Operación fue agregada.");
		}

	}
	
	@Command
	@NotifyChange("lOperacionesModulos")
	public void borrarOperacionConfirmacion(@BindingParam("operacion") Operacion operacion) {

		this.mensajeEliminar("El Modulo sera eliminado. \n Continuar?", new EventListener() {

			@Override
			public void onEvent(Event evt) throws Exception {

				if (evt.getName().equals(Messagebox.ON_YES)) {

					borrarOperacion(operacion);

				}

			}

		});
		
	}
	
	private void borrarOperacion(Operacion operacion) {
		
		this.reg.deleteObject(operacion);

		this.refrescarOperaciones(this.moduloSelectedOperacion);;

		BindUtils.postNotifyChange(null, null, this, "lOperacionesModulos");
		
	}

	// fin seccion Operacion

	public List<Modulo> getlModulos() {
		return lModulos;
	}

	public void setlModulos(List<Modulo> lModulos) {
		this.lModulos = lModulos;
	}

	public Modulo getModuloSelected() {
		return moduloSelected;
	}

	public void setModuloSelected(Modulo moduloSelected) {
		this.moduloSelected = moduloSelected;
	}

	public String[] getFiltroColumns() {
		return filtroColumns;
	}

	public void setFiltroColumns(String[] filtroColumns) {
		this.filtroColumns = filtroColumns;
	}

	public boolean isEditar() {
		return editar;
	}

	public void setEditar(boolean editar) {
		this.editar = editar;
	}

	public List<Operacion> getlOperacionesModulos() {
		return lOperacionesModulos;
	}

	public void setlOperacionesModulos(List<Operacion> lOperacionesModulos) {
		this.lOperacionesModulos = lOperacionesModulos;
	}

	public Operacion getOperacionSelected() {
		return operacionSelected;
	}

	public void setOperacionSelected(Operacion operacionSelected) {
		this.operacionSelected = operacionSelected;
	}

	public boolean isOpCrearModulo() {
		return opCrearModulo;
	}

	public void setOpCrearModulo(boolean opCrearModulo) {
		this.opCrearModulo = opCrearModulo;
	}

	public boolean isOpEditarModulo() {
		return opEditarModulo;
	}

	public void setOpEditarModulo(boolean opEditarModulo) {
		this.opEditarModulo = opEditarModulo;
	}

	public boolean isOpBorrarModulo() {
		return opBorrarModulo;
	}

	public void setOpBorrarModulo(boolean opBorrarModulo) {
		this.opBorrarModulo = opBorrarModulo;
	}

	

}
