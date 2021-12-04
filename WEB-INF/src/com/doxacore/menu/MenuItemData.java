package com.doxacore.menu;

import java.util.ArrayList;
import java.util.List;

public class MenuItemData {

	private static List<MenuItem> menus = new ArrayList<MenuItem>();
	static {

		MenuItem m1 = new MenuItem("Configuración",1);
		MenuItem m1_lv1 = new MenuItem("Editar Usuario",2);
		

		MenuItem m2 = new MenuItem("Logout",1);
		
		m1.addChild(m1_lv1);
		
		menus.add(m1);
		menus.add(m2);
		
		
		
		
		//MenuItem m1_Lv2 = new MenuItem("Accounts",3);

	/*	MenuItem m1_Lv3 = new MenuItem("User",4);
		m1_Lv2.addChild(m1_Lv3);
		m1_Lv3 = new MenuItem("Role",4);
		m1_Lv2.addChild(m1_Lv3);
		m1_Lv3 = new MenuItem("User Rights",4);
		m1_Lv2.addChild(m1_Lv3);
		m1_Lv3 = new MenuItem("Role Rights",4);
		m1_Lv2.addChild(m1_Lv3);
		m1_lv1.addChild(m1_Lv2);*/

/*		m1_Lv2 = new MenuItem("Regional",3);
		m1_Lv3 = new MenuItem("Clock",4);
		m1_Lv2.addChild(m1_Lv3);
		m1_Lv3 = new MenuItem("Language",4);
		m1_Lv2.addChild(m1_Lv3);
		m1_Lv3 = new MenuItem("Keyboard",4);
		m1_Lv2.addChild(m1_Lv3);
		m1_lv1.addChild(m1_Lv2);

		m1_Lv2 = new MenuItem("Devices",3);
		m1_Lv3 = new MenuItem("Printer",4);
		m1_Lv2.addChild(m1_Lv3);
		m1_Lv3 = new MenuItem("Projector",4);
		m1_Lv2.addChild(m1_Lv3);
		m1_Lv3 = new MenuItem("Mouse",4);
		m1_Lv2.addChild(m1_Lv3);
		m1_lv1.addChild(m1_Lv2);

		m1.addChild(m1_lv1);*/

		/***************************************************/
/*		m1_lv1 = new MenuItem("Network",2);
		m1_Lv2 = new MenuItem("Wireless",3);
		m1_Lv3 = new MenuItem("Connection 1",4);
		m1_Lv2.addChild(m1_Lv3);
		m1_Lv3 = new MenuItem("Connection 2",4);
		m1_Lv2.addChild(m1_Lv3);
		m1_lv1.addChild(m1_Lv2);

		m1_Lv2 = new MenuItem("Adapter",3);
		m1_Lv3 = new MenuItem("Local Area Connection",4);
		m1_Lv2.addChild(m1_Lv3);
		m1_Lv3 = new MenuItem("Virtual Box Network",4);
		m1_Lv2.addChild(m1_Lv3);

		m1_lv1.addChild(m1_Lv2);

		m1.addChild(m1_lv1);*/

		/***************************************************/
/*		m1_lv1 = new MenuItem("My Computer",2);
		m1_Lv2 = new MenuItem("Drives",3);
		m1_Lv3 = new MenuItem("C Drive",4);
		m1_Lv2.addChild(m1_Lv3);
		m1_Lv3 = new MenuItem("D Drive",4);
		m1_Lv2.addChild(m1_Lv3);
		m1_Lv3 = new MenuItem("E Drive",4);
		m1_Lv2.addChild(m1_Lv3);
		m1_lv1.addChild(m1_Lv2);

		m1_Lv2 = new MenuItem("Favorites",3);
		m1_Lv3 = new MenuItem("Desktop",4);
		m1_Lv2.addChild(m1_Lv3);
		m1_Lv3 = new MenuItem("Downloads",4);
		m1_Lv2.addChild(m1_Lv3);
		m1_Lv3 = new MenuItem("Recent Places",4);
		m1_Lv2.addChild(m1_Lv3);
		m1_Lv3 = new MenuItem("Google Drive",4);
		m1_Lv2.addChild(m1_Lv3);
		m1_lv1.addChild(m1_Lv2);
		m1.addChild(m1_lv1);
		menus.add(m1);*/

		/************************************************************************/
/*		m1 = new MenuItem("ZK",1);
		m1_lv1 = new MenuItem("Products",2);
		m1_Lv2 = new MenuItem("ZK SpreadSheet",3);

		m1_Lv3 = new MenuItem("3D Cell",4);
		m1_Lv2.addChild(m1_Lv3);
		m1_Lv3 = new MenuItem("Freeze rows",4);
		m1_Lv2.addChild(m1_Lv3);
		m1_Lv3 = new MenuItem("Ranged Cells",4);
		m1_Lv2.addChild(m1_Lv3);
		m1_lv1.addChild(m1_Lv2);

		m1_Lv2 = new MenuItem("ZK Pivottable",3);
		m1_Lv3 = new MenuItem("Drill Down",4);
		m1_Lv2.addChild(m1_Lv3);
		m1_Lv3 = new MenuItem("Render",4);
		m1_Lv2.addChild(m1_Lv3);
		m1_Lv3 = new MenuItem("Paging",4);
		m1_Lv2.addChild(m1_Lv3);
		m1_lv1.addChild(m1_Lv2);

		m1_Lv2 = new MenuItem("ZK Calender",3);
		m1_Lv3 = new MenuItem("Views",4);
		m1_Lv2.addChild(m1_Lv3);
		m1_Lv3 = new MenuItem("Drag and Drop",4);
		m1_Lv2.addChild(m1_Lv3);
		m1_Lv3 = new MenuItem("Time Zones",4);
		m1_Lv2.addChild(m1_Lv3);
		m1_lv1.addChild(m1_Lv2);

		m1_Lv2 = new MenuItem("ZK Spring",3);
		m1_Lv3 = new MenuItem("HTTP Request",4);
		m1_Lv2.addChild(m1_Lv3);
		m1_Lv3 = new MenuItem("HTTP Basic",4);
		m1_Lv2.addChild(m1_Lv3);
		m1_Lv3 = new MenuItem("MD4 Password",4);
		m1_Lv2.addChild(m1_Lv3);
		m1_lv1.addChild(m1_Lv2);
		
		m1.addChild(m1_lv1);*/

		/***************************************************/
/*		m1_lv1 = new MenuItem("ZK Demo",2);
		m1_Lv2 = new MenuItem("Grid",3);
		m1_Lv3 = new MenuItem("Master Detail",4);
		m1_Lv2.addChild(m1_Lv3);
		m1_Lv3 = new MenuItem("Data Binding",4);
		m1_Lv2.addChild(m1_Lv3);
		m1_Lv3 = new MenuItem("Dynamic Data",4);
		m1_Lv2.addChild(m1_Lv3);
		m1_Lv3 = new MenuItem("Data Filter",4);
		m1_Lv2.addChild(m1_Lv3);
		m1_lv1.addChild(m1_Lv2);

		m1_Lv2 = new MenuItem("ListBox",3);
		m1_Lv3 = new MenuItem("Dual ListBox",4);
		m1_Lv2.addChild(m1_Lv3);
		m1_Lv3 = new MenuItem("Paging",4);
		m1_Lv2.addChild(m1_Lv3);
		m1_Lv3 = new MenuItem("Auto Sort",4);
		m1_Lv2.addChild(m1_Lv3);
		m1_Lv3 = new MenuItem("List Group",4);
		m1_Lv2.addChild(m1_Lv3);
		
		m1_Lv2 = new MenuItem("Effects",3);
		m1_Lv3 = new MenuItem("jQuery Effects",4);
		m1_Lv2.addChild(m1_Lv3);
		m1_Lv3 = new MenuItem("Upload Effect",4);
		m1_Lv2.addChild(m1_Lv3);
		m1_Lv3 = new MenuItem("Login Effect",4);
		m1_Lv2.addChild(m1_Lv3);
		m1_Lv3 = new MenuItem("Display Action",4);
		m1_Lv2.addChild(m1_Lv3);

		
		m1_Lv2 = new MenuItem("Layout",3);
		m1_Lv3 = new MenuItem("Portal Layout",4);
		m1_Lv2.addChild(m1_Lv3);
		m1_Lv3 = new MenuItem("Complex Border Layout",4);
		m1_Lv2.addChild(m1_Lv3);
		m1_Lv3 = new MenuItem("Group Box",4);
		m1_Lv2.addChild(m1_Lv3);
		m1_Lv3 = new MenuItem("Boxes",4);
		m1_Lv2.addChild(m1_Lv3);
		
		
		m1_lv1.addChild(m1_Lv2);

		m1.addChild(m1_lv1);*/

		/***************************************************/
/*		m1_lv1 = new MenuItem("ZK Support",2);
		m1_Lv2 = new MenuItem("Documentation",3);
		m1_Lv3 = new MenuItem("Spread Sheet",4);
		m1_Lv2.addChild(m1_Lv3);
		m1_Lv3 = new MenuItem("ZK Calender",4);
		m1_Lv2.addChild(m1_Lv3);
		m1_Lv3 = new MenuItem("ZK Studio",4);
		m1_Lv2.addChild(m1_Lv3);
		m1_lv1.addChild(m1_Lv2);

		m1_Lv2 = new MenuItem("Downloads",3);
		m1_Lv3 = new MenuItem("ZK Spring",4);
		m1_Lv2.addChild(m1_Lv3);
		m1_Lv3 = new MenuItem("ZK JSP",4);
		m1_Lv2.addChild(m1_Lv3);
		m1_lv1.addChild(m1_Lv2);
		m1.addChild(m1_lv1);

		menus.add(m1);
		m1 = new MenuItem("Eclipse",1);
		menus.add(m1);
		m1_lv1 = new MenuItem("File",2);
		m1_Lv2 = new MenuItem("Project",3);

		m1_Lv3 = new MenuItem("New Maven",4);
		m1_Lv2.addChild(m1_Lv3);
		m1_Lv3 = new MenuItem("New JPA",4);
		m1_Lv2.addChild(m1_Lv3);
		m1_Lv3 = new MenuItem("New JEE",4);
		m1_Lv2.addChild(m1_Lv3);
		m1_lv1.addChild(m1_Lv2);
		m1.addChild(m1_lv1);*/
	}

	public static List<MenuItem> getAllMenus() {
		return new ArrayList<MenuItem>(menus);
	}




}
