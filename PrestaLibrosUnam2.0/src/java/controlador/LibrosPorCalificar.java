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
import modelo.CalificacionLibroDAO;
import modelo.Libro;
import modelo.Usuario;

/**
 *
 * @author jonathanjb
 */
@ManagedBean
@ViewScoped
public class LibrosPorCalificar implements Serializable{
    private List<Libro> libros;
    private Usuario usuario;
    private final FacesContext faceContext;
    private final HttpSession sesion;
    public LibrosPorCalificar() {
        faceContext=FacesContext.getCurrentInstance();
        sesion = (HttpSession) faceContext.getExternalContext().getSession(false);
    }
    public List<Libro> getLibros() {
        return libros;
    }
    @PostConstruct
    public void librosCalificar() {
        CalificacionLibroDAO lib = new CalificacionLibroDAO();
        usuario = (Usuario)sesion.getAttribute("usuario");
        libros = lib.librosPorCalificar(usuario.getIdusuario());
    }
}
