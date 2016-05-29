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
         
    @PostConstruct
    public void buscar(){
         LibroDAO lib = new LibroDAO();
         System.out.print("Este es el libro" + this.getTitulo());
         //this.setTitulo("Libro1");
         if(this.getTitulo()!=null && !this.getTitulo().equals(""))
         libros = lib.buscarNormal(this.getTitulo());
          titulo = "";
          //System.out.print(this.getLibros());
    }
    
}
