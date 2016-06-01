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
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;
import modelo.CalificacionUsuarioDAO;
import modelo.Libro;
import modelo.Solicitudes;
import modelo.SolicitudesDAO;
import modelo.Usuario;
/**
 *
 * @author jonathanjb
 */
@ManagedBean
@ViewScoped
public class MisPrestamos implements Serializable{
    private List<Libro> libros;
    private Usuario usuario;
    
    private final FacesContext faceContext;
    private final HttpSession sesion;
    private List<Solicitudes> solicitudes;
    
   
    public MisPrestamos() {
        faceContext=FacesContext.getCurrentInstance();
        sesion = (HttpSession) faceContext.getExternalContext().getSession(false);
    }
    
    public List<Solicitudes> getSolicitudes() {
        return solicitudes;
    }
    
    public void listener(ActionEvent event){
        usuario = (Usuario)event.getComponent().getAttributes().get("usuario");
    }
    @PostConstruct
    public void misPrestamos() {
        SolicitudesDAO sd = new SolicitudesDAO();
        usuario = (Usuario)sesion.getAttribute("usuario");
        solicitudes = sd.aceptadas(usuario.getIdusuario());
    }
}
