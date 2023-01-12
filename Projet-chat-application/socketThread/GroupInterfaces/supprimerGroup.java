package socketThread.GroupInterfaces;

import socketThread.OpperationGroup.ConnexionOpperation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.security.acl.Group;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class supprimerGroup extends JFrame{
    public  void DeleteG(int id_User){
        JLabel auth;
        JLabel GroupName;
        JLabel IdUserAmis;

        JTextField tGroupName;
        JTextField tIdUserAmis;
        JButton button;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 18);
        Font fb = new Font(Font.SANS_SERIF, Font.BOLD, 12);
        Font fb2 = new Font(Font.SANS_SERIF, Font.BOLD, 12);
        Font fauth = new Font(Font.SANS_SERIF, Font.BOLD, 28);


        auth = new JLabel("Supprimer Groupe");
        auth.setBounds(50, 10, 400, 70);
        auth.setFont(fauth);
        JPanel panel = new JPanel(null);
        panel.add(auth);
        GroupName = new JLabel("GroupName: ");
        GroupName.setFont(font);

        tGroupName = new JTextField();

        button = new JButton("Supprimer");

        button.setFont(fb);

        GroupName.setBounds(50, 100, 140, 40);
        tGroupName.setBounds(170, 100, 250, 40);

        button.setBounds(190, 150, 100, 40);
        button.setBackground(Color.blue);
        button.setForeground(Color.white);


        panel.add(GroupName);
        panel.add(tGroupName);

        panel.add(button);


        //   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setSize(700, 400);
        this.setContentPane(panel);

        button.addActionListener((event)->{
            try {
                ( new ConnexionOpperation()).SupprimerGroupe(id_User,tGroupName.getText());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });


    }
}
