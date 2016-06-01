/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

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
public class SolicitudesDAO extends AbstractDAO {

    public SolicitudesDAO() {
        super();
    }

    /**
     * Insert a new Solicitud into the database.
     *
     * @param solicitud
     */
    public void save(Solicitudes solicitud) throws DataAccessLayerException {
        super.save(solicitud);
    }

    /**
     * Updates a new Solicitud into the database.
     *
     * @param solicitud
     */
    public void update(Solicitudes solicitud) throws DataAccessLayerException {
        super.update(solicitud);
    }

    /**
     * Delete a detached Solicitud from the database.
     *
     * @param solicitud
     */
    public void delete(Solicitudes solicitud) throws DataAccessLayerException {
        super.delete(solicitud);
    }

    /**
     * Find an Event by its primary key.
     *
     * @param id
     * @return
     */
    public Solicitudes find(Long id) throws DataAccessLayerException {
        return (Solicitudes) super.find(Solicitudes.class, id);
    }

    /**
     * Lista todos los solicituds de la base de datos
     *
     * @return
     */
    public List findAll() throws DataAccessLayerException {
        return super.findAll(Solicitudes.class);
    }
    
    /**
     * Lista todos los solicituds de la base de datos
     *
     * @return
     */
    public int maxIndice() {
        return super.maxIndice("solicitudes","idsolicitudes");
    }
    
    public List<Solicitudes> pendientesUsuario(int id){
        SessionFactory factory; 
        List<Solicitudes> misprestamos = null;
        try{
            factory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex) { 
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex); 
        }    
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String sql = "SELECT * FROM solicitudes WHERE aceptado = FALSE AND libroidlibro IN (SELECT idlibro FROM libro WHERE usridusuario = "+id+")";
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(Solicitudes.class);
            misprestamos = query.list();
            tx.commit();
            return misprestamos;
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            return null; 
        }finally {
            session.close(); 
        }   
    }
     
    public List pendientesLibro(int idLibro){
        List<Solicitudes> objects = null;
        try{
            startOperation();
            Query query = session.createQuery("FROM Solicitudes WHERE libroidlibro = " + idLibro);
            objects = query.list();
            tx.commit();
        } catch(HibernateException e){
            handleException(e);            
        } finally {
            HibernateFactory.close(session);
        }
        return objects;
    }
    
    public Solicitudes solicitudUsuarioLibro(int idUsuario,int idLibro){
        Solicitudes s = null;
        try{
            startOperation();
            Query query = session.createQuery("FROM Solicitudes  WHERE libroidlibro = "+idLibro+" AND usridusuario = "+idUsuario);
            s = (Solicitudes) query.uniqueResult();
            tx.commit();
        } catch(HibernateException e){
            handleException(e);            
        } finally {
            HibernateFactory.close(session);
        }
        return s;
    }

    public List<Solicitudes> aceptadas(int idusuario) {
        SessionFactory factory; 
        List<Solicitudes> solicitudes = null;
        try{
            factory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex) { 
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex); 
        }    
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String sql = "SELECT * FROM solicitudes WHERE aceptado = TRUE AND libroidlibro IN (SELECT idlibro FROM libro WHERE usridusuario = "+idusuario+")";
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(Solicitudes.class);
            solicitudes = query.list();
            tx.commit();
            return solicitudes;
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            return null; 
        }finally {
            session.close(); 
        }  
    }
    
    public List<Solicitudes> librosPorCalificar(int id){
        SessionFactory factory; 
        List<Solicitudes> misprestamos = null;
        try{
            factory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex) { 
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex); 
        }    
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String sql = "SELECT * FROM solicitudes WHERE aceptado = TRUE AND califlibro = FALSE AND usridusuario = "+id;
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(Solicitudes.class);
            misprestamos = query.list();
            tx.commit();
            return misprestamos;
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            return null; 
        }finally {
            session.close(); 
        } 
    }
    
}