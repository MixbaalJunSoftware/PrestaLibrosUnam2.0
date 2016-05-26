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
    
    
    
}
