package com.doxacore.menu;

import java.util.ArrayList;
import java.util.List;

public class MenuItem {
	
		private String name;
		private List<MenuItem> children;
		private int level;
		
		public MenuItem(String name,int level) {
			this.name = name;
			this.level = level;
			children = new ArrayList<MenuItem>();
		}
		
		public void addChild(MenuItem node) {
			children.add(node);
		}

		public void appendChild(MenuItem child) {
			if (children == null)
				children = new ArrayList<MenuItem>();
			children.add(child);
		}
		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public List<MenuItem> getChildren() {
			return children;
		}

		public void setChildren(List<MenuItem> children) {
			this.children = children;
		}

		public int getLevel() {
			return level;
		}

		public void setLevel(int level) {
			this.level = level;
		}

}
