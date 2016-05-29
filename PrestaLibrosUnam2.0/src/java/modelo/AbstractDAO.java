/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import modelo.DataAccessLayerException;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author jonathanjb
 */
public abstract class AbstractDAO {
    protected Session session;
    protected Transaction tx;

    public AbstractDAO() {
        HibernateFactory.buildIfNeeded();
    }

    protected void save(Object obj) {
        try {
            startOperation();
            session.persist(obj);
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            HibernateFactory.close(session);
        }
    }

    protected void update(Object obj) {
        try {
            startOperation();
            session.update(obj);
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            HibernateFactory.close(session);
        }
    }

    protected void delete(Object obj) {
        try {
            startOperation();
            session.delete(obj);
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            HibernateFactory.close(session);
        }
    }

    protected Object find(Class clazz, Long id) {
        Object obj = null;
        try {
            startOperation();
            obj = session.load(clazz, id);
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            HibernateFactory.close(session);
        }
        return obj;
    }

    protected List findAll(Class clazz) {
        List objects = null;
        try {
            startOperation();
            Query query = session.createQuery("from " + clazz.getName());
            objects = query.list();
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            HibernateFactory.close(session);
        }
        return objects;
    }
    
    protected List findLibros(Class clazz, Long id){
        List objects = null;
        try{
            startOperation();
            Query query = session.createQuery("FROM " + clazz.getName() + " WHERE usridUsuario =" + String.valueOf(id));
            objects = query.list();
            tx.commit();
        } catch(HibernateException e){
            handleException(e);            
        } finally {
            HibernateFactory.close(session);
        }
        return objects;
    }
    
    protected List buscaNormal(Class clazz, String bus){
        List objects = null;
        try{
            startOperation();
            //System.out.print("FROM " + clazz.getName() +"lib"+ "WHERE lib.nombre like '%" + bus + "%'");
            Query query = session.createQuery("FROM " + clazz.getName() + " WHERE nombre like '%" + bus + "%'");
           // System.out.print(query);
            objects = query.list();
            tx.commit();
        } catch(HibernateException e){
            handleException(e);            
        } finally {
            HibernateFactory.close(session);
        }
        return objects;        
    }
    
    protected void handleException(HibernateException e) throws DataAccessLayerException {
        HibernateFactory.rollback(tx);
        throw new DataAccessLayerException(e);
    }

    protected void startOperation() throws HibernateException {
        session = HibernateFactory.openSession();
        tx = session.beginTransaction();
    }
    
    public int maxIndice(String tabla,String atributo){
      SessionFactory factory;
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        int max=-1;
        Session session = factory.openSession();
        Transaction tx = null;
        //System.out.println(max+1);
        try {
            tx = session.beginTransaction();
            String sql = "SELECT max("+atributo+") FROM "+tabla;
            SQLQuery query = session.createSQLQuery(sql);
            //System.out.println("hola"+sql);
            if(query.uniqueResult() != null){
                max = (int)query.uniqueResult();
            }
            //System.out.println(max);
            tx.commit();
            //return max;
        }catch(HibernateException e){
            System.out.println("coso"+e);
            
        }finally{
            session.close();
        }
      //System.out.println(max);
      return max+1;
    }
}
