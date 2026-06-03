package it.collegio.views;

import it.collegio.controllers.LoginController;
import it.collegio.utilities.SessionContext;
import javax.swing.JOptionPane;

public class HomeView extends javax.swing.JFrame {

    private javax.swing.JButton logsButton;
    private javax.swing.JButton manageTenantButton;
    private javax.swing.JLabel  userIndicatorLabel;

    private final LoginController loginController = new LoginController();

    public HomeView(){
        initComponents();

        logsButton = new javax.swing.JButton();
        logsButton.setBackground(new java.awt.Color(0, 204, 153));
        logsButton.setFont(new java.awt.Font("Lucida Grande", java.awt.Font.BOLD, 14));
        logsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/manage.png")));
        logsButton.setText("Access Logs");
        logsButton.addActionListener(e -> new LogsView().setVisible(true));
        getContentPane().add(logsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1360, 740, 250, 80));
        getContentPane().setComponentZOrder(logsButton, 0);

        manageTenantButton = new javax.swing.JButton();
        manageTenantButton.setBackground(new java.awt.Color(0, 204, 153));
        manageTenantButton.setFont(new java.awt.Font("Lucida Grande", java.awt.Font.BOLD, 14));
        manageTenantButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/manage.png")));
        manageTenantButton.setText("ManageTenant");
        manageTenantButton.addActionListener(e -> new ManageTenantView().setVisible(true));
        getContentPane().add(manageTenantButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1360, 395, 250, 80));
        getContentPane().setComponentZOrder(manageTenantButton, 0);

        configureButtonsForRole();

        userIndicatorLabel = new javax.swing.JLabel();
        String displayName = (SessionContext.nome != null ? SessionContext.nome : "") +
                             " " +
                             (SessionContext.cognome != null ? SessionContext.cognome : "");
        userIndicatorLabel.setText(displayName.trim());
        userIndicatorLabel.setFont(new java.awt.Font("Lucida Grande", java.awt.Font.BOLD, 13));
        userIndicatorLabel.setForeground(java.awt.Color.WHITE);
        userIndicatorLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/manage.png")));
        userIndicatorLabel.setIconTextGap(8);
        getContentPane().add(userIndicatorLabel,
            new org.netbeans.lib.awtextra.AbsoluteConstraints(1360, 8, 260, 35));
        getContentPane().setComponentZOrder(userIndicatorLabel, 0);
    }

    private void configureButtonsForRole() {
        String rt = SessionContext.roleType != null ? SessionContext.roleType : "";
        boolean isAS = "AS".equalsIgnoreCase(rt);
        boolean isAC = "AC".equalsIgnoreCase(rt);

        // ManageUser: tutti i ruoli (U vede solo la propria utenza)
        muserButton.setVisible(true);
        // ManageRiservation: tutti i ruoli (U vede solo le proprie prenotazioni)
        mriservationButton.setVisible(true);
        // ManageRoom: AS, AC
        mroomButton.setVisible(isAS || isAC);
        // ManageTenant: solo AS
        manageTenantButton.setVisible(isAS);
        // CheckIn, CheckOut: tutti (come v1)
        checkinButton.setVisible(true);
        checkoutButton.setVisible(true);
        // Access Logs: AS, AC
        logsButton.setVisible(isAS || isAC);
    }



    @SuppressWarnings("unchecked")                         
    private void initComponents() {

        closeButton = new javax.swing.JButton();
        mriservationButton = new javax.swing.JButton();
        mroomButton = new javax.swing.JButton();
        checkinButton = new javax.swing.JButton();
        checkoutButton = new javax.swing.JButton();
        logoutButton = new javax.swing.JButton();
        muserButton = new javax.swing.JButton();
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
        getContentPane().add(closeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1630, 0, 50, 40));

        mriservationButton.setBackground(new java.awt.Color(0, 204, 153));
        mriservationButton.setFont(new java.awt.Font("Lucida Grande", 3, 14)); // NOI18N
        mriservationButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/manage.png"))); // NOI18N
        mriservationButton.setText("ManageRiservation");
        mriservationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mriservationButtonActionPerformed(evt);
            }
        });
        getContentPane().add(mriservationButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1360, 165, 250, 80));

        mroomButton.setBackground(new java.awt.Color(0, 204, 153));
        mroomButton.setFont(new java.awt.Font(".AppleSystemUIFont", 3, 14)); // NOI18N
        mroomButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/manage.png"))); // NOI18N
        mroomButton.setText("ManageRoom");
        mroomButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mroomButtonActionPerformed(evt);
            }
        });
        getContentPane().add(mroomButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1360, 280, 250, 80));

        checkinButton.setBackground(new java.awt.Color(0, 204, 153));
        checkinButton.setFont(new java.awt.Font(".AppleSystemUIFont", 3, 14)); // NOI18N
        checkinButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Customer Registration & Check IN.png"))); // NOI18N
        checkinButton.setText("CheckIn");
        checkinButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkinButtonActionPerformed(evt);
            }
        });
        getContentPane().add(checkinButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1360, 510, 250, 80));

        checkoutButton.setBackground(new java.awt.Color(0, 204, 153));
        checkoutButton.setFont(new java.awt.Font(".AppleSystemUIFont", 3, 14)); // NOI18N
        checkoutButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/out.jpg"))); // NOI18N
        checkoutButton.setText("CheckOut");
        checkoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkoutButtonActionPerformed(evt);
            }
        });
        getContentPane().add(checkoutButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1360, 625, 250, 80));

        logoutButton.setBackground(new java.awt.Color(255, 0, 0));
        logoutButton.setFont(new java.awt.Font(".AppleSystemUIFont", 3, 14)); // NOI18N
        logoutButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/log out.jpg"))); // NOI18N
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });
        getContentPane().add(logoutButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1360, 855, 250, 80));

        muserButton.setBackground(new java.awt.Color(0, 204, 153));
        muserButton.setFont(new java.awt.Font(".SF NS Text", 3, 14)); // NOI18N
        muserButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/manage.png"))); // NOI18N
        muserButton.setText("ManageUser");
        muserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muserButtonActionPerformed(evt);
            }
        });
        getContentPane().add(muserButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1360, 50, 250, 80));

        bgLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/home.jpg"))); // NOI18N
        getContentPane().add(bgLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 1630, 1060));

        pack();
    }                       

    private void mroomButtonActionPerformed(java.awt.event.ActionEvent evt) {
        new ManageRoomView().setVisible(true);
    }

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {
        int yes = JOptionPane.showConfirmDialog(this, "Confirm ?", "Exit",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (yes == JOptionPane.YES_OPTION) {
            loginController.registraLogout();
            new LoginView().setVisible(true);
            this.dispose();
        }
    }

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {
        int yes = JOptionPane.showConfirmDialog(this, "Confirm ?", "Exit",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (yes == JOptionPane.YES_OPTION) {
            loginController.registraLogout();
            System.exit(0);
        }
    }

    private void muserButtonActionPerformed(java.awt.event.ActionEvent evt) {
        new ManageUserView().setVisible(true);
    }

    private void mriservationButtonActionPerformed(java.awt.event.ActionEvent evt) {
        new ManageRiservationView().setVisible(true);
    }

    private void checkinButtonActionPerformed(java.awt.event.ActionEvent evt) {
        new CheckInView().setVisible(true);
    }

    private void checkoutButtonActionPerformed(java.awt.event.ActionEvent evt) {
        new CheckOutView().setVisible(true);
    }

   
    public static void main(String args[]) {
        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JLabel bgLabel;
    private javax.swing.JButton checkinButton;
    private javax.swing.JButton checkoutButton;
    private javax.swing.JButton closeButton;
    private javax.swing.JButton logoutButton;
    private javax.swing.JButton mriservationButton;
    private javax.swing.JButton mroomButton;
    private javax.swing.JButton muserButton;
    // End of variables declaration   
}
