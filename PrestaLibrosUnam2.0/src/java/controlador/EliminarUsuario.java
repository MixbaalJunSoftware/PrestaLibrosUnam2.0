/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import modelo.Usuario;
import modelo.UsuarioDAO;
import controlador.Entrar;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
/**
 *
 * @author jonathanjb
 */

@ManagedBean
@SessionScoped
public class EliminarUsuario {

  public Usuario usuario;
  private String msn;
  
  
   public String getMsn() {
        return msn;
    }      

    public void setMsn(String msn) {
        this.msn = msn;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
     public void listener(ActionEvent event){
	usuario = (Usuario)event.getComponent().getAttributes().get("usuario");
    }

  public String eliminar() {
      UsuarioDAO usr = new UsuarioDAO();
      try{
          usr.delete(usuario);
          System.out.print("Se elimino correctamente");
          this.setMsn("Se elimino correctamente el usuario");
         FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario eliminado", "Hasta luego!");
         FacesContext.getCurrentInstance().addMessage(null, mensaje);
    
          Entrar en = new Entrar();
          return en.salir();          
      }catch(Exception e){
          this.setMsn("¡UPS! Ocurrio un error, vuelve a intentarlo");
          System.out.print("Ocurrio un error");
          FacesContext context = FacesContext.getCurrentInstance();
          context.addMessage(null, new FacesMessage("Ups", "Ocurrio un error") );
          context.addMessage(null, new FacesMessage("Alerta", "Tienes libros registrados y/o solicitudes pendientes") );
          return "";
      }
  }

}
