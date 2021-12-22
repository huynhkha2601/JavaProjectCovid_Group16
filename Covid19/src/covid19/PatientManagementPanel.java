/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package covid19;
import Profile.*;
import Patient.*;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC
 */
public class PatientManagementPanel extends javax.swing.JPanel {

    /**
     * Creates new form PatientManagementPanel
     */
    public PatientManagementPanel() {
        initComponents();
        refreshTable();
        filterOption.add("ID");
        filterOption.add("Fullname");
        filterOption.add("YoB");
        filterOption.add("Address");
        filterOption.add("Status");
        filterOption.add("Treatment");
        
        
    }
    public void clearTextField() {
        txfAddress.setText("");
        txfFullname.setText("");
        txfID.setText("");
        txfTreatment.setText("");
        txfStatus.setText("");
        txfYoB.setText("");
    }
    
    public void refreshTable() {
        DefaultTableModel model = (DefaultTableModel) tblPatient.getModel();
        model.setRowCount(0);
        List<Profile> list = PatientInf.getAllPatient();
        for (int i = 0; i < list.size(); i++) 
            model.addRow(new Object[]{list.get(i).getID(), list.get(i).getFullName(), Integer.toString(list.get(i).getYoB()), list.get(i).getAddress(), list.get(i).getStatus(), list.get(i).getTreatment(), Double.toString(list.get(i).getDebt())});
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTittle = new javax.swing.JLabel();
        pnlContent = new javax.swing.JPanel();
        pnlInformation = new javax.swing.JPanel();
        pnlInforInput = new javax.swing.JPanel();
        lblID = new javax.swing.JLabel();
        lblFullname = new javax.swing.JLabel();
        lblYoB = new javax.swing.JLabel();
        lblAddress = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        lblTreatment = new javax.swing.JLabel();
        txfID = new javax.swing.JTextField();
        txfYoB = new javax.swing.JTextField();
        txfAddress = new javax.swing.JTextField();
        txfTreatment = new javax.swing.JTextField();
        txfStatus = new javax.swing.JTextField();
        txfFullname = new javax.swing.JTextField();
        lblSubtittleRight = new javax.swing.JLabel();
        pnlInforButton = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        btnFind = new javax.swing.JButton();
        btnViewRelated = new javax.swing.JButton();
        btnHosptTranfer = new javax.swing.JButton();
        filterOption = new java.awt.Choice();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        scrollpnlPatient = new javax.swing.JScrollPane();
        tblPatient = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(988, 529));

        lblTittle.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTittle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTittle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Patients-32.png"))); // NOI18N
        lblTittle.setText("Covid-19 Patient Management");
        lblTittle.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        pnlInforInput.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblID.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblID.setText("ID:");

        lblFullname.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblFullname.setText("Fullname:");

        lblYoB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblYoB.setText("Year of birth:");

        lblAddress.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblAddress.setText("Address:");

        lblStatus.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblStatus.setText("Status:");

        lblTreatment.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblTreatment.setText("Treatment:");

        txfID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfIDActionPerformed(evt);
            }
        });

        txfYoB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfYoBActionPerformed(evt);
            }
        });

        txfTreatment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfTreatmentActionPerformed(evt);
            }
        });

        txfStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfStatusActionPerformed(evt);
            }
        });

        txfFullname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfFullnameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlInforInputLayout = new javax.swing.GroupLayout(pnlInforInput);
        pnlInforInput.setLayout(pnlInforInputLayout);
        pnlInforInputLayout.setHorizontalGroup(
            pnlInforInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInforInputLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInforInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTreatment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblStatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblYoB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblFullname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblAddress, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(pnlInforInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txfYoB)
                    .addComponent(txfFullname, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txfID, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txfAddress, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txfTreatment)
                    .addComponent(txfStatus))
                .addContainerGap())
        );
        pnlInforInputLayout.setVerticalGroup(
            pnlInforInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInforInputLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInforInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txfID, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
                .addGap(6, 6, 6)
                .addGroup(pnlInforInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txfFullname, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(lblFullname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(8, 8, 8)
                .addGroup(pnlInforInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblYoB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txfYoB, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
                .addGap(8, 8, 8)
                .addGroup(pnlInforInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAddress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txfAddress, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
                .addGap(5, 5, 5)
                .addGroup(pnlInforInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txfStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInforInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTreatment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txfTreatment, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
                .addContainerGap())
        );

        lblSubtittleRight.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblSubtittleRight.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSubtittleRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/about.png"))); // NOI18N
        lblSubtittleRight.setText("Patient Information");

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/add.png"))); // NOI18N
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/edit.png"))); // NOI18N
        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnRemove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/sub.png"))); // NOI18N
        btnRemove.setText("Remove");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/refresh.png"))); // NOI18N
        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        btnFind.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search.png"))); // NOI18N
        btnFind.setText("Find");
        btnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindActionPerformed(evt);
            }
        });

        btnViewRelated.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/view.png"))); // NOI18N
        btnViewRelated.setText("View Related");
        btnViewRelated.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewRelatedActionPerformed(evt);
            }
        });

        btnHosptTranfer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/hospital.png"))); // NOI18N
        btnHosptTranfer.setText("Hospital Transfer");
        btnHosptTranfer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHosptTranferActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlInforButtonLayout = new javax.swing.GroupLayout(pnlInforButton);
        pnlInforButton.setLayout(pnlInforButtonLayout);
        pnlInforButtonLayout.setHorizontalGroup(
            pnlInforButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInforButtonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInforButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnHosptTranfer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlInforButtonLayout.createSequentialGroup()
                        .addGroup(pnlInforButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                            .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlInforButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnFind, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlInforButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnRemove, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnViewRelated, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE))))
                .addContainerGap())
        );
        pnlInforButtonLayout.setVerticalGroup(
            pnlInforButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInforButtonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInforButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEdit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRemove, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInforButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnFind, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnViewRelated, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHosptTranfer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        filterOption.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                filterOptionItemStateChanged(evt);
            }
        });

        jLabel2.setText("Filter:");

        javax.swing.GroupLayout pnlInformationLayout = new javax.swing.GroupLayout(pnlInformation);
        pnlInformation.setLayout(pnlInformationLayout);
        pnlInformationLayout.setHorizontalGroup(
            pnlInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInformationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlInformationLayout.createSequentialGroup()
                        .addComponent(lblSubtittleRight, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(filterOption, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlInforInput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlInforButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlInformationLayout.setVerticalGroup(
            pnlInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInformationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblSubtittleRight, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(filterOption, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlInforInput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlInforButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tblPatient.setAutoCreateRowSorter(true);
        tblPatient.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Fullname", "Year of birth", "Address", "Status", "Treatment", "Debt"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPatient.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPatientMouseClicked(evt);
            }
        });
        scrollpnlPatient.setViewportView(tblPatient);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("List of Patient");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scrollpnlPatient, javax.swing.GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollpnlPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlContentLayout = new javax.swing.GroupLayout(pnlContent);
        pnlContent.setLayout(pnlContentLayout);
        pnlContentLayout.setHorizontalGroup(
            pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlInformation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlContentLayout.setVerticalGroup(
            pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlInformation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTittle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTittle, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txfIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txfIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txfIDActionPerformed

    private void txfYoBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txfYoBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txfYoBActionPerformed

    private void txfTreatmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txfTreatmentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txfTreatmentActionPerformed

    private void txfStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txfStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txfStatusActionPerformed

    private void txfFullnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txfFullnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txfFullnameActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if(txfAddress.getText().equals("") || txfFullname.getText().equals("") || txfID.getText().equals("") || txfStatus.getText().equals("") || txfTreatment.getText().equals("") || txfYoB.getText().equals(""))
            JOptionPane.showMessageDialog(pnlInformation, "Required fields cannot be blank", "Notification", JOptionPane.INFORMATION_MESSAGE);
        else if(PatientInf.addPatient(txfID.getText(), txfFullname.getText(), Integer.parseInt(txfYoB.getText()), txfAddress.getText(), txfStatus.getText(), txfTreatment.getText())) {
            JOptionPane.showMessageDialog(pnlInformation, "Add successfully", "Notification", JOptionPane.INFORMATION_MESSAGE);
            //DefaultTableModel model = (DefaultTableModel) tblPatient.getModel();
            //model.addRow(new Object[]{txfID.getText(), txfFullname.getText(), txfYoB.getText(), txfAddress.getText(), txfTreatment.getText(), txfStatus.getText(), "0"});
            refreshTable();
            clearTextField();
        }
        else
            JOptionPane.showMessageDialog(pnlInformation, "Add failed", "Notification", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        if(txfID.getText().equals(""))
            JOptionPane.showMessageDialog(pnlInformation, "ID field cannot be blank", "Notification", JOptionPane.ERROR_MESSAGE);
        else {
            int result = JOptionPane.showConfirmDialog(pnlInformation, "Do you want to save the changes", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(result == JOptionPane.YES_OPTION){
                if(txfAddress.getText().equals("") || txfFullname.getText().equals("") || txfStatus.getText().equals("") || txfYoB.getText().equals(""))
                    JOptionPane.showMessageDialog(pnlInformation, "Required fields cannot be blank", "Notification", JOptionPane.ERROR_MESSAGE);
                else {
                    if(PatientInf.updatePatient(txfID.getText(), txfFullname.getText(), Integer.parseInt(txfYoB.getText()), txfAddress.getText(), txfStatus.getText())) {
                        JOptionPane.showMessageDialog(pnlInformation, "Update successfully", "Notification", JOptionPane.INFORMATION_MESSAGE);
                        refreshTable();
                        clearTextField();
                    }
                    else
                        JOptionPane.showMessageDialog(pnlInformation, "Update failed", "Notification", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        if(txfID.getText().equals(""))
            JOptionPane.showMessageDialog(pnlInformation, "ID field cannot be blank", "Notification", JOptionPane.ERROR_MESSAGE);
        else {
            String id = txfID.getText();
            int result = JOptionPane.showConfirmDialog(pnlInformation, "Do you want to save the changes", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(result == JOptionPane.YES_OPTION){
                if(PatientInf.deletePatient(id)) {
                    JOptionPane.showMessageDialog(pnlInformation, "Remove successfully", "Notification", JOptionPane.INFORMATION_MESSAGE);
                    refreshTable();
                    clearTextField();
                }
                else
                    JOptionPane.showMessageDialog(pnlInformation, "Remove failed", "Notification", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        refreshTable();
        clearTextField();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindActionPerformed
        List<Profile> list = null;
        if (filterOption.getSelectedItem().equals("ID")) {
            if (txfID.getText().equals("")) {
                JOptionPane.showMessageDialog(pnlInformation, "ID field cannot be blank", "Notification", JOptionPane.ERROR_MESSAGE);
                return;
            }
            list = PatientInf.getPatientByFilter(txfID.getText(), "ID");  
        }
        else if (filterOption.getSelectedItem().equals("Fullname")) {
            if (txfFullname.getText().equals("")) {
                JOptionPane.showMessageDialog(pnlInformation, "Fullname field cannot be blank", "Notification", JOptionPane.ERROR_MESSAGE);
                return;
            }
            list = PatientInf.getPatientByFilter(txfFullname.getText(), "Fullname");
        }
        else if (filterOption.getSelectedItem().equals("YoB")) {
            if (txfYoB.getText().equals("")) {
                JOptionPane.showMessageDialog(pnlInformation, "YoB field cannot be blank", "Notification", JOptionPane.ERROR_MESSAGE);
                return;
            }
           list = PatientInf.getPatientByFilter(txfYoB.getText(), "YoB");
        }
        else if (filterOption.getSelectedItem().equals("Address")) {
            if (txfAddress.getText().equals("")) {
                JOptionPane.showMessageDialog(pnlInformation, "Address field cannot be blank", "Notification", JOptionPane.ERROR_MESSAGE);
                return;
            }
           list = PatientInf.getPatientByFilter(txfAddress.getText(), "Address");
        }
        else if (filterOption.getSelectedItem().equals("Status")) {
            if (txfStatus.getText().equals("")) {
                JOptionPane.showMessageDialog(pnlInformation, "Status field cannot be blank", "Notification", JOptionPane.ERROR_MESSAGE);
                return;
            }
           list = PatientInf.getPatientByFilter(txfStatus.getText(), "Status"); 
        }
        else if (filterOption.getSelectedItem().equals("Treatment")) {
            if (txfTreatment.getText().equals("")) {
                JOptionPane.showMessageDialog(pnlInformation, "Treatment field cannot be blank", "Notification", JOptionPane.ERROR_MESSAGE);
                return;
            }
           list = PatientInf.getPatientByFilter(txfTreatment.getText(), "Treatment");
        }
        if (!list.isEmpty()) {
            DefaultTableModel model = (DefaultTableModel) tblPatient.getModel();
            model.setRowCount(0);
            for (int i = 0; i < list.size(); i++) 
                model.addRow(new Object[]{list.get(i).getID(), list.get(i).getFullName(), Integer.toString(list.get(i).getYoB()), list.get(i).getAddress(), list.get(i).getStatus(), list.get(i).getTreatment(), Double.toString(list.get(i).getDebt())});
        }
        else
            JOptionPane.showMessageDialog(pnlInformation, "No result found", "Notification", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_btnFindActionPerformed

    private void btnViewRelatedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewRelatedActionPerformed
        new ViewRelatedFrame().setVisible(true);
    }//GEN-LAST:event_btnViewRelatedActionPerformed

    private void btnHosptTranferActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHosptTranferActionPerformed
        if(txfID.getText().equals(""))
            JOptionPane.showMessageDialog(pnlInformation, "ID field cannot be blank", "Notification", JOptionPane.ERROR_MESSAGE);
        else {
            String id = txfID.getText();
            HospitalTransferFrame transferFrame = new HospitalTransferFrame(id);
            transferFrame.setVisible(true);
        } 
    }//GEN-LAST:event_btnHosptTranferActionPerformed

    private void tblPatientMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPatientMouseClicked
        DefaultTableModel model = (DefaultTableModel) tblPatient.getModel();
        int selectedRowIndex = tblPatient.getSelectedRow();
        txfAddress.setText(model.getValueAt(selectedRowIndex, 3).toString());
        txfFullname.setText(model.getValueAt(selectedRowIndex, 1).toString());
        txfID.setText(model.getValueAt(selectedRowIndex, 0).toString());
        txfYoB.setText(model.getValueAt(selectedRowIndex, 2).toString());
        txfStatus.setText(model.getValueAt(selectedRowIndex, 4).toString());
        txfTreatment.setText(model.getValueAt(selectedRowIndex, 5).toString());
    }//GEN-LAST:event_tblPatientMouseClicked

    private void filterOptionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_filterOptionItemStateChanged
        clearTextField();
    }//GEN-LAST:event_filterOptionItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnFind;
    private javax.swing.JButton btnHosptTranfer;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnViewRelated;
    private java.awt.Choice filterOption;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblAddress;
    private javax.swing.JLabel lblFullname;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblSubtittleRight;
    private javax.swing.JLabel lblTittle;
    private javax.swing.JLabel lblTreatment;
    private javax.swing.JLabel lblYoB;
    private javax.swing.JPanel pnlContent;
    private javax.swing.JPanel pnlInforButton;
    private javax.swing.JPanel pnlInforInput;
    private javax.swing.JPanel pnlInformation;
    private javax.swing.JScrollPane scrollpnlPatient;
    private javax.swing.JTable tblPatient;
    private javax.swing.JTextField txfAddress;
    private javax.swing.JTextField txfFullname;
    private javax.swing.JTextField txfID;
    private javax.swing.JTextField txfStatus;
    private javax.swing.JTextField txfTreatment;
    private javax.swing.JTextField txfYoB;
    // End of variables declaration//GEN-END:variables
}
