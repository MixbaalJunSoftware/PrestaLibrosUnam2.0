/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import modelo.Usuario;
import modelo.UsuarioDAO;
import javax.faces.bean.ManagedBean;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
/**
 *
 * @author jonathanjb
 */

@ManagedBean
@SessionScoped

public class EditarUsuario implements Serializable{

  private Usuario usuario;
  
  private String nombre;//Nombre de el usuario.
  private String app;//Apellido paterno del usuario.
  private String apm;//Apellido materno del usuario.
  private String password;//contrasenia del usuario.
  private String cpassword;//confirmacion de la contraseña
  private String telefono;//Teleffono del usuario.
  private String facultad;//Facultad del usuario. 
  private String foto; //Foto del usuario.
  private String msn;  
  private boolean modificado;
 
  
  
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApp() {
        return app;
    }
    
    public void setApp(String app) {
        this.app = app;
    }

    public String getApm() {
        return apm;
    }
    
    public void setApm(String apm) {
        this.apm = apm;
    }

    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFacultad() {
        return facultad;
    }
    
    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public String getMsn() {
        return msn;
    }      

    public void setMsn(String msn) {
        this.msn = msn;
    }

    public String getCpassword() {
        return cpassword;
    }

    public void setCpassword(String cpassword) {
        this.cpassword = cpassword;
    }   

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
    
    
    
    public void listener(ActionEvent event){
        System.out.println("si inicializó al usuario");
	usuario = (Usuario)event.getComponent().getAttributes().get("usuario");
        
    }
    
    
      
    public String editar() {
        UsuarioDAO usr = new UsuarioDAO();
        if (this.getPassword() != null&&!this.getPassword().equals("")){  
            if(this.getPassword().equals(this.getCpassword())){
                usuario.setContrasenia(this.getPassword());
                modificado = true;
            }
            else{
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Error", "La contraseña no coincide") );
                return "";
            }
        }
        if(this.getTelefono()!= null&&!this.getTelefono().equals("")){
                usuario.setTelefono(this.getTelefono());
                modificado = true;
        }
        if (this.getFacultad()!=null&&!this.getFacultad().equals("")){
               usuario.setFacultad(this.getFacultad());
               modificado = true;
        }
        if (this.getFoto()!= null && !this.getFoto().equals("")){
            usuario.setFotoUsr(this.getFoto());
            modificado = true;
        }
        if (modificado){
            try{   
            usr.update(usuario);
            System.out.print("se actualizo el usuario");
            this.setMsn("Se actualizaron tus datos correctamente");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Exito", "Se actualizaron los datos correctamente") );
            password = "";
            cpassword = "";
            telefono = "";
            facultad = "";
            foto = "";
            return "PerfilIH?faces-redirect=true";   
           // return "";
            
        }catch(Exception e){
            System.out.print("algo salio mal");
             this.setMsn("¡Ups! Ocurrió un error");
             FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Ups", "Ocurrio un error, vuelve a intentarlo") );
            //return "EditarCuentaIH?faces-redirect=true"; 
            return "";
        }
        }
        else {
            this.setMsn("No hay ningun dato para modificar");
            System.out.print("no se edito nada");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Ups", "No hay ningun dato por modificar") );
            //return "EditarCuentaIH?faces-redirect=true";
            return "";
        }
    }
    
    public void upload (FileUploadEvent event) {
      FacesMessage msg = new FacesMessage("Success! ", event.getFile().getFileName() + " is uploaded.");
      FacesContext.getCurrentInstance().addMessage(null, msg);
      // Do what you want with the file
       try {
         copyFile(String.valueOf(usuario.getIdusuario()), event.getFile().getInputstream());
         System.out.print("hizo algo");
         this.setMsn("Actualización con éxito");
       } catch (IOException e) {
         FacesMessage msg2 = new FacesMessage("Is NOT Succesful", event.getFile().getFileName() + " is not uploaded.");
         FacesContext.getCurrentInstance().addMessage(null, msg);
         this.setMsn("Ocurrió un error, vuelve a intentarlo");
         
        }
       
    }
    
    
    public void copyFile(String fileName, InputStream in) {
        try {
            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String destination = (servletContext.getRealPath("/"))+"public/imagenes/";
            OutputStream out = new FileOutputStream(new File(destination + fileName));
            int read = 0; 
            byte[] bytes = new byte[1024]; 
            while ((read = in.read(bytes)) != -1) {
            out.write(bytes, 0, read);
            //UsuarioDAO ud = new UsuarioDAO();
            //usuario.setFotoUsr("/public/imagenes/" + fileName);
            //ud.update(usuario);
            this.setFoto("/public/imagenes/" + fileName);
           // this.setMsn("Actualización con éxito");
        }
        in.close();
        out.flush();
        out.close();
        System.out.println("New file created!");
       
        } catch (IOException e) {
            System.out.println(e.getMessage());
           
        }
    } 
    
}
