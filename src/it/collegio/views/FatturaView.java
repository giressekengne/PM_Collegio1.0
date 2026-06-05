package it.collegio.views;

import it.collegio.controllers.FatturaController;
import it.collegio.dto.FatturaDettaglio;
import it.collegio.enums.FatturaStatus;
import it.collegio.models.MetodoPagamento;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class FatturaView extends javax.swing.JFrame {

    private final FatturaController controller;
    private final int fatturaId;

    public FatturaView(int fatturaId) {
        this.fatturaId = fatturaId;
        this.controller = new FatturaController();
        initComponents();
        loadFattura();
    }

    private void loadFattura() {
        FatturaDettaglio dettaglio = controller.getDettaglio(fatturaId);
        if (dettaglio == null) {
            JOptionPane.showMessageDialog(this, "Fattura non trovata.");
            dispose();
            return;
        }

        idValueLabel.setText("# " + dettaglio.getFatturaId());
        resValueLabel.setText("P0" + String.format("%03d", dettaglio.getReservationId()));
        clienteValueLabel.setText(dettaglio.getClienteNome());
        cameraValueLabel.setText(String.valueOf(dettaglio.getNumeroStanza()));
        importoValueLabel.setText(String.format("€ %.2f", dettaglio.getImporto()));

        if (dettaglio.getDataEmissione() != null) {
            dataValueLabel.setText(dettaglio.getDataEmissione().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        } else {
            dataValueLabel.setText("---");
        }

        FatturaStatus stato = dettaglio.getStato();
        statoValueLabel.setText(stato != null ? stato.getDbValue() : "---");

        boolean fatturaPagabile = stato == FatturaStatus.IN_ATTESA;
        boolean reservationPagabile = isReservationPagabile(dettaglio.getReservationStato());
        boolean pagabile = fatturaPagabile && reservationPagabile;
        pagaButton.setEnabled(pagabile);
        annullaButton.setEnabled(pagabile);
    }

    /** Una fattura e' pagabile solo se la prenotazione e' chiusa (completata o cancellata). */
    private static boolean isReservationPagabile(String reservationStato) {
        return "completata".equalsIgnoreCase(reservationStato)
            || "cancellata".equalsIgnoreCase(reservationStato);
    }

    private void paga() {
        List<MetodoPagamento> metodi = controller.getMetodiPagamento();
        if (metodi.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nessun metodo di pagamento disponibile nel DB.");
            return;
        }

        DefaultComboBoxModel<MetodoPagamento> model = new DefaultComboBoxModel<>();
        for (MetodoPagamento m : metodi) {
            model.addElement(m);
        }

        JComboBox<MetodoPagamento> combo = new JComboBox<>(model);
        int result = JOptionPane.showConfirmDialog(this, combo,
                "Seleziona metodo di pagamento", JOptionPane.OK_CANCEL_OPTION);
        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        MetodoPagamento scelto = (MetodoPagamento) combo.getSelectedItem();
        if (scelto == null) {
            return;
        }

        boolean ok = controller.paga(fatturaId, scelto.getId());
        if (ok) {
            JOptionPane.showMessageDialog(this, "Pagamento registrato con successo!");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Errore durante il pagamento.");
        }
    }

    private void annulla() {
        int confirm = JOptionPane.showConfirmDialog(this,
                "Annullare la fattura? Lo stato diventerà 'non pagato'.",
                "Conferma annullamento", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        if (controller.annulla(fatturaId)) {
            JOptionPane.showMessageDialog(this, "Fattura annullata.");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Errore annullamento fattura.");
        }
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        closeButton = new javax.swing.JButton();
        closeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/close.png")));
        closeButton.addActionListener(e -> dispose());
        getContentPane().add(closeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 0, -1, -1));

        logoLabel = new javax.swing.JLabel("Fattura");
        logoLabel.setFont(new java.awt.Font("Lucida Grande", java.awt.Font.BOLD, 20));
        logoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/manage.png")));
        getContentPane().add(logoLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 250, 50));

        idValueLabel = new javax.swing.JLabel("# ---");
        idValueLabel.setFont(new java.awt.Font("Lucida Grande", java.awt.Font.BOLD, 22));
        getContentPane().add(idValueLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 300, 35));

        javax.swing.JLabel resLabel = new javax.swing.JLabel("Prenotazione:");
        resLabel.setFont(new java.awt.Font("Lucida Grande", java.awt.Font.BOLD, 13));
        getContentPane().add(resLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 145, 150, 28));
        resValueLabel = new javax.swing.JLabel("---");
        resValueLabel.setFont(new java.awt.Font("Lucida Grande", 0, 13));
        getContentPane().add(resValueLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 145, 200, 28));

        javax.swing.JLabel clienteLabel = new javax.swing.JLabel("Cliente:");
        clienteLabel.setFont(new java.awt.Font("Lucida Grande", java.awt.Font.BOLD, 13));
        getContentPane().add(clienteLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 183, 150, 28));
        clienteValueLabel = new javax.swing.JLabel("---");
        clienteValueLabel.setFont(new java.awt.Font("Lucida Grande", 0, 13));
        getContentPane().add(clienteValueLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 183, 300, 28));

        javax.swing.JLabel cameraLabel = new javax.swing.JLabel("Camera:");
        cameraLabel.setFont(new java.awt.Font("Lucida Grande", java.awt.Font.BOLD, 13));
        getContentPane().add(cameraLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 221, 150, 28));
        cameraValueLabel = new javax.swing.JLabel("---");
        cameraValueLabel.setFont(new java.awt.Font("Lucida Grande", 0, 13));
        getContentPane().add(cameraValueLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 221, 200, 28));

        javax.swing.JLabel dataLabel = new javax.swing.JLabel("Data emissione:");
        dataLabel.setFont(new java.awt.Font("Lucida Grande", java.awt.Font.BOLD, 13));
        getContentPane().add(dataLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 259, 150, 28));
        dataValueLabel = new javax.swing.JLabel("---");
        dataValueLabel.setFont(new java.awt.Font("Lucida Grande", 0, 13));
        getContentPane().add(dataValueLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 259, 250, 28));

        javax.swing.JLabel statoLabel = new javax.swing.JLabel("Stato:");
        statoLabel.setFont(new java.awt.Font("Lucida Grande", java.awt.Font.BOLD, 13));
        getContentPane().add(statoLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 297, 150, 28));
        statoValueLabel = new javax.swing.JLabel("---");
        statoValueLabel.setFont(new java.awt.Font("Lucida Grande", java.awt.Font.BOLD, 13));
        getContentPane().add(statoValueLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 297, 200, 28));

        javax.swing.JLabel importoLabel = new javax.swing.JLabel("Totale:");
        importoLabel.setFont(new java.awt.Font("Lucida Grande", java.awt.Font.BOLD, 16));
        getContentPane().add(importoLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 345, 150, 40));
        importoValueLabel = new javax.swing.JLabel("€ 0.00");
        importoValueLabel.setFont(new java.awt.Font("Lucida Grande", java.awt.Font.BOLD, 26));
        importoValueLabel.setForeground(new java.awt.Color(0, 120, 60));
        getContentPane().add(importoValueLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 338, 300, 50));

        javax.swing.JLabel sep = new javax.swing.JLabel();
        sep.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(200, 200, 200)));
        getContentPane().add(sep, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 405, 740, 2));

        pagaButton = new javax.swing.JButton("Paga");
        pagaButton.setBackground(new java.awt.Color(0, 204, 153));
        pagaButton.setFont(new java.awt.Font("Lucida Grande", java.awt.Font.BOLD, 16));
        pagaButton.setForeground(new java.awt.Color(0, 51, 0));
        pagaButton.addActionListener(e -> paga());
        getContentPane().add(pagaButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 425, 170, 50));

        annullaButton = new javax.swing.JButton("Annulla");
        annullaButton.setBackground(new java.awt.Color(204, 0, 0));
        annullaButton.setFont(new java.awt.Font("Lucida Grande", java.awt.Font.BOLD, 16));
        annullaButton.setForeground(java.awt.Color.WHITE);
        annullaButton.addActionListener(e -> annulla());
        getContentPane().add(annullaButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 425, 170, 50));

        bgLabel = new javax.swing.JLabel();
        bgLabel.setBackground(new java.awt.Color(250, 250, 252));
        bgLabel.setOpaque(true);
        getContentPane().add(bgLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 500));

        setSize(800, 500);
        setLocationRelativeTo(null);
    }

    private javax.swing.JButton closeButton;
    private javax.swing.JLabel logoLabel;
    private javax.swing.JLabel idValueLabel;
    private javax.swing.JLabel resValueLabel;
    private javax.swing.JLabel clienteValueLabel;
    private javax.swing.JLabel cameraValueLabel;
    private javax.swing.JLabel importoValueLabel;
    private javax.swing.JLabel dataValueLabel;
    private javax.swing.JLabel statoValueLabel;
    private javax.swing.JButton pagaButton;
    private javax.swing.JButton annullaButton;
    private javax.swing.JLabel bgLabel;
}
