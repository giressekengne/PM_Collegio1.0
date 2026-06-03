package it.collegio.views;

import it.collegio.controllers.CheckInController;
import it.collegio.models.Room;
import it.collegio.models.User;
import it.collegio.utilities.SessionContext;
import it.collegio.utilities.utility;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

public class CheckInView extends javax.swing.JFrame {

    private final CheckInController controller;

    public CheckInView() {
        this.controller = new CheckInController();
        initComponents();
        date();
        loadRoomFields();
    }

    private void date() {
        SimpleDateFormat dat = new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date();
        cidTextField.setText(dat.format(d));
        cidTextField.setEditable(false);
        surnameTextField.requestFocus();
    }

    private void loadRoomFields() {
        rnComboBox.removeAllItems();
        List<Room> disponibili = controller.getRoomDisponibili();
        for (Room r : disponibili) {
            rnComboBox.addItem(utility.convAlfaR(r.getId()));
        }
        if (rnComboBox.getItemCount() > 0) {
            applyRoomToFields(disponibili.get(0));
        } else {
            JOptionPane.showMessageDialog(this, "Nessuna camera disponibile al momento");
        }
    }

    private void applyRoomToFields(Room r) {
        if (r == null) return;
        rtComboBox.setSelectedItem(r.getrTipo() != null ? r.getrTipo().name().toLowerCase() : "");
        btComboBox.setSelectedItem(r.getLettoTipo() != null ? r.getLettoTipo().getDbValue() : "");
        priceTextField.setText(String.valueOf(r.getPrezzo()));
    }

    private void cercaUtente() {
        String email = emailTextField.getText();
        if (email.isEmpty()) {
            return;
        }
        User user = controller.cercaUserPerEmail(email);
        if (user != null) {
            surnameTextField.setText(user.getCognome());
            nameTextField.setText(user.getNome());
            genderComboBox.setSelectedItem(user.getGenere() != null ? capitalize(user.getGenere().name().toLowerCase()) : "ND");
            mobileTextField.setText(user.getMobile());
            surnameTextField.setEditable(false);
            nameTextField.setEditable(false);
            genderComboBox.setEditable(false);
            addressComboBox.setEditable(false);
            mobileTextField.setEditable(false);
        } else {
            JOptionPane.showMessageDialog(this, "User Not Found");
            emailTextField.setText("");
        }
    }

    private static String capitalize(String s) {
        if (s == null || s.isEmpty()) return s;
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }

    private void prenota() {
        if (emailTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Email obbligatoria");
            return;
        }
        if (surnameTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Cognome obbligatorio");
            return;
        }
        if (nameTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nome obbligatorio");
            return;
        }
        if (mobileTextField.getText().length() != 10) {
            JOptionPane.showMessageDialog(this, "Mobile deve avere 10 cifre");
            return;
        }
        if (cidTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Data check-in mancante");
            return;
        }
        if (rnComboBox.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Nessuna camera selezionata");
            return;
        }

        User user = controller.cercaUserPerEmail(emailTextField.getText());
        if (user == null) {
            JOptionPane.showMessageDialog(this, "User non valido");
            return;
        }

        int roomId = utility.convInt((String) rnComboBox.getSelectedItem());
        int committenteId = SessionContext.committenteId > 0 ? SessionContext.committenteId : 1;
        String note = null;

        int reservationId = controller.prenota(
                user.getCounter(),
                roomId,
                committenteId,
                cidTextField.getText(),
                note);

        if (reservationId > 0) {
            JOptionPane.showMessageDialog(this,
                    "Room Alloted! Con Allote_ID: " + utility.convAlfaP(reservationId));
            loadRoomFields();
            clearForm();
        } else {
            JOptionPane.showMessageDialog(this, "Errore durante la prenotazione");
        }
    }

    private void clearForm() {
        emailTextField.setText("");
        surnameTextField.setText("");
        nameTextField.setText("");
        mobileTextField.setText("");
        surnameTextField.setEditable(true);
        nameTextField.setEditable(true);
        mobileTextField.setEditable(true);
        if (genderComboBox.getItemCount() > 0) genderComboBox.setSelectedIndex(0);
        if (addressComboBox.getItemCount() > 0) addressComboBox.setSelectedIndex(0);
        priceTextField.setText("");
        emailLabel.setText("Email");
        emailLabel.setForeground(Color.BLACK);
        mobileLabel.setText("Mobile");
        mobileLabel.setForeground(Color.BLACK);
    }

    private void onRnComboChanged() {
        if (rnComboBox.getSelectedIndex() < 0 || rnComboBox.getSelectedItem() == null) {
            return;
        }
        int roomId = utility.convInt((String) rnComboBox.getSelectedItem());
        Room r = controller.getRoomById(roomId);
        applyRoomToFields(r);
    }

    private void onPriceChanged() {
        double prezzo;
        try {
            prezzo = Double.parseDouble(priceTextField.getText());
        } catch (NumberFormatException ex) {
            return;
        }
        List<Room> filtrate = controller.getRoomPerPrezzo(prezzo);
        rnComboBox.removeAllItems();
        for (Room r : filtrate) {
            rnComboBox.addItem(utility.convAlfaR(r.getId()));
        }
        if (rnComboBox.getItemCount() > 0) {
            applyRoomToFields(filtrate.get(0));
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        closeButton = new javax.swing.JButton();
        logoLabel = new javax.swing.JLabel();
        surnameLabel = new javax.swing.JLabel();
        surnameTextField = new javax.swing.JTextField();
        emailLabel = new javax.swing.JLabel();
        emailTextField = new javax.swing.JTextField();
        genderLabel = new javax.swing.JLabel();
        genderComboBox = new javax.swing.JComboBox<>();
        addressLabel = new javax.swing.JLabel();
        addressComboBox = new javax.swing.JComboBox<>();
        cidLabel = new javax.swing.JLabel();
        cidTextField = new javax.swing.JTextField();
        rnLabel = new javax.swing.JLabel();
        rtLabel = new javax.swing.JLabel();
        rtComboBox = new javax.swing.JComboBox<>();
        btLabel = new javax.swing.JLabel();
        btComboBox = new javax.swing.JComboBox<>();
        priceLabel = new javax.swing.JLabel();
        priceTextField = new javax.swing.JTextField();
        mobileLabel = new javax.swing.JLabel();
        mobileTextField = new javax.swing.JTextField();
        alloteButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        nameLabel = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        rnComboBox = new javax.swing.JComboBox<>();
        searchButton = new javax.swing.JButton();
        bgLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        closeButton.setFont(new java.awt.Font("Lucida Grande", 3, 14));
        closeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/close.png")));
        closeButton.addActionListener(e -> dispose());
        getContentPane().add(closeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 0, -1, -1));

        logoLabel.setFont(new java.awt.Font(".SF NS Text", 3, 14));
        logoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Customer Registration & Check IN.png")));
        logoLabel.setText(" Checkin");
        getContentPane().add(logoLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 6, 184, 71));

        surnameLabel.setFont(new java.awt.Font("Lucida Grande", 3, 14));
        surnameLabel.setText("Surname");
        getContentPane().add(surnameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, 261, 27));

        surnameTextField.setFont(new java.awt.Font("Lucida Grande", 3, 14));
        getContentPane().add(surnameTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 261, 34));

        emailLabel.setFont(new java.awt.Font("Lucida Grande", 3, 14));
        emailLabel.setText("Email");
        getContentPane().add(emailLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 30, 90, 40));

        emailTextField.setFont(new java.awt.Font("Lucida Grande", 3, 14));
        emailTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                emailTextFieldKeyReleased(evt);
            }
        });
        getContentPane().add(emailTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 30, 261, 40));

        genderLabel.setFont(new java.awt.Font("Lucida Grande", 3, 14));
        genderLabel.setText("Gender");
        getContentPane().add(genderLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 370, 253, 27));

        genderComboBox.setFont(new java.awt.Font("Lucida Grande", 3, 14));
        genderComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"ND", "Maschio", "Femmina", "Altro"}));
        getContentPane().add(genderComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 420, 260, 36));

        addressLabel.setFont(new java.awt.Font("Lucida Grande", 3, 14));
        addressLabel.setText("Address");
        getContentPane().add(addressLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 490, 298, 24));

        addressComboBox.setFont(new java.awt.Font("Lucida Grande", 3, 14));
        addressComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"GOLGI1", "GOLGI2", "CARDANO", "VOLTA", "GHISLIERI", "MAINO", "CAMPUS", "BORROMEO", "CAIROLI", "SPALLA"}));
        getContentPane().add(addressComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 540, 260, 37));

        cidLabel.setFont(new java.awt.Font("Lucida Grande", 3, 14));
        cidLabel.setText("CheckIn Date");
        getContentPane().add(cidLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(677, 132, 263, 24));

        cidTextField.setFont(new java.awt.Font("Lucida Grande", 3, 14));
        getContentPane().add(cidTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(677, 189, 263, 34));

        rnLabel.setFont(new java.awt.Font("Lucida Grande", 3, 14));
        rnLabel.setText("Room Number");
        getContentPane().add(rnLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(677, 260, 263, 27));

        rtLabel.setFont(new java.awt.Font("Lucida Grande", 3, 14));
        rtLabel.setText("Room Type");
        getContentPane().add(rtLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(677, 368, 263, 26));

        rtComboBox.setFont(new java.awt.Font("Lucida Grande", 3, 14));
        rtComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"singola", "doppia", "suite"}));
        getContentPane().add(rtComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(677, 421, 263, 34));

        btLabel.setFont(new java.awt.Font("Lucida Grande", 3, 14));
        btLabel.setText("Bed Type");
        getContentPane().add(btLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(677, 483, 263, 27));

        btComboBox.setFont(new java.awt.Font("Lucida Grande", 3, 14));
        btComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"matrimoniale", "singolo", "king-size"}));
        getContentPane().add(btComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(677, 537, 263, 36));

        priceLabel.setFont(new java.awt.Font("Lucida Grande", 3, 14));
        priceLabel.setText("Price");
        getContentPane().add(priceLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(677, 596, 263, 27));

        priceTextField.setFont(new java.awt.Font("Lucida Grande", 3, 14));
        priceTextField.addInputMethodListener(new java.awt.event.InputMethodListener() {
            @Override
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }

            @Override
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                onPriceChanged();
            }
        });
        getContentPane().add(priceTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(677, 643, 252, 34));

        mobileLabel.setFont(new java.awt.Font("Lucida Grande", 3, 14));
        mobileLabel.setText("Mobile");
        getContentPane().add(mobileLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 600, 251, 27));

        mobileTextField.setFont(new java.awt.Font("Lucida Grande", 3, 14));
        mobileTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                mobileTextFieldKeyReleased(evt);
            }
        });
        getContentPane().add(mobileTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 650, 251, 34));

        alloteButton.setBackground(new java.awt.Color(0, 204, 153));
        alloteButton.setFont(new java.awt.Font("Lucida Grande", 3, 14));
        alloteButton.setForeground(new java.awt.Color(153, 0, 0));
        alloteButton.setText("Allote");
        alloteButton.addActionListener(e -> prenota());
        getContentPane().add(alloteButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 940, 142, 40));

        clearButton.setBackground(new java.awt.Color(0, 204, 153));
        clearButton.setFont(new java.awt.Font("Lucida Grande", 3, 14));
        clearButton.setForeground(new java.awt.Color(153, 0, 0));
        clearButton.setText("Clear");
        clearButton.addActionListener(e -> clearForm());
        getContentPane().add(clearButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 940, 128, 40));

        nameLabel.setFont(new java.awt.Font("Lucida Grande", 3, 14));
        nameLabel.setText("Name");
        getContentPane().add(nameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, 261, 27));

        nameTextField.setFont(new java.awt.Font("Lucida Grande", 3, 14));
        getContentPane().add(nameTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 310, 261, 34));

        rnComboBox.setFont(new java.awt.Font("Lucida Grande", 3, 14));
        rnComboBox.addItemListener(evt -> {
            if (evt.getStateChange() != java.awt.event.ItemEvent.SELECTED) {
                return;
            }
            onRnComboChanged();
        });
        getContentPane().add(rnComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(677, 305, 263, 36));

        searchButton.setBackground(new java.awt.Color(0, 204, 153));
        searchButton.setFont(new java.awt.Font(".SF NS Text", 3, 14));
        searchButton.setForeground(new java.awt.Color(153, 0, 0));
        searchButton.setText("Search");
        searchButton.addActionListener(e -> cercaUtente());
        getContentPane().add(searchButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 30, 100, 40));

        bgLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/background.png")));
        getContentPane().add(bgLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1130, 1020));

        pack();
    }

    private void emailTextFieldKeyReleased(java.awt.event.KeyEvent evt) {
        emailTextField.setText(emailTextField.getText().toLowerCase());

        String email = emailTextField.getText();
        if (utility.isValidEmail(email)) {
            emailLabel.setText("Email valida ✅");
            emailLabel.setForeground(Color.GREEN);
        } else {
            emailLabel.setText("Email non valida ❌");
            emailLabel.setForeground(Color.RED);
        }
        if (emailTextField.getText().isEmpty()) {
            emailLabel.setText("Email");
            emailLabel.setForeground(Color.BLACK);
        }
    }

    private void mobileTextFieldKeyReleased(java.awt.event.KeyEvent evt) {
        String mobile = mobileTextField.getText();
        if (mobile.isEmpty()) {
            mobileLabel.setText("Mobile");
            mobileLabel.setForeground(Color.BLACK);
        } else if (utility.validaNumeroTelefono(mobile)) {
            mobileLabel.setText("Mobile valido ✅");
            mobileLabel.setForeground(Color.GREEN);
        } else {
            mobileLabel.setText("Mobile non valido ❌");
            mobileLabel.setForeground(Color.RED);
        }
    }

    // Variables declaration
    private javax.swing.JComboBox<String> addressComboBox;
    private javax.swing.JLabel addressLabel;
    private javax.swing.JButton alloteButton;
    private javax.swing.JLabel bgLabel;
    private javax.swing.JComboBox<String> btComboBox;
    private javax.swing.JLabel btLabel;
    private javax.swing.JLabel cidLabel;
    private javax.swing.JTextField cidTextField;
    private javax.swing.JButton clearButton;
    private javax.swing.JButton closeButton;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JComboBox<String> genderComboBox;
    private javax.swing.JLabel genderLabel;
    private javax.swing.JLabel logoLabel;
    private javax.swing.JLabel mobileLabel;
    private javax.swing.JTextField mobileTextField;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JLabel priceLabel;
    private javax.swing.JTextField priceTextField;
    private javax.swing.JComboBox<String> rnComboBox;
    private javax.swing.JLabel rnLabel;
    private javax.swing.JComboBox<String> rtComboBox;
    private javax.swing.JLabel rtLabel;
    private javax.swing.JButton searchButton;
    private javax.swing.JLabel surnameLabel;
    private javax.swing.JTextField surnameTextField;
}
