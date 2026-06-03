package it.collegio.views;

import it.collegio.dao.AccessLogDao;
import it.collegio.dto.AccessLogDettaglio;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class LogsView extends javax.swing.JFrame {

    private final AccessLogDao accessLogDao;
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public LogsView() {
        this.accessLogDao = new AccessLogDao();
        initComponents();
        loadLogs();
    }

    private void loadLogs() {
        DefaultTableModel model = (DefaultTableModel) logTable.getModel();
        model.setRowCount(0);

        List<AccessLogDettaglio> dettagli = accessLogDao.getDettagli();
        for (AccessLogDettaglio d : dettagli) {
            model.addRow(new Object[]{
                d.getUserNome(),
                d.getLoginTime() != null ? d.getLoginTime().format(FMT) : "",
                d.getLogoutTime() != null ? d.getLogoutTime().format(FMT) : "",
                d.getIpAddress(),
                d.getRoleNome()
            });
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        logoLabel = new javax.swing.JLabel();
        closeButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        logTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        logoLabel.setFont(new java.awt.Font(".SF NS Text", 3, 14));
        logoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/manage.png")));
        logoLabel.setText("Access Log");
        getContentPane().add(logoLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 210, 60));

        closeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/close.png")));
        closeButton.addActionListener(e -> dispose());
        getContentPane().add(closeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 0, -1, -1));

        logTable.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"User", "Login", "Logout", "IP Address", "Ruolo"}
        ) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        });
        jScrollPane1.setViewportView(logTable);
        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 1030, 590));

        pack();
    }

    private javax.swing.JButton closeButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable logTable;
    private javax.swing.JLabel logoLabel;
}
