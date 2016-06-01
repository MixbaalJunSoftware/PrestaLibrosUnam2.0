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
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;
import modelo.CalificacionUsuarioDAO;
import modelo.Calificacionusuario;
import modelo.Solicitudes;
import modelo.Usuario;

/**
 *
 * @author jonathanjb
 */
@ManagedBean
@SessionScoped
public class VerUsuario implements Serializable{
    private FacesContext faceContext;
    private HttpSession sesion;
    private Solicitudes solicitud;
    private Integer calificacion;
    private List<Calificacionusuario> calificacionesU;
    
    public VerUsuario() {
        faceContext=FacesContext.getCurrentInstance();
        sesion = (HttpSession) faceContext.getExternalContext().getSession(false);
    }
    
    public void listener(ActionEvent event){
        CalificacionUsuarioDAO cud = new CalificacionUsuarioDAO();
        solicitud = (Solicitudes)event.getComponent().getAttributes().get("solicitud");
        this.calificacion = this.rating(solicitud);
        this.calificacionesU = cud.calificacionesU(solicitud.getUsuario().getIdusuario());
    }

    public Solicitudes getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitudes solicitud) {
        this.solicitud = solicitud;
    }

    public List<Calificacionusuario> getCalificacionesU() {
        return calificacionesU;
    }

    public void setCalificacionesU(List<Calificacionusuario> calificacionesU) {
        this.calificacionesU = calificacionesU;
    }
           
    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }
    
    public Integer rating(Solicitudes s){
        CalificacionUsuarioDAO l = new CalificacionUsuarioDAO();
        Usuario u=s.getUsuario();
        Integer p = l.promedio(u.getIdusuario());
        System.out.println(p);
        if(p<0){
            return -1;
        }else{
            return p;
        }
           
    }
    
}
