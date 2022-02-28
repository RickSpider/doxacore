package com.doxacore.util;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.doxacore.modelo.Modelo;

public class Register {
	
	 private Session currentSession() {
         return HibernateUtil.getSessionFactory().getCurrentSession();
     }

	
	public synchronized void saveObject(Modelo m, String Usuario) {
		
	
		 Session sess =  currentSession();
		 
		 if (m.getCreadoUser()== null || m.getCreadoUser().length() == 0) {
			 
			 m.setCreadoUser(Usuario);
			 
		 }else 
		 
		 m.setModificacionUser(Usuario);

		 sess.saveOrUpdate(m);
		 
		 sess.flush();
	}
	
	public synchronized <T> T getObjectById(String entityName, long id) {
		
		 Session sess =  currentSession();
		 
		 return (T) sess.get(entityName, id);
		
	}
	
	public synchronized <T> T getObjectByColumnString(String entityName, String colummn, String value) {
		
		Session sess = currentSession();
		
		Query<T> query = sess.createQuery("from "+ entityName +" where " +colummn+ "=:value" ).setParameter("value", value);
		
		return query.uniqueResult();
	}
	
	public synchronized <T> List<T> getAllObjects(String entityName) {
		
		Session sess =  currentSession();
		
		return sess.createQuery("from " + entityName ).list();
		
	}
	
}
