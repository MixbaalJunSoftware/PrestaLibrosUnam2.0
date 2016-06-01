/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.File;
import modelo.CalificacionUsuarioDAO;
import modelo.Libro;
import modelo.LibroDAO;
import modelo.Usuario;
import controlador.Buscar;
import java.util.Date;
import java.util.List;
import modelo.CalificacionLibroDAO;
import modelo.Solicitudes;
import modelo.SolicitudesDAO;

/**
 *
 * @author jonathanjb
 */
public class Main {
   
    public static void main(String[] args){
        /*File miDir = new File (".");
        try {
            System.out.println ("Directorio actual: " + miDir.getCanonicalPath());
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        */
       /*
        CalificacionLibroDAO cu = new CalificacionLibroDAO();
        Integer c = cu.promedio(4);
        System.out.println(c);
       */
       /* 
        String tit = "Libro1";
        String aut = "Libro2";
        Buscar b = new Buscar();
        b.setTitulo(tit);
        b.setAutor(aut);
        System.out.print("Esto hacienco" +
                b.buscaAvanzado());
//        */
//       SolicitudesDAO sd = new SolicitudesDAO();
//       Solicitudes s = new Solicitudes();
//       s.setIdsolicitudes(sd.maxIndice());
//       s.setAceptado(Boolean.TRUE);
//       s.setCaliflibro(Boolean.FALSE);
//       s.setCalifusr(Boolean.FALSE);
//       s.setFecha(new Date());
//       s.setLibro(new Libro(1));
//       s.setUsuario(new Usuario(1));
//       sd.save(s);
        
               
    }       
}
