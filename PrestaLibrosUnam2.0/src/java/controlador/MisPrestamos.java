/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;
import modelo.CalificacionUsuarioDAO;
import modelo.Libro;
import modelo.Usuario;
/**
 *
 * @author jonathanjb
 */
@ManagedBean
@ViewScoped
public class MisPrestamos {
    private List<Libro> libros;
    private Usuario usuario;
    
    private final FacesContext faceContext;
    private final HttpSession sesion;
   
    public MisPrestamos() {
        faceContext=FacesContext.getCurrentInstance();
        sesion = (HttpSession) faceContext.getExternalContext().getSession(false);
    }
    
    public List<Libro> getLibros() {
        return libros;
    }
    
    public void listener(ActionEvent event){
        usuario = (Usuario)event.getComponent().getAttributes().get("usuario");
    }
    @PostConstruct
    public void misPrestamos() {
        CalificacionUsuarioDAO lib = new CalificacionUsuarioDAO();
        usuario = (Usuario)sesion.getAttribute("usuario");
        libros = lib.misPrestamos(usuario.getIdusuario());
    }
}
