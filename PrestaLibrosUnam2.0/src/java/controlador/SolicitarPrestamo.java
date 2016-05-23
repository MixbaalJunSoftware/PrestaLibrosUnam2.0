package controlador;

import java.io.Serializable;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import modelo.Libro;
import modelo.LibroDAO;
import modelo.Solicitudes;
import modelo.SolicitudesDAO;
import modelo.UsuarioDAO;
import modelo.Usuario;

@ManagedBean
@ViewScoped

public class SolicitarPrestamo implements Serializable{

    private Libro libro;
    private Usuario usuario;
  
  
    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
  
    public void listener(ActionEvent event){
	libro = (Libro)event.getComponent().getAttributes().get("libro");
        usuario = (Usuario)event.getComponent().getAttributes().get("usuario");
    }
    
  

    public String SolicitarPrestamo() {
        SolicitudesDAO sd = new SolicitudesDAO();
        LibroDAO ld = new LibroDAO();
        UsuarioDAO ud = new UsuarioDAO();
        Solicitudes solicitud = new Solicitudes();
        Date date = new Date();
        if(sd.solicitudUsuarioLibro(usuario.getIdusuario(),libro.getIdlibro()) != null){
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("ERROR!", "Ya has solicitado este libro antes") );
            return "";
        }
        solicitud.setIdsolicitudes(sd.maxIndice());
        solicitud.setUsuario(usuario);
        solicitud.setLibro(libro);
        solicitud.setAceptado(false);
        solicitud.setFecha(date);
        libro.getSolicitudeses().add(solicitud);
        usuario.getSolicitudeses().add(solicitud);
        sd.save(solicitud);
        Mail m = new Mail();
        String Subject = "Libro Solicitado";
        String Mensage = "Se ha solicitado tu libro" + libro.getNombre();
        System.out.println(libro.getUsuario().getCorreo());
        m.sendMail(Subject, Mensage, libro.getUsuario().getCorreo());
        return "PrincipalIH?faces-redirect=true";
    }

}