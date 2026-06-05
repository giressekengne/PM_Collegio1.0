package it.collegio.views;

import it.collegio.controllers.FatturaController;
import it.collegio.dto.FatturaDettaglio;
import it.collegio.models.MetodoPagamento;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class GestioneFattureView extends javax.swing.JFrame {

    private final FatturaController controller;
    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /** Lista corrente delle fatture mostrate in tabella, per leggere lo stato della reservation di ogni riga. */
    private List<FatturaDettaglio> dettagliCorrenti = java.util.Collections.emptyList();

    public GestioneFattureView() {
        this.controller = new FatturaController();
        initComponents();
        loadTable();

        fattureTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aggiornaBottoniPerRiga();
            }
        });
        pagaButton.setEnabled(false);
        annullaButton.setEnabled(false);
    }

    private void loadTable() {
        DefaultTableModel model = (DefaultTableModel) fattureTable.getModel();
        model.setRowCount(0);

        dettagliCorrenti = controller.getDettagliPerRuolo();
        for (FatturaDettaglio d : dettagliCorrenti) {
            String dataStr = d.getDataEmissione() != null ? d.getDataEmissione().format(DATE_FMT) : "";
            String statoStr = d.getStato() != null ? d.getStato().getDbValue() : "";
            String resStato = d.getReservationStato() != null ? d.getReservationStato() : "";

            model.addRow(new Object[]{
                d.getFatturaId(),
                "P0" + String.format("%03d", d.getReservationId()),
                d.getClienteNome(),
                d.getNumeroStanza(),
                String.format("€ %.2f", d.getImporto()),
                dataStr,
                statoStr,
                resStato
            });
        }
    }

    private void aggiornaBottoniPerRiga() {
        int row = fattureTable.getSelectedRow();
        if (row < 0 || row >= dettagliCorrenti.size()) {
            pagaButton.setEnabled(false);
            annullaButton.setEnabled(false);
            return;
        }
        FatturaDettaglio d = dettagliCorrenti.get(row);
        boolean fatturaPagabile = controller.isPagabile(d.getStato());
        boolean reservationPagabile = isReservationPagabile(d.getReservationStato());
        boolean pagabile = fatturaPagabile && reservationPagabile;
        pagaButton.setEnabled(pagabile);
        annullaButton.setEnabled(pagabile);
    }

    /** Una fattura e' pagabile solo se la prenotazione e' chiusa (completata o cancellata). */
    private static boolean isReservationPagabile(String reservationStato) {
        return "completata".equalsIgnoreCase(reservationStato)
            || "cancellata".equalsIgnoreCase(reservationStato);
    }

    private int getSelectedFatturaId() {
        int row = fattureTable.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Seleziona prima una fattura dalla tabella.");
            return -1;
        }
        return (int) ((DefaultTableModel) fattureTable.getModel()).getValueAt(row, 0);
    }

    private void pagaFattura() {
        int fatturaId = getSelectedFatturaId();
        if (fatturaId < 0) {
            return;
        }

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
            loadTable();
            pagaButton.setEnabled(false);
            annullaButton.setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(this, "Errore durante il pagamento.");
        }
    }

    private void annullaFattura() {
        int fatturaId = getSelectedFatturaId();
        if (fatturaId < 0) {
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Annullare la fattura #" + fatturaId + "? Lo stato diventerà 'non pagato'.",
                "Conferma annullamento", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        if (controller.annulla(fatturaId)) {
            JOptionPane.showMessageDialog(this, "Fattura annullata.");
            loadTable();
            pagaButton.setEnabled(false);
            annullaButton.setEnabled(false);
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
        getContentPane().add(closeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 0, -1, -1));

        logoLabel = new javax.swing.JLabel("Gestione Fatture");
        logoLabel.setFont(new java.awt.Font("Lucida Grande", java.awt.Font.BOLD, 18));
        logoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/manage.png")));
        getContentPane().add(logoLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 300, 50));

        fattureTable = new javax.swing.JTable();
        fattureTable.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID", "Prenotazione", "Cliente", "Camera", "Importo", "Data", "Stato", "Stato Prenot."}
        ) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        });
        javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(fattureTable);
        getContentPane().add(scrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 1040, 420));

        pagaButton = new javax.swing.JButton("Paga");
        pagaButton.setBackground(new java.awt.Color(0, 204, 153));
        pagaButton.setFont(new java.awt.Font("Lucida Grande", java.awt.Font.BOLD, 15));
        pagaButton.setForeground(new java.awt.Color(0, 51, 0));
        pagaButton.addActionListener(e -> pagaFattura());
        getContentPane().add(pagaButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 530, 160, 45));

        annullaButton = new javax.swing.JButton("Annulla");
        annullaButton.setBackground(new java.awt.Color(204, 0, 0));
        annullaButton.setFont(new java.awt.Font("Lucida Grande", java.awt.Font.BOLD, 15));
        annullaButton.setForeground(java.awt.Color.WHITE);
        annullaButton.addActionListener(e -> annullaFattura());
        getContentPane().add(annullaButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 530, 160, 45));

        bgLabel = new javax.swing.JLabel();
        bgLabel.setBackground(new java.awt.Color(250, 250, 252));
        bgLabel.setOpaque(true);
        getContentPane().add(bgLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, 600));

        setSize(1100, 600);
        setLocationRelativeTo(null);
    }

    private javax.swing.JButton closeButton;
    private javax.swing.JLabel logoLabel;
    private javax.swing.JTable fattureTable;
    private javax.swing.JButton pagaButton;
    private javax.swing.JButton annullaButton;
    private javax.swing.JLabel bgLabel;
}
