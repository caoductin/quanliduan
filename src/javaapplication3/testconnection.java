/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication3;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author caoductin
 */
public class testconnection {
    public static void main(String[] args){
        String user="root";
        String password= "tincnttk62";
        String url = "jdbc:mysql://localhost:3306/mysql";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection c;
            c = (Connection) DriverManager.getConnection(url,user,password);
            if(c!=null){
                System.out.print("suscess");
            }
            else{
                System.out.print("failed");
            }
        } catch (Exception ex) {
            Logger.getLogger(testconnection.class.getName()).log(Level.SEVERE, null, ex);
             System.out.print("failed");
        }
    }
    
}
