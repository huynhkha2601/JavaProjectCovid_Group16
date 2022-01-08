/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package covid19;

import Helper.DateFormatter;
import covid19.AccountFrame.SignInFrame;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import Account.Account;
import covid19.AccountFrame.SignUpFrame;

public class Covid19Main {

    public static void main(String[] args) {
        Account a = new Account();
        if (a.check() == 1) {
            new SignInFrame().setVisible(true);
        } else if (a.check() == 2) {
            new SignUpFrame(1).setVisible(true);
        }
    }

}
