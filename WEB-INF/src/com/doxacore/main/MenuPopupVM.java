package com.doxacore.main;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.util.Notification;
import org.zkoss.zul.Button;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import com.doxacore.TemplateViewModel;
import com.doxacore.modelo.Usuario;
import com.doxacore.util.Register;
import com.doxacore.util.UtilStaticMetodos;

public class MenuPopupVM {

	private String currentUser;
	private String currentPass;
	private String newPass;
	private String newPassControl;
	protected Component mainComponent;

	@Init(superclass = true)
	public void initMenuPopupVM(@ContextParam(ContextType.VIEW) Component view) {

		this.mainComponent = view;

	}

	@AfterCompose(superclass = true)
	public void afterComposeMenuPopupVM() {

	}

	private Window modal;

	@Command
	public void cambiarPass(@BindingParam("user") String user) {

		this.currentUser = user;

		this.currentPass = "";
		this.newPass = "";
		this.newPassControl = "";

		modal = (Window) Executions.createComponents("/doxacore/zul/main/cambiarPassModal.zul", this.mainComponent,
				null);

		Selectors.wireComponents(modal, this, false);
		modal.doModal();
	}

	@Command
	@NotifyChange({"currentPass","newPass","newPassControl"})
	public void guardarPass(@BindingParam("btnGuardar") Button btnGuardar) {

		Register reg = new Register();

		Usuario user = reg.getObjectByColumnString(Usuario.class.getName(), "account", this.currentUser);

		this.currentPass = UtilStaticMetodos.getSHA256(this.currentPass);

		if (this.currentPass.compareTo(user.getPassword()) != 0) {

			Notification.show("Contraseña actual incorrecta.", "error", btnGuardar, "after_start", 2000, false);

			this.currentPass = "";
			this.newPass = "";
			this.newPassControl = "";

			return;

		}
		
		if (this.newPass.length() == 0) {

			Notification.show("La contraseña no debe estar vacia.", "error", btnGuardar, "after_start", 2000, false);

			this.currentPass = "";
			this.newPass = "";
			this.newPassControl = "";
			return;
		}

		if (this.newPass.compareTo(this.newPassControl) != 0) {

			Notification.show("Contraseñas no coinciden.", "error", btnGuardar, "after_start", 2000, false);

			this.newPass = "";
			this.newPassControl = "";
			return;
		}
		
		user.setPassword(UtilStaticMetodos.getSHA256(this.newPass)); 
		
		reg.saveObject(user, this.currentUser);
		
		this.modal.detach();
		
		Notification.show("Contraseña Actualizada con exito.");

	}

	public String getCurrentPass() {
		return currentPass;
	}

	public void setCurrentPass(String currentPass) {
		this.currentPass = currentPass;
	}

	public String getNewPass() {
		return newPass;
	}

	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}

	public String getNewPassControl() {
		return newPassControl;
	}

	public void setNewPassControl(String newPassControl) {
		this.newPassControl = newPassControl;
	}

}
