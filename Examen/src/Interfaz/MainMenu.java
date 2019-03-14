
package Interfaz;

import BDD.objectQuery;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;

public class MainMenu extends javax.swing.JFrame {
    String username;
    DefaultTreeModel modelo;
    DefaultTreeCellRenderer render;
    
    public MainMenu(String user) {
        initComponents();
        this.username=user;
        DefaultMutableTreeNode raiz=new DefaultMutableTreeNode("Temas de interés");
        raiz.add(new DefaultMutableTreeNode("Electronica"));
        raiz.add(new DefaultMutableTreeNode("Redes"));
        modelo = new DefaultTreeModel(raiz);
        indice.setModel(modelo);
        render= (DefaultTreeCellRenderer)indice.getCellRenderer();
        render.setLeafIcon(new ImageIcon("C:\\Users\\Principal\\Documents\\GitHub\\ExamenRedes\\Examen\\iconos\\file.png"));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        createPost = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        indice = new javax.swing.JTree();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        textPost = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        createPost.setText("Crear Publicacion");
        createPost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createPostActionPerformed(evt);
            }
        });

        jButton2.setText("Buscar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(indice);

        jLabel1.setText("Temas");

        textPost.setColumns(20);
        textPost.setRows(5);
        jScrollPane2.setViewportView(textPost);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(424, 424, 424)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 255, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(124, 124, 124))
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(createPost)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(createPost)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Search s=new Search(username);
        s.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void createPostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createPostActionPerformed
        try {
            String texto=textPost.getText();
            DefaultMutableTreeNode node = (DefaultMutableTreeNode)indice.getLastSelectedPathComponent();
            if (node == null) return;
            Object nodeInfo = node.getUserObject();
            String tema=(String)nodeInfo;
            Socket cl=new Socket("127.0.0.1",1234);
            PrintWriter pw=new PrintWriter(new OutputStreamWriter(cl.getOutputStream()));
            String eco="Publicar";
            pw.println(eco);
            pw.flush();
            pw.close();
            cl.close();
            
            cl=new Socket("127.0.0.1",1234);
            pw=new PrintWriter(new OutputStreamWriter(cl.getOutputStream()));
            pw.println(tema);
            pw.flush();
            pw.println(texto);
            pw.flush();
            pw.println(username);
            pw.flush();
            pw.close();
            cl.close();
            
            cl=new Socket("127.0.0.1",1234);
            BufferedReader br=new BufferedReader(new InputStreamReader(cl.getInputStream()));
            String msj=br.readLine();
            System.out.println(""+msj);
            br.close();
            cl.close();
            return;
        } catch (IOException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_createPostActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenu(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton createPost;
    private javax.swing.JTree indice;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea textPost;
    // End of variables declaration//GEN-END:variables
}
