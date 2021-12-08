/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package covid19;

/**
 *
 * @author PC
 */
public class HelpPanel extends javax.swing.JPanel {

    /**
     * Creates new form HelpPanel
     */
    public HelpPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        pnlTitle = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        pnlHelp = new javax.swing.JPanel();
        lblHelp2 = new javax.swing.JLabel();
        lblHelp3 = new javax.swing.JLabel();
        lblHelp4 = new javax.swing.JLabel();
        lblHelp5 = new javax.swing.JLabel();
        lblHelp6 = new javax.swing.JLabel();
        lblHelp1 = new javax.swing.JLabel();

        jTextField1.setText("jTextField1");

        lblTitle.setFont(new java.awt.Font("Tahoma", 3, 36)); // NOI18N
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("Help");

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        lblHelp2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        lblHelp2.setText("- Sử dụng các nút bấm bên trái để hiển thị các hộp thoại: xem thông tin cá nhân, quản lý người bệnh, quản lý lịch sử tiếp xúc, quản lý thông tin chữa bênh.");

        lblHelp3.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        lblHelp3.setText("- Khi bật thì sẽ hiển thị các tab bên phải (là phần nội dung chức năng của mỗi  nút nhấn bên trên). Nếu bạn tiếp tục nhấn vào nút bấm sẽ tắt trang đó đi.");

        lblHelp4.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        lblHelp4.setText("- Để chuyển tab bạn chỉ cần thực hiện nhấn chuột vào các tab đang hiển thị. ");

        lblHelp5.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        lblHelp5.setText("- Tùy thuộc vào mỗi vai trò của người dùng, các nút nhấn sẽ được hiển thị để sử dụng hoặc ẩn đi.");

        lblHelp6.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        lblHelp6.setText("- Nhấn vào nút thoát bên trái phía trên của chương trình sẽ trở về màn hình đăng nhập.");

        lblHelp1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        lblHelp1.setText("- Ở màn hình đăng nhập, nếu chưa có tài khoản hãy đăng ký để tham gia sử dụng hệ thống Covid-19.");

        javax.swing.GroupLayout pnlHelpLayout = new javax.swing.GroupLayout(pnlHelp);
        pnlHelp.setLayout(pnlHelpLayout);
        pnlHelpLayout.setHorizontalGroup(
            pnlHelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHelpLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlHelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblHelp1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblHelp3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblHelp2, javax.swing.GroupLayout.DEFAULT_SIZE, 948, Short.MAX_VALUE)
                    .addComponent(lblHelp6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblHelp5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblHelp4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlHelpLayout.setVerticalGroup(
            pnlHelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHelpLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblHelp1, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblHelp2, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblHelp3, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblHelp4, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblHelp5, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblHelp6, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlHelp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlHelp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblHelp1;
    private javax.swing.JLabel lblHelp2;
    private javax.swing.JLabel lblHelp3;
    private javax.swing.JLabel lblHelp4;
    private javax.swing.JLabel lblHelp5;
    private javax.swing.JLabel lblHelp6;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlHelp;
    private javax.swing.JPanel pnlTitle;
    // End of variables declaration//GEN-END:variables
}
