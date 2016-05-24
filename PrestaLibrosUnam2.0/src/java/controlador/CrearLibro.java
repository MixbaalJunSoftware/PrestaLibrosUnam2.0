/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import modelo.Libro;
import modelo.LibroDAO;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import modelo.Usuario;
import org.primefaces.event.FileUploadEvent;

@ManagedBean
@ViewScoped
/**
 *
 * @author jonathanjb
 */
public class CrearLibro implements Serializable{
    
    private String nombre;
    private String editorial;
    private String autor;
    private String descripcion;
    private String genero;
    private String pais;
    private String foto;
    private int idLibro;
    private String msn ;
    private Usuario usuario;

    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }
    
    public String getMsn() {
        return msn;
    }

    public void setMsn(String msn) {
        this.msn = msn;
    }
    
    public void listener(ActionEvent event){
	usuario = (Usuario)event.getComponent().getAttributes().get("usuario");
    }
    
   
  
    public String crearLibro() {
        LibroDAO ld = new LibroDAO();
        Libro libro = new Libro();
        libro.setIdlibro(ld.maxIndice());
        libro.setNombre(this.getNombre());
        libro.setEditorial(this.getEditorial());
        libro.setAutor(this.getAutor());
        libro.setDescripcion(this.getDescripcion());
        libro.setGenero(this.getGenero());
        libro.setPais(this.getPais());
        libro.setUsuario(usuario);
        libro.setDisponible(true);
        if(foto == null){
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Error", "Debes subir una imagen") );
            return "";
        }
        libro.setFotoLibro(this.getFoto());
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Exito", "El libro Se creó correctamente") );
        nombre="";
        editorial="";
        autor="";
        descripcion="";
        genero="";
        pais="";
        foto="";
        ld.save(libro);
        return "PerfilIH?faces-redirect=true";
    }
    
    
    public void upload (FileUploadEvent event) {
      FacesMessage msg = new FacesMessage("Success! ", event.getFile().getFileName() + " is uploaded.");
      FacesContext.getCurrentInstance().addMessage(null, msg);
      // Do what you want with the file
        try {
            LibroDAO ld = new LibroDAO();
            copyFile(String.valueOf(ld.maxIndice()), event.getFile().getInputstream());
        } catch (IOException e) {
            FacesMessage msg2 = new FacesMessage("Is NOT Succesful", event.getFile().getFileName() + " is not uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
    
    public void copyFile(String fileName, InputStream in) {
        try {
            File miDir = new File (".");
            String destination = miDir.getCanonicalPath() + "/web/public/imagenes/libros/";
            OutputStream out = new FileOutputStream(new File(destination + fileName));
            int read = 0; 
            byte[] bytes = new byte[1024]; 
            while ((read = in.read(bytes)) != -1) {
            out.write(bytes, 0, read);
            this.setFoto("/public/imagenes/libros/" + fileName);
        }
        in.close();
        out.flush();
        out.close();
        System.out.println("New file created!");
        } catch (IOException e) {
            int i = 1/0;
            System.out.println(e.getMessage());
        }
    }   

}