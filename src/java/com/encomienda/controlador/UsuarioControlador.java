
package com.encomienda.controlador;

import com.encomienda.modelo.Usuario;
import com.encomienda.servicios.Consulta;
import com.encomienda.servicios.Email;
import com.encomienda.servicios.UsuarioServicio;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UsuarioControlador {
    
    ModelAndView mav = new ModelAndView();
    String mensaje_error_jne = "|||DNI no encontrado en Padrón Electoral";
    Usuario usuario = new Usuario();
    UsuarioServicio usuarioServicio = new UsuarioServicio();
    int longitud = 8;
    
    @RequestMapping(value = "RegistroUsuario.htm", method = RequestMethod.GET)
    public ModelAndView consultar (){
        mav.setViewName("RegistroUsuario");
        return mav;
    }
    
    @RequestMapping(value = "RegistroUsuario.htm", method = RequestMethod.POST)
    public ModelAndView registrar (HttpServletRequest request, Email email){
        String dni = request.getParameter("dni").trim();
        String correo = request.getParameter("correo").trim();
        String contraseña = request.getParameter("contra").trim();
        
        Consulta rec = new Consulta();
        String nombre = rec.getNombre("http://aplicaciones007.jne.gob.pe/srop_publico/Consulta/Afiliado/GetNombresCiudadano?DNI="+dni);
        if(nombre == null || nombre.isEmpty() || nombre.equals(mensaje_error_jne)){
            mav.addObject("msj", "no funciona");
            return new ModelAndView("redirect:/error.htm");
        }else{                                                    
            
            try {
                
                String activador = usuarioServicio.getCadenaAlfanumAleatoria(longitud);
                
                String mostrar = nombre.toLowerCase().replace("|", " ");
               
                usuario.setIdentidicador(dni);
                usuario.setNombre(mostrar);
                usuario.setCorreo(correo);
                usuario.setContra(contraseña);
                usuario.setActivador(activador);

                usuarioServicio.Registrar(usuario);
                email.EmailConfirmacion(dni, correo, activador);
                return new ModelAndView("redirect:/exito.htm");                 
                
            } catch (MessagingException e) {               
                return new ModelAndView("redirect:/error.htm");
            }
        }
    }    
        
    @RequestMapping(value = "respuesta.htm", method = RequestMethod.GET)
    public ModelAndView respuesta(HttpServletRequest request){
        
        String identificador = request.getParameter("identificador");
        String cadena = request.getParameter("aleatorio");
        
        try {
            usuarioServicio.getActivarCuenta(identificador, cadena);
            mav.addObject("msj", "su cuenta ha sido activada");
            mav.setViewName("respuesta");
            
        } catch (Exception e) {
            mav.addObject("msj", "problema: "+e);
            mav.setViewName("error");            
        }
        
        return mav;
    }    
}
