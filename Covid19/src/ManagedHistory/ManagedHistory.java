/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ManagedHistory;

import java.util.Date;

/**
 *
 * @author PC
 */
public class ManagedHistory {
    private String mID, fromStatus, toStatus;
    private Date record;

    public ManagedHistory() {
    }

    public ManagedHistory(String mID, String fromStatus, String toStatus, Date record) {
        this.mID = mID;
        this.fromStatus = fromStatus;
        this.toStatus = toStatus;
        this.record = record;
    }

    public String getmID() {
        return mID;
    }

    public void setmID(String mID) {
        this.mID = mID;
    }

    public String getFromStatus() {
        return fromStatus;
    }

    public void setFromStatus(String fromStatus) {
        this.fromStatus = fromStatus;
    }

    public String getToStatus() {
        return toStatus;
    }

    public void setToStatus(String toStatus) {
        this.toStatus = toStatus;
    }

    public Date getRecord() {
        return record;
    }

    public void setRecord(Date record) {
        this.record = record;
    }

    @Override
    public String toString() {
        return this.mID + " " + this.fromStatus + " " + this.toStatus + " " + this.record;
    }
      
}
