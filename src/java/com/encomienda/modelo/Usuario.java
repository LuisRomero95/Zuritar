
package com.encomienda.modelo;


public class Usuario {
    
    private String identidicador;
    private String nombre;
    private String correo;
    private String contra;
    private String activador;

    public Usuario() {
    }

    public Usuario(String identidicador, String nombre, String correo, String contra, String activador) {
        this.identidicador = identidicador;
        this.nombre = nombre;
        this.correo = correo;
        this.contra = contra;
        this.activador = activador;
    }

    
    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdentidicador() {
        return identidicador;
    }

    public void setIdentidicador(String identidicador) {
        this.identidicador = identidicador;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getActivador() {
        return activador;
    }

    public void setActivador(String activador) {
        this.activador = activador;
    }
    
    
}
