
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class Register extends JFrame implements Runnable{

        public void run() {
            JLabel auth;
            JLabel userName;
            JLabel login;
            JLabel password;
            JTextField tpseudo;
            JPasswordField tpass;
            JTextField tlog;
            JButton button;
            JButton button2;
            PreparedStatement ps = null;
            ResultSet rs = null;
            Connection con = null;
            Font font = new Font(Font.SANS_SERIF, Font.BOLD, 18);
            Font fb = new Font(Font.SANS_SERIF, Font.BOLD, 12);
            Font fb2 = new Font(Font.SANS_SERIF, Font.BOLD, 12);
            Font fauth = new Font(Font.SANS_SERIF, Font.BOLD, 28);


            auth = new JLabel("Système d'Authentification");
            auth.setBounds(50, 10, 400, 70);
            auth.setFont(fauth);
            JPanel panel = new JPanel(null);
            panel.add(auth);
            userName = new JLabel("userName: ");
            userName.setFont(font);
            login = new JLabel("login: ");
            login.setFont(font);
            password = new JLabel("Password: ");
            password.setFont(font);
            tpseudo = new JTextField();
            tlog = new JTextField();
            tpass = new JPasswordField();
            button = new JButton("enregistrer");
            button2 = new JButton("login");
            button.setFont(fb);
            button2.setFont(fb);
            login.setBounds(50, 300, 140, 40);
            password.setBounds(50, 200, 140, 40);
            userName.setBounds(50, 100, 140, 40);
            tpseudo.setBounds(170, 100, 250, 40);
            tpass.setBounds(170, 200, 250, 40);
            tlog.setBounds(170, 300, 250, 40);

            button.setBounds(190, 350, 100, 40);
            button.setBackground(Color.blue);
            button.setForeground(Color.white);
            button2.setBounds(300, 350, 100, 40);
            button2.setBackground(Color.gray);
            button2.setForeground(Color.white);
            panel.add(userName);
            panel.add(login);
            panel.add(password);
            panel.add(tpseudo);
            panel.add(tlog);
            panel.add(tpass);
            panel.add(button);
            panel.add(button2);

            this.setVisible(true);
            this.setLocationRelativeTo(null);
            this.setSize(700, 600);
            this.setContentPane(panel);

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    try {
                        Connection con = null;
                        ResultSet rs;
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        con= DriverManager.getConnection("jdbc:mysql://localhost/chatreseau", "root","");
                     //   Statement stmt=con.createStatement();//pour executer les requets sql
                        String sql= "INSERT INTO utilisateur (username, login, passwd) VALUES ('"+tpseudo.getText()+"','"+ tlog.getText()+"','"+ tpass.getText()+"');";
                        PreparedStatement  preparedStatement = con.prepareStatement(sql);
                        boolean ex=preparedStatement.execute();
                        if (ex){
                            JOptionPane.showMessageDialog(null, "le compte est cree avec succsses!");
                        }
                        else
                            JOptionPane.showMessageDialog(null, "Connexion refusée!");

                    }catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            });
            button2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setVisible(false);

                }
            });

        }
    }

