
package com.encomienda.controlador;

import com.encomienda.servicios.Conexion;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginControlador {
    
    Conexion con = new Conexion();
    JdbcTemplate jt = new  JdbcTemplate(con.conectar());
    ModelAndView mav = new ModelAndView();

    @RequestMapping(value = "login.htm", method = RequestMethod.GET)
    public ModelAndView login(){
        mav.setViewName("login");
        return mav;
    }
    
    @RequestMapping(value = "exito.htm", method = RequestMethod.GET)
    public ModelAndView exito(){
        mav.setViewName("exito");
        return mav;
    }    
    
    @RequestMapping(value = "error.htm", method = RequestMethod.GET)
    public ModelAndView error(){
        mav.setViewName("error");
        return mav;
    }        
           
    @RequestMapping(value = "login.htm", method = RequestMethod.POST)
    public ModelAndView login (HttpServletRequest request ){
        String nombre = request.getParameter("nombre");
        String contra = request.getParameter("contra");
        //String sql = "SELECT * FROM clientes WHERE nombre = '"+nombre+"' AND contraseña = '"+contra+"'";
        String sql = "SELECT nom, contraseña FROM usuarios WHERE nom = ? AND contraseña =?";
        List est =  this.jt.queryForList(sql, nombre, contra);
        if(est.size() > 0){
            return new ModelAndView("redirect:/exito.htm");
        }else{
            mav.addObject("msj", "usuario o contraseña incorrecto");
            return new ModelAndView("redirect:/error.htm");
        }
        
    }
    
}
