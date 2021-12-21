/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Related;

import java.time.LocalDateTime;

/**
 *
 * @author PC
 */
public class Related {
    private String srcID, desID;
    private LocalDateTime RecordDate;

    public Related() {
    }

    public Related(String srcID, String desID, LocalDateTime RecordDate) {
        this.srcID = srcID;
        this.desID = desID;
        this.RecordDate = RecordDate;
    }

    public String getSrcID() {
        return srcID;
    }

    public void setSrcID(String srcID) {
        this.srcID = srcID;
    }

    public String getDesID() {
        return desID;
    }

    public void setDesID(String desID) {
        this.desID = desID;
    }

    public LocalDateTime getRecordDate() {
        return RecordDate;
    }

    public void setRecordDate(LocalDateTime RecordDate) {
        this.RecordDate = RecordDate;
    }

    @Override
    public String toString() {
        return srcID + " " + desID + " " + RecordDate;
    }
    
}
