/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package paymentsystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import Helper.AES;
import Payment.BankInf;
import Account.*;

/**
 *
 * @author Ashbell
 */
public class PaymentMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AccountBank ab = BankInf.getAdminAccountBank();
        if (ab.getBankid().equals("")) 
            AccountInf.addAdminBank();
        MainFrame mainFrame = new MainFrame();
        mainFrame.setTitle("Payment system");
        mainFrame.setVisible(true);
        try {
            ServerSocket server = null;
            server = new ServerSocket(3000);
            while(true) {
                Socket client = server.accept();
                new ClientThread(client, mainFrame);
            }
        } catch (IOException ex) {
            Logger.getLogger(PaymentMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

class ClientThread extends Thread {
    private Socket client;
    private MainFrame mainFrame;
    ClientThread(Socket client, MainFrame mainFrame) {
        this.client = client;
        this.mainFrame = mainFrame;
        start();
    }
    @Override
    public void run() {
        InputStream clientIn = null;
        OutputStream clientOut = null;
        try {
            clientIn = client.getInputStream();
            clientOut = client.getOutputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(clientIn));
            PrintWriter pw = new PrintWriter(clientOut, true);
            String command = br.readLine();
            if (command.equals("pay")) {
                String secretKey = "backspace";
                String encrytedContent = br.readLine();
                String decryptedContent = AES.decrypt(encrytedContent, secretKey);
                //System.out.println(decryptedContent);
                String content[] = decryptedContent.split("/");
                boolean temp = BankInf.addTransaction(content[0], Float.parseFloat(content[1]), content[2]);
                String check = Boolean.toString(temp);
                pw.println(check);
                if (check.equals("true")) {
                    mainFrame.refreshTable();
                    mainFrame.setBalance(Float.parseFloat(content[1]));
                }
            }
            else {
                String id = br.readLine();
                Account account = AccountInf.getAccountBank(id);
                boolean temp = AccountInf.addAccountBank(account);
                String check = Boolean.toString(temp);
                pw.println(check);
                //System.out.print(account.getPassword());
                //System.out.println(id);
            }
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (client != null) client.close();
                if (clientIn != null) clientIn.close();
                if (clientOut != null) clientOut.close();
            } catch (IOException ex) {
                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
