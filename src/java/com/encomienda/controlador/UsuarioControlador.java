
package com.encomienda.controlador;

import com.encomienda.servicios.Conexion;
import com.encomienda.servicios.Consulta;
import javax.servlet.http.HttpServletRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UsuarioControlador {
    
    Conexion con = new Conexion();
    JdbcTemplate jt = new JdbcTemplate(con.conectar());
    ModelAndView mav = new ModelAndView();
    
    @RequestMapping(value = "reniec.htm", method = RequestMethod.GET)
    public ModelAndView consultar (){
        mav.setViewName("reniec");
        return mav;
    }
    
    @RequestMapping(value = "reniec.htm", method = RequestMethod.POST)
    public ModelAndView consultar (HttpServletRequest request){
        String dni = request.getParameter("dni").trim();
        Consulta rec = new Consulta();
        String nombre = rec.getNombre("http://aplicaciones007.jne.gob.pe/srop_publico/Consulta/Afiliado/GetNombresCiudadano?DNI="+dni);
        if(nombre == null || nombre.isEmpty() ){
            mav.addObject("msj", "no funciona");
            return new ModelAndView("redirect:/exito.htm");
        }else{
                        
                String mostrar = nombre.replace("|", " ");
                
                String sql = "INSERT INTO estudiante (nombre,edad) VALUES (?,?)";
                this.jt.update(sql,mostrar, 25);
                mav.addObject("nombre",mostrar);
                return new ModelAndView("redirect:/exito.htm");            
        }

    }    
    
}
