/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import modelo.CalificacionLibroDAO;
import modelo.Calificacionlibro;
import modelo.Libro;
import modelo.Usuario;

/**
 *
 * @author luis
 */
@ManagedBean
@SessionScoped

public class VerLibro implements Serializable{

    private Libro libro;
    private Usuario usuario;
    private List<Calificacionlibro> calificaciones;
    private Integer calificacion;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }


    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public List<Calificacionlibro> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(List<Calificacionlibro> calificaciones) {
        this.calificaciones = calificaciones;
    }
    
    public void listener(ActionEvent event){
        usuario = (Usuario)event.getComponent().getAttributes().get("usr");
	libro = (Libro)event.getComponent().getAttributes().get("lb");
    }

    public boolean esMiLibro(){
        if(usuario == null)
            return true;
        return libro.getUsuario().getIdusuario() == usuario.getIdusuario();
    }
    
    public String rating(){
        CalificacionLibroDAO l = new CalificacionLibroDAO();
        Integer p = l.promedio(libro.getIdlibro());
        if(p<0){
            this.setCalificacion(0);
        }else{
            this.setCalificacion(p);
        }
            
        return "LibroIH?faces-redirect=true"; 
    }
    
    public void ver() {
        CalificacionLibroDAO lib = new CalificacionLibroDAO();
        calificaciones = lib.calificaciones(libro.getIdlibro());
    }
    
}
