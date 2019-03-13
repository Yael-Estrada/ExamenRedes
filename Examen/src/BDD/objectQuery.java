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
    class temas implements Serializable{
        String nombre;

        @Override
        public String toString() {
            return "Tema{" + "nombre=" + nombre + '}';
        }
        
    }
    public class publicacion implements Serializable{
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

        @Override
        public String toString() {
            return "Publicacion{" + "tema=" + tema + ", texto=" + texto + ", usuario=" + usuario + ", fecha=" + fecha + '}';
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

    public Vector<publicacion> getPublicaciones() {
        return p;
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
