/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.Serializable;
import java.util.Date;
import java.util.Calendar;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import modelo.Libro;
import modelo.LibroDAO;
import modelo.Solicitudes;
import modelo.SolicitudesDAO;

/**
 *
 * @author luis
 */
@ManagedBean
@ViewScoped

public class ReactivarLibro implements Serializable{
    
    private Solicitudes solicitud;

    private Date sumarRestarDiasFecha(Date fecha, int dias){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); // Configuramos la fecha que se recibe
        calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de días a añadir, o restar en caso de días<0
        return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos
    }
    
    public void listener(ActionEvent event){
	solicitud = (Solicitudes)event.getComponent().getAttributes().get("lb");
    }
    
    public String reactivarLibro(){
        if(!solicitud.getCaliflibro()){
            //Date fecha = sumarRestarDiasFecha(solicitud.getFecha(),7);//fecha de una semana despues de hacer la solicitud
            //if( fecha.compareTo(new Date())> 0){ //comprobar si aun no se llega a dicha fecha
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Error \n Aun no han calificado el libro","") );
                return "";
            //}
        }
        Libro libro = solicitud.getLibro();
        libro.setDisponible(true);
        SolicitudesDAO sd = new SolicitudesDAO();
        LibroDAO l = new LibroDAO();
        l.update(libro);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Exito", "Se ha Reactivado el libro "+libro.getNombre()) );
        sd.delete(solicitud);
        return "MisPrestamosIH?faces-redirect=true";
    }
    
}
