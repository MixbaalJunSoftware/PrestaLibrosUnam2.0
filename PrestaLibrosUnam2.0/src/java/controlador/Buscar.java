/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import modelo.Libro;
import modelo.LibroDAO;


/**
 *
 * @author danii
 */

@ManagedBean
@SessionScoped

public class Buscar implements Serializable{
    private List<Libro> libros;
    private String titulo;
    private String editorial;
    private String autor;
    private String genero;
    private String pais;
    private String msn;

     public List<Libro> getLibros() {
        return libros;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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

    public String getMsn() {
        return msn;
    }

    public void setMsn(String msn) {
        this.msn = msn;
    }
        
    //@PostConstruct
    public String buscarLibro(){
         LibroDAO lib = new LibroDAO();
         System.out.print("Este es el libro " + this.getTitulo());
         if(this.getTitulo()!=null && !this.getTitulo().equals("")){
            libros = lib.buscarNormal(this.getTitulo());
            titulo="";
            if(libros.isEmpty()){
                return "ErrorBusquedaIH?faces-redirect=true";
            }
            else{
              return "ResultadosIH?faces-redirect=true";
            }
         }
         else{
             return "ErrorBusquedaIH?faces-redirect=true";                    
         }
    }
    
    public String buscaAvanzado(){
        LibroDAO libr = new LibroDAO();
        if(this.getTitulo() != null && !this.getTitulo().equals("")){
            if(this.getAutor()!= null && !this.getTitulo().equals("")){
                if(this.getGenero()!= null && !this.getGenero().equals("")){
                    if(this.getPais() != null && !this.getPais().equals("")){
                        libros = libr.buscarAvanzada(this.getTitulo(),this.getAutor(),this.getGenero(),this.getPais());
                        titulo="";
                        autor="";
                        genero="";
                        pais="";
                        if(libros.isEmpty())
                            return "ErrorBusquedaIH?faces-redirect=true";
                        return "ResultadosIH?faces-redirect=true";
                    }else{
                        libros = libr.buscarAvanzada2(this.getTitulo(),this.getAutor(),this.getGenero());
                        titulo="";
                        autor="";
                        genero="";
                        if(libros.isEmpty())
                            return "ErrorBusquedaIH?faces-redirect=true";
                        return "ResultadosIH?faces-redirect=true";
                    }
                }else{ 
                    libros = libr.buscarAvanzada3(this.getTitulo(),this.getAutor());
                    titulo="";
                    autor="";
                    if(libros.isEmpty())
                            return "ErrorBusquedaIH?faces-redirect=true";
                    return "ResultadosIH?faces-redirect=true";                    
                }
            }else{
               this.setMsn("Se requiere del autor para realizar la búsqueda");
               return "ErrorBusquedaIH?faces-redirect=true";   
            }
        }else{
            this.setMsn("Se requiere del nombre del libro para realizar la búsqueda");
            return "ErrorBusquedaIH?faces-redirect=true";       
        }
              
    }
}
