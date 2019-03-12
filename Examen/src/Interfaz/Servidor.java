
package Interfaz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
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
                    case "Crear Tema": crearTema(s);
                }
               
                br.close();
                cl.close();
            }   
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
