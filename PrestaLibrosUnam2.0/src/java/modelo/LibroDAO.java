/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;
/**
 *
 * @author jonathanjb
 */
public class LibroDAO extends AbstractDAO {
    
    public LibroDAO() {
        super();
    }

    /**
     * Insert a new Libro into the database.
     *
     * @param libro
     */
    public void save(Libro libro) throws DataAccessLayerException {
        super.save(libro);
    }

    /**
     * Updates a new Libro into the database.
     *
     * @param libro
     */
    public void update(Libro libro) throws DataAccessLayerException {
        super.update(libro);
    }

    /**
     * Delete a detached Libro from the database.
     *
     * @param libro
     */
    public void delete(Libro libro) throws DataAccessLayerException {
        super.delete(libro);
    }

    /**
     * Find an Event by its primary key.
     *
     * @param id
     * @return
     */
    public Libro find(Long id) throws DataAccessLayerException {
        return (Libro) super.find(Libro.class, id);
    }

    /**
     * Lista todos los libros de la base de datos
     *
     * @return
     */
    public List findAll() throws DataAccessLayerException {
        return super.findAll(Libro.class);
    }
    
    public int maxIndice(){
        return super.maxIndice("libro", "idlibro");
    }
    
     public List findLibros(Long id) throws DataAccessLayerException {
        return super.findLibros(Libro.class, id);
    }
}
