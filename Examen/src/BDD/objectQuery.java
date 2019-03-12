/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDD;

import java.io.*;
import java.util.*;

/**
 *
 * @author mario
 */
public class objectQuery implements Serializable{
    class temas{
        String nombre;
    }
    class publicacion{
        String tema,texto, usuario,fecha;
        public String getTemaPublicacion(){
            return tema;
        }
        public String getTextoPublicacion(){
            return texto;
        }
        public String getUsuarioPublicacion(){
            return usuario;
        }
        public String getFechaPublicacion(){
            return fecha;
        }
    }
    private Vector <String> t;
    private Vector <publicacion>p;
    private boolean tipo;
    
    public objectQuery(){
        t = new Vector();
        p = new Vector();
    }
    
    public void setTema(String tema){
        t.add(tema);
    }
    
    public Vector<String> getTemas(){
        return t;
    }
    
    public void setPublicaciones(String tema, String texto, String usuario, String fecha){
        publicacion c = new publicacion();
        c.tema=tema;
        c.texto=texto;
        c.usuario=usuario;
        c.fecha=fecha;
        p.add(c);
    }
    
    public void setTipo(boolean t){
        tipo = t;
    }
    
    public boolean getTipo(){
        return tipo;
    }
}
