package com.doxacore.login.servicios;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.doxacore.modelo.Usuarios;


public class UserInfoService2Impl implements UserInfoService,Serializable{
	private static final long serialVersionUID = 1L;
	
	static protected List<Usuarios> userList = new ArrayList<Usuarios>();  
	static{
		userList.add(new Usuarios("anonymous","1234","Anonymous","anonumous@your.com"));
		userList.add(new Usuarios("admin","1234","Admin","admin@your.com"));
		userList.add(new Usuarios("zkoss","1234","ZKOSS","info@zkoss.org"));
	}
	
	/** synchronized is just because we use static userList in this demo to prevent concurrent access **/
	public synchronized Usuarios findUser(String account){
		int s = userList.size();
		for(int i=0;i<s;i++){
			Usuarios u = userList.get(i);
			if(account.equals(u.getAccount())){
				return Usuarios.clone(u);
			}
		}
		return null;
	}
	
	/** synchronized is just because we use static userList in this demo to prevent concurrent access **/
	public synchronized Usuarios updateUser(Usuarios user){
		int s = userList.size();
		for(int i=0;i<s;i++){
			Usuarios u = userList.get(i);
			if(user.getAccount().equals(u.getAccount())){
				userList.set(i,u = Usuarios.clone(user));
				return u;
			}
		}
		throw new RuntimeException("user not found "+user.getAccount());
	}
}