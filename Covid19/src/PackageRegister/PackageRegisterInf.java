/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PackageRegister;

import DbConnection.SQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class PackageRegisterInf {
        public static List<PackageRegister> searchRegisters(String personID){
        String sql = "SELECT pk.PERSONID, p.NAME, pk.PACKAGEID, pk.RECORD, pk.QUANTITY, p.PRICE, p.PRICE*pk.QUANTITY AS TOTAL \n" +
" FROM dbo.PACKAGEREGISTER pk JOIN dbo.PACKAGE p ON p.ID = pk.PACKAGEID WHERE pk.PERSONID = ?";
        List<PackageRegister> lst = new ArrayList<>();
        try (
                 Connection connection = SQLConnection.getConnection();    
                PreparedStatement pstmt = connection.prepareStatement(sql);
            ) {
    
            pstmt.setString(1, personID);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                PackageRegister pk = new PackageRegister(rs.getString("PERSONID"),rs.getString("PACKAGEID"),rs.getString("NAME"),rs.getTimestamp("RECORD").toLocalDateTime(),rs.getInt("PRICE"), rs.getFloat("TOTAL"), (int) rs.getFloat("QUANTITY"));
                lst.add(pk);
            }
            
            return lst;
        } catch (SQLException e) {
            System.out.println("Can't add register package");
            e.printStackTrace();
        }
        return null;
    }
        
}
