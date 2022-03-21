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
import com.doxacore.modelo.Operacion;
import com.doxacore.modelo.Rol;
import com.doxacore.modelo.RolOperacion;
import com.doxacore.modelo.Usuario;
import com.doxacore.modelo.UsuarioRol;

public class RolVM extends TemplateViewModel{
	
	private List<Rol> lRoles; 
	private List<Rol> lRolesOri;
	private Rol rolSelected;
	
	@Init(superclass = true)
	public void initRolVM() {

		cargarRoles();
		inicializarFiltros();

	}

	@AfterCompose(superclass = true)
	public void afterComposeRolVM() {

	}
	

	private void cargarRoles() {

		this.lRoles = this.reg.getAllObjectsByCondicionOrder(Rol.class.getName(), null, "Rolid asc");
		this.lRolesOri = this.lRoles;
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
	@NotifyChange("lRoles")
	public void filtrarRol() {

		this.lRoles = this.filtrarLT(this.filtroColumns, this.lRolesOri);

	}
	
	//fin seccion 
	
	//seccion modal
	
	private Window modal;
	private boolean editar = false;

	@Command
	public void modalRolAgregar() {

		this.editar = false;
		modalRol(-1);

	}

	@Command
	public void modalRol(@BindingParam("rolid") long rolid) {

		if (rolid != -1) {

			this.rolSelected = this.reg.getObjectById(Rol.class.getName(), rolid);
			this.editar = true;

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
		
		if (editar) {
			
			Notification.show("El Rol fue Actualizado.");
			this.editar = false;
		}else {
			
			Notification.show("El Rol fue agregado.");
		}
		
		

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
	
	//operacion
	
	private List<RolOperacion> lOperacionesRoles = null;
	private Rol rolSelectedOperacion = null;
	
	@Command
	@NotifyChange({"lOperacionesRoles","buscarRol"})
	public void refrescarOperaciones(@BindingParam("rol") Rol rol) {

		this.rolSelectedOperacion = rol;
		this.lOperacionesRoles = this.reg.getAllObjectsByCondicionOrder(RolOperacion.class.getName(),
				"rolid = " + rol.getRolid(), "operacionid asc");
		
		this.buscarSelectedOperacion = null;
		this.buscarOperacion="";

	}
	
	@Command
	public void borrarOperacionConfirmacion(@BindingParam("RolOperacion") RolOperacion ro) {
		
		
		this.mensajeEliminar("La Operacion "+ro.getOperacion().getOperacion()+" sera removido del Rol "+ro.getRol().getRol()+" \n Continuar?",  
				new EventListener() {

			@Override
			public void onEvent(Event evt) throws Exception {

				if (evt.getName().equals(Messagebox.ON_YES)) {

					borrarRolOperacion(ro);

				}

			}

		});

	}
	
	private void borrarRolOperacion(RolOperacion ro) {
		
		this.reg.deleteObject(ro);

		this.refrescarOperaciones(this.rolSelectedOperacion);

		BindUtils.postNotifyChange(null, null, this, "lOperacionesRoles");
		
	}
	
	
	//fin operacion
	
	// buscador de Operacion
	
	

	private List<Object[]> lOperacionesbuscarOri = null;
	private List<Object[]> lOperacionesBuscar = null;
	private Operacion buscarSelectedOperacion = null;
	private String buscarOperacion = "";

	@Command
	@NotifyChange("lOperacionesBuscar")
	public void filtrarOperacionBuscar() {

		this.lOperacionesBuscar = this.filtrarListaObject(buscarOperacion, this.lOperacionesbuscarOri);

	}
	
	@Command
	@NotifyChange("lOperacionesBuscar")
	public void generarListaBuscarOperacion() {

		if (this.rolSelectedOperacion == null) {

			this.mensajeInfo("Selecciona un Rol.");

			return;
		}
		
		StringBuffer ids = new StringBuffer("");
		
		for (int i = 0 ;  i < this.lOperacionesRoles.size() ; i++) {
			
			ids.append(this.lOperacionesRoles.get(i).getOperacion().getOperacionid());
			
			if (i != this.lOperacionesRoles.size() - 1) {
				
				ids.append(", ");
				
			}
			
		}

		String condicion = "";
		
		if (ids.length() > 0) {
			
			condicion = "operacionid not in ("+ids+") AND ";
			
		}
		
		this.lOperacionesBuscar = this.reg.sqlNativo(
				"SELECT o.operacionid, o.operacion, o.descripcion FROM operaciones o WHERE "+condicion+"o.habilitado = true ORDER by o.operacionid asc;");
		this.lOperacionesbuscarOri = this.lOperacionesBuscar;
	}
	
	@Command
	@NotifyChange("buscarOperacion")
	public void onSelectOperacion(@BindingParam("id") long id) {
		
		this.buscarSelectedOperacion = this.reg.getObjectById(Operacion.class.getName(), id);		
		this.buscarOperacion = this.buscarSelectedOperacion.getOperacion();
		
	}
	
	@Command
	@NotifyChange({"lOperacionesRoles","buscarOperacion"})
	public void agregarOperacion() {
		
		if (this.buscarSelectedOperacion == null) {
			
			this.mensajeInfo("Selecciona una Operacion para agregar.");
			
			return;
			
		}
		
		for(RolOperacion x : this.lOperacionesRoles) {
			
			if (this.buscarSelectedOperacion.getOperacionid() == x.getOperacion().getOperacionid()) {
				
				this.mensajeError("El Usuario ya tiene la Operacion"+x.getOperacion().getOperacion());
				
				return;
				
			}
			
		}
		
		RolOperacion ro = new RolOperacion();
		ro.setOperacion(this.buscarSelectedOperacion);
		ro.setRol(this.rolSelectedOperacion);
		this.save(ro);

		this.refrescarOperaciones(this.rolSelectedOperacion);

	}

	
	//fin buscador de opreacion 

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

	public List<Object[]> getlOperacionesBuscar() {
		return lOperacionesBuscar;
	}

	public void setlOperacionesBuscar(List<Object[]> lOperacionesBuscar) {
		this.lOperacionesBuscar = lOperacionesBuscar;
	}

	public String getBuscarOperacion() {
		return buscarOperacion;
	}

	public void setBuscarOperacion(String buscarOperacion) {
		this.buscarOperacion = buscarOperacion;
	}

	public List<RolOperacion> getlOperacionesRoles() {
		return lOperacionesRoles;
	}

	public void setlOperacionesRoles(List<RolOperacion> lOperacionesRoles) {
		this.lOperacionesRoles = lOperacionesRoles;
	}
	
	
	
	
}
