package it.collegio.views;

import it.collegio.controllers.PasswordResetController;
import it.collegio.utilities.utility;
import java.awt.Color;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

public class PassWordView extends javax.swing.JFrame {

    private final PasswordResetController controller = new PasswordResetController();

    public PassWordView() {
        initComponents();
        sqTextField.setEditable(false);
    }
    
    @SuppressWarnings("unchecked")                        
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        titleLabel1 = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        sqLabel = new javax.swing.JLabel();
        answerLabel = new javax.swing.JLabel();
        npwLabel = new javax.swing.JLabel();
        emailTextField = new javax.swing.JTextField();
        sqTextField = new javax.swing.JTextField();
        answerTextField = new javax.swing.JTextField();
        npwTextField = new javax.swing.JTextField();
        confirmButton = new javax.swing.JButton();
        b2lButton = new javax.swing.JButton();
        searchButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();
        bgLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titleLabel1.setFont(new java.awt.Font("Lucida Grande", 3, 14)); // NOI18N
        titleLabel1.setText("      Reset password");

        emailLabel.setFont(new java.awt.Font("Lucida Grande", 3, 14)); // NOI18N
        emailLabel.setText("Email");

        sqLabel.setFont(new java.awt.Font("Lucida Grande", 3, 14)); // NOI18N
        sqLabel.setText("Security Question");

        answerLabel.setFont(new java.awt.Font("Lucida Grande", 3, 14)); // NOI18N
        answerLabel.setText("Answer");

        npwLabel.setFont(new java.awt.Font("Lucida Grande", 3, 14)); // NOI18N
        npwLabel.setText("New Password");

        emailTextField.setFont(new java.awt.Font("Lucida Grande", 3, 14)); // NOI18N
        emailTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                emailTextFieldKeyReleased(evt);
            }
        });

        sqTextField.setFont(new java.awt.Font("Lucida Grande", 3, 14)); // NOI18N
        sqTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sqTextFieldActionPerformed(evt);
            }
        });

        answerTextField.setFont(new java.awt.Font("Lucida Grande", 3, 14)); // NOI18N
        answerTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                answerTextFieldKeyPressed(evt);
            }
        });

        npwTextField.setFont(new java.awt.Font("Lucida Grande", 3, 14)); // NOI18N
        npwTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                npwTextFieldKeyPressed(evt);
            }
        });

        confirmButton.setBackground(new java.awt.Color(0, 204, 153));
        confirmButton.setFont(new java.awt.Font("Lucida Grande", 3, 14)); // NOI18N
        confirmButton.setForeground(new java.awt.Color(153, 0, 0));
        confirmButton.setText("Confirm");
        confirmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmButtonActionPerformed(evt);
            }
        });

        b2lButton.setBackground(new java.awt.Color(0, 204, 153));
        b2lButton.setFont(new java.awt.Font("Lucida Grande", 3, 14)); // NOI18N
        b2lButton.setForeground(new java.awt.Color(153, 0, 0));
        b2lButton.setText("Back To Login");
        b2lButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b2lButtonActionPerformed(evt);
            }
        });

        searchButton.setBackground(new java.awt.Color(0, 204, 153));
        searchButton.setFont(new java.awt.Font("Lucida Grande", 3, 14)); // NOI18N
        searchButton.setForeground(new java.awt.Color(153, 0, 0));
        searchButton.setText("Search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(55, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(titleLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(217, 217, 217))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(confirmButton, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(b2lButton, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(sqLabel)
                                    .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(answerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(npwLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(88, 88, 88)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(npwTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(emailTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(sqTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(answerTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(sqLabel))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sqTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(answerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(answerTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(npwLabel)
                    .addComponent(npwTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirmButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b2lButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(66, 66, 66))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 250, 670, 410));

        closeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/close.png"))); // NOI18N
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });
        getContentPane().add(closeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1640, 0, -1, -1));

        bgLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/home.jpg"))); // NOI18N
        getContentPane().add(bgLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 1630, 1060));

        pack();
    }// </editor-fold>                        

    private void sqTextFieldActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
        
        int yes=JOptionPane.showConfirmDialog(this, "Confirm ?","Exit",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if(yes==JOptionPane.YES_OPTION){
            System.exit(0); 
        }
    }                                           

    private void b2lButtonActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
        new LoginView().setVisible(true); 
    }                                         

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String email = emailTextField.getText();
        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Inserisci una email");
            emailTextField.requestFocus();
            return;
        }
        String domanda = controller.getDomandaSicurezza(email);
        if (domanda == null) {
            JOptionPane.showMessageDialog(this, "Email non registrata");
            emailTextField.setText("");
            sqTextField.setText("");
            emailTextField.requestFocus();
            return;
        }
        sqTextField.setText(domanda);
    }

    private void confirmButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (sqTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Inserisci la email e clicca Search prima");
            emailTextField.requestFocus();
            return;
        }
        if (answerTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Inserisci la risposta");
            answerTextField.requestFocus();
            return;
        }
        if (npwTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Inserisci la nuova password");
            npwTextField.requestFocus();
            return;
        }

        boolean ok = controller.resetPassword(
                emailTextField.getText(),
                answerTextField.getText(),
                npwTextField.getText());

        if (ok) {
            JOptionPane.showMessageDialog(this, "Password aggiornata con successo!");
            new LoginView().setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Risposta errata o errore nel reset",
                    "Reset fallito", JOptionPane.WARNING_MESSAGE);
            answerTextField.setText("");
            answerTextField.requestFocus();
        }
    }

    private void answerTextFieldKeyPressed(java.awt.event.KeyEvent evt) {                                           
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER) {
            npwTextField.requestFocus();// TODO add your handling code here:
        }
    }                                          

    private void npwTextFieldKeyPressed(java.awt.event.KeyEvent evt) {                                        
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            if(sqTextField.getText().equals("")){
                JOptionPane.showMessageDialog(this,"Enter Email and Search it");   
                emailTextField.requestFocus();
            }
            else if(sqTextField.getText().equals("")){
                JOptionPane.showMessageDialog(this,"Enter vaild Answer");
                answerTextField.requestFocus();
            }
            else if(npwTextField.getText().equals("")){
                JOptionPane.showMessageDialog(this,"set Password Field Not Empity");
                npwTextField.requestFocus();
            }
           
        }  
        
    }                                       

    private void emailTextFieldKeyReleased(java.awt.event.KeyEvent evt) {
        emailTextField.setText(emailTextField.getText().toLowerCase());

        String email = emailTextField.getText();
        if (email.isEmpty()) {
            emailLabel.setText("Email");
            emailLabel.setForeground(Color.BLACK);
        } else if (utility.isValidEmail(email)) {
            emailLabel.setText("Email valida ✅");
            emailLabel.setForeground(Color.GREEN);
        } else {
            emailLabel.setText("Email non valida ❌");
            emailLabel.setForeground(Color.RED);
        }
    }
   
    public static void main(String args[]) {
       
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PassWordView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PassWordView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PassWordView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PassWordView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
       

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PassWordView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JLabel answerLabel;
    private javax.swing.JTextField answerTextField;
    private javax.swing.JButton b2lButton;
    private javax.swing.JLabel bgLabel;
    private javax.swing.JButton closeButton;
    private javax.swing.JButton confirmButton;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel npwLabel;
    private javax.swing.JTextField npwTextField;
    private javax.swing.JButton searchButton;
    private javax.swing.JLabel sqLabel;
    private javax.swing.JTextField sqTextField;
    private javax.swing.JLabel titleLabel1;
    // End of variables declaration   
}
