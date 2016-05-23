/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import modelo.DataAccessLayerException;
import modelo.Libro;
import modelo.LibroDAO;
/**
 *
 * @author jonathanjb
 */
@ManagedBean
@RequestScoped
public class EliminarLibro {

    public Libro libro;

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }
    
    public void listener(ActionEvent event){
	libro = (Libro)event.getComponent().getAttributes().get("libro1");
        
    }

    public String EliminarLibro() {
        LibroDAO ld = new LibroDAO();
        FacesContext context = FacesContext.getCurrentInstance();
        try{
            ld.delete(libro);
            return "MisLibrosIH?faces-redirect=true";
        }catch(DataAccessLayerException dale){            
            context.addMessage(null, new FacesMessage("ERROR!", "El libro no puede ser eliminado. Aun tiene solicitudes pendientes") );
            return "";
        }
    }

}
