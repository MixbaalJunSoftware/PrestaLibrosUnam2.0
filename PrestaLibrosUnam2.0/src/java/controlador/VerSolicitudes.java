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
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;
import modelo.CalificacionUsuarioDAO;
import modelo.Solicitudes;
import modelo.SolicitudesDAO;
import modelo.Usuario;

/**
 *
 * @author luis
 */

@ManagedBean
@SessionScoped

public class VerSolicitudes implements Serializable{
    
    private FacesContext faceContext;
    private HttpSession sesion;
    
    private Integer calificacion;
    
    public VerSolicitudes() {
        faceContext=FacesContext.getCurrentInstance();
        sesion = (HttpSession) faceContext.getExternalContext().getSession(false);
        
    }
    
    
    private Usuario usuario;
    private List<Solicitudes> lsolicitud;

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Solicitudes> getLsolicitud() {
        return lsolicitud;
    }

    public void setLsolicitud(List<Solicitudes> lsolicitud) {
        this.lsolicitud = lsolicitud;
    }
    
    public void listener(ActionEvent event){
        usuario = (Usuario)event.getComponent().getAttributes().get("usuario");
        this.setCalificacion(rating(usuario));
    }
    
    public Integer rating(Usuario u){
        CalificacionUsuarioDAO l = new CalificacionUsuarioDAO();
        Integer p = l.promedio(u.getIdusuario());
        if(p<0){
            return -1;
        }else{
            return p;
        }
           
    }

    @PostConstruct
    public void verSolicitudes(){
        SolicitudesDAO sd = new SolicitudesDAO();
        usuario = (Usuario)sesion.getAttribute("usuario");
        lsolicitud = sd.pendientesUsuario(usuario.getIdusuario());
    }
    
}