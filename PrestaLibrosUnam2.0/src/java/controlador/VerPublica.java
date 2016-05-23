/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.ManagedProperty;
import modelo.Libro;
import modelo.LibroDAO;
import modelo.Usuario;
/**
 *
 * @author jonathanjb
 */
public class VerPublica implements Serializable {
   private List<Libro> libros;
    private Usuario usuario;
       
    @PostConstruct
    public void ver() {
        LibroDAO lib = new LibroDAO();
        libros = lib.findAll();
    }
 
    public List<Libro> getLibros() {
        return libros;
    }
    
    public String verUsuario(){
        return usuario.getFotoUsr();
    } 
}
