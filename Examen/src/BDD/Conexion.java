/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDD;

//import com.mysql.jdbc.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mario
 */
public class Conexion {
    
    public static void main(String [] args){
        new Conexion();
    }
    
    Connection conexion;
    public  Conexion(){
        String url = "jdbc:mysql://localhost:3306/FORO?useSSL=false";
        String user = "root";
        String pass = "root";
        System.out.println("Conectando…");
        try{
            conexion = DriverManager.getConnection(url,user,pass);
            System.out.println("Se ha conectado con la base de datos");    
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }       
    }
    
    public objectQuery buscarTemas(){
        objectQuery temas = new objectQuery();
        temas.setTipo(true);//1 temas, 0 publicaciones
        try {
            Statement s = conexion.createStatement();
            ResultSet r;
            r = s.executeQuery("select nombre from temas;");
            while(r.next()){
                temas.setTema(r.getString("nombre"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return temas;
    }
    
    
    public objectQuery mostrarPublicaciones(String tema){
        objectQuery publicaciones = new objectQuery();
        publicaciones.setTipo(false);
        try {
            Statement s = conexion.createStatement();
            ResultSet r;
            r = s.executeQuery("call mostrarPublicaciones('"+tema+"');");
             while(r.next()){
                publicaciones.setPublicaciones(r.getString("Tema"),r.getString("publicacion"),r.getString("usuario"),r.getString("fecha"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return publicaciones;
    }
    
    public objectQuery buscarPublicaciones(String tema, String fecha){
        objectQuery publicaciones = new objectQuery();
        publicaciones.setTipo(false);
        int idtema=-1;
        try {
            Statement s = conexion.createStatement();
            ResultSet r;
            r = s.executeQuery("select idTemas from temas where nombre='"+tema+"';");
            if(r.next()){
              idtema=r.getInt("idTemas");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        String pub = null,us = null,fe=null;
        try {
            Statement s = conexion.createStatement();
           ResultSet r;
            r = s.executeQuery("select*from publicaciones where idTemas="+idtema+" or fecha='"+fecha+"';");
            while(r.next()){
                idtema=r.getInt("idTemas");
                pub=r.getString("publicacion");
                us=r.getString("usuario");
                fe=r.getString("fecha");
                //publicaciones.setPublicaciones(tema,r.getString("publicacion"),r.getString("usuario"),r.getString("fecha"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        String nom="";
        try {
            Statement s = conexion.createStatement();
            ResultSet r;
            r = s.executeQuery("select nombre from temas where idTemas="+idtema+";");
            if(r.next()){
              nom=r.getString("nombre");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        publicaciones.setPublicaciones(nom,pub,us,fe);
        return publicaciones;
    }
    
    public void crearTema(String tema, String descripcion){
        try {
            try {
                Statement s = conexion.createStatement();
                s.executeQuery("call crearTema('"+tema+"','"+descripcion+"');");
                s.close();
            } catch (SQLException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("No se pudo crear el tema");
            }
            int pto=1234;
            String host="127.0.0.1";
            Socket cl=new Socket(host,pto);
            PrintWriter pw=new PrintWriter(new OutputStreamWriter(cl.getOutputStream())); 
            String eco="Crear Tema";
            pw.println(eco);
            pw.flush();
            pw.close();
            cl.close();
            
            cl=new Socket(host,pto);
            ObjectOutputStream oos=new ObjectOutputStream(cl.getOutputStream());
            System.out.println("Enviando la informacion:"+tema+" "+descripcion);
            oos.writeUTF(tema);
            oos.flush();
            oos.writeUTF(descripcion);
            oos.flush();
            
            oos.close();
            cl.close();

        } catch (IOException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public void publicar(String tema, String nombre, String publicacion, boolean tipo){
        try {
            Statement s = conexion.createStatement();
            java.util.Date fecha = new java.util.Date();
            java.sql.Date f = new java.sql.Date(fecha.getTime());
            s.executeQuery("call publicar('"+tema+"','"+nombre+"','"+publicacion+"',"+tipo+",'"+f+"');");
            s.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
