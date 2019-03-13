
package Interfaz;

import BDD.Conexion;
import BDD.objectQuery;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Servidor {
    public static int pto=1234;
    public static String host="127.0.0.1";
    public static String pathServidor="C:\\Users\\Principal\\Documents\\GitHub\\ExamenRedes\\";
    
    public static void main(String[] args) {
        try {
            ServerSocket s=new ServerSocket(pto);
            s.setReuseAddress(true);
            while(true){
                System.out.println("Esperando al cliente...");
                Socket cl=s.accept();
                String msj;
                System.out.println("Cliente conectado desde"+cl.getInetAddress()+" : "+ cl.getPort());
                PrintWriter pw=new PrintWriter(new OutputStreamWriter(cl.getOutputStream()));
                BufferedReader br=new BufferedReader(new InputStreamReader(cl.getInputStream()));
                msj=br.readLine();
                switch(msj){
                    case "Crear Tema": crearTema(s); break;
                    case "Buscar por tema": searchByTopic(s); break;
                    case "Buscar por fecha": searchByDate(s); break;
                    case "Buscar por tema y fecha": search(s); break;
                }
               
                br.close();
                cl.close();
            }   
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void searchByTopic(ServerSocket s){
                String tema="";
                 try {   
                    Socket cl=s.accept();
                    System.out.println("Recibiendo... ");
                    BufferedReader br=new BufferedReader(new InputStreamReader(cl.getInputStream()));
                    tema=br.readLine();
                    br.close();
                    
                    Conexion conexion=new Conexion();
                    objectQuery oq=new objectQuery();
                    System.out.println("Buscando por tema:");
                    oq=conexion.buscarPublicaciones(tema,null);
                    for(objectQuery.publicacion p: oq.getPublicaciones()){
                        System.out.println(""+p.toString());
                    }
                    cl.close();
                    
                    cl=s.accept();
                    ObjectOutputStream oos=new ObjectOutputStream(cl.getOutputStream());
                    oos.writeObject(oq);
                    oos.flush();
                    oos.close();
                    cl.close();
                    
                } catch (IOException ex) {
                    Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                } 
    }
    
    private static void searchByDate(ServerSocket s){
                String fecha="";
                 try {   
                    Socket cl=s.accept();
                    System.out.println("Recibiendo... ");
                    BufferedReader br=new BufferedReader(new InputStreamReader(cl.getInputStream()));
                    fecha=br.readLine();
                    br.close();
                    
                    Conexion conexion=new Conexion();
                    objectQuery oq=new objectQuery();
                    System.out.println("Buscando por fecha:");
                    oq=conexion.buscarPublicaciones(null,fecha);
                    for(objectQuery.publicacion p: oq.getPublicaciones()){
                        System.out.println(""+p.toString());
                    }
                    
                    cl=s.accept();
                    ObjectOutputStream oos=new ObjectOutputStream(cl.getOutputStream());
                    oos.writeObject(oq);
                    oos.flush();
                    oos.close();
                    cl.close();
                    
                } catch (IOException ex) {
                    Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                } 
    }
    private static void search(ServerSocket s){
                String tema="",fecha="";
                 try {   
                    Socket cl=s.accept();
                    System.out.println("Recibiendo... ");
                   BufferedReader br=new BufferedReader(new InputStreamReader(cl.getInputStream()));
                    tema=br.readLine();
                    fecha=br.readLine();
                    br.close();
                    
                    Conexion conexion=new Conexion();
                    objectQuery oq=new objectQuery();
                    System.out.println("Buscando por tema y fecha:");
                    oq=conexion.buscarPublicaciones(tema,fecha);
                    for(objectQuery.publicacion p: oq.getPublicaciones()){
                        System.out.println(""+p.toString());
                    }
                    
                    cl=s.accept();
                    ObjectOutputStream oos=new ObjectOutputStream(cl.getOutputStream());
                    oos.writeObject(oq);
                    oos.flush();
                    oos.close();
                    cl.close();
                    
                } catch (IOException ex) {
                    Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                } 
    }

    private static void crearTema(ServerSocket s) {
       try {   
            Socket cl=s.accept();
            System.out.println("Recibiendo... ");
            ObjectInputStream ois=new ObjectInputStream(cl.getInputStream());
            String tema=ois.readUTF();
            String descrip=ois.readUTF();
            ois.close();
            cl.close();
            File f=new File(pathServidor+"Publicaciones\\"+tema+"\\");
            if(!f.exists()) f.mkdir();
            File f2=new File(pathServidor+"Publicaciones\\"+tema+"\\descripcion.txt");
            FileWriter fichero = null;
            PrintWriter pw = null;
            fichero = new FileWriter(pathServidor+"Publicaciones\\"+tema+"\\descripcion.txt",true);
            pw = new PrintWriter(fichero);
            pw.println(descrip);
            pw.flush();
            fichero.close();
            System.out.println("Tema creado!");
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
