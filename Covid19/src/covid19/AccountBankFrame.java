/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package covid19;

import covid19.AccountFrame.ChangePasswordFrame;
import Account.AccountBank;
import Payment.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Helper.AES;

/**
 *
 * @author PC
 */
public class AccountBankFrame extends javax.swing.JFrame {
    private AccountBank userBank = new AccountBank();
    /**
     * Creates new form AccountBankFrame
     */
    /*
    public AccountBankFrame() {
        initComponents();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }
    */
    public AccountBankFrame(AccountBank accBank){
        userBank = accBank;
        initComponents();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        txfBalance.setText(String.format("%.1f", userBank.getBalance()));
        txfBankID.setText(userBank.getBankid());
        txfBalance.setEditable(false);
        txfBankID.setEditable(false);
        refreshTable();
    }
    
    public void refreshTable() {
        DefaultTableModel model = (DefaultTableModel) tblBank.getModel();
        model.setRowCount(0);
        List<Transaction> list = BankInf.getTransaction(userBank.getBankid());
        for (int i = 0; i < list.size(); i++) 
            model.addRow(new Object[]{(list.get(i).getMoney()), list.get(i).getContent(), list.get(i).getRecord().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))});
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        pnlTitle = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        pnlAccountBankDetail = new javax.swing.JPanel();
        pnlBankDetail = new javax.swing.JPanel();
        lblBankID = new javax.swing.JLabel();
        lblBalance = new javax.swing.JLabel();
        lblCash = new javax.swing.JLabel();
        txfCash = new javax.swing.JTextField();
        txfBankID = new javax.swing.JTextField();
        txfBalance = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txfContent = new javax.swing.JTextField();
        pnlTableBank = new javax.swing.JPanel();
        spnlBank = new javax.swing.JScrollPane();
        tblBank = new javax.swing.JTable();
        pnlBankButton = new javax.swing.JPanel();
        btnChangePassword = new javax.swing.JButton();
        btnPayment = new javax.swing.JButton();
        btnGoBack = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitle.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/bank-48.png"))); // NOI18N
        lblTitle.setText("Account Bank");

        javax.swing.GroupLayout pnlTitleLayout = new javax.swing.GroupLayout(pnlTitle);
        pnlTitle.setLayout(pnlTitleLayout);
        pnlTitleLayout.setHorizontalGroup(
            pnlTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlTitleLayout.setVerticalGroup(
            pnlTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        lblBankID.setText("Bank ID:");

        lblBalance.setText("Balance:");

        lblCash.setText("Cash:");

        jLabel1.setText("Content:");

        txfContent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfContentActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBankDetailLayout = new javax.swing.GroupLayout(pnlBankDetail);
        pnlBankDetail.setLayout(pnlBankDetailLayout);
        pnlBankDetailLayout.setHorizontalGroup(
            pnlBankDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBankDetailLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBankDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBankDetailLayout.createSequentialGroup()
                        .addComponent(lblBankID, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txfBankID))
                    .addGroup(pnlBankDetailLayout.createSequentialGroup()
                        .addGroup(pnlBankDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCash, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlBankDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txfContent)
                            .addComponent(txfCash)
                            .addComponent(txfBalance))))
                .addContainerGap())
        );
        pnlBankDetailLayout.setVerticalGroup(
            pnlBankDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBankDetailLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBankDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBankID, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txfBankID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBankDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txfBalance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBankDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCash, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txfCash, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlBankDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txfContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tblBank.setAutoCreateRowSorter(true);
        tblBank.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Money", "Content", "Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Float.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        spnlBank.setViewportView(tblBank);

        javax.swing.GroupLayout pnlTableBankLayout = new javax.swing.GroupLayout(pnlTableBank);
        pnlTableBank.setLayout(pnlTableBankLayout);
        pnlTableBankLayout.setHorizontalGroup(
            pnlTableBankLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTableBankLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(spnlBank, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTableBankLayout.setVerticalGroup(
            pnlTableBankLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTableBankLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(spnlBank, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnChangePassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/changepassword.png"))); // NOI18N
        btnChangePassword.setText("Change Password");
        btnChangePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangePasswordActionPerformed(evt);
            }
        });

        btnPayment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/payment.png"))); // NOI18N
        btnPayment.setText("Payment");
        btnPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPaymentActionPerformed(evt);
            }
        });

        btnGoBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/back.png"))); // NOI18N
        btnGoBack.setText("Go Back");
        btnGoBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBankButtonLayout = new javax.swing.GroupLayout(pnlBankButton);
        pnlBankButton.setLayout(pnlBankButtonLayout);
        pnlBankButtonLayout.setHorizontalGroup(
            pnlBankButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBankButtonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBankButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBankButtonLayout.createSequentialGroup()
                        .addComponent(btnChangePassword, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnGoBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlBankButtonLayout.setVerticalGroup(
            pnlBankButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBankButtonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBankButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnChangePassword, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                    .addComponent(btnPayment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGoBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlAccountBankDetailLayout = new javax.swing.GroupLayout(pnlAccountBankDetail);
        pnlAccountBankDetail.setLayout(pnlAccountBankDetailLayout);
        pnlAccountBankDetailLayout.setHorizontalGroup(
            pnlAccountBankDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAccountBankDetailLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlTableBank, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlAccountBankDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlBankButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlBankDetail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlAccountBankDetailLayout.setVerticalGroup(
            pnlAccountBankDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAccountBankDetailLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAccountBankDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTableBank, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlAccountBankDetailLayout.createSequentialGroup()
                        .addComponent(pnlBankDetail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pnlBankButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlAccountBankDetail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlAccountBankDetail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPaymentActionPerformed
        if(txfCash.getText().equals("") || txfContent.getText().equals(""))
            JOptionPane.showMessageDialog(this, "Required field cannot be blank", "Notification", JOptionPane.ERROR_MESSAGE);
        else {
            String check = "error";
            if (Float.parseFloat(txfCash.getText()) > 0 && Float.parseFloat(txfCash.getText()) <= Float.parseFloat(txfBalance.getText())) {
                InputStream clientIn = null;
                OutputStream clientOut = null;
                Socket client = null;
                try {
                    client = new Socket(InetAddress.getLocalHost(), 3000);
                    clientIn = client.getInputStream();
                    clientOut = client.getOutputStream();
                    PrintWriter pw = new PrintWriter(clientOut, true);
                    BufferedReader br = new BufferedReader(new InputStreamReader(clientIn));
                    String secretKey = "backspace";
                    String content = userBank.getBankid() + "/" + txfCash.getText() + "/" + txfContent.getText();
                    String encryptedContent = AES.encrypt(content, secretKey);
                    pw.println(encryptedContent);
                    check = br.readLine();
                    //check = Boolean.parseBoolean(temp);
                    //System.out.println(check);
                    /*
                    if(BankInf.addTransaction(userBank.getBankid(), Float.parseFloat(txfCash.getText()), txfContent.getText())) {
                    JOptionPane.showMessageDialog(pnlBankDetail, "Pay successully", "Notification", JOptionPane.INFORMATION_MESSAGE);
                    txfBalance.setText(Float.toString(Float.parseFloat(txfBalance.getText()) - Float.parseFloat(txfCash.getText())));
                    txfCash.setText("");
                    txfContent.setText("");
                    refreshTable();
                    }
                    else
                    JOptionPane.showMessageDialog(pnlBankDetail, "Pay failed", "Error", JOptionPane.ERROR_MESSAGE);
                    */
                } catch (IOException ex) {
                    Logger.getLogger(AccountBankFrame.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        if (client != null) client.close();
                        if (clientIn != null) clientIn.close();
                        if (clientOut != null) clientOut.close();
                    } catch (IOException ex) {
                        Logger.getLogger(AccountBankFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (check.equals("true")) {
                    JOptionPane.showMessageDialog(this, "Pay successully", "Notification", JOptionPane.INFORMATION_MESSAGE);
                    txfBalance.setText(String.format("%.1f", Float.parseFloat(txfBalance.getText()) - Float.parseFloat(txfCash.getText())));
                    txfCash.setText("");
                    txfContent.setText("");
                    refreshTable();
                }
                else if (check.equals("failed"))
                    JOptionPane.showMessageDialog(this, "Pay failed", "Error", JOptionPane.ERROR_MESSAGE);
                else 
                    JOptionPane.showMessageDialog(this, "Payment system error", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else
                JOptionPane.showMessageDialog(this, "Invalid value", "Notification", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnPaymentActionPerformed

    private void btnChangePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangePasswordActionPerformed
        new ChangePasswordFrame().setVisible(true);
    }//GEN-LAST:event_btnChangePasswordActionPerformed

    private void btnGoBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoBackActionPerformed
        //new MainFrame().setVisible(true);
        //this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_btnGoBackActionPerformed

    private void txfContentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txfContentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txfContentActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AccountBankFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AccountBankFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AccountBankFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AccountBankFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AccountBankFrame(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChangePassword;
    private javax.swing.JButton btnGoBack;
    private javax.swing.JButton btnPayment;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblBalance;
    private javax.swing.JLabel lblBankID;
    private javax.swing.JLabel lblCash;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlAccountBankDetail;
    private javax.swing.JPanel pnlBankButton;
    private javax.swing.JPanel pnlBankDetail;
    private javax.swing.JPanel pnlTableBank;
    private javax.swing.JPanel pnlTitle;
    private javax.swing.JScrollPane spnlBank;
    private javax.swing.JTable tblBank;
    private javax.swing.JTextField txfBalance;
    private javax.swing.JTextField txfBankID;
    private javax.swing.JTextField txfCash;
    private javax.swing.JTextField txfContent;
    // End of variables declaration//GEN-END:variables
}
