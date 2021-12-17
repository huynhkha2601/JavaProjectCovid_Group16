/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helper;

import java.awt.Color;
import javax.swing.JTextField;

/**
 *
 * @author PC
 */
public class Validator {

    public static void validate(boolean statement, JTextField field, StringBuilder sb, String errorMessage) {
        if (statement) {
            sb.append(errorMessage).append("\n");
            field.setBackground(Color.red);
        } else {
            field.setBackground(Color.white);

        }
    }

    public static boolean validateEmpty(JTextField field) {
        return field.getText().equals("");
    }

    public static void validateEmpty(JTextField field, StringBuilder sb, String errorMessage) {
        if (field.getText().equals("")) {
            sb.append(errorMessage).append("\n");
            field.setBackground(Color.red);
            field.requestFocus();
        } else {
            field.setBackground(Color.white);
        }
    }

}
