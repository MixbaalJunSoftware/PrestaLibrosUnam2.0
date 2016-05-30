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
import modelo.CalificacionLibroDAO;
import modelo.Calificacionlibro;
import modelo.Libro;
import modelo.Usuario;
import modelo.UsuarioDAO;
/**
 *
 * @author jonathanjb
 */
@ManagedBean
@SessionScoped
public class Entrar implements Serializable{
    
    private String correo;
    private String contrasenia;
    private String msn;
    private Integer calificacion;
    private Usuario usuario;
    private Libro libro;
    private  FacesContext faceContext;
    private HttpSession sesion;
    private List<Calificacionlibro> calificaciones;
        
    public Entrar(){
        faceContext = FacesContext.getCurrentInstance();
        sesion=(HttpSession)faceContext.getExternalContext().getSession(true);
    }
    
    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }
    
    public String getMsn() {
        return msn;
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
    
    
    public void setMsn(String msn) {
        this.msn = msn;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public boolean hayUsuario(){
        return this.getUsuario()!=null;
    }
    
//    public String salir(){
//        this.setUsuario(null);
//        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
//        return "PrincipalIH?faces-redirect=true";
//    }
    
    public String entrar(){
        UsuarioDAO ud = new UsuarioDAO();
        Usuario u = ud.valida(this.getCorreo(),this.getContrasenia());
        if(u!=null){
            this.setUsuario(u);
            this.setMsn("");
            this.setCorreo("");
            this.setContrasenia("");
            sesion.setAttribute("usuario", u);
            return "PrincipalIH?faces-redirect=true";
        }
        this.setMsn("Error! Contraseña o correo incorrectos");
        return "EntrarIH?faces-redirect=true";
    }
    
    public void listener(ActionEvent event){
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
