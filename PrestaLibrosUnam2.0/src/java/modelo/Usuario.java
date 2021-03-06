package modelo;
// Generated 17/05/2016 05:07:52 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Usuario generated by hbm2java
 */
public class Usuario  implements java.io.Serializable {


     private int idusuario;
     private String nombre;
     private String app;
     private String apm;
     private String correo;
     private String facultad;
     private String telefono;
     private String contrasenia;
     private String fotoUsr;
     private Set calificacionlibros = new HashSet(0);
     private Set solicitudeses = new HashSet(0);
     private Set calificacionusuariosForPrestadoridusr = new HashSet(0);
     private Set libros = new HashSet(0);
     private Set calificacionusuariosForConsumidoridusr = new HashSet(0);

    public Usuario() {
    }

	
    public Usuario(int idusuario) {
        this.idusuario = idusuario;
    }
    public Usuario(int idusuario, String nombre, String app, String apm, String correo, String facultad, String telefono, String contrasenia, String fotoUsr, Set calificacionlibros, Set solicitudeses, Set calificacionusuariosForPrestadoridusr, Set libros, Set calificacionusuariosForConsumidoridusr) {
       this.idusuario = idusuario;
       this.nombre = nombre;
       this.app = app;
       this.apm = apm;
       this.correo = correo;
       this.facultad = facultad;
       this.telefono = telefono;
       this.contrasenia = contrasenia;
       this.fotoUsr = fotoUsr;
       this.calificacionlibros = calificacionlibros;
       this.solicitudeses = solicitudeses;
       this.calificacionusuariosForPrestadoridusr = calificacionusuariosForPrestadoridusr;
       this.libros = libros;
       this.calificacionusuariosForConsumidoridusr = calificacionusuariosForConsumidoridusr;
    }
   
    public int getIdusuario() {
        return this.idusuario;
    }
    
    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApp() {
        return this.app;
    }
    
    public void setApp(String app) {
        this.app = app;
    }
    public String getApm() {
        return this.apm;
    }
    
    public void setApm(String apm) {
        this.apm = apm;
    }
    public String getCorreo() {
        return this.correo;
    }
    
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getFacultad() {
        return this.facultad;
    }
    
    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }
    public String getTelefono() {
        return this.telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getContrasenia() {
        return this.contrasenia;
    }
    
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    public String getFotoUsr() {
        return this.fotoUsr;
    }
    
    public void setFotoUsr(String fotoUsr) {
        this.fotoUsr = fotoUsr;
    }
    public Set getCalificacionlibros() {
        return this.calificacionlibros;
    }
    
    public void setCalificacionlibros(Set calificacionlibros) {
        this.calificacionlibros = calificacionlibros;
    }
    public Set getSolicitudeses() {
        return this.solicitudeses;
    }
    
    public void setSolicitudeses(Set solicitudeses) {
        this.solicitudeses = solicitudeses;
    }
    public Set getCalificacionusuariosForPrestadoridusr() {
        return this.calificacionusuariosForPrestadoridusr;
    }
    
    public void setCalificacionusuariosForPrestadoridusr(Set calificacionusuariosForPrestadoridusr) {
        this.calificacionusuariosForPrestadoridusr = calificacionusuariosForPrestadoridusr;
    }
    public Set getLibros() {
        return this.libros;
    }
    
    public void setLibros(Set libros) {
        this.libros = libros;
    }
    public Set getCalificacionusuariosForConsumidoridusr() {
        return this.calificacionusuariosForConsumidoridusr;
    }
    
    public void setCalificacionusuariosForConsumidoridusr(Set calificacionusuariosForConsumidoridusr) {
        this.calificacionusuariosForConsumidoridusr = calificacionusuariosForConsumidoridusr;
    }

    @Override public String toString(){
        return this.getNombre() + this.contrasenia+this.app+this.getIdusuario();
    }


}


