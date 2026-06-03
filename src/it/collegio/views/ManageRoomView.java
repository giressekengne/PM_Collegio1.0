package it.collegio.views;

import it.collegio.controllers.ManageRoomController;
import it.collegio.models.Room;
import it.collegio.utilities.utility;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ManageRoomView extends javax.swing.JFrame{

    private final ManageRoomController controller;

    public ManageRoomView() {
        this.controller = new ManageRoomController();
        initComponents();
        autoID();
        loadRooms();
        wireTableMouseListener();
        wireButtonListeners();
        updateButton.setEnabled(false);
    }

    private void wireButtonListeners() {
        for (java.awt.event.ActionListener al : addrButton.getActionListeners()) {
            addrButton.removeActionListener(al);
        }
        for (java.awt.event.ActionListener al : updateButton.getActionListeners()) {
            updateButton.removeActionListener(al);
        }
        for (java.awt.event.ActionListener al : clearButton.getActionListeners()) {
            clearButton.removeActionListener(al);
        }
        addrButton.addActionListener(e -> add());
        updateButton.addActionListener(e -> update());
        clearButton.addActionListener(e -> clear());
    }

    private void wireTableMouseListener() {
        roomTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                onRowClicked(evt);
            }
        });
    }

    private void autoID() {
        rnTextField.setText(utility.convAlfaR(controller.getNextId()));
        rnTextField.setEditable(false);
    }

    private void loadRooms() {
        DefaultTableModel model = (DefaultTableModel) roomTable.getModel();
        model.setRowCount(0);

        List<Room> rooms = controller.getRooms();
        for (Room r : rooms) {
            model.addRow(new Object[]{
                utility.convAlfaR(r.getId()),
                r.getrTipo() != null ? r.getrTipo().name().toLowerCase() : "",
                r.getLettoTipo() != null ? r.getLettoTipo().getDbValue() : "",
                String.valueOf(r.getPrezzo()),
                r.getStato() != null ? r.getStato().name().toLowerCase() : ""
            });
        }
        updateButton.setEnabled(false);
    }

    private void clear() {
        autoID();
        if (rtComboBox.getItemCount() > 0) rtComboBox.setSelectedIndex(0);
        if (btComboBox.getItemCount() > 0) btComboBox.setSelectedIndex(0);
        priceTextField.setText("");
        addrButton.setEnabled(true);
        updateButton.setEnabled(false);
    }

    private void onRowClicked(java.awt.event.MouseEvent evt) {
        DefaultTableModel model = (DefaultTableModel) roomTable.getModel();
        int row = roomTable.getSelectedRow();
        if (row < 0) return;

        String roomCode = (String) model.getValueAt(row, 0);
        String tipo = (String) model.getValueAt(row, 1);
        String lettoTipo = (String) model.getValueAt(row, 2);
        String prezzo = (String) model.getValueAt(row, 3);
        String stato = (String) model.getValueAt(row, 4);

        if (evt.getClickCount() == 1) {
            if (controller.isOccupata(stato)) {
                JOptionPane.showMessageDialog(this, "Camera occupata: impossibile modificarla");
                return;
            }
            updateButton.setEnabled(true);
            addrButton.setEnabled(false);
            rnTextField.setText(roomCode);
            rnTextField.setEditable(false);
            rtComboBox.setSelectedItem(tipo);
            btComboBox.setSelectedItem(lettoTipo);
            priceTextField.setText(prezzo);
        } else {
            int yes = JOptionPane.showConfirmDialog(this, "Eliminare la camera " + roomCode + "?",
                    "Conferma cancellazione", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (yes != JOptionPane.YES_OPTION) return;
            if (controller.isOccupata(stato)) {
                JOptionPane.showMessageDialog(this, "Camera occupata: impossibile cancellarla");
                return;
            }
            int id = utility.convInt(roomCode);
            if (controller.deleteRoom(id)) {
                JOptionPane.showMessageDialog(this, "Camera eliminata");
                autoID();
                loadRooms();
                clear();
            } else {
                JOptionPane.showMessageDialog(this, "Errore eliminazione (camera occupata o inesistente)");
            }
        }
    }

    private void add() {
        if (rnTextField.getText().isEmpty() || priceTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tutti i campi sono obbligatori");
            return;
        }
        int id;
        double prezzo;
        try {
            id = utility.convInt(rnTextField.getText());
            prezzo = Double.parseDouble(priceTextField.getText());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Numero camera o prezzo non valido");
            return;
        }

        String tipo = (String) rtComboBox.getSelectedItem();
        String lettoTipo = (String) btComboBox.getSelectedItem();

        if (controller.addRoom(id, tipo, prezzo, lettoTipo)) {
            JOptionPane.showMessageDialog(this, "Camera aggiunta");
            autoID();
            loadRooms();
            priceTextField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Camera gia esistente o errore inserimento");
        }
    }

    private void update() {
        if (priceTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Prezzo obbligatorio");
            return;
        }
        int id;
        double prezzo;
        try {
            id = utility.convInt(rnTextField.getText());
            prezzo = Double.parseDouble(priceTextField.getText());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Numero camera o prezzo non valido");
            return;
        }

        String tipo = (String) rtComboBox.getSelectedItem();
        String lettoTipo = (String) btComboBox.getSelectedItem();

        if (controller.updateRoom(id, tipo, prezzo, lettoTipo)) {
            JOptionPane.showMessageDialog(this, "Camera aggiornata");
            autoID();
            loadRooms();
            clear();
        } else {
            JOptionPane.showMessageDialog(this, "Errore aggiornamento (camera occupata o inesistente)");
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")                          
    private void initComponents() {

        closeButton = new javax.swing.JButton();
        logoLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        roomTable = new javax.swing.JTable();
        rnLabel = new javax.swing.JLabel();
        rnTextField = new javax.swing.JTextField();
        btLabel = new javax.swing.JLabel();
        btComboBox = new javax.swing.JComboBox<>();
        rtLabel = new javax.swing.JLabel();
        rtComboBox = new javax.swing.JComboBox<>();
        priceLabel = new javax.swing.JLabel();
        priceTextField = new javax.swing.JTextField();
        addrButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        messageLabel = new javax.swing.JLabel();
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
        getContentPane().add(closeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 0, -1, -1));

        logoLabel.setFont(new java.awt.Font("Lucida Grande", 3, 14)); // NOI18N
        logoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/manage.png"))); // NOI18N
        logoLabel.setText("    Manage Room");
        getContentPane().add(logoLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 16, 184, -1));

        roomTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Room Number", "Room Type", "Bed Type", "Price", "Status"
            }
        ));
        roomTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                roomTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(roomTable);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 1110, 420));

        rnLabel.setFont(new java.awt.Font("Lucida Grande", 3, 14)); // NOI18N
        rnLabel.setText("Roon Number");
        getContentPane().add(rnLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 570, 300, 24));

        rnTextField.setFont(new java.awt.Font("Lucida Grande", 3, 14)); // NOI18N
        rnTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rnTextFieldActionPerformed(evt);
            }
        });
        getContentPane().add(rnTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 620, 300, 34));

        btLabel.setFont(new java.awt.Font("Lucida Grande", 3, 14)); // NOI18N
        btLabel.setText("Bed Type");
        getContentPane().add(btLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 700, 300, 26));

        btComboBox.setFont(new java.awt.Font("Lucida Grande", 3, 14)); // NOI18N
        btComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "matrimoniale", "singolo", "king-size", " " }));
        getContentPane().add(btComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 750, 290, 36));

        rtLabel.setFont(new java.awt.Font("Lucida Grande", 3, 14)); // NOI18N
        rtLabel.setText("Room Type");
        getContentPane().add(rtLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 570, 313, 35));

        rtComboBox.setFont(new java.awt.Font("Lucida Grande", 3, 14)); // NOI18N
        rtComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "singola", "doppia", "suite", " ", " " }));
        getContentPane().add(rtComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 620, 313, 35));

        priceLabel.setFont(new java.awt.Font("Lucida Grande", 3, 14)); // NOI18N
        priceLabel.setText("Price");
        getContentPane().add(priceLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 700, 313, 25));

        priceTextField.setFont(new java.awt.Font("Lucida Grande", 3, 14)); // NOI18N
        priceTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                priceTextFieldActionPerformed(evt);
            }
        });
        getContentPane().add(priceTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 750, 313, 36));

        addrButton.setBackground(new java.awt.Color(0, 204, 153));
        addrButton.setFont(new java.awt.Font("Lucida Grande", 3, 14)); // NOI18N
        addrButton.setForeground(new java.awt.Color(153, 0, 0));
        addrButton.setText("Add Room");
        addrButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addrButtonActionPerformed(evt);
            }
        });
        getContentPane().add(addrButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 930, 162, 40));

        updateButton.setBackground(new java.awt.Color(0, 204, 153));
        updateButton.setFont(new java.awt.Font(".SF NS Text", 3, 14)); // NOI18N
        updateButton.setForeground(new java.awt.Color(153, 0, 0));
        updateButton.setText("Update");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });
        getContentPane().add(updateButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 930, 150, 40));

        clearButton.setBackground(new java.awt.Color(0, 204, 153));
        clearButton.setFont(new java.awt.Font(".SF NS Text", 3, 14)); // NOI18N
        clearButton.setForeground(new java.awt.Color(153, 0, 0));
        clearButton.setText("Clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });
        getContentPane().add(clearButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 930, 150, 40));

        messageLabel.setText("Click to update the record!!    Double-click to delete it!!");
        getContentPane().add(messageLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 30, 380, 20));

        bgLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/all pages background.png"))); // NOI18N
        getContentPane().add(bgLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 1040));

        pack();
    }                       

    private void rnTextFieldActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
        dispose();
    }                                           

    // Vecchi handler autogenerati: la logica reale e' nei metodi add/update/clear
    // collegati programmaticamente in wireButtonListeners() / wireTableMouseListener().
    private void addrButtonActionPerformed(java.awt.event.ActionEvent evt) { }
    private void roomTableMouseClicked(java.awt.event.MouseEvent evt) { }
    private void priceTextFieldActionPerformed(java.awt.event.ActionEvent evt) { }
    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) { }
    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) { }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ManageRoomView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageRoomView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageRoomView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageRoomView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManageRoomView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton addrButton;
    private javax.swing.JLabel bgLabel;
    private javax.swing.JComboBox<String> btComboBox;
    private javax.swing.JLabel btLabel;
    private javax.swing.JButton clearButton;
    private javax.swing.JButton closeButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel logoLabel;
    private javax.swing.JLabel messageLabel;
    private javax.swing.JLabel priceLabel;
    private javax.swing.JTextField priceTextField;
    private javax.swing.JLabel rnLabel;
    private javax.swing.JTextField rnTextField;
    private javax.swing.JTable roomTable;
    private javax.swing.JComboBox<String> rtComboBox;
    private javax.swing.JLabel rtLabel;
    private javax.swing.JButton updateButton;
    // End of variables declaration 
}
