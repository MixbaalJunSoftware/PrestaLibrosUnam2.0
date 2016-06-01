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
import modelo.CalificacionLibroDAO;
import modelo.Usuario;
import modelo.Calificacionlibro;
import modelo.Libro;
import modelo.Solicitudes;
import modelo.SolicitudesDAO;
/**
 *
 * @author jonathanjb
 */
@ManagedBean
@SessionScoped
public class CalificaLibro implements Serializable {
    private Solicitudes solicitud;
    private Usuario usuarioC;
    private Integer calificacion;
    private String comentarios;
    
    private final FacesContext faceContext;
    private final HttpSession sesion;

    public CalificaLibro() {
        faceContext=FacesContext.getCurrentInstance();
        sesion = (HttpSession) faceContext.getExternalContext().getSession(true);
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
    
    public String calificarl(){
        CalificacionLibroDAO lib = new CalificacionLibroDAO();
        usuarioC = (Usuario)sesion.getAttribute("usuario");
        Libro libro = solicitud.getLibro();
        if(libro==null)
            return "#2";
        
        if( this.getCalificacion()==null || this.getComentarios()==null){
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Error", "Debes dar una calificacion") );
            return "";
        }
        Calificacionlibro cl = new Calificacionlibro();
        cl.setIdcalificacionlibro(lib.maxIndice());
        cl.setCalificacion(calificacion);
        cl.setComentario(comentarios);
        cl.setLibro(libro);
        cl.setUsuario(usuarioC);
        usuarioC.getCalificacionlibros().add(cl);
        libro.getCalificacionlibros().add(cl);
        lib.save(cl);
        solicitud.setCaliflibro(true);
        SolicitudesDAO sd = new SolicitudesDAO();
        sd.update(solicitud);
        cl.setCalificacion(0);
        cl.setComentario(null);
        return "LibrosPorCalificarIH?faces-redirect=true";
    }
}
