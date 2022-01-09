/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package covid19;

import Helper.DateFormatter;
import Helper.MessageDialog;
import Helper.Validator;
import Packages.*;
import java.time.LocalDate;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC
 */
public class PackageManagementPanel extends javax.swing.JPanel {

    private StringBuilder sb;

    /**
     * Creates new form PackageManagementPanel
     */
    public PackageManagementPanel() {
        initComponents();
        sb = new StringBuilder();
        displayDataTable();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTittle = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        pnlListPackage = new javax.swing.JPanel();
        scrollpnlPackage = new javax.swing.JScrollPane();
        tblPackage = new javax.swing.JTable();
        lblSubtiitleRight = new javax.swing.JLabel();
        pnlInfo = new javax.swing.JPanel();
        pnlInformation = new javax.swing.JPanel();
        pntSubtitleLeft = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        lblLnumber = new javax.swing.JLabel();
        lblPrice = new javax.swing.JLabel();
        lblExpiration = new javax.swing.JLabel();
        lblQuantity = new javax.swing.JLabel();
        txfId = new javax.swing.JTextField();
        txfName = new javax.swing.JTextField();
        txfNumber = new javax.swing.JTextField();
        txfExpiration = new javax.swing.JTextField();
        txfPrice = new javax.swing.JTextField();
        txfQuantity = new javax.swing.JTextField();
        pnlButton = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        btnSDebt = new javax.swing.JButton();
        btnSPackage = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(988, 529));

        pnlTittle.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlTittle.setPreferredSize(new java.awt.Dimension(968, 70));

        lblTitle.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/package-48.png"))); // NOI18N
        lblTitle.setText("Package Management");
        lblTitle.setMaximumSize(new java.awt.Dimension(944, 48));
        lblTitle.setPreferredSize(new java.awt.Dimension(944, 48));

        javax.swing.GroupLayout pnlTittleLayout = new javax.swing.GroupLayout(pnlTittle);
        pnlTittle.setLayout(pnlTittleLayout);
        pnlTittleLayout.setHorizontalGroup(
            pnlTittleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTittleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlTittleLayout.setVerticalGroup(
            pnlTittleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTittleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlListPackage.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlListPackage.setPreferredSize(new java.awt.Dimension(618, 431));

        scrollpnlPackage.setPreferredSize(new java.awt.Dimension(592, 364));

        tblPackage.setAutoCreateRowSorter(true);
        tblPackage.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Limited Number", "Expiration", "Price", "Quantity"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Float.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPackage.setPreferredSize(new java.awt.Dimension(596, 366));
        tblPackage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPackageMouseClicked(evt);
            }
        });
        scrollpnlPackage.setViewportView(tblPackage);
        if (tblPackage.getColumnModel().getColumnCount() > 0) {
            tblPackage.getColumnModel().getColumn(0).setPreferredWidth(40);
            tblPackage.getColumnModel().getColumn(1).setPreferredWidth(200);
            tblPackage.getColumnModel().getColumn(2).setPreferredWidth(100);
            tblPackage.getColumnModel().getColumn(3).setPreferredWidth(100);
            tblPackage.getColumnModel().getColumn(4).setPreferredWidth(60);
        }

        lblSubtiitleRight.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblSubtiitleRight.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSubtiitleRight.setText("List of Packages");
        lblSubtiitleRight.setPreferredSize(new java.awt.Dimension(596, 19));

        javax.swing.GroupLayout pnlListPackageLayout = new javax.swing.GroupLayout(pnlListPackage);
        pnlListPackage.setLayout(pnlListPackageLayout);
        pnlListPackageLayout.setHorizontalGroup(
            pnlListPackageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlListPackageLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlListPackageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSubtiitleRight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrollpnlPackage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlListPackageLayout.setVerticalGroup(
            pnlListPackageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlListPackageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSubtiitleRight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollpnlPackage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlInfo.setPreferredSize(new java.awt.Dimension(342, 431));

        pnlInformation.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlInformation.setPreferredSize(new java.awt.Dimension(322, 253));

        pntSubtitleLeft.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        pntSubtitleLeft.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pntSubtitleLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/about.png"))); // NOI18N
        pntSubtitleLeft.setText("Package Information");

        lblName.setText("Package Name:");

        lblId.setText("Package ID:");

        lblLnumber.setText("Limited Number:");

        lblPrice.setText("Price:");

        lblExpiration.setText("Expiration:");

        lblQuantity.setText("Quantity:");

        txfId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfIdActionPerformed(evt);
            }
        });

        txfNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfNumberActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlInformationLayout = new javax.swing.GroupLayout(pnlInformation);
        pnlInformation.setLayout(pnlInformationLayout);
        pnlInformationLayout.setHorizontalGroup(
            pnlInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInformationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pntSubtitleLeft, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlInformationLayout.createSequentialGroup()
                        .addGroup(pnlInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblLnumber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblExpiration, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblPrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblQuantity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txfId)
                            .addComponent(txfName)
                            .addComponent(txfNumber)
                            .addComponent(txfExpiration)
                            .addComponent(txfPrice)
                            .addComponent(txfQuantity))))
                .addContainerGap())
        );
        pnlInformationLayout.setVerticalGroup(
            pnlInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInformationLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pntSubtitleLeft)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txfId))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txfName)
                    .addComponent(lblName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txfNumber)
                    .addComponent(lblLnumber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblExpiration, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txfExpiration))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txfPrice))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblQuantity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txfQuantity))
                .addContainerGap())
        );

        pnlButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnAdd.setBackground(new java.awt.Color(204, 204, 255));
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/add.png"))); // NOI18N
        btnAdd.setText("Add");
        btnAdd.setMaximumSize(new java.awt.Dimension(120, 35));
        btnAdd.setPreferredSize(new java.awt.Dimension(100, 35));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnEdit.setBackground(new java.awt.Color(204, 204, 255));
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/edit.png"))); // NOI18N
        btnEdit.setText("Edit");
        btnEdit.setMaximumSize(new java.awt.Dimension(100, 35));
        btnEdit.setPreferredSize(new java.awt.Dimension(100, 35));
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(204, 204, 255));
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/sub.png"))); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.setMaximumSize(new java.awt.Dimension(100, 35));
        btnDelete.setPreferredSize(new java.awt.Dimension(100, 35));
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnRefresh.setBackground(new java.awt.Color(204, 204, 255));
        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/refresh.png"))); // NOI18N
        btnRefresh.setText("Refresh");
        btnRefresh.setMaximumSize(new java.awt.Dimension(100, 35));
        btnRefresh.setPreferredSize(new java.awt.Dimension(100, 35));
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        btnSDebt.setBackground(new java.awt.Color(204, 204, 255));
        btnSDebt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/dollar.png"))); // NOI18N
        btnSDebt.setText("Statictis Debt");
        btnSDebt.setMaximumSize(new java.awt.Dimension(100, 35));
        btnSDebt.setPreferredSize(new java.awt.Dimension(100, 35));
        btnSDebt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSDebtActionPerformed(evt);
            }
        });

        btnSPackage.setBackground(new java.awt.Color(204, 204, 255));
        btnSPackage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/package.png"))); // NOI18N
        btnSPackage.setText("Statictis Package");
        btnSPackage.setMaximumSize(new java.awt.Dimension(100, 35));
        btnSPackage.setPreferredSize(new java.awt.Dimension(100, 35));
        btnSPackage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSPackageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlButtonLayout = new javax.swing.GroupLayout(pnlButton);
        pnlButton.setLayout(pnlButtonLayout);
        pnlButtonLayout.setHorizontalGroup(
            pnlButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlButtonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSDebt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSPackage, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlButtonLayout.setVerticalGroup(
            pnlButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlButtonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSDebt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSPackage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlInfoLayout = new javax.swing.GroupLayout(pnlInfo);
        pnlInfo.setLayout(pnlInfoLayout);
        pnlInfoLayout.setHorizontalGroup(
            pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlInformation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlInfoLayout.setVerticalGroup(
            pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlInformation, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTittle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlListPackage, javax.swing.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlTittle, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlListPackage, javax.swing.GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
                    .addComponent(pnlInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        Validator.validateEmpty(txfId);
        if (showStringBuilder()) {
            return; 
        }

        if (PackageInf.removePackages(txfId.getText())) {
            MessageDialog.showMessageDialog(this, "Remove package successfully!", "Notification!");
        } else {
            MessageDialog.showErrorDialog(this, "Remove package Failed!", "Error!");
        }

        btnRefreshActionPerformed(evt);
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnSDebtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSDebtActionPerformed
        new StatisticDebtFrame().setVisible(true);
    }//GEN-LAST:event_btnSDebtActionPerformed

    private void btnSPackageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSPackageActionPerformed
        new StatisticPackageFrame().setVisible(true);
    }//GEN-LAST:event_btnSPackageActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed

        validateAllField();
        if (showStringBuilder()) {
            return;
        }

        if (PackageInf.searchPackage(txfId.getText())) {
            MessageDialog.showErrorDialog(this, "This package has already existed!!", "Error!");
            return;
        }

        Packages pk = getPackageInformation();

        constraintCheck(pk);
        if (showStringBuilder()) {
            return;
        }

        if (PackageInf.addPackages(pk)) {
            MessageDialog.showMessageDialog(this, "Add package successfully!", "Notification!");
        } else {
            MessageDialog.showErrorDialog(this, "Add package Failed!", "Error!");
        }
        btnRefreshActionPerformed(evt);
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        validateAllField();
        if (showStringBuilder()) {
            return;
        }

        Packages pk = getPackageInformation();

        constraintCheck(pk);
        if (showStringBuilder()) {
            return;
        }

        if (PackageInf.updatePackages(pk)) {
            MessageDialog.showMessageDialog(this, "Edit package successfully!", "Notification!");
        } else {
            MessageDialog.showErrorDialog(this, "Edit package Failed!", "Error!");
        }
        
        btnRefreshActionPerformed(evt);
    }//GEN-LAST:event_btnEditActionPerformed

    private void tblPackageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPackageMouseClicked
        try{
            int row = 0;
            row = tblPackage.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) tblPackage.getModel();

            txfId.setText((String) model.getValueAt(row, 0));
            txfName.setText((String) model.getValueAt(row, 1));
            txfNumber.setText(model.getValueAt(row, 2).toString());
            txfExpiration.setText((String) model.getValueAt(row, 3));
            txfPrice.setText((String) model.getValueAt(row, 4).toString());
            txfQuantity.setText((String) model.getValueAt(row, 5).toString());
        }catch(Exception e){
            
        }
        
    }//GEN-LAST:event_tblPackageMouseClicked

    private void txfNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txfNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txfNumberActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        txfId.setText("");
        txfNumber.setText("");
        txfName.setText("");
        txfPrice.setText("");
        txfExpiration.setText("");
        txfQuantity.setText("");
        sb.setLength(0);
        displayDataTable();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void txfIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txfIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txfIdActionPerformed

    private void displayDataTable() {
        DefaultTableModel model = (DefaultTableModel) tblPackage.getModel();
        model.setRowCount(0);

        List<Packages> lst = PackageInf.getAllPackages();
        if (lst == null)
            return;
        for (Packages packages : lst) {
            Object[] obj = {packages.getpID(), packages.getpName(), packages.getLimitNum(), DateFormatter.parse(packages.getLimitTime()),
                packages.getPrice(), packages.getQuantity()};
            model.addRow(obj);
        }

    }

    private Packages getPackageInformation() {

        String id = txfId.getText();
        String name = txfName.getText();
        int numb = Integer.parseInt(txfNumber.getText());
        LocalDate exp = LocalDate.parse(DateFormatter.formatToSQLDate(txfExpiration.getText()));
        float price = Float.parseFloat(txfPrice.getText());
        int quantity = Integer.parseInt(txfQuantity.getText());

        return new Packages(id, name, numb, exp, price, quantity);
    }

    private void validateAllField() {
        Validator.validateEmpty(txfId, sb, "Empty ID!");
        Validator.validateEmpty(txfName, sb, "Empty Name!");
        Validator.validateEmpty(txfPrice, sb, "Empty Price!");
        Validator.validateEmpty(txfQuantity, sb, "Empty Quantity!");
        Validator.validateEmpty(txfExpiration, sb, "Empty Expiration!");
        Validator.validateEmpty(txfNumber, sb, "Empty Limited Number!");
    }

    private void constraintCheck(Packages pk) {
        Validator.validate((pk.getLimitNum() <= 0), txfNumber, sb, "Limit Number can be 0 or negative!");
        Validator.validate((pk.getQuantity() <= 0), txfQuantity, sb, "Quantity can't be 0!");
        Validator.validate((!pk.getLimitTime().isAfter(LocalDate.now())), txfExpiration, sb, "Expiration must be after Now!");
        Validator.validate((pk.getPrice() <= 0), txfPrice, sb, "Price can't be 0!");
    }

    private boolean showStringBuilder() {
        if (sb.length() > 0) {
            MessageDialog.showErrorDialog(this, sb.toString(), "Error!");
            return true;
        }
        return false;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSDebt;
    private javax.swing.JButton btnSPackage;
    private javax.swing.JLabel lblExpiration;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblLnumber;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblPrice;
    private javax.swing.JLabel lblQuantity;
    private javax.swing.JLabel lblSubtiitleRight;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlButton;
    private javax.swing.JPanel pnlInfo;
    private javax.swing.JPanel pnlInformation;
    private javax.swing.JPanel pnlListPackage;
    private javax.swing.JPanel pnlTittle;
    private javax.swing.JLabel pntSubtitleLeft;
    private javax.swing.JScrollPane scrollpnlPackage;
    private javax.swing.JTable tblPackage;
    private javax.swing.JTextField txfExpiration;
    private javax.swing.JTextField txfId;
    private javax.swing.JTextField txfName;
    private javax.swing.JTextField txfNumber;
    private javax.swing.JTextField txfPrice;
    private javax.swing.JTextField txfQuantity;
    // End of variables declaration//GEN-END:variables
}
