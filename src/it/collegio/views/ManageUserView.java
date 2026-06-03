package it.collegio.views;

import it.collegio.controllers.ManageUserController;
import it.collegio.models.User;
import it.collegio.utilities.SessionContext;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ManageUserView extends javax.swing.JFrame {

    private final ManageUserController controller;

    public ManageUserView() {
        this.controller = new ManageUserController();
        initComponents();
        initComboBoxes();
        loadTable();
        wireTableMouseListener();
        updateButton.setEnabled(false);
        addButton.setEnabled(true);
        configureForRole();
    }

    private void wireTableMouseListener() {
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = jTable1.getSelectedRow();
                if (row < 0) {
                    return;
                }
                String email = (String) ((javax.swing.table.DefaultTableModel) jTable1.getModel()).getValueAt(row, 1);
                if (email == null || email.isEmpty()) {
                    return;
                }
                emailTextField.setText(email);
                cercaUtente();
                addButton.setEnabled(false);
                updateButton.setEnabled(true);
            }
        });
    }

    private void initComboBoxes() {
        statusComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"attivo", "disattivato", "attesa"}));
        genderComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Maschio", "Femmina", "Altro"}));
        qstComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{
            "giocatore preferito?", "disciplina preferita?", "cantante preferito?",
            "libro preferita?", "programma televisivo preferito?"}));
        addressComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{
            "GOLGI1", "GOLGI2", "CARDANO", "VOLTA", "GHISLIERI", "MAINO", "CAMPUS",
            "BORROMEO", "CAIROLI", "SPALLA"}));
    }

    private void configureForRole() {
        if ("U".equalsIgnoreCase(SessionContext.roleType)) {
            searchButton.setVisible(false);
            RefreshButton.setVisible(false);
            addButton.setVisible(false);
            jScrollPane1.setVisible(false);
            if (SessionContext.email != null) {
                emailTextField.setText(SessionContext.email);
                emailTextField.setEditable(false);
                cercaUtente();
            }
        }
    }

    private void loadTable() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        List<User> users = controller.getUsersPerRuolo();
        for (User u : users) {
            String statoStr = u.getStato() != null ? u.getStato().name().toLowerCase() : "";
            model.addRow(new Object[]{
                u.getNome(),
                u.getEmail(),
                u.getPw(),
                u.getResponse(),
                statoStr
            });
        }
    }

    private void cercaUtente() {
        String email = emailTextField.getText();
        if (email.isEmpty()) {
            return;
        }
        User u = controller.cercaUserPerEmail(email);
        if (u == null) {
            JOptionPane.showMessageDialog(this, "Utente non trovato");
            emailTextField.setText("");
            return;
        }
        nameTextField.setText(u.getNome());
        surnameTextField.setText(u.getCognome());
        pwTextField.setText(u.getPw());
        mobileTextField.setText(u.getMobile());
        responseTextField.setText(u.getResponse());
        if (u.getStato() != null) {
            statusComboBox.setSelectedItem(u.getStato().name().toLowerCase());
        }
        if (u.getGenere() != null) {
            String gStr = u.getGenere().name();
            statusComboBox.getModel();
            genderComboBox.setSelectedItem(capitalize(gStr.toLowerCase()));
        }
        if (u.getRecupero() != null) {
            qstComboBox.setSelectedItem(u.getRecupero());
        }
    }

    private static String capitalize(String s) {
        if (s == null || s.isEmpty()) return s;
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }

    private void clearFields() {
        nameTextField.setText("");
        surnameTextField.setText("");
        if (!"U".equalsIgnoreCase(SessionContext.roleType)) {
            emailTextField.setText("");
        }
        pwTextField.setText("");
        mobileTextField.setText("");
        responseTextField.setText("");
        statusComboBox.setSelectedIndex(0);
        addressComboBox.setSelectedIndex(0);
        qstComboBox.setSelectedIndex(0);
        genderComboBox.setSelectedIndex(0);
        jTable1.clearSelection();
        addButton.setEnabled(true);
        updateButton.setEnabled(false);
    }

    private void addUser() {
        if (nameTextField.getText().isEmpty() || emailTextField.getText().isEmpty()
                || pwTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nome, Email e Password sono obbligatori");
            return;
        }

        boolean ok = controller.insertUser(
                nameTextField.getText(),
                surnameTextField.getText(),
                emailTextField.getText(),
                pwTextField.getText(),
                (String) statusComboBox.getSelectedItem(),
                mobileTextField.getText(),
                (String) addressComboBox.getSelectedItem(),
                (String) qstComboBox.getSelectedItem(),
                responseTextField.getText(),
                (String) genderComboBox.getSelectedItem());

        if (ok) {
            JOptionPane.showMessageDialog(this, "Utente aggiunto con successo");
            loadTable();
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Errore inserimento utente (email duplicata o dati invalidi)");
        }
    }

    private void updateUser() {
        if (emailTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Cercare prima un utente per email");
            return;
        }
        boolean ok = controller.updateUser(
                emailTextField.getText(),
                nameTextField.getText(),
                surnameTextField.getText(),
                pwTextField.getText(),
                mobileTextField.getText(),
                (String) statusComboBox.getSelectedItem(),
                (String) qstComboBox.getSelectedItem(),
                responseTextField.getText(),
                (String) genderComboBox.getSelectedItem());

        if (ok) {
            JOptionPane.showMessageDialog(this, "Utente aggiornato con successo");
            loadTable();
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Errore aggiornamento utente");
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        closeButton = new javax.swing.JButton();
        emailLabel = new javax.swing.JLabel();
        emailTextField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        RefreshButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        logoLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        surnameLabel = new javax.swing.JLabel();
        pwLabel = new javax.swing.JLabel();
        statusLabel = new javax.swing.JLabel();
        mobileLabel = new javax.swing.JLabel();
        addressLabel = new javax.swing.JLabel();
        recuperoLabel = new javax.swing.JLabel();
        responseLabel = new javax.swing.JLabel();
        genderLabel = new javax.swing.JLabel();
        addButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        nameTextField = new javax.swing.JTextField();
        surnameTextField = new javax.swing.JTextField();
        pwTextField = new javax.swing.JTextField();
        statusComboBox = new javax.swing.JComboBox<>();
        mobileTextField = new javax.swing.JTextField();
        addressComboBox = new javax.swing.JComboBox<>();
        qstComboBox = new javax.swing.JComboBox<>();
        responseTextField = new javax.swing.JTextField();
        genderComboBox = new javax.swing.JComboBox<>();
        bgLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        closeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/close.png")));
        closeButton.addActionListener(e -> dispose());
        getContentPane().add(closeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 0, -1, -1));

        emailLabel.setFont(new java.awt.Font("Lucida Grande", 3, 14));
        emailLabel.setText("Search by Email");
        getContentPane().add(emailLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, 120, 27));

        emailTextField.setFont(new java.awt.Font("Lucida Grande", 3, 14));
        getContentPane().add(emailTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 50, 180, 36));

        searchButton.setBackground(new java.awt.Color(0, 204, 153));
        searchButton.setFont(new java.awt.Font("Lucida Grande", 3, 14));
        searchButton.setForeground(new java.awt.Color(153, 0, 0));
        searchButton.setText("Search");
        searchButton.addActionListener(e -> cercaUtente());
        getContentPane().add(searchButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 50, 100, 36));

        RefreshButton.setBackground(new java.awt.Color(0, 204, 153));
        RefreshButton.setFont(new java.awt.Font("Lucida Grande", 3, 14));
        RefreshButton.setForeground(new java.awt.Color(153, 0, 0));
        RefreshButton.setText("Refresh");
        RefreshButton.addActionListener(e -> loadTable());
        getContentPane().add(RefreshButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 50, 100, 40));

        jTable1.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"Name", "Email", "Password", "Answer", "Status"}
        ) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        });
        jScrollPane1.setViewportView(jTable1);
        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 1140, 530));

        logoLabel.setFont(new java.awt.Font(".SF NS Text", 3, 14));
        logoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/manage.png")));
        logoLabel.setText("Manage User");
        getContentPane().add(logoLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 190, 70));

        nameLabel.setFont(new java.awt.Font(".SF NS Text", 3, 14));
        nameLabel.setText("Name");
        getContentPane().add(nameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 680, 120, 30));

        surnameLabel.setFont(new java.awt.Font(".SF NS Text", 3, 14));
        surnameLabel.setText("Surname");
        getContentPane().add(surnameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 680, 100, 30));

        pwLabel.setFont(new java.awt.Font(".SF NS Text", 3, 14));
        pwLabel.setText("Password");
        getContentPane().add(pwLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 680, 100, 30));

        statusLabel.setFont(new java.awt.Font(".SF NS Text", 3, 14));
        statusLabel.setText("Status");
        getContentPane().add(statusLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 780, 110, 30));

        mobileLabel.setFont(new java.awt.Font(".SF NS Text", 3, 14));
        mobileLabel.setText("Mobile");
        getContentPane().add(mobileLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 780, 110, 30));

        addressLabel.setFont(new java.awt.Font(".SF NS Text", 3, 14));
        addressLabel.setText("Address");
        getContentPane().add(addressLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 770, 90, 30));

        recuperoLabel.setFont(new java.awt.Font(".SF NS Text", 3, 14));
        recuperoLabel.setText("Recupero");
        getContentPane().add(recuperoLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 890, 120, 30));

        responseLabel.setFont(new java.awt.Font(".SF NS Text", 3, 14));
        responseLabel.setText("Response");
        getContentPane().add(responseLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 890, 100, 30));

        genderLabel.setFont(new java.awt.Font(".SF NS Text", 3, 14));
        genderLabel.setText("Gender");
        getContentPane().add(genderLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 880, 90, 30));

        addButton.setBackground(new java.awt.Color(0, 204, 153));
        addButton.setFont(new java.awt.Font(".SF NS Text", 3, 14));
        addButton.setForeground(new java.awt.Color(153, 0, 0));
        addButton.setText("Add");
        addButton.addActionListener(e -> addUser());
        getContentPane().add(addButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 710, 120, 40));

        updateButton.setBackground(new java.awt.Color(0, 204, 153));
        updateButton.setFont(new java.awt.Font(".SF NS Text", 3, 14));
        updateButton.setForeground(new java.awt.Color(153, 0, 0));
        updateButton.setText("Update");
        updateButton.addActionListener(e -> updateUser());
        getContentPane().add(updateButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 800, 120, 40));

        clearButton.setBackground(new java.awt.Color(0, 204, 153));
        clearButton.setFont(new java.awt.Font(".SF NS Text", 3, 14));
        clearButton.setForeground(new java.awt.Color(153, 0, 0));
        clearButton.setText("Clear");
        clearButton.addActionListener(e -> clearFields());
        getContentPane().add(clearButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 890, 120, 40));

        nameTextField.setFont(new java.awt.Font(".SF NS Text", 3, 14));
        getContentPane().add(nameTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 720, 160, 30));

        surnameTextField.setFont(new java.awt.Font(".SF NS Text", 3, 14));
        getContentPane().add(surnameTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 720, 150, 30));

        pwTextField.setFont(new java.awt.Font(".SF NS Text", 3, 14));
        getContentPane().add(pwTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 720, 160, 30));

        statusComboBox.setFont(new java.awt.Font(".SF NS Text", 3, 14));
        getContentPane().add(statusComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 830, 160, 30));

        mobileTextField.setFont(new java.awt.Font(".SF NS Text", 3, 14));
        getContentPane().add(mobileTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 830, 150, 30));

        addressComboBox.setFont(new java.awt.Font(".SF NS Text", 3, 14));
        getContentPane().add(addressComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 820, 160, 30));

        qstComboBox.setFont(new java.awt.Font(".SF NS Text", 3, 14));
        getContentPane().add(qstComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 930, 160, 30));

        responseTextField.setFont(new java.awt.Font(".SF NS Text", 3, 14));
        getContentPane().add(responseTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 930, 150, 30));

        genderComboBox.setFont(new java.awt.Font(".SF NS Text", 3, 14));
        getContentPane().add(genderComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 920, 160, 30));

        bgLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/all pages background.png")));
        getContentPane().add(bgLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 1010));

        pack();
    }

    private javax.swing.JButton RefreshButton;
    private javax.swing.JButton addButton;
    private javax.swing.JComboBox<String> addressComboBox;
    private javax.swing.JLabel addressLabel;
    private javax.swing.JLabel bgLabel;
    private javax.swing.JButton clearButton;
    private javax.swing.JButton closeButton;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JComboBox<String> genderComboBox;
    private javax.swing.JLabel genderLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel logoLabel;
    private javax.swing.JLabel mobileLabel;
    private javax.swing.JTextField mobileTextField;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JLabel pwLabel;
    private javax.swing.JTextField pwTextField;
    private javax.swing.JComboBox<String> qstComboBox;
    private javax.swing.JLabel recuperoLabel;
    private javax.swing.JLabel responseLabel;
    private javax.swing.JTextField responseTextField;
    private javax.swing.JButton searchButton;
    private javax.swing.JComboBox<String> statusComboBox;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JLabel surnameLabel;
    private javax.swing.JTextField surnameTextField;
    private javax.swing.JButton updateButton;
}
