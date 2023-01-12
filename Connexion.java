import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {

    public static String url = "jdbc:mysql://localhost/chatreseau";
    public static String user = "root";
    public static String password = "";
    public static String driver = "com.mysql.cj.jdbc.Driver";

    public static Connection getConnect() {
        Connection con = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
            if (con != null) {
                System.out.println(" connection etablit");
                return con;

            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();

        }

        return null;
    }
}