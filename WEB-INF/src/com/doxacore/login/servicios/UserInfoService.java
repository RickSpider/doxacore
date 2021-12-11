package com.doxacore.login.servicios;

import com.doxacore.modelo.Usuarios;

public interface UserInfoService {
	
	/** find user by account **/
	public Usuarios findUser(String account);
	
	/** update user **/
	public Usuarios updateUser(Usuarios user);

}
