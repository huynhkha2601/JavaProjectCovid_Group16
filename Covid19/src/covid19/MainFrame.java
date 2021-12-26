/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package covid19;

import covid19.AccountFrame.SignInFrame;    
import java.awt.Component;
import javax.swing.JFrame;
import Account.Account;
import DbConnection.SQLConnection;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author PC
 */
public class MainFrame extends javax.swing.JFrame {
    Account user = new Account();
    LocalDateTime login;
    private int role;

    private int packageFlag = 0;
    private int aboutUsFlag = 0;
    private int helpFlag = 0;
    private int profileFlag = 0;
    private int statusFlag = 0;
    private int patientFlag = 0;
    private int findFlag = 0;

    private PackageManagementPanel pkmp = null;
    private AboutUsPanel abup = new AboutUsPanel();
    private HelpPanel hpn = new HelpPanel();
    private ProfilePanel pfp = null;
    private StatusManagementPanel rmp = null;
    private PatientManagementPanel ptmp = null;
    private FindAndPurchasePanel fpn = null;

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
                
        initComponents();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        new SQLConnection();
        AddActionPerformed();
    }

    public MainFrame(Account a,int role) {
        this.role = role;
        initComponents();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        user = a;
        if (role == 1) {
            btnProfile.setEnabled(false);
            btnFind.setEnabled(false);
        } else if (role == 2) {
            btnManagePackage.setEnabled(false);
            btnManagePerson.setEnabled(false);
            btnManageRelated.setEnabled(false);
        }
    }
        public MainFrame(Account a,int role,LocalDateTime now) {
        this.role = role;
        initComponents();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        user = a;
        if (role == 1) {
            btnProfile.setEnabled(false);
            btnFind.setEnabled(false);
        } else if (role == 2) {
            login = now;
            btnManagePackage.setEnabled(false);
            btnManagePerson.setEnabled(false);
            btnManageRelated.setEnabled(false);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlWelcome = new javax.swing.JPanel();
        lblAppTittle = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        lblWelcome = new javax.swing.JLabel();
        pnlContent = new javax.swing.JPanel();
        pnlButton = new javax.swing.JPanel();
        btnManagePerson = new javax.swing.JButton();
        btnFind = new javax.swing.JButton();
        btnProfile = new javax.swing.JButton();
        btnManageRelated = new javax.swing.JButton();
        btnManagePackage = new javax.swing.JButton();
        pnlFind1 = new javax.swing.JButton();
        pnlFind2 = new javax.swing.JButton();
        tpnContent = new javax.swing.JTabbedPane();
        menuBar = new javax.swing.JMenuBar();
        menuFile = new javax.swing.JMenu();
        mitNew = new javax.swing.JMenuItem();
        mitReload = new javax.swing.JMenuItem();
        sptFile = new javax.swing.JPopupMenu.Separator();
        mitFileLogout = new javax.swing.JMenuItem();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        menuEdit = new javax.swing.JMenu();
        mitUndo = new javax.swing.JMenuItem();
        mitRedo = new javax.swing.JMenuItem();
        sptEditTop = new javax.swing.JPopupMenu.Separator();
        mitCopy = new javax.swing.JMenuItem();
        mitCut = new javax.swing.JMenuItem();
        mitPaste = new javax.swing.JMenuItem();
        sptEditBottom = new javax.swing.JPopupMenu.Separator();
        mitFind = new javax.swing.JMenuItem();
        mitReplace = new javax.swing.JMenuItem();
        menuManagement = new javax.swing.JMenu();
        mitMUserProfile = new javax.swing.JMenuItem();
        mitMPatientManagement = new javax.swing.JMenuItem();
        mitMStatusManagement = new javax.swing.JMenuItem();
        mitMPackageManagement = new javax.swing.JMenuItem();
        mitMFindAndPurchase = new javax.swing.JMenuItem();
        menuAboutUs = new javax.swing.JMenu();
        menuHelp = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1200, 740));

        pnlWelcome.setMaximumSize(new java.awt.Dimension(120, 48));
        pnlWelcome.setMinimumSize(new java.awt.Dimension(120, 30));
        pnlWelcome.setPreferredSize(new java.awt.Dimension(1217, 120));

        lblAppTittle.setFont(new java.awt.Font("Times New Roman", 3, 36)); // NOI18N
        lblAppTittle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAppTittle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/virus-remove-48.png"))); // NOI18N
        lblAppTittle.setText("Ứng dụng quản lý thông tin dịch bệnh Covid-19");
        lblAppTittle.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblAppTittle.setMaximumSize(new java.awt.Dimension(443, 30));
        lblAppTittle.setPreferredSize(new java.awt.Dimension(1001, 98));

        btnLogout.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logout.png"))); // NOI18N
        btnLogout.setText("Log out");
        btnLogout.setMaximumSize(new java.awt.Dimension(60, 30));
        btnLogout.setMinimumSize(new java.awt.Dimension(60, 25));
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        lblWelcome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblWelcome.setText("Welcome,");
        lblWelcome.setToolTipText("<br/>");
        lblWelcome.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout pnlWelcomeLayout = new javax.swing.GroupLayout(pnlWelcome);
        pnlWelcome.setLayout(pnlWelcomeLayout);
        pnlWelcomeLayout.setHorizontalGroup(
            pnlWelcomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlWelcomeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblWelcome, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAppTittle, javax.swing.GroupLayout.DEFAULT_SIZE, 1012, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlWelcomeLayout.setVerticalGroup(
            pnlWelcomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlWelcomeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlWelcomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblWelcome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLogout, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblAppTittle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pnlContent.setPreferredSize(new java.awt.Dimension(1217, 529));

        pnlButton.setMaximumSize(new java.awt.Dimension(200, 420));
        pnlButton.setMinimumSize(new java.awt.Dimension(200, 100));
        pnlButton.setPreferredSize(new java.awt.Dimension(203, 529));

        btnManagePerson.setBackground(new java.awt.Color(153, 255, 204));
        btnManagePerson.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnManagePerson.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/patients.png"))); // NOI18N
        btnManagePerson.setText("Patient Management");
        btnManagePerson.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnManagePerson.setMaximumSize(new java.awt.Dimension(115, 60));
        btnManagePerson.setPreferredSize(new java.awt.Dimension(130, 40));
        btnManagePerson.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManagePersonActionPerformed(evt);
            }
        });

        btnFind.setBackground(new java.awt.Color(255, 51, 255));
        btnFind.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnFind.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search.png"))); // NOI18N
        btnFind.setText("Find & Purchase");
        btnFind.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnFind.setMaximumSize(new java.awt.Dimension(115, 60));
        btnFind.setPreferredSize(new java.awt.Dimension(130, 40));
        btnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindActionPerformed(evt);
            }
        });

        btnProfile.setBackground(new java.awt.Color(52, 188, 52));
        btnProfile.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnProfile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/profile.png"))); // NOI18N
        btnProfile.setText("User Profile");
        btnProfile.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnProfile.setMaximumSize(new java.awt.Dimension(115, 60));
        btnProfile.setPreferredSize(new java.awt.Dimension(130, 40));
        btnProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProfileActionPerformed(evt);
            }
        });

        btnManageRelated.setBackground(new java.awt.Color(255, 204, 153));
        btnManageRelated.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnManageRelated.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/status_warn.png"))); // NOI18N
        btnManageRelated.setText("Status Management");
        btnManageRelated.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnManageRelated.setMaximumSize(new java.awt.Dimension(115, 60));
        btnManageRelated.setPreferredSize(new java.awt.Dimension(130, 40));
        btnManageRelated.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManageRelatedActionPerformed(evt);
            }
        });

        btnManagePackage.setBackground(new java.awt.Color(255, 0, 0));
        btnManagePackage.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnManagePackage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/package.png"))); // NOI18N
        btnManagePackage.setText("Package Management");
        btnManagePackage.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnManagePackage.setMaximumSize(new java.awt.Dimension(115, 60));
        btnManagePackage.setPreferredSize(new java.awt.Dimension(130, 40));
        btnManagePackage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManagePackageActionPerformed(evt);
            }
        });

        pnlFind1.setBackground(new java.awt.Color(51, 51, 255));
        pnlFind1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        pnlFind1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/about.png"))); // NOI18N
        pnlFind1.setText("About Us");
        pnlFind1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        pnlFind1.setMaximumSize(new java.awt.Dimension(115, 60));
        pnlFind1.setPreferredSize(new java.awt.Dimension(130, 40));
        pnlFind1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pnlFind1ActionPerformed(evt);
            }
        });

        pnlFind2.setBackground(new java.awt.Color(102, 255, 255));
        pnlFind2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        pnlFind2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/help.png"))); // NOI18N
        pnlFind2.setText("Help");
        pnlFind2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        pnlFind2.setMaximumSize(new java.awt.Dimension(115, 60));
        pnlFind2.setPreferredSize(new java.awt.Dimension(130, 40));
        pnlFind2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pnlFind2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlButtonLayout = new javax.swing.GroupLayout(pnlButton);
        pnlButton.setLayout(pnlButtonLayout);
        pnlButtonLayout.setHorizontalGroup(
            pnlButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlButtonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(pnlFind1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                    .addComponent(btnFind, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnManagePackage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnManageRelated, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnProfile, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnManagePerson, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlFind2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlButtonLayout.setVerticalGroup(
            pnlButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlButtonLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(btnProfile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(21, 21, 21)
                .addComponent(btnManagePerson, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(21, 21, 21)
                .addComponent(btnManageRelated, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(21, 21, 21)
                .addComponent(btnManagePackage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(21, 21, 21)
                .addComponent(btnFind, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(21, 21, 21)
                .addComponent(pnlFind1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(21, 21, 21)
                .addComponent(pnlFind2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(24, 24, 24))
        );

        tpnContent.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        tpnContent.setPreferredSize(new java.awt.Dimension(988, 529));

        javax.swing.GroupLayout pnlContentLayout = new javax.swing.GroupLayout(pnlContent);
        pnlContent.setLayout(pnlContentLayout);
        pnlContentLayout.setHorizontalGroup(
            pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tpnContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlContentLayout.setVerticalGroup(
            pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tpnContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        menuFile.setText("File");

        mitNew.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mitNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/add.png"))); // NOI18N
        mitNew.setText("New Window");
        menuFile.add(mitNew);

        mitReload.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mitReload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/refresh.png"))); // NOI18N
        mitReload.setText("Reload");
        mitReload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitReloadActionPerformed(evt);
            }
        });
        menuFile.add(mitReload);
        menuFile.add(sptFile);

        mitFileLogout.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mitFileLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logout.png"))); // NOI18N
        mitFileLogout.setText("Log out");
        mitFileLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitFileLogoutActionPerformed(evt);
            }
        });
        menuFile.add(mitFileLogout);

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");
        menuFile.add(jCheckBoxMenuItem1);

        menuBar.add(menuFile);

        menuEdit.setText("Edit");

        mitUndo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mitUndo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/undo.png"))); // NOI18N
        mitUndo.setText("Undo");
        menuEdit.add(mitUndo);

        mitRedo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mitRedo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/redo.png"))); // NOI18N
        mitRedo.setText("Redo");
        mitRedo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitRedoActionPerformed(evt);
            }
        });
        menuEdit.add(mitRedo);
        menuEdit.add(sptEditTop);

        mitCopy.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mitCopy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/copy.png"))); // NOI18N
        mitCopy.setText("Copy");
        mitCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitCopyActionPerformed(evt);
            }
        });
        menuEdit.add(mitCopy);

        mitCut.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mitCut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/cut.png"))); // NOI18N
        mitCut.setText("Cut");
        menuEdit.add(mitCut);

        mitPaste.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mitPaste.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/paste.png"))); // NOI18N
        mitPaste.setText("Paste");
        mitPaste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitPasteActionPerformed(evt);
            }
        });
        menuEdit.add(mitPaste);
        menuEdit.add(sptEditBottom);

        mitFind.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mitFind.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search.png"))); // NOI18N
        mitFind.setText("Find");
        menuEdit.add(mitFind);

        mitReplace.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mitReplace.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/replace.png"))); // NOI18N
        mitReplace.setText("Replace");
        menuEdit.add(mitReplace);

        menuBar.add(menuEdit);

        menuManagement.setText("Management");

        mitMUserProfile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mitMUserProfile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/profile.png"))); // NOI18N
        mitMUserProfile.setText("User Profile");
        menuManagement.add(mitMUserProfile);

        mitMPatientManagement.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mitMPatientManagement.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/patients.png"))); // NOI18N
        mitMPatientManagement.setText("Patient Management");
        menuManagement.add(mitMPatientManagement);

        mitMStatusManagement.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_3, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mitMStatusManagement.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/status_warn.png"))); // NOI18N
        mitMStatusManagement.setText("Status Management");
        menuManagement.add(mitMStatusManagement);

        mitMPackageManagement.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_4, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mitMPackageManagement.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/package.png"))); // NOI18N
        mitMPackageManagement.setText("Package Management");
        menuManagement.add(mitMPackageManagement);

        mitMFindAndPurchase.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_5, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mitMFindAndPurchase.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search.png"))); // NOI18N
        mitMFindAndPurchase.setText("Find & Purchase");
        menuManagement.add(mitMFindAndPurchase);

        menuBar.add(menuManagement);

        menuAboutUs.setText("Abous Us");
        menuBar.add(menuAboutUs);

        menuHelp.setText("Help");
        menuBar.add(menuHelp);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlWelcome, javax.swing.GroupLayout.DEFAULT_SIZE, 1228, Short.MAX_VALUE)
            .addComponent(pnlContent, javax.swing.GroupLayout.DEFAULT_SIZE, 1228, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlWelcome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProfileActionPerformed
        if (profileFlag == 0) {
            if (pfp == null){
                pfp = new ProfilePanel();
            }
            tpnContent.add("Thông tin cá nhân", pfp);
            profileFlag++;
            tpnContent.setSelectedComponent(pfp);

        } else {
            tpnContent.remove(pfp);
            profileFlag--;
        }
    }//GEN-LAST:event_btnProfileActionPerformed

    private void btnManageRelatedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManageRelatedActionPerformed
        if (statusFlag == 0) {
            if (rmp == null){
                rmp = new StatusManagementPanel();
            }
            tpnContent.add("Quản lý người tiếp xúc", rmp);
            statusFlag++;
            tpnContent.setSelectedComponent(rmp);

        } else {
            tpnContent.remove(rmp);
            statusFlag--;
        }
    }//GEN-LAST:event_btnManageRelatedActionPerformed

    private void btnManagePersonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManagePersonActionPerformed
        
        if (patientFlag == 0) {
            if (ptmp == null) {
                ptmp = new PatientManagementPanel();
            }
            tpnContent.add("Quản lý người liên quan", ptmp);
            patientFlag++;
            tpnContent.setSelectedComponent(ptmp);

        } else {
            tpnContent.remove(ptmp);
            patientFlag--;
        }
    }//GEN-LAST:event_btnManagePersonActionPerformed

    private void btnManagePackageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManagePackageActionPerformed

        if (packageFlag == 0) {
            if (pkmp == null){
                pkmp = new PackageManagementPanel();
            }
            tpnContent.add("Quản lý các gói hỗ trợ", pkmp);
            packageFlag++;
            tpnContent.setSelectedComponent(pkmp);

        } else {
            tpnContent.remove(pkmp);
            packageFlag--;
        }

    }//GEN-LAST:event_btnManagePackageActionPerformed

    private void pnlFind1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pnlFind1ActionPerformed
        if (aboutUsFlag == 0) {
            tpnContent.add("Về chúng tôi", abup);
            aboutUsFlag++;
            tpnContent.setSelectedComponent(abup);

        } else {
            tpnContent.remove(abup);
            aboutUsFlag--;
        }
    }//GEN-LAST:event_pnlFind1ActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        this.setVisible(false);
        new SignInFrame().setVisible(true);
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void pnlFind2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pnlFind2ActionPerformed
        if (helpFlag == 0) {
            tpnContent.add("Trợ giúp", hpn);
            helpFlag++;
            tpnContent.setSelectedComponent(hpn);

        } else {
            tpnContent.remove(hpn);
            helpFlag--;
        }
    }//GEN-LAST:event_pnlFind2ActionPerformed

    private void btnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindActionPerformed
        if (findFlag == 0) {
            if (fpn == null){
                fpn = new FindAndPurchasePanel();
            }
            tpnContent.add("Find & Purchase", fpn);
            findFlag++;
            tpnContent.setSelectedComponent(fpn);

        } else {
            tpnContent.remove(fpn);
            findFlag--;
        }

    }//GEN-LAST:event_btnFindActionPerformed

    private void mitReloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitReloadActionPerformed
        // TODO add your handling code here:
        if (packageFlag == 1){
            tpnContent.remove(pkmp);
            pkmp = null;
            packageFlag--;
        }
        if (aboutUsFlag == 1){
            tpnContent.remove(abup);
            aboutUsFlag--;
        }
        if (helpFlag == 1){
            tpnContent.remove(hpn);
            helpFlag--;
        }
        if (profileFlag == 1){
            tpnContent.remove(pfp);
            pfp = null;
            profileFlag--;
        }
        if (statusFlag == 1){
            tpnContent.remove(rmp);
            rmp = null;
            statusFlag--;
        }
        if (patientFlag == 1){
            tpnContent.remove(ptmp);
            ptmp = null;
            patientFlag--;
        }
        if (findFlag == 1){
            tpnContent.remove(fpn);
            fpn = null;
            findFlag--;
        }
    }//GEN-LAST:event_mitReloadActionPerformed

    private void mitFileLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitFileLogoutActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new SignInFrame().setVisible(true);
    }//GEN-LAST:event_mitFileLogoutActionPerformed

    private void mitCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitCopyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mitCopyActionPerformed

    private void mitRedoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitRedoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mitRedoActionPerformed

    private void mitPasteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitPasteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mitPasteActionPerformed
    
    
    private void AddActionPerformed(){
        mitNew.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitNewActionPerformed(evt);
            }
        });
    }
    
    private void mitNewActionPerformed(java.awt.event.ActionEvent evt){
        MainFrame newMainFrame = new MainFrame();
        newMainFrame.setVisible(true);
    }
    
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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFind;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnManagePackage;
    private javax.swing.JButton btnManagePerson;
    private javax.swing.JButton btnManageRelated;
    private javax.swing.JButton btnProfile;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JLabel lblAppTittle;
    private javax.swing.JLabel lblWelcome;
    private javax.swing.JMenu menuAboutUs;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuEdit;
    private javax.swing.JMenu menuFile;
    private javax.swing.JMenu menuHelp;
    private javax.swing.JMenu menuManagement;
    private javax.swing.JMenuItem mitCopy;
    private javax.swing.JMenuItem mitCut;
    private javax.swing.JMenuItem mitFileLogout;
    private javax.swing.JMenuItem mitFind;
    private javax.swing.JMenuItem mitMFindAndPurchase;
    private javax.swing.JMenuItem mitMPackageManagement;
    private javax.swing.JMenuItem mitMPatientManagement;
    private javax.swing.JMenuItem mitMStatusManagement;
    private javax.swing.JMenuItem mitMUserProfile;
    private javax.swing.JMenuItem mitNew;
    private javax.swing.JMenuItem mitPaste;
    private javax.swing.JMenuItem mitRedo;
    private javax.swing.JMenuItem mitReload;
    private javax.swing.JMenuItem mitReplace;
    private javax.swing.JMenuItem mitUndo;
    private javax.swing.JPanel pnlButton;
    private javax.swing.JPanel pnlContent;
    private javax.swing.JButton pnlFind1;
    private javax.swing.JButton pnlFind2;
    private javax.swing.JPanel pnlWelcome;
    private javax.swing.JPopupMenu.Separator sptEditBottom;
    private javax.swing.JPopupMenu.Separator sptEditTop;
    private javax.swing.JPopupMenu.Separator sptFile;
    private javax.swing.JTabbedPane tpnContent;
    // End of variables declaration//GEN-END:variables
}
