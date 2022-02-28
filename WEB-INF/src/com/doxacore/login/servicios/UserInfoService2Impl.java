package com.doxacore.login.servicios;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.doxacore.modelo.Usuario;
import com.doxacore.util.Register;


public class UserInfoService2Impl implements UserInfoService,Serializable{
	private static final long serialVersionUID = 1L;
	
	static protected List<Usuario> userList = new ArrayList<Usuario>();  
	
	static{
		/*userList.add(new Usuario("anonymous","1234","Anonymous","anonumous@your.com"));
		userList.add(new Usuario("admin","1234","Admin","admin@your.com"));
		userList.add(new Usuario("zkoss","1234","ZKOSS","info@zkoss.org"));*/
		Register r = new Register();
		
		userList = r.getAllObjects(Usuario.class.getName());
		
	}
	
	/** synchronized is just because we use static userList in this demo to prevent concurrent access **/
	public synchronized Usuario findUser(String account){
		int s = userList.size();
		for(int i=0;i<s;i++){
			Usuario u = userList.get(i);
			if(account.equals(u.getAccount())){
				return Usuario.clone(u);
			}
		}
		return null;
	}
	
	/** synchronized is just because we use static userList in this demo to prevent concurrent access **/
	public synchronized Usuario updateUser(Usuario user){
		int s = userList.size();
		for(int i=0;i<s;i++){
			Usuario u = userList.get(i);
			if(user.getAccount().equals(u.getAccount())){
				userList.set(i,u = Usuario.clone(user));
				return u;
			}
		}
		throw new RuntimeException("user not found "+user.getAccount());
	}
}