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
    
    public List buscarNormal(String nombre) throws DataAccessLayerException {
        return super.buscaNormal(Libro.class, nombre);
    }
    
    public List buscarAvanzada(String nombre, String autor, String genero, String pais){
        return super.buscaAvanzada(Libro.class, nombre, autor, genero, pais);
    }
    
    public List buscarAvanzada2(String nombre, String autor, String genero){
        return super.buscaAvanzada2(Libro.class, nombre, autor, genero);
    }
    
    public List buscarAvanzada3(String nombre, String autor){
       return super.buscaAvanzada3(Libro.class, nombre, autor);
    }
    
    public List librosDisponibles() throws DataAccessLayerException {
        return super.findDisponibles(Libro.class);
    } 
    
}
