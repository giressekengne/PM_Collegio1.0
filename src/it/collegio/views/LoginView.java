package it.collegio.views;

import com.mysql.cj.xdevapi.Statement;
import it.collegio.utilities.utility;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import it.collegio.controllers.LoginController;
import it.collegio.dto.LoginResponse;
import it.collegio.models.User;

public class LoginView extends javax.swing.JFrame{

    private LoginController controller = new LoginController();
    private int flag = 0;

     public LoginView() {
        initComponents();
    }
     
      @SuppressWarnings("unchecked")                         
    private void initComponents() {

        closeButton = new javax.swing.JButton();
        formLoginPanel = new javax.swing.JPanel();
        formNameLabel = new javax.swing.JLabel();
        userIdLabel = new javax.swing.JLabel();
        PwLabel = new javax.swing.JLabel();
        emailTextField = new javax.swing.JTextField();
        PwPasswordField = new javax.swing.JPasswordField();
        loginButton = new javax.swing.JButton();
        registrationButton = new javax.swing.JButton();
        forgotPwButton = new javax.swing.JButton();
        visibilitaPwLabel = new javax.swing.JLabel();
        sfondoFotoLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        closeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/close.png"))); // NOI18N
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });
        getContentPane().add(closeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1640, 0, 40, 42));

        formNameLabel.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        formNameLabel.setText("Sign In");

        userIdLabel.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        userIdLabel.setText("Email");

        PwLabel.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        PwLabel.setText("Password");

        emailTextField.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        emailTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                emailTextFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                emailTextFieldKeyReleased(evt);
            }
        });

        PwPasswordField.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        PwPasswordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PwPasswordFieldActionPerformed(evt);
            }
        });
        PwPasswordField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PwPasswordFieldKeyPressed(evt);
            }
        });

        loginButton.setBackground(new java.awt.Color(0, 204, 153));
        loginButton.setFont(new java.awt.Font("Lucida Grande", 3, 14)); // NOI18N
        loginButton.setForeground(new java.awt.Color(153, 0, 0));
        loginButton.setText("Login");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        registrationButton.setBackground(new java.awt.Color(0, 204, 153));
        registrationButton.setFont(new java.awt.Font("Lucida Grande", 3, 14)); // NOI18N
        registrationButton.setForeground(new java.awt.Color(153, 0, 0));
        registrationButton.setText("Signup");
        registrationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrationButtonActionPerformed(evt);
            }
        });

        forgotPwButton.setBackground(new java.awt.Color(0, 204, 153));
        forgotPwButton.setFont(new java.awt.Font("Lucida Grande", 3, 14)); // NOI18N
        forgotPwButton.setForeground(new java.awt.Color(153, 0, 0));
        forgotPwButton.setText("Forgot Password");
        forgotPwButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                forgotPwButtonActionPerformed(evt);
            }
        });

        visibilitaPwLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/h.png"))); // NOI18N
        visibilitaPwLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                visibilitaPwLabelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout formLoginPanelLayout = new javax.swing.GroupLayout(formLoginPanel);
        formLoginPanel.setLayout(formLoginPanelLayout);
        formLoginPanelLayout.setHorizontalGroup(
            formLoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formLoginPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(formLoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(formLoginPanelLayout.createSequentialGroup()
                        .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(registrationButton, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                        .addComponent(forgotPwButton))
                    .addGroup(formLoginPanelLayout.createSequentialGroup()
                        .addGroup(formLoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(userIdLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(PwLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
                        .addGap(55, 55, 55)
                        .addGroup(formLoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(formNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(formLoginPanelLayout.createSequentialGroup()
                                .addGroup(formLoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(emailTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                                    .addComponent(PwPasswordField))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(visibilitaPwLabel)))))
                .addGap(42, 42, 42))
        );
        formLoginPanelLayout.setVerticalGroup(
            formLoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formLoginPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(formNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addGroup(formLoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(formLoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(visibilitaPwLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(formLoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(PwLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(PwPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addGroup(formLoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(registrationButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(forgotPwButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        getContentPane().add(formLoginPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 310, 510, -1));

        sfondoFotoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/home.jpg"))); // NOI18N
        getContentPane().add(sfondoFotoLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 1620, 1050));

        pack();
    }
    
    private void PwPasswordFieldActionPerformed(java.awt.event.ActionEvent evt) {                                                
        // TODO add your handling code here:
    }                                               

    //bottone login
    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {

        String email = emailTextField.getText();
        String password = new String(PwPasswordField.getPassword());

        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Inserisci email e password");
            return;
        }

        LoginResponse response = controller.login(email, password);

        if (response.isSuccessful()) {
            new HomeView().setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, response.getErrorMessage(),
                    "Accesso negato", JOptionPane.WARNING_MESSAGE);
            emailTextField.requestFocus();
        }
    }

    
    /// Action del bottone rosso exit
    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
        int yes=JOptionPane.showConfirmDialog(this, "Confirm ?","Exit",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if(yes==JOptionPane.YES_OPTION){
            System.exit(0);
        }       
    }                                           

    //click per visibilita o meno della pw
    private void visibilitaPwLabelMouseClicked(java.awt.event.MouseEvent evt) {
        if (flag == 0) {
            visibilitaPwLabel.setIcon(new ImageIcon(getClass().getResource("/image/s.png")));
            flag = 1;
            PwPasswordField.setEchoChar((char) 0);
        } else {
            visibilitaPwLabel.setIcon(new ImageIcon(getClass().getResource("/image/h.png")));
            flag = 0;
            PwPasswordField.setEchoChar('*');
        }
    }
// bottone signup
    private void registrationButtonActionPerformed(java.awt.event.ActionEvent evt) {
        new RegistrationView().setVisible(true);
        this.dispose();
    }

    private void forgotPwButtonActionPerformed(java.awt.event.ActionEvent evt) {
        new PassWordView().setVisible(true);
        this.dispose();
    }

    private void emailTextFieldKeyPressed(java.awt.event.KeyEvent evt) {                                          
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
        PwPasswordField.requestFocus();
        }
    }                                         

    private void PwPasswordFieldKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            loginButton.doClick();
        }
    }

    private void emailTextFieldKeyReleased(java.awt.event.KeyEvent evt) {                                           
        // TODO add your handling code here:
        emailTextField.setText(emailTextField.getText().toLowerCase()); 

        String email = emailTextField.getText();
        if (utility.isValidEmail(email)) {
            userIdLabel.setText("Email valida ✅");
            userIdLabel.setForeground(Color.GREEN);
        } else {
            userIdLabel.setText("Email non valida ❌");
            userIdLabel.setForeground(Color.RED);
        }
        if(emailTextField.getText().equals("")){
            userIdLabel.setText("");
        }
            
    }                                          

    
    
   
    public static void main(String args[]) {
      
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JLabel PwLabel;
    private javax.swing.JPasswordField PwPasswordField;
    private javax.swing.JButton closeButton;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JButton forgotPwButton;
    private javax.swing.JPanel formLoginPanel;
    private javax.swing.JLabel formNameLabel;
    private javax.swing.JButton loginButton;
    private javax.swing.JButton registrationButton;
    private javax.swing.JLabel sfondoFotoLabel;
    private javax.swing.JLabel userIdLabel;
    private javax.swing.JLabel visibilitaPwLabel;
    // End of variables declaration  
}
