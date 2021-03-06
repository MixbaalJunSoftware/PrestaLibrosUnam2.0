/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.math.BigDecimal;
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
public class CalificacionUsuarioDAO extends AbstractDAO {
    public CalificacionUsuarioDAO() {
        super();
    }

    /**
     * Insert a new CalificacionUsuario into the database.
     *
     * @param calificacionu
     */
    public void save(Calificacionusuario calificacionu) throws DataAccessLayerException {
        super.save(calificacionu);
    }

    /**
     * Updates a new Calificacionuser into the database.
     *
     * @param calificacionu
     */
    public void update(Calificacionusuario calificacionu) throws DataAccessLayerException {
        super.update(calificacionu);
    }

    /**
     * Delete a detached Calificacionusuario from the database.
     *
     * @param calificacionu
     */
    public void delete(Calificacionusuario calificacionu) throws DataAccessLayerException {
        super.delete(calificacionu);
    }

    /**
     * Find an Event by its primary key.
     *
     * @param id
     * @return
     */
    public Calificacionusuario find(Long id) throws DataAccessLayerException {
        return (Calificacionusuario) super.find(Calificacionusuario.class, id);
    }

    /**
     * Lista todos las calificaciones de un usuario de la base de datos
     *
     * @return
     */
    public List findAll() throws DataAccessLayerException {
        return super.findAll(Calificacionusuario.class);
    }
    
    /**
     * Lista todas los solicitudes de la base de datos
     *
     * @return
     */
    public int maxIndice() {
        return super.maxIndice("calificacionusuario","idcalificacionusr");
    }
    
    public List<Libro> misPrestamos(int id){
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
            String sql = "SELECT * FROM libro WHERE usridusuario = "+id+" AND idlibro IN (SELECT libroidlibro FROM solicitudes WHERE aceptado = TRUE)";
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
    
    public Usuario consumidor(int libro){
        SessionFactory factory; 
        Usuario consumidor =null; 
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
            String sql = "SELECT * FROM usuario WHERE idusuario in (SELECT usridusuario FROM solicitudes WHERE libroidlibro =" +libro+"AND aceptado = TRUE)" ;
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(Usuario.class);
            consumidor = (Usuario)query.uniqueResult();
            tx.commit();
            return consumidor;
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            return null; 
        }finally {
            session.close(); 
        }
    }
    
    public Integer promedio(int id){
        SessionFactory factory; 
        Integer p = -1;
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
            String sql = "SELECT avg(calificacion) FROM calificacionusuario WHERE consumidoridusr in (SELECT usridusuario FROM solicitudes WHERE usridusuario ="+id+")";
            SQLQuery query = session.createSQLQuery(sql);
            if(query.uniqueResult() != null){
                p = ((BigDecimal)query.uniqueResult()).intValue();
            }
            tx.commit();
            return p;
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            return-1; 
        }finally {
            session.close(); 
        }
    }
    
    public List<Calificacionusuario> calificacionesU(int id){
        SessionFactory factory; 
        List<Calificacionusuario> calificacion = null;
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
            String sql = "SELECT * FROM calificacionusuario WHERE consumidoridusr = "+id;
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(Calificacionusuario.class);
            calificacion = query.list();
            tx.commit();
            return calificacion;
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            return null; 
        }finally {
            session.close(); 
        } 
    }
}
