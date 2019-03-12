
package Interfaz;

import BDD.objectQuery.publicacion;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;


public class ShowTopics extends javax.swing.JFrame {
    String username;
    DefaultTreeModel modelo;
    DefaultTreeCellRenderer render;
    Vector<publicacion> publicaciones;
    public ShowTopics(String user) {
        initComponents();
        this.username=user;
        DefaultMutableTreeNode raiz=new DefaultMutableTreeNode("Temas de interés");
        modelo = new DefaultTreeModel(raiz);
        indice.setModel(modelo);
        render= (DefaultTreeCellRenderer)indice.getCellRenderer();
        render.setLeafIcon(new ImageIcon("C:\\Users\\Principal\\Documents\\GitHub\\ExamenRedes\\Examen\\iconos\\file.png"));
    }

    public void setPublicaciones(Vector<publicacion> p){
        publicaciones=p;
        int ind=0;
        for(publicacion s:publicaciones){
            modelo.insertNodeInto(new DefaultMutableTreeNode(s.getTemaPublicacion()),(DefaultMutableTreeNode)modelo.getRoot(), ind++);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        indice = new javax.swing.JTree();
        openTopic = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(indice);

        openTopic.setText("Abrir");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(162, 162, 162)
                        .addComponent(openTopic)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(openTopic)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

   
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ShowTopics(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTree indice;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton openTopic;
    // End of variables declaration//GEN-END:variables
}
