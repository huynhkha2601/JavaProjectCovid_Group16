/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Related;

import DbConnection.SQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class RelatedInf {
    
    public static List<Related> getAllRelatedColumn(){
        List<Related> lst = new ArrayList<>();
        String sql = "select * from RELATED order by REPLICATE(' ',6-LEN(PERSON_ID1)) + PERSON_ID1";
        try (
               Connection connection = SQLConnection.getConnection();    
               PreparedStatement pstmt = connection.prepareStatement(sql);){
           
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                Related rlt = new Related(rs.getString("PERSON_ID1"),rs.getString("PERSON_ID2"),
                rs.getTimestamp("RECORD").toLocalDateTime());
                
                lst.add(rlt);
            }
            
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static boolean addRelated(Related rlt){
        String sql = "insert into RELATED VALUES (?,?,?)";
        try (
               Connection connection = SQLConnection.getConnection();    
               PreparedStatement pstmt = connection.prepareStatement(sql);){
            
            pstmt.setString(1, rlt.getSrcID());
            pstmt.setString(2, rlt.getDesID());
            pstmt.setTimestamp(3, java.sql.Timestamp.valueOf(rlt.getRecordDate()));
            
            pstmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
   public static boolean removeRelated(Related rlt){
        String sql = "Delete from RELATED where PERSON_ID1=? AND PERSON_ID2=? AND RECORD=?";
        try (
               Connection connection = SQLConnection.getConnection();    
               PreparedStatement pstmt = connection.prepareStatement(sql);){
            
            pstmt.setString(1, rlt.getSrcID());
            pstmt.setString(2, rlt.getDesID());
            pstmt.setTimestamp(3, java.sql.Timestamp.valueOf(rlt.getRecordDate()));
            
            pstmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
   
     public static boolean searchRelated(Related rlt){
        String sql = "select* from RELATED where PERSON_ID1=? AND PERSON_ID2=? AND RECORD=?";
        try (
               Connection connection = SQLConnection.getConnection();    
               PreparedStatement pstmt = connection.prepareStatement(sql);){
            
            pstmt.setString(1, rlt.getSrcID());
            pstmt.setString(2, rlt.getDesID());
            pstmt.setTimestamp(3, java.sql.Timestamp.valueOf(rlt.getRecordDate()));
            
            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
   
}
