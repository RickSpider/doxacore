package com.doxacore.util;

import com.doxacore.modelo.SistemaPropiedad;

public class Control {
	
	protected Register reg = new Register();
	
	public Control() {
		
		this.reg = new Register();
		
	}
	
	protected SistemaPropiedad getSistemaPropiedad(String clave) {
		
		SistemaPropiedad sp = this.reg.getObjectByColumnString(SistemaPropiedad.class.getName(), "clave", clave);
		
		return sp;
		
	}

}
