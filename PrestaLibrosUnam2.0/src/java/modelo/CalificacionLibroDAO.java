/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author jonathanjb
 */
public class CalificacionLibroDAO extends AbstractDAO {
   public CalificacionLibroDAO() {
        super();
    }

    /**
     * Insert a new Calificacionlibro into the database.
     *
     * @param calificacionl
     */
    public void save(Calificacionlibro calificacionl) throws DataAccessLayerException {
        super.save(calificacionl);
    }

    /**
     * Updates a new Calificacionlibro into the database.
     *
     * @param calificacionl
     */
    public void update(Calificacionlibro calificacionl) throws DataAccessLayerException {
        super.update(calificacionl);
    }

    /**
     * Delete a detached Calificacionulibro from the database.
     *
     * @param calificacionl
     */
    public void delete(Calificacionlibro calificacionl) throws DataAccessLayerException {
        super.delete(calificacionl);
    }

    /**
     * Find an Event by its primary key.
     *
     * @param id
     * @return
     */
    public Calificacionlibro find(Long id) throws DataAccessLayerException {
        return (Calificacionlibro) super.find(Calificacionlibro.class, id);
    }

    /**
     * Lista todos las calificaciones de los libros de la base de datos
     *
     * @return
     */
    public List findAll() throws DataAccessLayerException {
        return super.findAll(Calificacionlibro.class);
    }
    
    /**
     * Lista todos los libros de la base de datos
     *
     * @return
     */
    public int maxIndice() {
        return super.maxIndice("calificacionlibro","idcalificacionlibro");
    }
    
    public List<Libro> librosPorCalificar(int id){
        SessionFactory factory; 
        List<Libro> misprestamos = null;
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
            String sql = "SELECT * FROM libro WHERE idlibro IN (SELECT libroidlibro FROM solicitudes WHERE aceptado = TRUE AND usridusuario = "+id+" )";
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(Libro.class);
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
