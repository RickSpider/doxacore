package com.doxacore.menu;

import java.util.ArrayList;
import java.util.List;

public class MenuBarVM {
	
	private List<MenuItem> nodes;

	public List<MenuItem> getNodes() {
		return nodes;
	}

	public void setNodes(List<MenuItem> nodes) {
		this.nodes = nodes;
	}

	public MenuBarVM() {
		nodes = new ArrayList<MenuItem>();
		nodes = MenuItemData.getAllMenus();
	}


}
