
package com.encomienda.modelo;


public class Usuario {
    
    private String nombre;
    String correo;
    private String contra;

    public Usuario() {
    }

    
    public Usuario(String nombre, String contra) {
        this.nombre = nombre;
        this.contra = contra;
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
    
    
}
