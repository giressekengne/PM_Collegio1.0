
package it.collegio.views;

import it.collegio.controllers.RegistrationController;
import it.collegio.dto.LoginResponse;
import it.collegio.utilities.utility;
import java.awt.Color;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class RegistrationView extends javax.swing.JFrame {

    int flag = 0;
    private final RegistrationController controller = new RegistrationController();

     public RegistrationView() {
        initComponents();
    }
     
    
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        adressLabel = new javax.swing.JLabel();
        phoneLabel = new javax.swing.JLabel();
        questionLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        responseLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        surnameLabel = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        genderLabel = new javax.swing.JLabel();
        registrationButton = new javax.swing.JButton();
        back2loginButton = new javax.swing.JButton();
        questionComboBox = new javax.swing.JComboBox<>();
        genderComboBox = new javax.swing.JComboBox<>();
        adressComboBox = new javax.swing.JComboBox<>();
        nameTextField = new javax.swing.JTextField();
        surnameTextField = new javax.swing.JTextField();
        emailTextField = new javax.swing.JTextField();
        phoneTextField = new javax.swing.JTextField();
        responseTextField = new javax.swing.JTextField();
        pwVisibilitaLabel = new javax.swing.JLabel();
        pwPasswordField = new javax.swing.JPasswordField();
        closeButton = new javax.swing.JButton();
        bgLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1675, 765));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titleLabel.setFont(new java.awt.Font("Lucida Grande", 3, 16)); // NOI18N
        titleLabel.setText("Registrate Now");

        adressLabel.setFont(new java.awt.Font("Lucida Grande", 3, 14)); // NOI18N
        adressLabel.setText("Adress");

        phoneLabel.setFont(new java.awt.Font("Lucida Grande", 3, 14)); // NOI18N
        phoneLabel.setText("Phone");

        questionLabel.setFont(new java.awt.Font("Lucida Grande", 3, 14)); // NOI18N
        questionLabel.setText("Question");

        nameLabel.setFont(new java.awt.Font("Lucida Grande", 3, 14)); // NOI18N
        nameLabel.setText("Name");

        responseLabel.setFont(new java.awt.Font("Lucida Grande", 3, 14)); // NOI18N
        responseLabel.setText("Response");

        passwordLabel.setFont(new java.awt.Font("Lucida Grande", 3, 14)); // NOI18N
        passwordLabel.setText("Password");

        surnameLabel.setFont(new java.awt.Font("Lucida Grande", 3, 14)); // NOI18N
        surnameLabel.setText("Surname");

        emailLabel.setFont(new java.awt.Font("Lucida Grande", 3, 14)); // NOI18N
        emailLabel.setText("Email");

        genderLabel.setFont(new java.awt.Font("Lucida Grande", 3, 14)); // NOI18N
        genderLabel.setText("Gender");

        registrationButton.setBackground(new java.awt.Color(0, 204, 153));
        registrationButton.setFont(new java.awt.Font("Lucida Grande", 3, 14)); // NOI18N
        registrationButton.setForeground(new java.awt.Color(153, 0, 0));
        registrationButton.setText("Registrate Now");
        registrationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrationButtonActionPerformed(evt);
            }
        });

        back2loginButton.setBackground(new java.awt.Color(0, 204, 153));
        back2loginButton.setFont(new java.awt.Font("Lucida Grande", 3, 14)); // NOI18N
        back2loginButton.setForeground(new java.awt.Color(153, 0, 0));
        back2loginButton.setText("Back To Login");
        back2loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                back2loginButtonActionPerformed(evt);
            }
        });

        questionComboBox.setFont(new java.awt.Font("Lucida Grande", 3, 14)); // NOI18N
        questionComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "giocatore preferito?", "disciplina preferita?", "cantante preferito?", "libro preferita?", "programma televisivo preferito?", " " }));
        questionComboBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                questionComboBoxKeyPressed(evt);
            }
        });

        genderComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ND", "Maschio", "Femmina", " " }));
        genderComboBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                genderComboBoxKeyPressed(evt);
            }
        });

        adressComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "GOLGI1", "GOLGI2", "CARDANO", "VOLTA", "GHISLIERI", "MAINO", "CAMPUS", "BORROMEO", "CAIROLI", "SPALLA", "DON BOSCO", "FRACCARO", "SAN SIRO", "ROGEREDO", "MALPENXA", "COLOSSEO", "TERMINE", "GASTALDI", "MESTRE", "EI (DE)", "EI (FR)", "EI (BE)", "EI (ES)", "EI (PRT)", "EI (GB)", "EE (USA)", "EE (CAN)", "EE (MEX)", "EE (BR)", "EE (ARG)", "EE (CN)", "EE (JPN)", "EE (KOR)", "EE (IND)", "EE (MA)", "EE (CMR)", "EE (SEN)", "EE (KEN)", "EE (ZA)" }));
        adressComboBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                adressComboBoxKeyPressed(evt);
            }
        });

        nameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameTextFieldActionPerformed(evt);
            }
        });
        nameTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nameTextFieldKeyPressed(evt);
            }
        });

        surnameTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                surnameTextFieldKeyPressed(evt);
            }
        });

        emailTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailTextFieldActionPerformed(evt);
            }
        });
        emailTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                emailTextFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                emailTextFieldKeyReleased(evt);
            }
        });

        phoneTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                phoneTextFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                phoneTextFieldKeyReleased(evt);
            }
        });

        responseTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                responseTextFieldKeyPressed(evt);
            }
        });

        pwVisibilitaLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/h.png"))); // NOI18N
        pwVisibilitaLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pwVisibilitaLabelMouseClicked(evt);
            }
        });

        pwPasswordField.setFont(new java.awt.Font("Lucida Grande", 3, 14)); // NOI18N
        pwPasswordField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pwPasswordFieldKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(177, 177, 177)
                        .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(surnameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(surnameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(phoneLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(phoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(questionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(questionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(registrationButton, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(responseLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(54, 54, 54)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(responseTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(back2loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(adressLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(54, 54, 54)
                                .addComponent(adressComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(genderLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(passwordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(54, 54, 54)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(pwPasswordField)
                                    .addComponent(genderComboBox, 0, 195, Short.MAX_VALUE))))
                        .addGap(28, 28, 28)
                        .addComponent(pwVisibilitaLabel)))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(surnameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(surnameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(genderLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(genderComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(passwordLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pwVisibilitaLabel)
                    .addComponent(pwPasswordField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(adressLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(adressComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(phoneLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(phoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(questionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(questionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(responseLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(responseTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(registrationButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(back2loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 150, 570, 640));

        closeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/close.png"))); // NOI18N
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });
        getContentPane().add(closeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1630, 0, -1, -1));

        bgLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/home.jpg"))); // NOI18N
        getContentPane().add(bgLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 1630, 1010));

        pack();
    }// </editor-fold>                        

    
    
    private void nameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
    }                                             

    private void emailTextFieldActionPerformed(java.awt.event.ActionEvent evt) {                                               
        // TODO add your handling code here:
    }                                              

    
    
    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {                                            
        
        // TODO add your handling code here:
        int yes=JOptionPane.showConfirmDialog(this, "Confirm ?","Exit",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if(yes==JOptionPane.YES_OPTION){
            System.exit(0); 
        }
    }                                           

    
    // Code behind il bottone Registra
    private void registrationButtonActionPerformed(java.awt.event.ActionEvent evt) {

        if (nameTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nome obbligatorio");
            nameTextField.requestFocus();
            return;
        }
        if (surnameTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Cognome obbligatorio");
            surnameTextField.requestFocus();
            return;
        }
        if (emailTextField.getText().isEmpty() || !utility.isValidEmail(emailTextField.getText())) {
            JOptionPane.showMessageDialog(this, "Email non valida");
            emailTextField.requestFocus();
            return;
        }
        String pw = new String(pwPasswordField.getPassword());
        if (pw.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Password obbligatoria");
            pwPasswordField.requestFocus();
            return;
        }
        if (responseTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Risposta di sicurezza obbligatoria");
            responseTextField.requestFocus();
            return;
        }

        LoginResponse response = controller.registra(
                nameTextField.getText(),
                surnameTextField.getText(),
                emailTextField.getText(),
                pw,
                phoneTextField.getText(),
                (String) adressComboBox.getSelectedItem(),
                (String) questionComboBox.getSelectedItem(),
                responseTextField.getText(),
                (String) genderComboBox.getSelectedItem());

        if (response.isSuccessful()) {
            JOptionPane.showMessageDialog(this,
                    "Registrazione completata!\nIl tuo account e in attesa di approvazione.");
            new LoginView().setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, response.getErrorMessage(),
                    "Errore registrazione", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void back2loginButtonActionPerformed(java.awt.event.ActionEvent evt) {
        new LoginView().setVisible(true);
        this.dispose();
    }

    private void pwVisibilitaLabelMouseClicked(java.awt.event.MouseEvent evt) {                                               
        // TODO add your handling code here:
        if(flag==0){
            pwVisibilitaLabel.setIcon(new ImageIcon("/Users/gigatore/NetBeansProjects/PM_Collegio/src/image/s.png"));
   
            flag=1;
            pwPasswordField.setEchoChar((char)0);
        }
        else
        {
            pwVisibilitaLabel.setIcon(new ImageIcon("/Users/gigatore/NetBeansProjects/PM_Collegio/src/image/h.png"));
            flag=0;
            pwPasswordField.setEchoChar('*');
        
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
    
    private void responseTextFieldKeyPressed(java.awt.event.KeyEvent evt) {                                             
        // TODO add your handling code here:
                         
    }                                            

    private void nameTextFieldKeyPressed(java.awt.event.KeyEvent evt) {                                         
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
        surnameTextField.requestFocus();
        }
    }                                        

    private void surnameTextFieldKeyPressed(java.awt.event.KeyEvent evt) {                                            
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
        emailTextField.requestFocus();
        }
    }                                           

    private void emailTextFieldKeyPressed(java.awt.event.KeyEvent evt) {                                          
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
        genderComboBox.requestFocus();
        }
    }                                         

    private void genderComboBoxKeyPressed(java.awt.event.KeyEvent evt) {                                          
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
        pwPasswordField.requestFocus();
        }
    }                                         

    private void pwPasswordFieldKeyPressed(java.awt.event.KeyEvent evt) {                                           
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
        adressComboBox.requestFocus();
        }
    }                                          

    private void adressComboBoxKeyPressed(java.awt.event.KeyEvent evt) {                                          
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
        phoneTextField.requestFocus();
        }
    }                                         

    private void phoneTextFieldKeyPressed(java.awt.event.KeyEvent evt) {                                          
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
        questionComboBox.requestFocus();
        }
    }                                         

    private void questionComboBoxKeyPressed(java.awt.event.KeyEvent evt) {                                            
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
        responseTextField.requestFocus();
        }
    }                                           

    private void phoneTextFieldKeyReleased(java.awt.event.KeyEvent evt) {
        String mobile = phoneTextField.getText();
        if (mobile.isEmpty()) {
            phoneLabel.setText("Phone");
            phoneLabel.setForeground(Color.BLACK);
        } else if (utility.validaNumeroTelefono(mobile)) {
            phoneLabel.setText("Mobile valido ✅");
            phoneLabel.setForeground(Color.GREEN);
        } else {
            phoneLabel.setText("Mobile non valido ❌");
            phoneLabel.setForeground(Color.RED);
        }
    }                                          

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RegistrationView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistrationView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistrationView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistrationView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistrationView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JComboBox<String> adressComboBox;
    private javax.swing.JLabel adressLabel;
    private javax.swing.JButton back2loginButton;
    private javax.swing.JLabel bgLabel;
    private javax.swing.JButton closeButton;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JComboBox<String> genderComboBox;
    private javax.swing.JLabel genderLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JLabel phoneLabel;
    private javax.swing.JTextField phoneTextField;
    private javax.swing.JPasswordField pwPasswordField;
    private javax.swing.JLabel pwVisibilitaLabel;
    private javax.swing.JComboBox<String> questionComboBox;
    private javax.swing.JLabel questionLabel;
    private javax.swing.JButton registrationButton;
    private javax.swing.JLabel responseLabel;
    private javax.swing.JTextField responseTextField;
    private javax.swing.JLabel surnameLabel;
    private javax.swing.JTextField surnameTextField;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration  
}
