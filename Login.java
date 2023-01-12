
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;
import socketThread.client;
import socketThread.entities.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Login extends JFrame{
    JLabel auth;
    JLabel pseudo;
    JLabel password;
    JTextField tpseudo;
    JPasswordField tpass;
    JButton button;
    JButton button2;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Connection con = null;
    Font font = new Font(Font.SANS_SERIF, Font.BOLD, 18);
    Font fb = new Font(Font.SANS_SERIF, Font.BOLD, 12);
    Font fb2 = new Font(Font.SANS_SERIF, Font.BOLD, 12);
    Font fauth = new Font(Font.SANS_SERIF, Font.BOLD, 28);

    public void appel(){
        new JFXPanel();

        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                try {
                    int id=(new User()).getIdUser(tpseudo.getText(),tpass.getText());
                    client clt=new client(id);
                    //pour remplire la session des client connecter
                    (new User()).addConnectAmis(new User().getUserById(id));
                    clt.start(new Stage());

                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SocketException e) {
                    e.printStackTrace();
                }

            }
        });
    }
    public void log(String[] args) {
        auth = new JLabel("Système de Login");
        auth.setBounds(50, 10, 400, 70);
        auth.setFont(fauth);
        JPanel panel = new JPanel(null);
        panel.add(auth);
        pseudo = new JLabel("Pseudo");
        pseudo.setFont(font);
        password = new JLabel("Password");
        password.setFont(font);
        tpseudo = new JTextField();
        tpass = new JPasswordField();
        button = new JButton("Connecter");
        button2 = new JButton("Register");
        button.setFont(fb);
        button2.setFont(fb2);
        pseudo.setBounds(50, 100, 90, 40);
        password.setBounds(50, 200, 90, 40);
        tpseudo.setBounds(150, 100, 250, 40);
        tpass.setBounds(150, 200, 250, 40);
        button.setBounds(150, 250, 100, 40);
        button.setBackground(Color.blue);
        button.setForeground(Color.white);
        button2.setBounds(300, 250, 100, 40);
        button2.setBackground(Color.gray);
        button2.setForeground(Color.white);
        panel.add(pseudo);
        panel.add(password);
        panel.add(tpseudo);
        panel.add(tpass);
        panel.add(button);
        panel.add(button2);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!tpseudo.getText().isEmpty() || !tpass.getText().isEmpty()) {
                    con = Connexion.getConnect();
                    try {
                        ps = con.prepareStatement("SELECT login FROM utilisateur WHERE login=? AND passwd =?");
                        ps.setString(1, tpseudo.getText());
                        ps.setString(2, tpass.getText());
                        rs = ps.executeQuery();

                        if (rs.next()) {
                        //appel a la methode qui vas ouvrire la session du client
                            appel();

                        } else {
                            JOptionPane.showMessageDialog(null, "Connexion refusée!");
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Erreur de saisir!");
                }
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                (new Thread (new Register())).start();

                setVisible(true);
            }});


        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setSize(600, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel);
    }

    public static void main(String[] args) {
        (new Login()).log(args);



    }
}