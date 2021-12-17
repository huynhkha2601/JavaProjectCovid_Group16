/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helper;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author PC
 */
public class MessageDialog {
      
    public static void showMessageDialog(Component root, String msg, String title){
        JOptionPane.showMessageDialog(root, msg, title, JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void showErrorDialog(Component root, String message, String title){
        JOptionPane.showMessageDialog(root, message, title, JOptionPane.ERROR_MESSAGE);
    }
    
    public static int showConfirmDialog(Component root, String message, String title){
        return JOptionPane.showConfirmDialog(root, message, title, JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
    }
    
}
