package com.doxacore.util;

import java.util.Date;
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

	
	public synchronized <T extends Modelo> T saveObject(T m, String Usuario) {
		
	
		 Session sess =  currentSession();
		 
		 if (m.getCreadoUser()== null || m.getCreadoUser().length() == 0) {
			 
			 m.setCreadoUser(Usuario);
			 
		 } else {
			 
			 m.setModificacion(new Date());

		 }
		 
		 m.setModificacionUser(Usuario);
		
		 //sess.saveOrUpdate(m);
		 m = (T) sess.merge(m);
		 
		 sess.flush();
		 
		 return m;
	}
	
	public synchronized <T extends Modelo> T getObjectById(String entityName, long id) {
		
		 Session sess =  currentSession();
		 
		 return (T) sess.get(entityName, id);
		
	}
	
	public synchronized <T extends Modelo> T getObjectByColumnString(String entityName, String colummn, String value) {
		
		Session sess = currentSession();
		
		Query<T> query = sess.createQuery("from "+ entityName +" where " +colummn+ "=:value" ).setParameter("value", value);
		
		return query.uniqueResult();
	}
	
	public synchronized <T extends Modelo> List<T> getAllObjects(String entityName) {
		
		Session sess =  currentSession();

		return sess.createQuery("from " + entityName ).list();
			
	}
	
	public synchronized <T extends Modelo> List<T> getAllObjectsByCondicionOrder(String entityName, String condicion, String order) {
		
		Session sess =  currentSession();
		
		StringBuffer wo = new StringBuffer();
		
		if (condicion != null) {
			
			wo.append(" where "+condicion);
			
		}
		
		if (order != null) {
			
			wo.append(" order by "+order);
		}
		
		//System.out.println("================Este es el WO: "+wo+"================");

		return sess.createQuery("from " + entityName + wo).list();
			
	}
	
	public synchronized <T extends Modelo> void deleteObject(T m) {
		
		Session sess = currentSession();
		
		sess.delete(m);
		
	}
	
	public synchronized List<Object[]> sqlNativo(String sql){
		
		Session sess = currentSession();
		
		return sess.createSQLQuery(sql).list();
	}
	
	
}
