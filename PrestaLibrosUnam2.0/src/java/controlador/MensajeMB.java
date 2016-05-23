/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import modelo.Usuario;
import modelo.UsuarioDAO;

/**
 *
 * @author luis
 */
@ManagedBean
@ViewScoped

public class MensajeMB implements Serializable {
    
    private String correo;
    private String msn;

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getMsn() {
        return msn;
    }

    public void setMsn(String msn) {
        this.msn = msn;
    }
    
    public String contraseniaOlvidada(){
        UsuarioDAO ud = new UsuarioDAO();
        Mail m = new Mail();
        Usuario u = ud.findCorreo(correo);
        if(u == null){
            msn = "Error, Ese correo No está registrado";
            return "";
        }
        String asunto = "Recuperar contraseña";
        String mensaje = "Su contraseña es:\n"+u.getContrasenia();
        m.sendMail(asunto, mensaje,u.getCorreo());
        correo = "";
        msn = "";
        return "EntrarIH?faces-redirect=true";
    }
    
}