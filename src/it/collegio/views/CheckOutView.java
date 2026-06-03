package it.collegio.views;

import it.collegio.controllers.CheckOutController;
import it.collegio.dto.CheckoutPreview;
import it.collegio.utilities.utility;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

public class CheckOutView extends javax.swing.JFrame {

    private final CheckOutController controller;

    public CheckOutView() {
        this.controller = new CheckOutController();
        initComponents();
        date();
    }

    private void date() {
        SimpleDateFormat dat = new SimpleDateFormat("yyyy-MM-dd");
        codTextField.setText(dat.format(new Date()));
        codTextField.setEditable(false);
    }

    private void cancel() {
        idTextField.setText("");
        emailTextField.setText("");
        nameTextField.setText("");
        mobileTextField.setText("");
        cidTextField.setText("");
        rnTextField.setText("");
        ppdTextField.setText("");
        totalTextField.setText("");
        nodTextField.setText("");

        emailTextField.setEditable(true);
        nameTextField.setEditable(true);
        mobileTextField.setEditable(true);
        cidTextField.setEditable(true);
        rnTextField.setEditable(true);
        ppdTextField.setEditable(true);
        totalTextField.setEditable(true);
        nodTextField.setEditable(true);
    }

    private void cerca() {
        String ids = idTextField.getText();
        if (ids.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Inserisci un ID Allote (es. P0001)");
            return;
        }

        int reservationId;
        try {
            reservationId = utility.convInt(ids);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Formato ID non valido. Usa P0xxx.");
            return;
        }

        CheckoutPreview preview = controller.getCheckoutPreview(reservationId, codTextField.getText());
        if (preview == null) {
            JOptionPane.showMessageDialog(this, "Reservation Not Found");
            idTextField.setText("");
            return;
        }

        emailTextField.setText(preview.getUserEmail());
        nameTextField.setText(preview.getUserNome());
        mobileTextField.setText(preview.getUserMobile());
        cidTextField.setText(preview.getCheckInDate());
        rnTextField.setText(utility.convAlfaR(preview.getRoomId()));
        ppdTextField.setText(String.valueOf(preview.getPrezzoGiornaliero()));
        nodTextField.setText(String.valueOf(preview.getGiorni()));
        totalTextField.setText(String.format("%.2f", preview.getTotale()));

        emailTextField.setEditable(false);
        nameTextField.setEditable(false);
        mobileTextField.setEditable(false);
        cidTextField.setEditable(false);
        rnTextField.setEditable(false);
        ppdTextField.setEditable(false);
        totalTextField.setEditable(false);
        nodTextField.setEditable(false);
    }

    private void confermaCheckout() {
        if (idTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Cercare prima una prenotazione");
            return;
        }
        if (codTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Data checkout mancante");
            return;
        }

        int reservationId;
        try {
            reservationId = utility.convInt(idTextField.getText());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Formato ID non valido");
            return;
        }

        int fatturaId = controller.eseguiCheckout(reservationId, codTextField.getText());
        if (fatturaId > 0) {
            cancel();
            new FatturaView(fatturaId).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Errore durante il checkout. Verifica i dati e riprova.");
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        closeButton = new javax.swing.JButton();
        logoLabel = new javax.swing.JLabel();
        idLabel = new javax.swing.JLabel();
        idTextField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        nameLabel = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        rnLabel = new javax.swing.JLabel();
        rnTextField = new javax.swing.JTextField();
        cidLabel = new javax.swing.JLabel();
        cidTextField = new javax.swing.JTextField();
        mobileLabel = new javax.swing.JLabel();
        ppdLabel = new javax.swing.JLabel();
        mobileTextField = new javax.swing.JTextField();
        ppdTextField = new javax.swing.JTextField();
        emailLabel = new javax.swing.JLabel();
        emailTextField = new javax.swing.JTextField();
        nodLabel = new javax.swing.JLabel();
        nodTextField = new javax.swing.JTextField();
        codLabel = new javax.swing.JLabel();
        codTextField = new javax.swing.JTextField();
        totalLabel = new javax.swing.JLabel();
        totalTextField = new javax.swing.JTextField();
        coButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        bgLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        closeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/close.png")));
        closeButton.addActionListener(e -> dispose());
        getContentPane().add(closeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 0, -1, -1));

        logoLabel.setFont(new java.awt.Font("Lucida Grande", 3, 14));
        logoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/checked.png")));
        logoLabel.setText("CheckOut");
        getContentPane().add(logoLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 151, -1));

        idLabel.setFont(new java.awt.Font("Lucida Grande", 3, 14));
        idLabel.setText("ID Allote");
        getContentPane().add(idLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(294, 66, 100, 24));

        idTextField.setFont(new java.awt.Font("Lucida Grande", 3, 14));
        getContentPane().add(idTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(392, 62, 220, 34));

        searchButton.setBackground(new java.awt.Color(0, 204, 153));
        searchButton.setFont(new java.awt.Font("Lucida Grande", 3, 14));
        searchButton.setForeground(new java.awt.Color(153, 0, 0));
        searchButton.setText("Search");
        searchButton.addActionListener(e -> cerca());
        getContentPane().add(searchButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 62, 126, 34));

        nameLabel.setFont(new java.awt.Font("Lucida Grande", 3, 14));
        nameLabel.setText("Name");
        getContentPane().add(nameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 151, 26));

        nameTextField.setFont(new java.awt.Font("Lucida Grande", 3, 14));
        getContentPane().add(nameTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 180, 34));

        rnLabel.setFont(new java.awt.Font("Lucida Grande", 3, 14));
        rnLabel.setText("Room Number");
        getContentPane().add(rnLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, 151, 26));

        rnTextField.setFont(new java.awt.Font("Lucida Grande", 3, 14));
        getContentPane().add(rnTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, 180, 36));

        cidLabel.setFont(new java.awt.Font("Lucida Grande", 3, 14));
        cidLabel.setText("CheckIn Date");
        getContentPane().add(cidLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, 142, 26));

        cidTextField.setFont(new java.awt.Font("Lucida Grande", 3, 14));
        getContentPane().add(cidTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 430, 170, 34));

        mobileLabel.setFont(new java.awt.Font("Lucida Grande", 3, 14));
        mobileLabel.setText("Mobile");
        getContentPane().add(mobileLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(428, 132, 184, 26));

        ppdLabel.setFont(new java.awt.Font("Lucida Grande", 3, 14));
        ppdLabel.setText("Price Per Day");
        getContentPane().add(ppdLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(776, 132, 172, 26));

        mobileTextField.setFont(new java.awt.Font("Lucida Grande", 3, 14));
        getContentPane().add(mobileTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(428, 186, 184, 34));

        ppdTextField.setFont(new java.awt.Font("Lucida Grande", 3, 14));
        getContentPane().add(ppdTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(776, 186, 172, 34));

        emailLabel.setFont(new java.awt.Font("Lucida Grande", 3, 14));
        emailLabel.setText("Email");
        getContentPane().add(emailLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(428, 247, 184, 26));

        emailTextField.setFont(new java.awt.Font("Lucida Grande", 3, 14));
        getContentPane().add(emailTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(428, 312, 184, 36));

        nodLabel.setFont(new java.awt.Font("Lucida Grande", 3, 14));
        nodLabel.setText("Number Of Days");
        getContentPane().add(nodLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(776, 247, 172, 26));

        nodTextField.setFont(new java.awt.Font("Lucida Grande", 3, 14));
        getContentPane().add(nodTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(776, 312, 172, 34));

        codLabel.setFont(new java.awt.Font("Lucida Grande", 3, 14));
        codLabel.setText("CheckOut Date");
        getContentPane().add(codLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(428, 378, 184, 26));

        codTextField.setFont(new java.awt.Font("Lucida Grande", 3, 14));
        getContentPane().add(codTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(428, 431, 184, 34));

        totalLabel.setFont(new java.awt.Font("Lucida Grande", 3, 14));
        totalLabel.setText("Total Amount");
        getContentPane().add(totalLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(776, 378, 172, 26));

        totalTextField.setFont(new java.awt.Font("Lucida Grande", 3, 14));
        getContentPane().add(totalTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(776, 431, 172, 34));

        coButton.setBackground(new java.awt.Color(0, 204, 153));
        coButton.setFont(new java.awt.Font("Lucida Grande", 3, 14));
        coButton.setForeground(new java.awt.Color(153, 0, 0));
        coButton.setText("CheckOut");
        coButton.addActionListener(e -> confermaCheckout());
        getContentPane().add(coButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(641, 502, 115, 39));

        clearButton.setBackground(new java.awt.Color(0, 204, 153));
        clearButton.setFont(new java.awt.Font("Lucida Grande", 3, 14));
        clearButton.setForeground(new java.awt.Color(153, 0, 0));
        clearButton.setText("Clear");
        clearButton.addActionListener(e -> cancel());
        getContentPane().add(clearButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(834, 502, 114, 39));

        bgLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/background.png")));
        getContentPane().add(bgLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 0, 1160, 1010));

        pack();
    }

    private javax.swing.JLabel bgLabel;
    private javax.swing.JLabel cidLabel;
    private javax.swing.JTextField cidTextField;
    private javax.swing.JButton clearButton;
    private javax.swing.JButton closeButton;
    private javax.swing.JButton coButton;
    private javax.swing.JLabel codLabel;
    private javax.swing.JTextField codTextField;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JLabel idLabel;
    private javax.swing.JTextField idTextField;
    private javax.swing.JLabel logoLabel;
    private javax.swing.JLabel mobileLabel;
    private javax.swing.JTextField mobileTextField;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JLabel nodLabel;
    private javax.swing.JTextField nodTextField;
    private javax.swing.JLabel ppdLabel;
    private javax.swing.JTextField ppdTextField;
    private javax.swing.JLabel rnLabel;
    private javax.swing.JTextField rnTextField;
    private javax.swing.JButton searchButton;
    private javax.swing.JLabel totalLabel;
    private javax.swing.JTextField totalTextField;
}
