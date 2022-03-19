package com.doxacore.main.menu;

public class NavigationTitle {

	private String title;
	private boolean visible;
	private String icon;
	
	public NavigationTitle(String title, boolean visible, String icon) {
		super();
		this.title = title;
		this.visible = visible;
		this.icon = icon;
	}
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}


	public String getIcon() {
		return icon;
	}


	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	
	
	
}
