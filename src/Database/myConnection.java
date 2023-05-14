package Database;


//import com.sun.jdi.connect.spi.Connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class myConnection {
    
    
    
    
    private Connection connection;
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
                JOptionPane.showMessageDialog( null,"MySQL error: " + ex.getMessage(), "MySQL Error", JOptionPane.ERROR_MESSAGE);
            }
            return con;
    
      }
        public void deleteData(String id, String sqlQuery) throws SQLException {
            //"DELETE FROM Nhanvien WHERE id = ?"
        PreparedStatement statement = getConnection().prepareStatement(sqlQuery);
        statement.setString(1, id);
        statement.executeUpdate();
        statement.close();
    }

}
