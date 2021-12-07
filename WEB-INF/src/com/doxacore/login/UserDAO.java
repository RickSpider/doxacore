package com.doxacore.login;

import javax.transaction.Transactional;

import org.hibernate.Session;

import com.doxacore.util.HibernateUtil;



public class UserDAO {
	
	 Session currentSession() {
         return HibernateUtil.getSessionFactory().getCurrentSession();
     }
     
	
	 public void saveOrUpdate(String account, String pass) {
		 
		 Session sess =  currentSession();
		 User user = new User();
		 user.setAccount(account);
		 user.setPassword(pass);
		 sess.save(user);
		 sess.flush();
		 
	 }

}
