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
import com.doxacore.modelo.Modulo;

public class ModuloVM extends TemplateViewModel{
	
	private List<Modulo> lModulos; 
	private List<Modulo> lModulosOri;
	private Modulo moduloSelected;
	
	@Init(superclass = true)
	public void initModuloVM() {

		cargarModulos();
		inicializarFiltros();

	}

	@AfterCompose(superclass = true)
	public void afterComposeModuloVM() {

	}
	

	private void cargarModulos() {

		this.lModulos = this.reg.getAllObjectsByCondicionOrder(Modulo.class.getName(), null, "moduloid asc");
		this.lModulosOri = this.lModulos;
	}
	
	//seccion filtro 
	
	private String filtroColumns[];
	
	private void inicializarFiltros(){
		
		this.filtroColumns = new String[2]; // se debe de iniciar el filtro deacuerdo a la cantidad declarada en el modelo
		
		for (int i = 0 ; i<this.filtroColumns.length; i++) {
			
			this.filtroColumns[i] = "";
			
		}
		
	}
	
	@Command
	@NotifyChange("lModulos")
	public void filtrarModulo() {

		this.lModulos = this.filtrarLT(this.filtroColumns, this.lModulosOri);

	}
	
	//fin Seccion filtro
	
	//seccion modal
	
		private Window modal;
		private boolean editar = false;

		@Command
		public void modalModuloAgregar() {

			modalModulo(-1);

		}

		@Command
		public void modalModulo(@BindingParam("moduloid") long moduloid) {

			if (moduloid != -1) {

				this.moduloSelected = this.reg.getObjectById(Modulo.class.getName(), moduloid);
				this.editar = true;

			} else {

				moduloSelected = new Modulo();

			}

			modal = (Window) Executions.createComponents("/corezul/configuracion/moduloModal.zul", this.mainComponent,
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
			}else {
				
				Notification.show("El Modulo fue agregado.");
			}
			
			

		}

		//fin modal
		
		@Command
		public void borrarModuloConfirmacion(@BindingParam("modulo") Modulo modulo) {
			
			EventListener event = new EventListener () {

				@Override
				public void onEvent(Event evt) throws Exception {
					
					if (evt.getName().equals(Messagebox.ON_YES)) {
						
						borrarModulo(modulo);
						
					}
					
				}

			};
			
			this.mensajeEliminar("El Modulo sera eliminado. \n Continuar?", event);
		}
		
		
		private void borrarModulo (Modulo modulo) {
			
			this.reg.deleteObject(modulo);
			
			this.cargarModulos();
			
			BindUtils.postNotifyChange(null,null,this,"lModulos");
			
		}

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
		
		
}
