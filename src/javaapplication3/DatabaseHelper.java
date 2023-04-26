/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication3;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 *
 * @author caoductin
 */
public class DatabaseHelper {
     private Connection connection;

    public DatabaseHelper() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost/quanliduan", "root", "1234");
    }

    public void deleteData(String id,String sqlQuery) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sqlQuery);
        statement.setString(1, id);
        statement.executeUpdate();
    statement.close();
    }

    public void close() throws SQLException {
        connection.close();
    }
    
}
