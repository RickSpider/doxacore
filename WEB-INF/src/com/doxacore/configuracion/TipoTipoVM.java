package com.doxacore.configuracion;

import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.util.Notification;
import org.zkoss.zul.Window;

import com.doxacore.TemplateViewModel;
import com.doxacore.modelo.Tipo;
import com.doxacore.modelo.TipoTipo;
import com.doxacore.util.Params;

public class TipoTipoVM extends TemplateViewModel{

	private List<TipoTipo> lTipoTipos;
	private List<TipoTipo> lTipoTiposOri;
	private TipoTipo tipoTipoSelected;
	private TipoTipo tipoTipoSelectedTipo;
	private List<Tipo> lTiposTipoTipos;
	
	private boolean opCrearTipoTipo;
	private boolean opEditarTipoTipo;
	private boolean opBorrarTipoTipo;
	
	@Init(superclass = true)
	public void initTipoTipoVM() {

		cargarTipoTipos();
		inicializarFiltros();

	}

	@AfterCompose(superclass = true)
	public void afterComposeTipoTipoVM() {

	}
	
	@Override
	protected void inicializarOperaciones() {

		this.opCrearTipoTipo = this.operacionHabilitada(Params.OP_CREAR_TIPOTIPO);
		this.opEditarTipoTipo = this.operacionHabilitada(Params.OP_EDITAR_TIPOTIPO);
		this.opBorrarTipoTipo = this.operacionHabilitada(Params.OP_BORRAR_TIPOTIPO);
		
	}
	
	private void cargarTipoTipos() {

		this.lTipoTipos = this.reg.getAllObjectsByCondicionOrder(TipoTipo.class.getName(), null, "tipotipoid asc");
		this.lTipoTiposOri = this.lTipoTipos;
	}

	// seccion filtro

	private String filtroColumns[];

	private void inicializarFiltros() {

		this.filtroColumns = new String[3]; // se debe de iniciar el filtro deacuerdo a la cantidad declarada en el
											// modelo

		for (int i = 0; i < this.filtroColumns.length; i++) {

			this.filtroColumns[i] = "";

		}

	}

	@Command
	@NotifyChange("lTipoTipos")
	public void filtrarTipoTipo() {

		this.lTipoTipos = this.filtrarLT(this.filtroColumns, this.lTipoTiposOri);

	}
	
	// seccion modal

		private Window modal;
		private boolean editar = false;

		@Command
		public void modalTipoTipoAgregar() {
			
			if (!this.opCrearTipoTipo) 
				return;

			this.editar = false;
			modalTipotipo(-1);

		}

		@Command
		public void modalTipotipo(@BindingParam("tipotipoid") long tipotipoid) {

			if (tipotipoid != -1) {
				
				if (!this.opEditarTipoTipo)
					return;

				this.tipoTipoSelected = this.reg.getObjectById(TipoTipo.class.getName(), tipotipoid);
				this.editar = true;

			} else {
				
				tipoTipoSelected = new TipoTipo();

			}

			modal = (Window) Executions.createComponents("/corezul/configuracion/tipoTipoModal.zul", this.mainComponent,
					null);
			Selectors.wireComponents(modal, this, false);
			modal.doModal();

		}

		@Command
		@NotifyChange("lTipoTipos")
		public void guardar() {

			this.save(tipoTipoSelected);

			this.tipoTipoSelected = null;

			this.cargarTipoTipos();

			this.modal.detach();

			if (editar) {

				Notification.show("El Modulo fue Actualizado.");
				this.editar = false;
			} else {

				Notification.show("El Modulo fue agregado.");
			}

		}

		public List<TipoTipo> getlTipoTipos() {
			return lTipoTipos;
		}

		public void setlTipoTipos(List<TipoTipo> lTipoTipos) {
			this.lTipoTipos = lTipoTipos;
		}

		public TipoTipo getTipoTipoSelected() {
			return tipoTipoSelected;
		}

		public void setTipoTipoSelected(TipoTipo tipoTipoSelected) {
			this.tipoTipoSelected = tipoTipoSelected;
		}

		public TipoTipo getTipoTipoSelectedTipo() {
			return tipoTipoSelectedTipo;
		}

		public void setTipoTipoSelectedTipo(TipoTipo tipoTipoSelectedTipo) {
			this.tipoTipoSelectedTipo = tipoTipoSelectedTipo;
		}

		public List<Tipo> getlTiposTipoTipos() {
			return lTiposTipoTipos;
		}

		public void setlTiposTipoTipos(List<Tipo> lTiposTipoTipos) {
			this.lTiposTipoTipos = lTiposTipoTipos;
		}

		public boolean isOpCrearTipoTipo() {
			return opCrearTipoTipo;
		}

		public void setOpCrearTipoTipo(boolean opCrearTipoTipo) {
			this.opCrearTipoTipo = opCrearTipoTipo;
		}

		public boolean isOpEditarTipoTipo() {
			return opEditarTipoTipo;
		}

		public void setOpEditarTipoTipo(boolean opEditarTipoTipo) {
			this.opEditarTipoTipo = opEditarTipoTipo;
		}

		public boolean isOpBorrarTipoTipo() {
			return opBorrarTipoTipo;
		}

		public void setOpBorrarTipoTipo(boolean opBorrarTipoTipo) {
			this.opBorrarTipoTipo = opBorrarTipoTipo;
		}

		public boolean isEditar() {
			return editar;
		}

		public void setEditar(boolean editar) {
			this.editar = editar;
		}

		public String[] getFiltroColumns() {
			return filtroColumns;
		}

		public void setFiltroColumns(String[] filtroColumns) {
			this.filtroColumns = filtroColumns;
		}

		// fin modal
	
	

}
