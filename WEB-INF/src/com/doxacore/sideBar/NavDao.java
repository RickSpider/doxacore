package com.doxacore.adminTemplate;


import java.util.*;

public class NavDao {
    private static List<Menu> menuList = new LinkedList<>();

    static{
        initMenus();
    }

    static public void initMenus(){
        Menu menuD = new Menu("Dashboard", "z-icon-home");
        Menu menuE = new Menu("Ecommerce");
        menuE.setPath(NavigationMdel.DASHBOARD_ECOMMERCE_ZUL);
        Menu menuP = new Menu("Project");
        menuP.setPath(NavigationMdel.DASHBOARD_PROJECT_ZUL);
        List<Menu> subMenus = new ArrayList<>();
        subMenus.add(menuE);
        subMenus.add(menuP);
        menuD.setSubMenus(subMenus);
        menuList.add(menuD);

        Menu menuUI = new Menu("UI Elements", "z-icon-flag-o");
        menuList.add(menuUI);
        Menu menuTable = new Menu("Tables", "z-icon-flag-o");
        menuList.add(menuTable);

        Menu menuC = new Menu("Contact", "z-icon-envelope-o");
        menuList.add(menuC);
        Menu menuF = new Menu("Freeze", "z-icon-snowflake-o");
        menuList.add(menuF);
        
    }

    static public List<Menu> queryMenu(){
        return menuList;
    }
}
