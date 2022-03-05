package com.doxacore.util;

import org.zkoss.zk.ui.Executions;

public class SystemInfo {

	public static String SISTEMA_PATH_ABSOLUTO;

	static {

		// DIRECTORIO_BASE_WEB = Executions.getCurrent().getDesktop().getCurrentDirectory();
		SISTEMA_PATH_ABSOLUTO = Executions.getCurrent().getDesktop().getWebApp().getRealPath("/");

	}

}
