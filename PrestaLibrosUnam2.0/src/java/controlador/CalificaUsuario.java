/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;
import modelo.CalificacionUsuarioDAO;
import modelo.Usuario;
import modelo.Calificacionusuario;
import modelo.Libro;
import modelo.Solicitudes;
import modelo.SolicitudesDAO;


/**
 *
 * @author jonathanjb
 */
@ManagedBean
@SessionScoped
public class CalificaUsuario implements Serializable{
    private Usuario usuarioP;
    private Solicitudes solicitud;
    private Usuario usuarioC;
    private Integer calificacion;
    private String comentarios;
    
    private final FacesContext faceContext;
    private final HttpSession sesion;
    
    public CalificaUsuario() {
        faceContext=FacesContext.getCurrentInstance();
        sesion = (HttpSession) faceContext.getExternalContext().getSession(true);
    }

    public Solicitudes getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitudes solicitud) {
        this.solicitud = solicitud;
    }
    
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
    
    public void listener(ActionEvent event){
        System.out.println("Donde estas libro??");
	solicitud = (Solicitudes)event.getComponent().getAttributes().get("lb");
    }
    
    public String calificar(){
        CalificacionUsuarioDAO lib = new CalificacionUsuarioDAO();
        Libro libro = solicitud.getLibro();
        usuarioP = (Usuario)sesion.getAttribute("usuario");
        if(usuarioP==null)
           return "#1";
        if(libro==null)
            return "#2";
        usuarioC = lib.consumidor(libro.getIdlibro());
        if(usuarioC==null)
            return "#3";
        System.out.println(usuarioP);
        System.out.println(usuarioC);
        System.out.println(libro);
        if( this.getCalificacion()==null || this.getComentarios()==null){
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Error", "Debes dar una calificacion") );
            return "";
        }
        Calificacionusuario cu = new Calificacionusuario();
        cu.setCalificacion(calificacion);
        cu.setComentarios(comentarios);
        cu.setIdcalificacionusr(lib.maxIndice());
        cu.setUsuarioByConsumidoridusr(usuarioC);
        cu.setUsuarioByPrestadoridusr(usuarioP);
        usuarioP.getCalificacionusuariosForPrestadoridusr().add(cu);
        usuarioC.getCalificacionusuariosForConsumidoridusr().add(cu);
        lib.save(cu);
        SolicitudesDAO sd = new SolicitudesDAO();
        solicitud.setCalifusr(true);
        sd.update(solicitud);
        return "MisPrestamosIH?faces-redirect=true";
    }
    
    public boolean yaCAlifique(){
        return calificacion>0;
    }
    
    
}
