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
        
        CalificacionUsuarioDAO cu = new CalificacionUsuarioDAO();
        Usuario c = cu.consumidor(3);
        System.out.println(c);
    }       
}
