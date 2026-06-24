package it.collegio.views;

import it.collegio.controllers.LoginController;
import it.collegio.controllers.ManageTenantController;
import it.collegio.dto.CommittenteDettaglio;
import it.collegio.utilities.SessionContext;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SelectCommittenteView extends JFrame {

    private final ManageTenantController controller;
    private List<CommittenteDettaglio> committenti;

    private JLabel titleLabel;
    private JLabel welcomeLabel;
    private JLabel comboLabel;
    private JComboBox<String> committenteCombo;
    private JButton accediButton;
    private JButton logoutButton;

    public SelectCommittenteView() {
        this.controller = new ManageTenantController();
        initComponents();
        popolaCombo();
        aggiornaWelcome();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        setTitle("PM Collegio - Selezione Committente");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(540, 300);
        setResizable(false);

        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        main.setBackground(Color.WHITE);

        titleLabel = new JLabel("Admin Sistema — Selezione Committente");
        titleLabel.setFont(new Font("Lucida Grande", Font.BOLD, 15));
        titleLabel.setForeground(new Color(0, 153, 102));
        titleLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        welcomeLabel = new JLabel(" ");
        welcomeLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
        welcomeLabel.setForeground(Color.DARK_GRAY);
        welcomeLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        comboLabel = new JLabel("Committente:");
        comboLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
        comboLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);

        committenteCombo = new JComboBox<>();
        committenteCombo.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
        committenteCombo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 34));
        committenteCombo.setAlignmentX(JLabel.LEFT_ALIGNMENT);

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonsPanel.setBackground(Color.WHITE);
        buttonsPanel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        accediButton = new JButton("Accedi");
        accediButton.setBackground(new Color(0, 204, 153));
        accediButton.setForeground(new Color(153, 0, 0));
        accediButton.setFont(new Font("Lucida Grande", Font.BOLD, 13));
        accediButton.setPreferredSize(new Dimension(110, 34));
        accediButton.addActionListener(e -> accediButtonActionPerformed());

        logoutButton = new JButton("Logout");
        logoutButton.setBackground(new Color(210, 210, 210));
        logoutButton.setForeground(Color.DARK_GRAY);
        logoutButton.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
        logoutButton.setPreferredSize(new Dimension(100, 34));
        logoutButton.addActionListener(e -> logoutButtonActionPerformed());

        buttonsPanel.add(accediButton);
        buttonsPanel.add(logoutButton);

        main.add(titleLabel);
        main.add(Box.createVerticalStrut(8));
        main.add(welcomeLabel);
        main.add(Box.createVerticalStrut(20));
        main.add(comboLabel);
        main.add(Box.createVerticalStrut(6));
        main.add(committenteCombo);
        main.add(Box.createVerticalStrut(28));
        main.add(buttonsPanel);

        setContentPane(main);
    }

    private void popolaCombo() {
        committenti = controller.getDettagli();
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (CommittenteDettaglio c : committenti) {
            model.addElement(c.getRagioneSociale() + " (ID: " + c.getCodCommittente() + ")");
        }
        committenteCombo.setModel(model);
    }

    private void aggiornaWelcome() {
        String nome = SessionContext.nome != null ? SessionContext.nome : "Admin Sistema";
        welcomeLabel.setText("Benvenuto " + nome + ". Scegli il committente per questa sessione.");
    }

    private void accediButtonActionPerformed() {
        int idx = committenteCombo.getSelectedIndex();
        if (idx < 0 || committenti == null || committenti.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nessun committente disponibile.", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }
        controller.selezionaCommittente(committenti.get(idx).getCodCommittente());
        new HomeView().setVisible(true);
        this.dispose();
    }

    private void logoutButtonActionPerformed() {
        int confirm = JOptionPane.showConfirmDialog(this, "Vuoi davvero uscire?", "Logout", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            new LoginController().registraLogout();
            new LoginView().setVisible(true);
            this.dispose();
        }
    }
}
