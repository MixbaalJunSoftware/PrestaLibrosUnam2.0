/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import modelo.Libro;

/**
 *
 * @author luis
 */
@ManagedBean
@RequestScoped

public class ReactivarLibro {
    private Libro libro;
    
    public String reactivarLibro(){
        libro.setDisponible(true);
        return "";
    }
    
}
