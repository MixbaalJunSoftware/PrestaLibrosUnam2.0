/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;
import modelo.CalificacionUsuarioDAO;
import modelo.Usuario;
import modelo.Calificacionusuario;
import modelo.Libro;

/**
 *
 * @author jonathanjb
 */
@ManagedBean
@ViewScoped
public class CalificaUsuario {
    private Usuario usuarioP;
    private int libro;
    private int usuarioC;
    private Integer calificacion;
    private String comentarios;
    
    private final FacesContext faceContext;
    private final HttpSession sesion;
    
    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
    
    

    public CalificaUsuario(FacesContext faceContext, HttpSession sesion) {
        this.faceContext = faceContext;
        this.sesion = sesion;
    }
    
    public void listener(ActionEvent event){
        libro = (int)event.getComponent().getAttributes().get("libro");
    }
    
    public String calificar(){
        CalificacionUsuarioDAO lib = new CalificacionUsuarioDAO();
        usuarioP = (Usuario)sesion.getAttribute("usuario");
        usuarioC = lib.consumidor(libro,usuarioP.getIdusuario());
        return " ";
    }
}
