package it.collegio.views;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.time.ZoneId;
import it.collegio.controllers.ReservationController;
import it.collegio.dto.ReservationDettaglio;
import it.collegio.utilities.SessionContext;
import it.collegio.utilities.utility;

public class ManageRiservationView extends javax.swing.JFrame{

    private javax.swing.JButton fattureButton;
    private final ReservationController controller;

    public ManageRiservationView() {
        this.controller = new ReservationController();
        initComponents();
        date();

        fattureButton = new javax.swing.JButton("Fatture");
        fattureButton.setBackground(new java.awt.Color(0, 204, 153));
        fattureButton.setFont(new java.awt.Font("Lucida Grande", java.awt.Font.BOLD, 14));
        fattureButton.setForeground(new java.awt.Color(153, 0, 0));
        fattureButton.addActionListener(e -> new GestioneFattureView().setVisible(true));
        getContentPane().add(fattureButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 502, 114, 39));
        getContentPane().setComponentZOrder(fattureButton, 0);

        wireActionListeners();
        loadTable();
        configureForRole();

        // Click su una riga della tabella -> ricerca dettaglio via Controller e popola il form
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = jTable1.getSelectedRow();
                if (row < 0) return;
                Object idCell = jTable1.getValueAt(row, 0);
                if (idCell == null) return;
                try {
                    int reservationId = utility.convInt(idCell.toString());
                    ReservationDettaglio d = controller.cercaDettaglio(reservationId);
                    if (d != null) popolaForm(d);
                } catch (Exception ex) {
                    // ID non interpretabile: ignora silenziosamente
                }
            }
        });
    }

    /** Popola tutti i campi del form a partire dal DTO ricevuto dal Controller. */
    private void popolaForm(ReservationDettaglio d) {
        idTextField.setText(utility.convAlfaP(d.getReservationId()));
        userTextField.setText(d.getUserNome());
        committenteTextField.setText(String.valueOf(d.getCommittenteId()));
        rnTextField.setText(utility.convAlfaR(d.getRoomId()));
        cidTextField.setText(d.getCheckIn());
        codTextField.setText(d.getCheckOut() != null ? d.getCheckOut() : "");
        ppdTextField.setText(d.getStato());
        noteTextField.setText(d.getNote() != null ? d.getNote() : "");
        nodTextField.setText(String.valueOf(d.getGiorni()));
        totalTextField.setText(String.valueOf(d.getTotale()));
    }

    private void wireActionListeners() {
        for (java.awt.event.ActionListener al : searchButton.getActionListeners()) {
            searchButton.removeActionListener(al);
        }
        for (java.awt.event.ActionListener al : updateButton.getActionListeners()) {
            updateButton.removeActionListener(al);
        }
        for (java.awt.event.ActionListener al : clearButton.getActionListeners()) {
            clearButton.removeActionListener(al);
        }

        searchButton.addActionListener(e -> cerca());
        updateButton.addActionListener(e -> conferma());
        clearButton.addActionListener(e -> cancel());
    }

    private void configureForRole() {
        if ("U".equalsIgnoreCase(SessionContext.roleType)) {
            updateButton.setVisible(false);
            cidTextField.setEditable(false);
            codTextField.setEditable(false);
            noteTextField.setEditable(false);
        }
    }

    private void loadTable() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        List<ReservationDettaglio> dettagli = controller.getDettagliPerRuolo();
        for (ReservationDettaglio d : dettagli) {
            model.addRow(new Object[]{
                utility.convAlfaP(d.getReservationId()),
                d.getUserNome(),
                d.getCommittenteId(),
                d.getCheckIn(),
                d.getCheckOut(),
                d.getStato(),
                utility.convAlfaR(d.getRoomId()),
                d.getNote(),
                d.getGiorni(),
                d.getTotale()
            });
        }
    }

    private void cerca() {
        String ids = idTextField.getText();
        if (ids.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Inserisci un ID prenotazione (es. P0001)");
            return;
        }
        int reservationId;
        try {
            reservationId = utility.convInt(ids);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Formato ID non valido. Usa P0xxx.");
            return;
        }

        ReservationDettaglio d = controller.cercaDettaglio(reservationId);
        if (d == null) {
            JOptionPane.showMessageDialog(this, "Prenotazione non trovata");
            idTextField.setText("");
            return;
        }

        popolaForm(d);
    }

    private void conferma() {
        if ("U".equalsIgnoreCase(SessionContext.roleType)) {
            return;
        }
        if (idTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Cercare prima una prenotazione per ID");
            return;
        }

        int reservationId;
        try {
            reservationId = utility.convInt(idTextField.getText());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Formato ID non valido");
            return;
        }

        boolean ok = controller.aggiornaConStorico(
                reservationId,
                cidTextField.getText(),
                codTextField.getText(),
                noteTextField.getText());

        if (ok) {
            JOptionPane.showMessageDialog(this, "Prenotazione aggiornata con successo");
            loadTable();
            cancel();
        } else {
            JOptionPane.showMessageDialog(this, "Errore aggiornamento prenotazione");
        }
    }
    
    
    
    public void date() {
        
        SimpleDateFormat  dat = new SimpleDateFormat("yyyy-MM-dd ");
        Date d = new Date();
        codTextField.setText(dat.format(d));       
        codTextField.setEditable(false);
    
    }
    
   
    
    public void cancel() {
    
        idTextField.setText("");
//        emailTextField.setText("");
//        nameTextField.setText("");
//        mobileTextField.setText("");
        cidTextField.setText("");
        rnTextField.setText("");
//        ppdTextField.setText("");
        totalTextField.setText("");
        nodTextField.setText("");
    }
    
   
    
    public void dataFields() {  
    
    }
    
    public void conferm() {
        
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        closeButton = new javax.swing.JButton();
        logoLabel = new javax.swing.JLabel();
        idLabel = new javax.swing.JLabel();
        idTextField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        userLabel = new javax.swing.JLabel();
        userTextField = new javax.swing.JTextField();
        rnLabel = new javax.swing.JLabel();
        rnTextField = new javax.swing.JTextField();
        cidLabel = new javax.swing.JLabel();
        cidTextField = new javax.swing.JTextField();
        committenteLabel = new javax.swing.JLabel();
        statusLabel = new javax.swing.JLabel();
        committenteTextField = new javax.swing.JTextField();
        ppdTextField = new javax.swing.JTextField();
        noteLabel = new javax.swing.JLabel();
        noteTextField = new javax.swing.JTextField();
        nodLabel = new javax.swing.JLabel();
        nodTextField = new javax.swing.JTextField();
        codLabel = new javax.swing.JLabel();
        codTextField = new javax.swing.JTextField();
        totalLabel = new javax.swing.JLabel();
        totalTextField = new javax.swing.JTextField();
        updateButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        bgLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        closeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/close.png"))); // NOI18N
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });
        getContentPane().add(closeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 0, -1, -1));

        logoLabel.setFont(new java.awt.Font("Lucida Grande", 3, 14)); // NOI18N
        logoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/manage.png"))); // NOI18N
        logoLabel.setText("Manage Riservation");
        getContentPane().add(logoLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 210, -1));

        idLabel.setFont(new java.awt.Font("Lucida Grande", 3, 14)); // NOI18N
        idLabel.setText("ID Allote");
        getContentPane().add(idLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(294, 66, 100, 24));

        idTextField.setFont(new java.awt.Font("Lucida Grande", 3, 14)); // NOI18N
        getContentPane().add(idTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(392, 62, 220, 34));

        searchButton.setBackground(new java.awt.Color(0, 204, 153));
        searchButton.setFont(new java.awt.Font("Lucida Grande", 3, 14)); // NOI18N
        searchButton.setForeground(new java.awt.Color(153, 0, 0));
        searchButton.setText("Search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });
        getContentPane().add(searchButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 62, 126, 34));

        userLabel.setFont(new java.awt.Font("Lucida Grande", 3, 14)); // NOI18N
        userLabel.setText("User");
        getContentPane().add(userLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 151, 26));

        userTextField.setFont(new java.awt.Font("Lucida Grande", 3, 14)); // NOI18N
        getContentPane().add(userTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 180, 34));

        rnLabel.setFont(new java.awt.Font("Lucida Grande", 3, 14)); // NOI18N
        rnLabel.setText("Room Number");
        getContentPane().add(rnLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, 151, 26));

        rnTextField.setFont(new java.awt.Font("Lucida Grande", 3, 14)); // NOI18N
        getContentPane().add(rnTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, 180, 36));

        cidLabel.setFont(new java.awt.Font("Lucida Grande", 3, 14)); // NOI18N
        cidLabel.setText("CheckIn Date");
        getContentPane().add(cidLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, 142, 26));

        cidTextField.setFont(new java.awt.Font("Lucida Grande", 3, 14)); // NOI18N
        getContentPane().add(cidTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 430, 170, 34));

        committenteLabel.setFont(new java.awt.Font("Lucida Grande", 3, 14)); // NOI18N
        committenteLabel.setText("Committente");
        getContentPane().add(committenteLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(428, 132, 184, 26));

        statusLabel.setFont(new java.awt.Font("Lucida Grande", 3, 14)); // NOI18N
        statusLabel.setText("Status");
        getContentPane().add(statusLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(776, 132, 172, 26));

        committenteTextField.setFont(new java.awt.Font("Lucida Grande", 3, 14)); // NOI18N
        getContentPane().add(committenteTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(428, 186, 184, 34));

        ppdTextField.setFont(new java.awt.Font("Lucida Grande", 3, 14)); // NOI18N
        ppdTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppdTextFieldActionPerformed(evt);
            }
        });
        getContentPane().add(ppdTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(776, 186, 172, 34));

        noteLabel.setFont(new java.awt.Font("Lucida Grande", 3, 14)); // NOI18N
        noteLabel.setText("Note");
        getContentPane().add(noteLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(428, 247, 184, 26));

        noteTextField.setFont(new java.awt.Font("Lucida Grande", 3, 14)); // NOI18N
        getContentPane().add(noteTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(428, 312, 184, 36));

        nodLabel.setFont(new java.awt.Font("Lucida Grande", 3, 14)); // NOI18N
        nodLabel.setText("Number Of Days");
        getContentPane().add(nodLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(776, 247, 172, 26));

        nodTextField.setFont(new java.awt.Font("Lucida Grande", 3, 14)); // NOI18N
        getContentPane().add(nodTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(776, 312, 172, 34));

        codLabel.setFont(new java.awt.Font("Lucida Grande", 3, 14)); // NOI18N
        codLabel.setText("CheckOut Date");
        getContentPane().add(codLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(428, 378, 184, 26));

        codTextField.setFont(new java.awt.Font("Lucida Grande", 3, 14)); // NOI18N
        getContentPane().add(codTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(428, 431, 184, 34));

        totalLabel.setFont(new java.awt.Font("Lucida Grande", 3, 14)); // NOI18N
        totalLabel.setText("Total Amount");
        getContentPane().add(totalLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(776, 378, 172, 26));

        totalTextField.setFont(new java.awt.Font("Lucida Grande", 3, 14)); // NOI18N
        getContentPane().add(totalTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(776, 431, 172, 34));

        updateButton.setBackground(new java.awt.Color(0, 204, 153));
        updateButton.setFont(new java.awt.Font("Lucida Grande", 3, 14)); // NOI18N
        updateButton.setForeground(new java.awt.Color(153, 0, 0));
        updateButton.setText("Update");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });
        getContentPane().add(updateButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(641, 502, 115, 39));

        clearButton.setBackground(new java.awt.Color(0, 204, 153));
        clearButton.setFont(new java.awt.Font("Lucida Grande", 3, 14)); // NOI18N
        clearButton.setForeground(new java.awt.Color(153, 0, 0));
        clearButton.setText("Clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });
        getContentPane().add(clearButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(834, 502, 114, 39));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Allote", "User", "Committente", "CheckIn", "CheckOut", "Status", "Room Number", "Note", "Ndays", "Total"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 570, 1130, -1));

        bgLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/background.png"))); // NOI18N
        getContentPane().add(bgLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 1160, 1010));

        pack();
    }// </editor-fold>                        

    private void ppdTextFieldActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
    }                                            

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
        dataFields();
    }                                            

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
        dispose();
    }                                           

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
         cancel();    
    }                                           

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
        conferm();
        
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
            java.util.logging.Logger.getLogger(ManageRiservationView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageRiservationView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageRiservationView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageRiservationView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManageRiservationView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JLabel bgLabel;
    private javax.swing.JLabel cidLabel;
    private javax.swing.JTextField cidTextField;
    private javax.swing.JButton clearButton;
    private javax.swing.JButton closeButton;
    private javax.swing.JLabel codLabel;
    private javax.swing.JTextField codTextField;
    private javax.swing.JLabel committenteLabel;
    private javax.swing.JTextField committenteTextField;
    private javax.swing.JLabel idLabel;
    private javax.swing.JTextField idTextField;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel logoLabel;
    private javax.swing.JLabel nodLabel;
    private javax.swing.JTextField nodTextField;
    private javax.swing.JLabel noteLabel;
    private javax.swing.JTextField noteTextField;
    private javax.swing.JTextField ppdTextField;
    private javax.swing.JLabel rnLabel;
    private javax.swing.JTextField rnTextField;
    private javax.swing.JButton searchButton;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JLabel totalLabel;
    private javax.swing.JTextField totalTextField;
    private javax.swing.JButton updateButton;
    private javax.swing.JLabel userLabel;
    private javax.swing.JTextField userTextField;
    // End of variables declaration  
}
