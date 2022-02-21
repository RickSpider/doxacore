package com.doxacore.modelo;

import java.util.List;

import org.hibernate.Session;

import com.doxacore.util.HibernateUtil;

public class Register {
	
	 Session currentSession() {
         return HibernateUtil.getSessionFactory().getCurrentSession();
     }

	
	public synchronized void saveObject(Modelo m, String Usuario) {
		
		 Session sess =  currentSession();
		 
		 if (m.getCreadoUser().length() == 0) {
			 
			 m.setCreadoUser(Usuario);
			 
		 }
		 
		 m.setModificacionUser(Usuario);
		
		 sess.save(m);
		 sess.flush();
	}
	
	public synchronized Object getObjectById(String entityName, long id) {
		
		 Session sess =  currentSession();
		 
		 return sess.get(entityName, id);
		
	}
	
	public synchronized <T> List<T> getAllObjects(String entityName) {
		
		Session sess =  currentSession();
		
		return sess.createQuery("from " + entityName ).list();
		
	}
	
}
