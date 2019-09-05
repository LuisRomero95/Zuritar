/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encomienda.servicios;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 *
 * @author LMROMERO
 */
public class Consulta {
 
    //método que obtiene los datos de la página web
    //y extrae el resultado de la consulta
    public  String getNombre (String url){
        String datos = "";
        try {
            //se lee la pagina web con el url
            Document doc = Jsoup.connect(url).get();
            //se obtiene los datos de la etiqueta body
            Element el = doc.body();
            //se extrae los datos de la etiqueta elegida
            datos = el.text();
        } catch (IOException ex) {
            Logger.getLogger(Consulta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datos;
    }
    
}
