/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helper;

import Account.Account;
import Account.AccountBank;
import Record.ActivityHistory;
import Record.TransactionHistory;
import TreatmentPlace.TreatmentPlace;

/**
 *
 * @author ACER
 */
public class Comparators {
    final public AccountComparator  accCom = new AccountComparator();
    final public AccountBankComparator accbCom = new  AccountBankComparator();
    final public TreatmentPlaceComparator tpCom = new TreatmentPlaceComparator();
    final public ActivityHistoryComparator ahCom = new ActivityHistoryComparator();
    final public TransactionHistoryComparator thCom = new TransactionHistoryComparator();
}

class AccountComparator implements java.util.Comparator<Account>{
    @Override
    public int compare(Account acc1, Account acc2) {
        int result = acc1.getRole().compareToIgnoreCase(acc2.getRole());
        if (result == 0){
            result = acc1.getDatePublished().compareTo(acc2.getDatePublished());
            if (result == 0){
                result = acc1.getUsername().compareTo(acc2.getUsername());
            }
        }
        return result;
    }
}

class AccountBankComparator implements java.util.Comparator<AccountBank>{
    @Override
    public int compare(AccountBank accb1, AccountBank accb2) {
         int result = accb1.getRole().compareToIgnoreCase(accb2.getRole());
        if (result == 0){
            result = accb1.getDatePublished().compareTo(accb2.getDatePublished());
            if (result == 0){
                result = accb1.getBankid().compareTo(accb2.getBankid());
            }
        }
        return result;
    }
}

class TreatmentPlaceComparator implements java.util.Comparator<TreatmentPlace>{
    @Override
    public int compare(TreatmentPlace tp1, TreatmentPlace tp2) {
        int result = tp1.getID().compareToIgnoreCase(tp2.getID());
        if (result == 0){
            result = tp1.getName().compareTo(tp2.getName());
        }
        return result;
    }
}

class ActivityHistoryComparator implements java.util.Comparator<ActivityHistory>{
    @Override
    public int compare(ActivityHistory ah1, ActivityHistory ah2) {
        int result = ah1.getLoginDT().compareTo(ah2.getLoginDT());
        if (result == 0){
            result = ah1.getLogoutDT().compareTo(ah2.getLogoutDT());
        }
        return result;
    }
}

class TransactionHistoryComparator implements java.util.Comparator<TransactionHistory>{
    @Override
    public int compare(TransactionHistory th1, TransactionHistory th2) {
        return th1.getDate().compareTo(th2.getDate());
    }
}
