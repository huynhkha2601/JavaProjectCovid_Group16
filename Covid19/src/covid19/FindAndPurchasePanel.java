/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package covid19;

import Helper.DateFormatter;
import Helper.MessageDialog;
import Helper.Validator;
import Packages.PackageInf;
import Packages.Packages;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC
 */
public class FindAndPurchasePanel extends javax.swing.JPanel {
    
    private String userID;
    /**
     * Creates new form FindPanel
     */
    public FindAndPurchasePanel(String userID) {
        this.userID = userID;
        initComponents();
        initValue();
        displayDataTable();
    }
    
    public FindAndPurchasePanel() {
        initComponents();
        //initValue();
        //displayDataTable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTitle = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        PnlSearch = new javax.swing.JPanel();
        pnlPackageList = new javax.swing.JPanel();
        srlpPackageList = new javax.swing.JScrollPane();
        tblPackage = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        pnlPackage = new javax.swing.JPanel();
        lblPackage = new javax.swing.JLabel();
        cbbPackage = new javax.swing.JComboBox<>();
        lblRegistered = new javax.swing.JLabel();
        txtRegisterd = new javax.swing.JTextField();
        lblQuantity = new javax.swing.JLabel();
        txtQuantity = new javax.swing.JTextField();
        pnlButtonBuy = new javax.swing.JPanel();
        btnBuy = new javax.swing.JButton();
        pnlButtonSearch = new javax.swing.JPanel();
        btnSearch = new javax.swing.JButton();
        btnFilter = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        pnlSearchbyName = new javax.swing.JPanel();
        lblSearchbyName = new javax.swing.JLabel();
        txtfSearchbyName = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(988, 529));

        lblTitle.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search-48.png"))); // NOI18N
        lblTitle.setText("Tìm kiếm");
        lblTitle.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        pnlPackageList.setBorder(javax.swing.BorderFactory.createTitledBorder("Package List"));

        tblPackage.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Limit Number", "Expiration", "Quantity"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        srlpPackageList.setViewportView(tblPackage);
        if (tblPackage.getColumnModel().getColumnCount() > 0) {
            tblPackage.getColumnModel().getColumn(0).setPreferredWidth(20);
            tblPackage.getColumnModel().getColumn(1).setPreferredWidth(200);
        }

        javax.swing.GroupLayout pnlPackageListLayout = new javax.swing.GroupLayout(pnlPackageList);
        pnlPackageList.setLayout(pnlPackageListLayout);
        pnlPackageListLayout.setHorizontalGroup(
            pnlPackageListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(pnlPackageListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlPackageListLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(srlpPackageList)
                    .addContainerGap()))
        );
        pnlPackageListLayout.setVerticalGroup(
            pnlPackageListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(pnlPackageListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlPackageListLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(srlpPackageList)
                    .addContainerGap()))
        );

        lblPackage.setText("Choose Package:");

        cbbPackage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbPackageActionPerformed(evt);
            }
        });

        lblRegistered.setText("Max Number:");

        txtRegisterd.setEditable(false);
        txtRegisterd.setBackground(new java.awt.Color(255, 255, 255));
        txtRegisterd.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtRegisterd.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtRegisterd.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtRegisterd.setEnabled(false);
        txtRegisterd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRegisterdActionPerformed(evt);
            }
        });

        lblQuantity.setText("Quantity Purcharsed:");

        txtQuantity.setText("jTextField1");

        javax.swing.GroupLayout pnlPackageLayout = new javax.swing.GroupLayout(pnlPackage);
        pnlPackage.setLayout(pnlPackageLayout);
        pnlPackageLayout.setHorizontalGroup(
            pnlPackageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPackageLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPackageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblQuantity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPackage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblRegistered, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPackageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbbPackage, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtRegisterd)
                    .addComponent(txtQuantity))
                .addContainerGap())
        );
        pnlPackageLayout.setVerticalGroup(
            pnlPackageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPackageLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPackageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbbPackage, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPackage, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPackageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblRegistered, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRegisterd, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPackageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblQuantity)
                    .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pnlPackageLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cbbPackage, lblPackage, lblQuantity, lblRegistered, txtQuantity, txtRegisterd});

        btnBuy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/buy.png"))); // NOI18N
        btnBuy.setText("Buy");
        btnBuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlButtonBuyLayout = new javax.swing.GroupLayout(pnlButtonBuy);
        pnlButtonBuy.setLayout(pnlButtonBuyLayout);
        pnlButtonBuyLayout.setHorizontalGroup(
            pnlButtonBuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlButtonBuyLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBuy, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlButtonBuyLayout.setVerticalGroup(
            pnlButtonBuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlButtonBuyLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBuy, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search.png"))); // NOI18N
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnFilter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search.png"))); // NOI18N
        btnFilter.setText("Filter");
        btnFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilterActionPerformed(evt);
            }
        });

        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/refresh.png"))); // NOI18N
        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlButtonSearchLayout = new javax.swing.GroupLayout(pnlButtonSearch);
        pnlButtonSearch.setLayout(pnlButtonSearchLayout);
        pnlButtonSearchLayout.setHorizontalGroup(
            pnlButtonSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlButtonSearchLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlButtonSearchLayout.setVerticalGroup(
            pnlButtonSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlButtonSearchLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlButtonSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                    .addComponent(btnFilter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        lblSearchbyName.setText("Search by Name:");

        txtfSearchbyName.setText("jTextField2");

        javax.swing.GroupLayout pnlSearchbyNameLayout = new javax.swing.GroupLayout(pnlSearchbyName);
        pnlSearchbyName.setLayout(pnlSearchbyNameLayout);
        pnlSearchbyNameLayout.setHorizontalGroup(
            pnlSearchbyNameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSearchbyNameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSearchbyName, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtfSearchbyName)
                .addContainerGap())
        );
        pnlSearchbyNameLayout.setVerticalGroup(
            pnlSearchbyNameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSearchbyNameLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSearchbyNameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtfSearchbyName, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(lblSearchbyName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlButtonBuy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlPackage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlSearchbyName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlButtonSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlSearchbyName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(pnlButtonSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlPackage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlButtonBuy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout PnlSearchLayout = new javax.swing.GroupLayout(PnlSearch);
        PnlSearch.setLayout(PnlSearchLayout);
        PnlSearchLayout.setHorizontalGroup(
            PnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PnlSearchLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlPackageList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        PnlSearchLayout.setVerticalGroup(
            PnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlSearchLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlPackageList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlTitleLayout = new javax.swing.GroupLayout(pnlTitle);
        pnlTitle.setLayout(pnlTitleLayout);
        pnlTitleLayout.setHorizontalGroup(
            pnlTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PnlSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlTitleLayout.setVerticalGroup(
            pnlTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTitleLayout.createSequentialGroup()
                .addComponent(lblTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PnlSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed

        if (Validator.validateEmpty(txtfSearchbyName)) {
            MessageDialog.showErrorDialog(this, "Search Name can be empty!", "Error!");
            return;
        }

        List<Packages> lst;
        lst = PackageInf.searchPackageByName(txtfSearchbyName.getText());
        if (Objects.isNull(lst) || lst.isEmpty()) {
            MessageDialog.showMessageDialog(this, "Couldn't find the package of essentials you were looking for!", "Notification!");
            return;
        }
        displayDataTable(lst);
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilterActionPerformed

        if (Validator.validateEmpty(txtfSearchbyName)) {
            MessageDialog.showErrorDialog(this, "Search Name can be empty!", "Error!");
            return;
        }
        
        List<Packages> lst;
        lst = PackageInf.filterPackageByName(txtfSearchbyName.getText());
        if (Objects.isNull(lst) || lst.isEmpty()) {
            MessageDialog.showMessageDialog(this, "Couldn't find the package of essentials you were looking for!", "Notification!");
            return;
        }
        displayDataTable(lst);
    }//GEN-LAST:event_btnFilterActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        txtQuantity.setText("");
        txtfSearchbyName.setText("");
        displayDataTable();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void txtRegisterdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRegisterdActionPerformed
        
    }//GEN-LAST:event_txtRegisterdActionPerformed

    private void btnBuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuyActionPerformed
        if (!isParsable(txtQuantity.getText()) || Validator.validateEmpty(txtQuantity)) {
            MessageDialog.showErrorDialog(this, "Quantity cannot be empty or string!", "Error!");
            return;
        }
        
        if (Integer.parseInt(txtQuantity.getText()) > Integer.parseInt(txtRegisterd.getText())) {
            MessageDialog.showErrorDialog(this, "Over register quantity!!!!", "Error!");
            return;
        }
        
        int i = MessageDialog.showConfirmDialog(this, "Are you want buy this package?","Confirm!");
        if(i==0) {
            String[] s = ((String) cbbPackage.getModel().getSelectedItem()).split(" - ");
            if (PackageInf.buyPackage(userID, s[0], Integer.parseInt(txtQuantity.getText()))){
                MessageDialog.showMessageDialog(this, "Buy succesfully!", "Notification");
            }else{
                MessageDialog.showErrorDialog(this, "Buy failed!", "Error!");
            }
        }
    }//GEN-LAST:event_btnBuyActionPerformed

    private void cbbPackageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbPackageActionPerformed
        String[] i = ((String) cbbPackage.getModel().getSelectedItem()).split(" - ");
        System.out.println(i);
        String s = PackageInf.getQuantity(userID , i[0]);
        txtRegisterd.setText(s);
    }//GEN-LAST:event_cbbPackageActionPerformed

    private void displayDataTable() {
        DefaultTableModel model = (DefaultTableModel) tblPackage.getModel();
        model.setRowCount(0);

        List<Packages> lst = PackageInf.getAllPackages();
        if (lst == null) {
            return;
        }
        for (Packages packages : lst) {
            Object[] obj = {packages.getpID(), packages.getpName(), packages.getLimitNum(), DateFormatter.parse(packages.getLimitTime()),
                packages.getPrice(), packages.getQuantity()};
            model.addRow(obj);
        }

    }

    private void displayDataTable(List<Packages> lst) {
        DefaultTableModel model = (DefaultTableModel) tblPackage.getModel();
        model.setRowCount(0);

        if (lst == null) {
            return;
        }
        for (Packages packages : lst) {
            Object[] obj = {packages.getpID(), packages.getpName(), packages.getLimitNum(), DateFormatter.parse(packages.getLimitTime()),
                packages.getPrice(), packages.getQuantity()};
            model.addRow(obj);
        }     
    }

    private void initValue() {
        String[] vt = PackageInf.getAllIdPackages();
        cbbPackage.setModel(new DefaultComboBoxModel<>(vt));
        String[] i = ((String) cbbPackage.getModel().getSelectedItem()).split(" - ");
        txtRegisterd.setText(PackageInf.getQuantity("1" , i[0]));
    }
    
    private boolean isParsable(String input) {
    try {
        Integer.parseInt(input);
        return true;
    } catch (final NumberFormatException e) {
        return false;
    }
}
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PnlSearch;
    private javax.swing.JButton btnBuy;
    private javax.swing.JButton btnFilter;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox<String> cbbPackage;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblPackage;
    private javax.swing.JLabel lblQuantity;
    private javax.swing.JLabel lblRegistered;
    private javax.swing.JLabel lblSearchbyName;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlButtonBuy;
    private javax.swing.JPanel pnlButtonSearch;
    private javax.swing.JPanel pnlPackage;
    private javax.swing.JPanel pnlPackageList;
    private javax.swing.JPanel pnlSearchbyName;
    private javax.swing.JPanel pnlTitle;
    private javax.swing.JScrollPane srlpPackageList;
    private javax.swing.JTable tblPackage;
    private javax.swing.JTextField txtQuantity;
    private javax.swing.JTextField txtRegisterd;
    private javax.swing.JTextField txtfSearchbyName;
    // End of variables declaration//GEN-END:variables
}
