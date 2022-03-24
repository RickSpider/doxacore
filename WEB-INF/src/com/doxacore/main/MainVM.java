package com.doxacore.main;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Sessions;

import com.doxacore.login.UsuarioCredencial;
import com.doxacore.main.menu.NavigationPage;
import com.doxacore.main.menu.NavigationTitle;
import com.doxacore.modelo.Modulo;
import com.doxacore.modelo.Operacion;
import com.doxacore.util.Register;
import com.doxacore.util.UtilMetodos;

public class MainVM {

	private List<NavigationTitle> menus = new ArrayList<NavigationTitle>();
	private NavigationPage currentPage;
	private Map<String, Map<String, NavigationPage>> pageMap;
	private List<Modulo> lModulos = new ArrayList<Modulo>();

	@Init
	public void init() {

		initListaModulos();
		initPageMap();

		currentPage = pageMap.get("Main").get("Blank");

		pageMap.get("Main").values().size();
	}

	private void initListaModulos() {

		Register reg = new Register();

		UtilMetodos utilMetodos = new UtilMetodos();

		UsuarioCredencial usuarioCredencial = (UsuarioCredencial) Sessions.getCurrent().getAttribute("userCredential");

		String UsuarioOperacionesSQL = utilMetodos.getCoreSql("usuarioOperaciones.sql").replace("?1", "true")
				.replace("?2", usuarioCredencial.getAccount());

		List<Object[]> lUsuariosOperaciones = reg.sqlNativo(UsuarioOperacionesSQL);

		List<Operacion> lOperaciones = reg.getAllObjectsByCondicionOrder(Operacion.class.getName(), "abremodulo = true",
				null);

		for (Operacion o : lOperaciones) {

			if (o.getModulo().isHabilitado()) {

				for (Object[] obj : lUsuariosOperaciones) {

					if (o.getOperacion().compareTo(obj[0].toString()) == 0) {

						lModulos.add(o.getModulo());

					}

				}

			}

		}

	}

	@Command
	public void navigatePage(@BindingParam("target") NavigationPage targetPage) {

		BindUtils.postNotifyChange(null, null, currentPage, "selected");
		this.currentPage = targetPage;
		BindUtils.postNotifyChange(null, null, this, "currentPage");

	}

	public NavigationPage getCurrentPage() {
		return currentPage;
	}

	public Map<String, Map<String, NavigationPage>> getPageMap() {
		return pageMap;
	}

	private void initPageMap() {
		pageMap = new LinkedHashMap<String, Map<String, NavigationPage>>();

		this.menus.add(new NavigationTitle("Main", true, "z-icon-home"));
		addPage("Main", "Blank", "/corezul/blank.zul");

		this.menus.add(new NavigationTitle("Configuracion", true, "z-icon-gear"));

		for (Modulo m : lModulos) {

			System.out.println("cargado Modulo " + m.getModulo());
			addPage(m.getMenu(), m.getTitulo(), m.getPath(), m.getModulo());

		}

	}

	public boolean menuVisible(@BindingParam("size") int size) {

		if (size == 0)
			return false;

		return true;
	}

	private void addPage(String title, String subTitle, String includeUri) {
		addPage(title, subTitle, includeUri, null);
	}

	private void addPage(String title, String subTitle, String includeUri, String data) {
		String folder = "/";
		Map<String, NavigationPage> subPageMap = pageMap.get(title);
		if (subPageMap == null) {
			subPageMap = new LinkedHashMap<String, NavigationPage>();
			pageMap.put(title, subPageMap);
		}
		NavigationPage navigationPage = new NavigationPage(title, subTitle,
				folder + includeUri + "?random=" + Math.random(), data) {
			@Override
			public boolean isSelected() {

				return currentPage == this;
			}
		};
		subPageMap.put(subTitle, navigationPage);
	}

	public List<NavigationTitle> getMenus() {
		return menus;
	}

	public void setMenus(List<NavigationTitle> menus) {
		this.menus = menus;
	}

}
