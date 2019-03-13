
package Interfaz;

import BDD.Conexion;
import BDD.objectQuery;
import BDD.objectQuery.publicacion;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Search extends javax.swing.JFrame {
    String username;
    public Search(String u) {
        initComponents();
        username=u;
        topic.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
             topic.setText("");
            }
        });
        date.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                date.setText("");
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        topic = new javax.swing.JTextField();
        date = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        topic.setText("Por tema:");

        date.setText("Por fecha: Ej. 2015-02-19");
        date.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(148, 148, 148)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(date)
                            .addComponent(topic, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE))))
                .addContainerGap(111, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(topic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(61, 61, 61))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateActionPerformed

        
    }//GEN-LAST:event_dateActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            String tema=topic.getText();
            String fecha=date.getText();
            if((tema.equals("")||tema.equals("Por tema:"))&&(fecha.equals("")||fecha.equals("Por fecha: Ej. 19-02-2015"))){
                JOptionPane.showMessageDialog(null, "No se ha ingresado ningun criterio de busqueda", "Campo vacio",JOptionPane.WARNING_MESSAGE);
                return;
            }
            if(!(tema.equals("")||tema.equals("Por tema:"))&&!(fecha.equals("")||fecha.equals("Por fecha: Ej. 2015-02-19"))){
                System.out.println("Buscando por tema y fecha");
                Socket cl=new Socket("127.0.0.1",1234);
                PrintWriter pw=new PrintWriter(new OutputStreamWriter(cl.getOutputStream())); 
                String eco="Buscar por tema y fecha";
                pw.println(eco);
                pw.flush();
                pw.close();
                cl.close();

                cl=new Socket("127.0.0.1",1234);
                pw=new PrintWriter(new OutputStreamWriter(cl.getOutputStream())); 
                pw.println(tema);
                pw.flush();
                pw.println(fecha);
                pw.flush();
                pw.close();
                cl.close();
                
                cl=new Socket("127.0.0.1",1234);
                ObjectInputStream ois=new ObjectInputStream(cl.getInputStream());
                objectQuery oq=(objectQuery)ois.readObject();
                ois.close();
                cl.close();
                System.out.println("Se recibió la consulta!");
                return;
            }
            if(!(tema.equals("")||tema.equals("Por tema:"))){
                System.out.println("Buscando por tema");
                Socket cl=new Socket("127.0.0.1",1234);
                PrintWriter pw=new PrintWriter(new OutputStreamWriter(cl.getOutputStream())); 
                String eco="Buscar por tema";
                pw.println(eco);
                pw.flush();
                pw.close();
                cl.close();
                
                cl=new Socket("127.0.0.1",1234);
                pw=new PrintWriter(new OutputStreamWriter(cl.getOutputStream())); 
                pw.println(tema);
                pw.flush();
                pw.close();
                cl.close();
                
                cl=new Socket("127.0.0.1",1234);
                ObjectInputStream ois=new ObjectInputStream(cl.getInputStream());
                objectQuery oq=(objectQuery)ois.readObject();
                ois.close();
                
                cl.close();
                System.out.println("Se recibió la consulta!");
                return;
            }
            if(!(fecha.equals("")||fecha.equals("Por fecha: Ej. 19-02-2015"))){
                System.out.println("Buscando por fecha");
                Socket cl=new Socket("127.0.0.1",1234);
                PrintWriter pw=new PrintWriter(new OutputStreamWriter(cl.getOutputStream())); 
                String eco="Buscar por fecha";
                pw.println(eco);
                pw.flush();
                pw.close();
                cl.close();

                cl=new Socket("127.0.0.1",1234);
                pw=new PrintWriter(new OutputStreamWriter(cl.getOutputStream())); 
                pw.println(fecha);
                pw.flush();
                pw.close();
                cl.close();
                
                cl=new Socket("127.0.0.1",1234);
                ObjectInputStream ois=new ObjectInputStream(cl.getInputStream());
                objectQuery oq=(objectQuery)ois.readObject();
                ois.close();
                cl.close();
                System.out.println("Se recibió la consulta!");
                return;
            }
        } catch (IOException ex) {
            Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Search(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField date;
    private javax.swing.JButton jButton1;
    private javax.swing.JTextField topic;
    // End of variables declaration//GEN-END:variables
}
