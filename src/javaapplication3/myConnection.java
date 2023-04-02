package javaapplication3;


//import com.sun.jdi.connect.spi.Connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class myConnection {
    public static Connection getConnection () {
//        Connection con = null;
//        try {
//            // Register MySQL JDBC driver
//            Class.forName("com.mysql.cj.jdbc.Driver");
//
//            // Open a connection to the database
//            con = DriverManager.getConnection("jdbc:mysql://localhost/javacontactapp", "root", " ");
//
//            // Do something with the connection, such as query the database
//            // ...
//
//        } catch (ClassNotFoundException | SQLException ex) {
//            ex.printStackTrace();
//        } finally {
//            // Close the connection
//            if (con != null) {
//                try {
//                    con.close();
//                } catch (SQLException ex) {
//                    ex.printStackTrace();
//                }
//            }
//        }
//        return con;
            Connection con = null;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost/quanliduan", "root", "tincnttk62"); 
            } catch(Exception ex){
                System.out.print(ex.getMessage());
            }
            return con;
    
      }
}
