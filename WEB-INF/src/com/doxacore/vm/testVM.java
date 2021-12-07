package com.doxacore.vm;

import org.zkoss.bind.annotation.Command;

import com.doxacore.login.User;
import com.doxacore.login.UserDAO;

public class testVM {
	
	private User user = new User();
	private UserDAO ud = new UserDAO();

	
	@Command
	public void guardar() {
		
		ud.saveOrUpdate(user.getAccount(), user.getPassword());
		
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserDAO getUd() {
		return ud;
	}

	public void setUd(UserDAO ud) {
		this.ud = ud;
	}
	
}
