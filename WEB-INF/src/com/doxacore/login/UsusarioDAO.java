package com.doxacore.login;

import org.hibernate.Session;

import com.doxacore.modelo.Usuarios;
import com.doxacore.util.HibernateUtil;

public class UsusarioDAO {
	
	 Session currentSession() {
         return HibernateUtil.getSessionFactory().getCurrentSession();
     }
     
	
	 public void saveOrUpdate(String account, String pass) {
		 
		 Session sess =  currentSession();
		 Usuarios user = new Usuarios();
		 user.setAccount(account);
		 user.setPassword(pass);
		 sess.save(user);
		 sess.flush();
		 
	 }

}
