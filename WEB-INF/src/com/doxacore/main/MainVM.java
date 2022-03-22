package com.doxacore.main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Sessions;

import com.doxacore.login.UsuarioCredencial;
import com.doxacore.main.menu.NavigationPage;
import com.doxacore.main.menu.NavigationTitle;
import com.doxacore.modelo.Modulo;
import com.doxacore.modelo.Operacion;
import com.doxacore.modelo.Usuario;
import com.doxacore.util.Register;

public class MainVM {
	
	private List<NavigationTitle> menus = new ArrayList<NavigationTitle>();
	private NavigationPage currentPage;
    private Map<String, Map<String, NavigationPage>> pageMap;
    private Usuario currentUser;
    private List<Modulo> lModulos = new ArrayList<Modulo>();
     
   // private static Register reg = new Register();
     
    @Init
    public void init() {
       
        
      
		
    	initListaModulos();	
		initPageMap();
        
        currentPage = pageMap.get("Main").get("Blank");
        
        pageMap.get("Main").values().size();
    }
    
    private void initListaModulos(){
    	
    	Register reg = new Register();
    	
    	UsuarioCredencial usuarioCredencial = (UsuarioCredencial) Sessions.getCurrent().getAttribute("userCredential");
    	
    	List<Object[]> lUsuariosOperaciones = reg.sqlNativo(
    			"select "
    	    	+ "o.operacion, "
    			+ "u.account "
    			+ "from usuariosroles ur\n" + 
    			"left join usuarios u on u.usuarioid = ur.usuarioid\n" + 
    			"left join rolesoperaciones ro on ro.rolid = ur.rolid\n" + 
    			"left join operaciones o on o.operacionid = ro.operacionid\n" + 
    			"where o.abremodulo = true and u.account = '"+usuarioCredencial.getAccount()+"';");    	
    	
    	List <Operacion> lOperaciones = reg.getAllObjectsByCondicionOrder(Operacion.class.getName(), "abremodulo = true", null);
    	
    	for (Operacion o : lOperaciones) {
    		
    		if (o.getModulo().isHabilitado()) {
    			
    			for (Object [] obj : lUsuariosOperaciones) {
    				
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
        currentPage = targetPage;
        System.out.println("El CurrentPage "+currentPage.getData());
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
        
        this.menus.add(new NavigationTitle("Main", true,"z-icon-home"));         
        addPage("Main", "page 1", "/corezul/main/test.zul");
        addPage("Main", "Blank", "/corezul/blank.zul");
        
        
        this.menus.add(new NavigationTitle("Configuracion", true, "z-icon-gear"));   
        
        for (Modulo m : lModulos) {
        	
        	addPage(m.getMenu(), m.getTitulo(), m.getPath(),m.getModulo());
        	
        }
        
      /*  addPage("Configuracion", "Usuarios", "/corezul/configuracion/usuario.zul","Usuario");
        addPage("Configuracion", "Roles", "/corezul/configuracion/rol.zul","Rol");
        addPage("Configuracion", "Modulos", "/corezul/configuracion/modulo.zul","Modulo");*/
      
    }
    
    @Command
    public boolean menuVisible(@BindingParam("size") int size ) {
    	
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
        if(subPageMap == null) {
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
