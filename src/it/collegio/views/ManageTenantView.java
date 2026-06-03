package it.collegio.views;

import it.collegio.controllers.ManageTenantController;
import it.collegio.dto.CommittenteDettaglio;
import it.collegio.models.Indirizzo;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ManageTenantView extends javax.swing.JFrame {

    private final ManageTenantController controller;

    public ManageTenantView() {
        this.controller = new ManageTenantController();
        initComponents();
        initComboBoxes();
        autoID();
        loadTable();
        updateButton.setEnabled(false);

        tenantTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = tenantTable.getSelectedRow();
                if (row >= 0) {
                    int codCommittente = (int) ((DefaultTableModel) tenantTable.getModel()).getValueAt(row, 0);
                    loadFields(codCommittente);
                    updateButton.setEnabled(true);
                    addButton.setEnabled(false);
                }
            }
        });
    }

    private void autoID() {
        idTextField.setText(String.valueOf(controller.getNextId()));
    }

    private void initComboBoxes() {
        adminComboBox.removeAllItems();
        for (String email : controller.getAdminEmails()) {
            adminComboBox.addItem(email);
        }

        addressComboBox.removeAllItems();
        List<Indirizzo> indirizzi = controller.getIndirizzi();
        for (Indirizzo i : indirizzi) {
            addressComboBox.addItem(i.getVia());
        }
    }

    private void loadTable() {
        DefaultTableModel model = (DefaultTableModel) tenantTable.getModel();
        model.setRowCount(0);

        List<CommittenteDettaglio> dettagli = controller.getDettagli();
        for (CommittenteDettaglio d : dettagli) {
            model.addRow(new Object[]{
                d.getCodCommittente(),
                d.getRagioneSociale(),
                d.getGestoreEmail() != null ? d.getGestoreEmail() : "",
                d.getEmail() != null ? d.getEmail() : ""
            });
        }
    }

    private void loadFields(int codCommittente) {
        CommittenteDettaglio d = controller.getDettaglio(codCommittente);
        if (d == null) {
            JOptionPane.showMessageDialog(this, "Committente non trovato");
            return;
        }
        idTextField.setText(String.valueOf(d.getCodCommittente()));
        idTextField.setEditable(false);
        rsTextField.setText(d.getRagioneSociale());
        if (d.getGestoreEmail() != null) adminComboBox.setSelectedItem(d.getGestoreEmail());
        emailTextField.setText(d.getEmail() != null ? d.getEmail() : "");
        telTextField.setText(d.getTelefono() != null ? d.getTelefono() : "");
        if (d.getVia() != null) addressComboBox.setSelectedItem(d.getVia());
    }

    private void clearFields() {
        autoID();
        idTextField.setEditable(true);
        rsTextField.setText("");
        emailTextField.setText("");
        telTextField.setText("");
        if (adminComboBox.getItemCount() > 0) adminComboBox.setSelectedIndex(0);
        if (addressComboBox.getItemCount() > 0) addressComboBox.setSelectedIndex(0);
        tenantTable.clearSelection();
        addButton.setEnabled(true);
        updateButton.setEnabled(false);
    }

    private void addCommittente() {
        if (rsTextField.getText().isEmpty() || emailTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ragione Sociale ed Email sono obbligatori");
            return;
        }
        int codCommittente;
        try {
            codCommittente = Integer.parseInt(idTextField.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID committente non valido");
            return;
        }

        boolean ok = controller.addCommittente(
                codCommittente,
                rsTextField.getText(),
                (String) adminComboBox.getSelectedItem(),
                emailTextField.getText(),
                telTextField.getText(),
                (String) addressComboBox.getSelectedItem());

        if (ok) {
            JOptionPane.showMessageDialog(this, "Committente aggiunto con successo");
            loadTable();
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Errore inserimento committente");
        }
    }

    private void updateCommittente() {
        if (idTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selezionare prima un committente dalla tabella");
            return;
        }
        int codCommittente;
        try {
            codCommittente = Integer.parseInt(idTextField.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID committente non valido");
            return;
        }

        boolean ok = controller.updateCommittente(
                codCommittente,
                rsTextField.getText(),
                (String) adminComboBox.getSelectedItem(),
                emailTextField.getText(),
                telTextField.getText(),
                (String) addressComboBox.getSelectedItem());

        if (ok) {
            JOptionPane.showMessageDialog(this, "Committente aggiornato con successo");
            loadTable();
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Errore aggiornamento committente");
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        logoLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tenantTable = new javax.swing.JTable();
        closeButton = new javax.swing.JButton();
        idLabel = new javax.swing.JLabel();
        rsLabel = new javax.swing.JLabel();
        adminLabel = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        telLabel = new javax.swing.JLabel();
        addressLabel = new javax.swing.JLabel();
        addButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        idTextField = new javax.swing.JTextField();
        rsTextField = new javax.swing.JTextField();
        adminComboBox = new javax.swing.JComboBox<>();
        emailTextField = new javax.swing.JTextField();
        telTextField = new javax.swing.JTextField();
        addressComboBox = new javax.swing.JComboBox<>();
        bgLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        logoLabel.setFont(new java.awt.Font(".SF NS Text", 3, 14));
        logoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/manage.png")));
        logoLabel.setText("Manage Tenant");
        getContentPane().add(logoLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 38, 193, -1));

        tenantTable.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"Committente", "Ragione Sociale", "Admin", "Email"}
        ) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        });
        jScrollPane1.setViewportView(tenantTable);
        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 126, 1034, 366));

        closeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/close.png")));
        closeButton.addActionListener(e -> dispose());
        getContentPane().add(closeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1069, 6, -1, -1));

        idLabel.setText("Cod Committente");
        getContentPane().add(idLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 553, 119, 32));

        rsLabel.setText("Ragione Sociale");
        getContentPane().add(rsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(186, 553, 125, 32));

        adminLabel.setText("Gestore");
        getContentPane().add(adminLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 553, 148, 32));

        emailLabel.setText("Email");
        getContentPane().add(emailLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(578, 553, 139, 31));

        telLabel.setText("Telefono");
        getContentPane().add(telLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(762, 554, 126, 28));

        addressLabel.setText("Indirizzo");
        getContentPane().add(addressLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(915, 554, 140, 28));

        addButton.setBackground(new java.awt.Color(0, 204, 153));
        addButton.setFont(new java.awt.Font(".SF NS Text", 3, 14));
        addButton.setForeground(new java.awt.Color(153, 0, 0));
        addButton.setText("Add");
        addButton.addActionListener(e -> addCommittente());
        getContentPane().add(addButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(619, 706, 98, 31));

        updateButton.setBackground(new java.awt.Color(0, 204, 153));
        updateButton.setFont(new java.awt.Font(".SF NS Text", 3, 14));
        updateButton.setForeground(new java.awt.Color(153, 0, 0));
        updateButton.setText("Update");
        updateButton.addActionListener(e -> updateCommittente());
        getContentPane().add(updateButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(798, 704, 90, 34));

        clearButton.setBackground(new java.awt.Color(0, 204, 153));
        clearButton.setFont(new java.awt.Font(".SF NS Text", 3, 14));
        clearButton.setForeground(new java.awt.Color(153, 0, 0));
        clearButton.setText("Clear");
        clearButton.addActionListener(e -> clearFields());
        getContentPane().add(clearButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(963, 704, 92, 34));

        getContentPane().add(idTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 603, 119, 33));
        getContentPane().add(rsTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(186, 603, 125, 33));
        getContentPane().add(adminComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 600, 135, 33));
        getContentPane().add(emailTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 600, 130, 30));
        getContentPane().add(telTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 600, 126, 30));
        getContentPane().add(addressComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 600, 130, 28));

        bgLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/all pages background.png")));
        getContentPane().add(bgLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, 760));

        pack();
    }

    private javax.swing.JButton addButton;
    private javax.swing.JComboBox<String> addressComboBox;
    private javax.swing.JLabel addressLabel;
    private javax.swing.JComboBox<String> adminComboBox;
    private javax.swing.JLabel adminLabel;
    private javax.swing.JLabel bgLabel;
    private javax.swing.JButton clearButton;
    private javax.swing.JButton closeButton;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JLabel idLabel;
    private javax.swing.JTextField idTextField;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel logoLabel;
    private javax.swing.JLabel rsLabel;
    private javax.swing.JTextField rsTextField;
    private javax.swing.JLabel telLabel;
    private javax.swing.JTextField telTextField;
    private javax.swing.JTable tenantTable;
    private javax.swing.JButton updateButton;
}
