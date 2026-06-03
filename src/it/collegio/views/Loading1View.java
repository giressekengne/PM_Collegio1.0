package it.collegio.views;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Loading1View extends javax.swing.JFrame{
    
    public Loading1View() {
        initComponents();
        Thread cb = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    new LoadingView().setVisible(true);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Loading1View.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        cb.start();
    }

   
    @SuppressWarnings("unchecked")                         
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/home.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 1640, 1050));

        pack();
    }                       

    public static void main(String args[]) {
              
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Loading1View().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JLabel jLabel1;
                      
}
