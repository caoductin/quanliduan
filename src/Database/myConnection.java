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
//        
            Connection con = null;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanliduan", "root", "1234"); 
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
