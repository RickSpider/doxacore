package com.doxacore.util;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.query.Query;

import com.doxacore.modelo.Modelo;
import com.doxacore.modelo.Usuario;

public class Register {
	
	 private Session currentSession() {
         return HibernateUtil.getSessionFactory().getCurrentSession();
     }

	
	public synchronized void saveObject(Modelo m, String Usuario) {
		
	
		 Session sess =  currentSession();
		 
		 if (m.getCreadoUser()== null || m.getCreadoUser().length() == 0) {
			 
			 m.setCreadoUser(Usuario);
			 
		 } 
		 
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
	
	public synchronized <T> List<T> getAllObjectsByCondicionOrder(String entityName, String condicion, String order) {
		
		Session sess =  currentSession();
		
		String wo = "";
		
		if (condicion != null) {
			
			wo = " where "+condicion;
			
		}
		
		if (order != null) {
			
			wo = " order by "+order;
		}

		return sess.createQuery("from " + entityName + wo).list();
			
	}
	
	public synchronized void deleteObject(Modelo m) {
		
		Session sess = currentSession();
		
		sess.delete(m);
		
	}
	
	
}
