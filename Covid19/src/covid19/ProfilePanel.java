/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package covid19;

import covid19.AccountFrame.ChangePasswordFrame;
import covid19.AccountFrame.SignInFrame;
import Profile.*;
import javax.swing.JOptionPane;
import Account.AccountBank;
import Payment.*;

/**
 *
 * @author PC
 */
public class ProfilePanel extends javax.swing.JPanel {

    /**
     * Creates new form ProfilePanel
     */
    private String id = "1";
    public ProfilePanel() {
        initComponents();
        
 
        Profile resultProfile = ProfileInf.getProfile(id);
        txfCCCD.setText(resultProfile.getID());
        txfFullname.setText(resultProfile.getFullName());
        txfAddress.setText(resultProfile.getAddress());
        txfStatus.setText(resultProfile.getStatus());
        txfYob.setText(Integer.toString(resultProfile.getYoB()));
        txfTreatment.setText(resultProfile.getTreatment());
        txfDebt.setText(Double.toString(resultProfile.getDebt()));
        
        txfCCCD.setEditable(false);
        txfTreatment.setEditable(false);
        txfStatus.setEditable(false);
        txfDebt.setEditable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitle = new javax.swing.JLabel();
        pnlLeft = new javax.swing.JPanel();
        pnlButton = new javax.swing.JPanel();
        btnRefresh = new javax.swing.JButton();
        btnBuyPackage = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnChangePw = new javax.swing.JButton();
        pnlInformation = new javax.swing.JPanel();
        lblCccd = new javax.swing.JLabel();
        lblFullname = new javax.swing.JLabel();
        lblYob = new javax.swing.JLabel();
        txfCCCD = new javax.swing.JTextField();
        txfFullname = new javax.swing.JTextField();
        txfYob = new javax.swing.JTextField();
        lblAddress = new javax.swing.JLabel();
        txfAddress = new javax.swing.JTextField();
        txfStatus = new javax.swing.JTextField();
        lblStatus = new javax.swing.JLabel();
        lblTreatment = new javax.swing.JLabel();
        txfTreatment = new javax.swing.JTextField();
        lblCccd1 = new javax.swing.JLabel();
        txfDebt = new javax.swing.JTextField();
        lblSubtittleLeft = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        pnlMHistory = new javax.swing.JPanel();
        scrollpnlMH = new javax.swing.JScrollPane();
        tblMHistory = new javax.swing.JTable();
        lblMHistory = new javax.swing.JLabel();
        pnlMPackage = new javax.swing.JPanel();
        scrollpnlMP = new javax.swing.JScrollPane();
        tblMPackage = new javax.swing.JTable();
        lblMPackage = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(988, 529));

        lblTitle.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/profile-48.png"))); // NOI18N
        lblTitle.setText("Personal Information");
        lblTitle.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblTitle.setPreferredSize(new java.awt.Dimension(968, 71));

        pnlLeft.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlLeft.setMaximumSize(null);

        pnlButton.setPreferredSize(new java.awt.Dimension(396, 99));

        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/refresh.png"))); // NOI18N
        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        btnBuyPackage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/payment.png"))); // NOI18N
        btnBuyPackage.setText("Payment");
        btnBuyPackage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuyPackageActionPerformed(evt);
            }
        });

        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/edit.png"))); // NOI18N
        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnChangePw.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/changepassword.png"))); // NOI18N
        btnChangePw.setText("Change password");
        btnChangePw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangePwActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlButtonLayout = new javax.swing.GroupLayout(pnlButton);
        pnlButton.setLayout(pnlButtonLayout);
        pnlButtonLayout.setHorizontalGroup(
            pnlButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlButtonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlButtonLayout.createSequentialGroup()
                        .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnChangePw, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(14, 14, 14)
                .addComponent(btnBuyPackage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlButtonLayout.setVerticalGroup(
            pnlButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlButtonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnBuyPackage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlButtonLayout.createSequentialGroup()
                        .addGroup(pnlButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                            .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnChangePw, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pnlInformation.setPreferredSize(new java.awt.Dimension(396, 247));

        lblCccd.setText("CCCD:");
        lblCccd.setPreferredSize(new java.awt.Dimension(100, 27));

        lblFullname.setText("Full Name:");
        lblFullname.setPreferredSize(new java.awt.Dimension(100, 27));

        lblYob.setText("Year of birth:");
        lblYob.setPreferredSize(new java.awt.Dimension(100, 27));

        txfCCCD.setText("jTextField1");
        txfCCCD.setPreferredSize(new java.awt.Dimension(304, 27));
        txfCCCD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfCCCDActionPerformed(evt);
            }
        });

        txfFullname.setText("jTextField1");
        txfFullname.setPreferredSize(new java.awt.Dimension(304, 27));
        txfFullname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfFullnameActionPerformed(evt);
            }
        });

        txfYob.setText("jTextField1");
        txfYob.setPreferredSize(new java.awt.Dimension(304, 27));
        txfYob.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfYobActionPerformed(evt);
            }
        });

        lblAddress.setText("Address:");
        lblAddress.setPreferredSize(new java.awt.Dimension(100, 27));

        txfAddress.setText("jTextField1");
        txfAddress.setPreferredSize(new java.awt.Dimension(304, 27));

        txfStatus.setText("jTextField1");
        txfStatus.setPreferredSize(new java.awt.Dimension(304, 27));

        lblStatus.setText("Status:");
        lblStatus.setPreferredSize(new java.awt.Dimension(100, 27));

        lblTreatment.setText("TreatMent:");
        lblTreatment.setPreferredSize(new java.awt.Dimension(100, 27));

        txfTreatment.setText("jTextField1");
        txfTreatment.setPreferredSize(new java.awt.Dimension(304, 27));

        lblCccd1.setText("Debt:");
        lblCccd1.setPreferredSize(new java.awt.Dimension(100, 27));

        txfDebt.setText("jTextField1");
        txfDebt.setPreferredSize(new java.awt.Dimension(304, 27));

        javax.swing.GroupLayout pnlInformationLayout = new javax.swing.GroupLayout(pnlInformation);
        pnlInformation.setLayout(pnlInformationLayout);
        pnlInformationLayout.setHorizontalGroup(
            pnlInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInformationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTreatment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblAddress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCccd1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblYob, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCccd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblFullname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txfCCCD, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(txfFullname, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(txfAddress, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                    .addComponent(txfYob, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                    .addComponent(txfStatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                    .addComponent(txfTreatment, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                    .addComponent(txfDebt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlInformationLayout.setVerticalGroup(
            pnlInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInformationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCccd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txfCCCD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblFullname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txfFullname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblYob, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txfYob, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAddress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txfAddress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txfStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTreatment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txfTreatment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCccd1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txfDebt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        lblSubtittleLeft.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblSubtittleLeft.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSubtittleLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/about.png"))); // NOI18N
        lblSubtittleLeft.setText("Information:");
        lblSubtittleLeft.setPreferredSize(new java.awt.Dimension(396, 36));

        javax.swing.GroupLayout pnlLeftLayout = new javax.swing.GroupLayout(pnlLeft);
        pnlLeft.setLayout(pnlLeftLayout);
        pnlLeftLayout.setHorizontalGroup(
            pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLeftLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblSubtittleLeft, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlInformation, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlLeftLayout.setVerticalGroup(
            pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLeftLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSubtittleLeft, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlInformation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.setPreferredSize(new java.awt.Dimension(542, 425));

        pnlMHistory.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlMHistory.setPreferredSize(new java.awt.Dimension(522, 198));

        scrollpnlMH.setPreferredSize(new java.awt.Dimension(518, 135));

        tblMHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "From Status", "To Status", "Date"
            }
        ));
        tblMHistory.setMaximumSize(null);
        tblMHistory.setMinimumSize(null);
        tblMHistory.setPreferredSize(new java.awt.Dimension(522, 135));
        scrollpnlMH.setViewportView(tblMHistory);

        lblMHistory.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblMHistory.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMHistory.setText("Management history:");
        lblMHistory.setPreferredSize(new java.awt.Dimension(498, 36));

        javax.swing.GroupLayout pnlMHistoryLayout = new javax.swing.GroupLayout(pnlMHistory);
        pnlMHistory.setLayout(pnlMHistoryLayout);
        pnlMHistoryLayout.setHorizontalGroup(
            pnlMHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMHistoryLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMHistory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrollpnlMH, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlMHistoryLayout.setVerticalGroup(
            pnlMHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMHistoryLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollpnlMH, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlMPackage.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlMPackage.setPreferredSize(new java.awt.Dimension(522, 198));

        tblMPackage.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Package ID", "Package Name", "Date Register", "Quantity", "Price", "Total"
            }
        ));
        tblMPackage.setPreferredSize(new java.awt.Dimension(518, 138));
        scrollpnlMP.setViewportView(tblMPackage);
        if (tblMPackage.getColumnModel().getColumnCount() > 0) {
            tblMPackage.getColumnModel().getColumn(1).setPreferredWidth(140);
        }

        lblMPackage.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblMPackage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMPackage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/package.png"))); // NOI18N
        lblMPackage.setText("Consumption history of essential packages:");
        lblMPackage.setMaximumSize(null);
        lblMPackage.setMinimumSize(null);
        lblMPackage.setPreferredSize(new java.awt.Dimension(518, 38));

        javax.swing.GroupLayout pnlMPackageLayout = new javax.swing.GroupLayout(pnlMPackage);
        pnlMPackage.setLayout(pnlMPackageLayout);
        pnlMPackageLayout.setHorizontalGroup(
            pnlMPackageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMPackageLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMPackageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scrollpnlMP)
                    .addComponent(lblMPackage, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlMPackageLayout.setVerticalGroup(
            pnlMPackageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMPackageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMPackage, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollpnlMP, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlMPackage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlMHistory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlMHistory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(7, 7, 7)
                .addComponent(pnlMPackage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlLeft, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlLeft, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txfFullnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txfFullnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txfFullnameActionPerformed

    private void txfYobActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txfYobActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txfYobActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        int result = JOptionPane.showConfirmDialog(pnlInformation, "Do you want to save the changes", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(result == JOptionPane.YES_OPTION){
            if(ProfileInf.updateProfile(txfCCCD.getText(), txfFullname.getText(), txfAddress.getText(), Integer.parseInt(txfYob.getText())))
                JOptionPane.showMessageDialog(pnlInformation, "All changes have been saved", "Notification", JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(pnlInformation, "Error occured", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else {
            Profile resultProfile = ProfileInf.getProfile(id);
            txfFullname.setText(resultProfile.getFullName());
            txfAddress.setText(resultProfile.getAddress());
            txfYob.setText(Integer.toString(resultProfile.getYoB()));
        } 
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnBuyPackageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuyPackageActionPerformed
        //new SignInFrame().setVisible(true);
        AccountBank accBank = BankInf.getAccountBank(id);
        //System.out.println(accBank.getDatePublished());
        AccountBankFrame accountBankFrame = new AccountBankFrame(accBank);
        accountBankFrame.setTitle("Payment system");
        accountBankFrame.setVisible(true);
        //this.setEnabled(false);
    }//GEN-LAST:event_btnBuyPackageActionPerformed

    private void btnChangePwActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangePwActionPerformed
        ChangePasswordFrame changePasswordFrame = new ChangePasswordFrame();
        changePasswordFrame.setTitle("Change password");
        changePasswordFrame.setVisible(true);
    }//GEN-LAST:event_btnChangePwActionPerformed

    private void txfCCCDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txfCCCDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txfCCCDActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuyPackage;
    private javax.swing.JButton btnChangePw;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblAddress;
    private javax.swing.JLabel lblCccd;
    private javax.swing.JLabel lblCccd1;
    private javax.swing.JLabel lblFullname;
    private javax.swing.JLabel lblMHistory;
    private javax.swing.JLabel lblMPackage;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblSubtittleLeft;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTreatment;
    private javax.swing.JLabel lblYob;
    private javax.swing.JPanel pnlButton;
    private javax.swing.JPanel pnlInformation;
    private javax.swing.JPanel pnlLeft;
    private javax.swing.JPanel pnlMHistory;
    private javax.swing.JPanel pnlMPackage;
    private javax.swing.JScrollPane scrollpnlMH;
    private javax.swing.JScrollPane scrollpnlMP;
    private javax.swing.JTable tblMHistory;
    private javax.swing.JTable tblMPackage;
    private javax.swing.JTextField txfAddress;
    private javax.swing.JTextField txfCCCD;
    private javax.swing.JTextField txfDebt;
    private javax.swing.JTextField txfFullname;
    private javax.swing.JTextField txfStatus;
    private javax.swing.JTextField txfTreatment;
    private javax.swing.JTextField txfYob;
    // End of variables declaration//GEN-END:variables
}
