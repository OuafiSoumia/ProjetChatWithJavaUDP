package socketThread.entities;

import socketThread.OpperationGroup.ConnexionOpperation;
import socketThread.OpperationSession.ConnexionSession;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    public int id;
    public String name;
    public String login;
    public  String passwd;
    public int Port=6000;

    public void User(int id, String name, String login, String passwd) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.passwd = passwd;
    }
    public List<User> listAmisConnecter() throws SQLException, ClassNotFoundException {
        ConnexionSession cs=new ConnexionSession();
        User amis=new User();
        List<User> ListAmis = new ArrayList<>();
        List<Integer> ids=cs.getListAmisConnecter();
        for (Integer id:ids){
            amis=(new User().getUserById(id));
            ListAmis.add(amis);
        }

        return  ListAmis;
    }
    public void DeleteAmisFromSession(int id) throws SQLException, ClassNotFoundException {
        ConnexionSession cs=new ConnexionSession();
        cs.DeleteUserFromSession(id);

    }
    public void addConnectAmis(User user) throws SQLException, ClassNotFoundException {
        ConnexionSession cs=new ConnexionSession();
        cs.AddUserToSession(user);
    }
    public List<User> ListUsers() throws SQLException, ClassNotFoundException {
        ConnexionOpperation co=new ConnexionOpperation();
      return co.getListUser();

    }  public User getUserById(int id) throws SQLException, ClassNotFoundException {
        ConnexionOpperation co=new ConnexionOpperation();
        return co.getUser(id);
    }
    public int getIdUser(String login,String mdp) throws SQLException, ClassNotFoundException {
        ConnexionOpperation co=new ConnexionOpperation();
        return co.getIdUser(login,mdp);
    }
    private void readObject(java.io.ObjectInputStream stream) throws IOException, ClassNotFoundException {
        id = (int) stream.readObject();
        name = (String) stream.readObject();
        login = (String) stream.readObject();
        passwd = (String) stream.readObject();
    }
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.writeObject(id);
        stream.writeObject(name);
        stream.writeObject(login);
        stream.writeObject(passwd);
    }

    public InetAddress getAddress() throws UnknownHostException {
        return InetAddress.getLocalHost();
    }
    public  int getPort(int id){
        if(id==1)
            return  6000;
         if (id==2)
            return  6001;
        if (id==3)
            return  6002;
        if (id==4)
            return  6003;
        if (id==5)
            return  6004;
        else return 0;
    }
    public String getNameByPort(String port) throws SQLException, ClassNotFoundException {
        if(port.equals("6000"))
            return this.getUserById(1).name;
        if (port.equals("6001"))
            return this.getUserById(2).name;
        if (port.equals("6002"))
            return this.getUserById(3).name;
        if (port.equals("6003"))
            return this.getUserById(4).name;
        if (port.equals("6004"))
            return this.getUserById(5).name;
else return "anonyme";
    }
    public int getIdByPort(int port){
        if(port== 6000)
            return 1;
        if (port==6001)
            return  2;
        if (port==6002)
            return  3;
        if (port==6003)
            return  4;
        if (port==6004)
            return  5;

        else return 0;
    }
}
