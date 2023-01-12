package socketThread.OpperationSession;

import socketThread.entities.User;

import javax.swing.*;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ConnexionSession {
    Connection con = null;

    public List<Integer> getListAmisConnecter() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con= DriverManager.getConnection("jdbc:mysql://localhost/chatreseau", "root","");
        Statement statement = con.createStatement();
        String requete = "SELECT * FROM session ";
        ResultSet result = statement.executeQuery(requete);
        List<Integer> id = new LinkedList<>();
        while (result.next()){
            id.add(result.getInt("id_userConnecter"));
        }

        return id;
    }
    public void AddUserToSession(User user) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        con= DriverManager.getConnection("jdbc:mysql://localhost/chatreseau", "root","");

        String sql= "INSERT INTO `Session`(`id_userConnecter`) VALUES ('"+user.id+"');";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        boolean ex=preparedStatement.execute();


    }
    public void DeleteUserFromSession(int IdUser) throws ClassNotFoundException, SQLException {


        Class.forName("com.mysql.cj.jdbc.Driver");
        con= DriverManager.getConnection("jdbc:mysql://localhost/chatreseau", "root","");

        String sql= "delete  from  `Session` where `id_userConnecter`='"+IdUser+"'";

        PreparedStatement preparedStatement = con.prepareStatement(sql);
        boolean ex=preparedStatement.execute();


    }
}
