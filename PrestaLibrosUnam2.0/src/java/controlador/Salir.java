/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author jonathanjb
 */
@ManagedBean
@ViewScoped

public class Salir {
    
    public String salir(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "PrincipalIH?faces-redirect=true";
    }
    
}
