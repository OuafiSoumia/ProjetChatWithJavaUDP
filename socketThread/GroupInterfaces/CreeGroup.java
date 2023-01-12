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

public class CreeGroup extends JFrame{
    public  void creeG(int id_User){
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


        auth = new JLabel("Creation Groupe");
        auth.setBounds(50, 10, 400, 70);
        auth.setFont(fauth);
        JPanel panel = new JPanel(null);
        panel.add(auth);
        GroupName = new JLabel("GroupName: ");
        GroupName.setFont(font);
        IdUserAmis = new JLabel("Id_Amis: ");
        IdUserAmis.setFont(font);
        tGroupName = new JTextField();
        tIdUserAmis = new JTextField(){
            public void processKeyEvent(KeyEvent ev) {
                char c = ev.getKeyChar();
                if (c >= 48 && c <= 57) {
                    super.processKeyEvent(ev);
                }
            }
        };

        button = new JButton("enregistrer");

        button.setFont(fb);

        IdUserAmis.setBounds(50, 100, 140, 40);
        GroupName.setBounds(50, 300, 140, 40);
        tIdUserAmis.setBounds(170, 100, 250, 40);
        tGroupName.setBounds(170, 300, 250, 40);

        button.setBounds(190, 350, 100, 40);
        button.setBackground(Color.blue);
        button.setForeground(Color.white);


        panel.add(IdUserAmis);
        panel.add(tIdUserAmis);
        panel.add(GroupName);
        panel.add(tGroupName);

        panel.add(button);


        //   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setSize(700, 600);
        this.setContentPane(panel);

        button.addActionListener((event)->{
            try {
                ( new ConnexionOpperation()).CreeGroupe(id_User,tGroupName.getText(), Integer.parseInt(tIdUserAmis.getText()));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });


    }
}
