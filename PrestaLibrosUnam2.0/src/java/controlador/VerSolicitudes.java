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
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;
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
    
    
    public VerSolicitudes() {
        faceContext=FacesContext.getCurrentInstance();
        sesion = (HttpSession) faceContext.getExternalContext().getSession(false);
        usuario = (Usuario)sesion.getAttribute("usuario");
    }

    private Usuario usuario;
    private List<Solicitudes> lsolicitud;
    
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Solicitudes> getLsolicitud() {
        SolicitudesDAO sd = new SolicitudesDAO();
        lsolicitud = sd.pendientesUsuario(usuario.getIdusuario());
        return lsolicitud;
    }

    public void setLsolicitud(List<Solicitudes> lsolicitud) {
        this.lsolicitud = lsolicitud;
    }
    
    public void listener(ActionEvent event){
    }

}