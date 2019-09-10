/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encomienda.servicios;

import com.encomienda.modelo.Usuario;
import java.util.List;
import java.util.Random;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.ModelAndView;


public class UsuarioServicio implements Crud{
    
    Conexion con = new Conexion();    
    JdbcTemplate jt = new JdbcTemplate(con.conectar());
    ModelAndView mav = new ModelAndView();
    String mensaje_error_jne = "|||DNI no encontrado en Padrón Electoral";    
    
    @Override
    public void Registrar(Object object) {
        Usuario u = (Usuario) object;
        String sql = "INSERT INTO usuarios (identificador, nom, correo, contraseña, activador) VALUES (?,?,?,?,?)";
        try {
            this.jt.update(sql, u.getIdentidicador(), u.getNombre(), u.getCorreo(), u.getContra(), u.getActivador());    
        } catch (DataAccessException e) {
            throw e;
        }                        
    }

    @Override
    public void Editar(Object Object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Eliminar(Object Object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object BuscarPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List Consultar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public String getCadenaAlfanumAleatoria(int longitud) {
        String cadenaAleatoria = "";
        long milis = new java.util.GregorianCalendar().getTimeInMillis();
        Random r = new Random(milis);
        int i = 0;
        while (i < longitud) {
                char c = (char) r.nextInt(255);
                // System.out.println("char:"+c);
                if ((c >= '0' && c <= 9) || (c >= 'A' && c <= 'Z')) {
                        cadenaAleatoria += c;
                        i++;
                }
        }
        return cadenaAleatoria;
    }
    
    public void getActivarCuenta(String identificador, String activador){
        String sql ="UPDATE usuarios set estado = ? WHERE identificador = ? AND activador = ? AND estado = 1";
        this.jt.update(sql, 2, identificador, activador);
    }
    
    
}
