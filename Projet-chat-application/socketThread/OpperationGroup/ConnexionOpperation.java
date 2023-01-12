package socketThread.OpperationGroup;

import socketThread.entities.User;
import socketThread.entities.group;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ConnexionOpperation {
    Connection con = null;

    public List<User> getListUser() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con= DriverManager.getConnection("jdbc:mysql://localhost/chatreseau", "root","");
        Statement statement = con.createStatement();
        String requete = "SELECT * FROM utilisateur";
        ResultSet result = statement.executeQuery(requete);
        List<User> objs = new LinkedList<>();
        while (result.next()){

            User obj = new User();
            obj.id =  result.getInt("id_user");
            obj.name = result.getString("username");
            obj.login = result.getString("login");
            obj.passwd = result.getString("passwd");
            objs.add(obj);
        }
        User[] tab = new User[objs.size()];
        objs.toArray(tab);
        return objs;
    }
    public User getUser(int id) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con= DriverManager.getConnection("jdbc:mysql://localhost/chatreseau", "root","");
        Statement statement = con.createStatement();
        String requete = "SELECT * FROM `utilisateur` where id_user='"+id+"';";
        ResultSet result = statement.executeQuery(requete);

            User obj = new User();
            while (result.next()){
                obj.id =  result.getInt("id_user");
                obj.name = result.getString("username");
                obj.login = result.getString("login");
                obj.passwd = result.getString("passwd");
            }


        return obj;
    }
    public int getIdUser(String login,String mdp) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con= DriverManager.getConnection("jdbc:mysql://localhost/chatreseau", "root","");
        Statement statement = con.createStatement();
        String requete = "SELECT * FROM utilisateur where login='"+login+"' and passwd='"+mdp+"'";
        ResultSet result = statement.executeQuery(requete);
        int i=0;
        while (result.next()){
            System.out.println(result.getInt("id_user"));
             i= result.getInt("id_user");
        }
        return i;
    }




    public void CreeGroupe(int IdUser,String NameGroup,int IdAmis) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        con= DriverManager.getConnection("jdbc:mysql://localhost/chatreseau", "root","");

            String sql= "INSERT INTO `group`(`id_user_creator`, `groupe_name`, `id_user_amis`) VALUES ('"+IdUser+"','g_"+ NameGroup+"','"+ IdAmis+"');";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            boolean ex=preparedStatement.execute();
            if (ex){
                JOptionPane.showMessageDialog(null, "le groupe est cree avec succsses!");
            }
            else
                JOptionPane.showMessageDialog(null, "le groupe est cree avec succsses!");

        }
    public void SupprimerGroupe(int IdUser,String NameGroup) throws ClassNotFoundException, SQLException {


        Class.forName("com.mysql.cj.jdbc.Driver");
        con= DriverManager.getConnection("jdbc:mysql://localhost/chatreseau", "root","");

        String sql= "delete  from  `group` where `id_user_creator`='"+IdUser+"' and `groupe_name`='"+NameGroup+"'";

        PreparedStatement preparedStatement = con.prepareStatement(sql);
        boolean ex=preparedStatement.execute();
        if (ex){
            JOptionPane.showMessageDialog(null, "le group est supprimer avec succsses!");
        }
        else
            JOptionPane.showMessageDialog(null, "le group est supprimer avec succsses!");

    }

    public List<User> getListAmisInGroup(int id,String nom) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con= DriverManager.getConnection("jdbc:mysql://localhost/chatreseau", "root","");
        Statement statement = con.createStatement();
        System.out.println("ana f opperation id_user_creator"+id+" le nom "+nom);
             // JOptionPane.showMessageDialog(null, "l'id "+id+" le nom "+nom);

        String requete = "SELECT * FROM `group` where `id_user_creator`='" + id + "' and `groupe_name`='" + nom + "';";
        ResultSet result = statement.executeQuery(requete);
      //  ResultSetMetaData resultat = result.getMetaData();
        List<User> objs = new LinkedList<>();
        while (result.next()){

            int id_amis=result.getInt("id_user_amis");
            objs.add(getUser(id_amis));
        }
        User[] tab = new User[objs.size()];
        objs.toArray(tab);
        return objs;
    }

    public ArrayList<group> getUsersGroup(int id) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con= DriverManager.getConnection("jdbc:mysql://localhost/chatreseau", "root","");
        Statement statement = con.createStatement();
        // JOptionPane.showMessageDialog(null, "l'id "+id+" le nom "+nom);

        String requete = "SELECT * FROM `group` where `id_user_creator`='" + id + "' ;";
        ResultSet result = statement.executeQuery(requete);
        ArrayList<group> objs = new ArrayList<>();
        while (result.next()){
            group obj = new group();

            obj.setIdGroup(result.getInt("id_group"));
            obj.setIdCreator(result.getInt("id_user_creator"));
            obj.setGroupName(result.getString("groupe_name"));
            obj.setIdAmis(result.getInt("id_user_amis"));

            objs.add(obj);
        }
        group[] tab = new group[objs.size()];
        objs.toArray(tab);
        return objs;

    }

    public int getCreatorGroup(String nameG) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con= DriverManager.getConnection("jdbc:mysql://localhost/chatreseau", "root","");
        Statement statement = con.createStatement();
        // JOptionPane.showMessageDialog(null, "l'id "+id+" le nom "+nom);

        String requete = "SELECT * FROM `group` where `groupe_name`='" + nameG + "';";
        ResultSet result = statement.executeQuery(requete);
        //  ResultSetMetaData resultat = result.getMetaData();
        int id_creator=0;
        while (result.next()){

            id_creator=result.getInt("id_user_creator");
        }
        return id_creator;
    }
    //    public void ModifierGroup(int IdUser,String NameGroup,int IdAmis) throws ClassNotFoundException, SQLException {
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        con= DriverManager.getConnection("jdbc:mysql://localhost/chatreseau", "root","");
//
//        String sql= "INSERT INTO `group` (id_user_creator, groupe_name, id_user_amis) VALUES ('g_"+IdUser+"','"+ NameGroup+"','"+ IdAmis+"');";
//        PreparedStatement preparedStatement = con.prepareStatement(sql);
//        boolean ex=preparedStatement.execute();
//        if (ex){
//            JOptionPane.showMessageDialog(null, "le groupe est cree avec succsses!");
//        }
//        else
//            JOptionPane.showMessageDialog(null, "Connexion refusée!");
//
//
//    }


//    public group getGroupByIdAndName(int id, String name) throws ClassNotFoundException, SQLException {
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        con = DriverManager.getConnection("jdbc:mysql://localhost/chatreseau", "root", "");
//        Statement statement = con.createStatement();
//        String requete = "SELECT * FROM `group` where `id_user_creator`='" + id + "' and `groupe_name`='" + name + "';";
//        JOptionPane.showMessageDialog(null, "l'id "+id+" le nom "+name);
//
//        ResultSet result = statement.executeQuery(requete);
//        int id_creator=result.getInt("id_user_creator");
//        String NameGroup=result.getString("groupe_name");
//        int id_amis=result.getInt("id_user_amis");
//        group groupGet=new group(id_creator,NameGroup,id_amis);
//        return groupGet;
//    }
}

