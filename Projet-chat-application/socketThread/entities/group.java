package socketThread.entities;

import socketThread.OpperationGroup.ConnexionOpperation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class group {
        private int idGroup;
        private int idCreator;
        private String groupName;
        private int idAmis;


    public group(int id,int idCreator, String groupName,int idAmis) {
        this.idCreator = idCreator;
        this.groupName = groupName;
        this.idAmis=idAmis;
        this.idGroup=id;
    }

    public int getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(int idGroup) {
        this.idGroup = idGroup;
    }

    public group(int idCreator, String groupName) {
        this.idCreator = idCreator;
        this.groupName = groupName;
    }

    public group() {

    }

//    public group getGroupByIdAndName(int id,String name) throws SQLException, ClassNotFoundException {
//
//        ConnexionOpperation co=new ConnexionOpperation();
//        return co.getGroupByIdAndName(id,name);
//    }
    public List<User> getListUsersInGroupe(int id,String nom) throws SQLException, ClassNotFoundException {
        ConnexionOpperation co=new ConnexionOpperation();
        return co.getListAmisInGroup(id,nom);
    }
    public  ArrayList<group> getInfoGroupUser(int id) throws SQLException, ClassNotFoundException {
        ConnexionOpperation co=new ConnexionOpperation();
        return co.getUsersGroup(id);
    }

    public int getCreatorGroupByNameGroup(String nameG) throws SQLException, ClassNotFoundException {
        ConnexionOpperation co=new ConnexionOpperation();
        return co.getCreatorGroup(nameG);
    }

    public int getIdAmis() {
        return idAmis;
    }

    public void setIdAmis(int idAmis) {
        this.idAmis = idAmis;
    }

    public int getIdCreator() {
        return idCreator;
    }

    public void setIdCreator(int idCreator) {
        this.idCreator = idCreator;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }



}
