package com.doxacore.sideBar;


import java.util.*;

public class NavDao {
    private static List<Menu> menuList = new LinkedList<>();

    static{
        initMenus();
    }

    static public void initMenus(){
        Menu menuD = new Menu("Dashboard", "z-icon-home");
        Menu menuE = new Menu("Ejemplo de Menu 1");
        menuE.setPath(NavigationMdel.DASHBOARD_ECOMMERCE_ZUL);
        Menu menuP = new Menu("Ejemplo de Menu 2");
        menuP.setPath(NavigationMdel.DASHBOARD_PROJECT_ZUL);
        List<Menu> subMenus = new ArrayList<>();
        subMenus.add(menuE);
        subMenus.add(menuP);
        menuD.setSubMenus(subMenus);
        menuList.add(menuD);

        Menu menuUI = new Menu("Ejemplo de Menu 3", "z-icon-flag-o");
        menuList.add(menuUI);
        Menu menuTable = new Menu("Ejemplo de Menu 4", "z-icon-flag-o");
        menuList.add(menuTable);

        Menu menuC = new Menu("Contacto", "z-icon-envelope-o");
        menuList.add(menuC);
         
    }

    static public List<Menu> queryMenu(){
        return menuList;
    }
}
