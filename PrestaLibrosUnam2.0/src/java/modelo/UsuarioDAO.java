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
public class UsuarioDAO extends AbstractDAO {

    public UsuarioDAO() {
        super();
    }

    /**
     * Insert a new Usuario into the database.
     *
     * @param usuario
     */
    public void save(Usuario usuario) throws DataAccessLayerException {
        super.save(usuario);
    }

    /**
     * Updates a new Usuario into the database.
     *
     * @param usuario
     */
    public void update(Usuario usuario) throws DataAccessLayerException {
        super.update(usuario);
    }

    /**
     * Delete a detached Usuario from the database.
     *
     * @param usuario
     */
    public void delete(Usuario usuario) throws DataAccessLayerException {
        super.delete(usuario);
    }

    /**
     * Find an Event by its primary key.
     *
     * @param id
     * @return
     */
    public Usuario find(Long id) throws DataAccessLayerException {
        return (Usuario) super.find(Usuario.class, id);
    }

    /**
     * Lista todos los usuarios de la base de datos
     *
     * @return
     */
    public List findAll() throws DataAccessLayerException {
        return super.findAll(Usuario.class);
    }
    
    public Usuario valida(String correo,String contrasenia){
        SessionFactory factory; 
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
            String sql = "SELECT * FROM usuario where correo ='" + correo + "' and contrasenia = '" + contrasenia+"'";
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(Usuario.class);
            List<Usuario> userList = query.list();
            tx.commit();
            if (userList!= null && !userList.isEmpty()) {
                System.out.println(userList.get(0));
                return userList.get(0);
            }else{
                return null;
            }
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            return null; 
        }finally {
            session.close(); 
        }  
    }
    
    public int maxIndice() {
        return super.maxIndice("usuario","idusuario");
    }
    
    public Usuario findCorreo(String correo){
        Usuario u = null;
        try{
            startOperation();
            Query query = session.createQuery("FROM Usuario WHERE correo  = \'" +correo+"\'");
            u =(Usuario)query.uniqueResult();
            tx.commit();
        } catch(HibernateException e){
            handleException(e);            
        } finally {
            HibernateFactory.close(session);
        }
        return u;
    }
    
}