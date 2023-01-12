package socketThread;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import socketThread.AmisConnecterInterface.AmisConnecter;
import socketThread.GroupInterfaces.CreeGroup;
import socketThread.GroupInterfaces.ListGroup;
import socketThread.GroupInterfaces.supprimerGroup;
import socketThread.entities.User;


import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

public class client extends Application implements Runnable{
    PrintStream out;
    int port;

    DatagramSocket socket = new DatagramSocket();

    public User user;



    public  client(int id) throws SQLException, ClassNotFoundException, SocketException {
        User u=new User();
        this.user=u.getUserById(id);
        this.port=u.getPort(id);
        System.out.println("****** port "+port);
        System.out.println("****** id "+id);

    }
    public void run(){

            launch();

    }
    public void start(Stage primaryStage)  {

        Button btnQuit = new Button();
        btnQuit.setText("Quitter");
        btnQuit.setAlignment(Pos.TOP_LEFT);





        System.out.println(" client CONNECTER");

        BorderPane borderPane=new BorderPane();

        Label labelTitre=new Label("Hello To Chat Iterface");
        Label labelHost=new Label("Host:");
        Label labelPort=new Label("Port:");

        TextField textFieldHost=new TextField("localhost");
        TextField textFieldPort=new TextField("5000");

        Button buttonConnecter=new Button( "Connecter");
        Label labelMessage=new Label("Message:");
        TextField textFieldMessage=new TextField();
        textFieldMessage.setPrefSize(400,30);
        Button buttonEnvoyer=new Button("envoyer");

        Label labelPortEnvoyer=new Label("Send To Client ID :");
        TextField textFieldPortEnvoyer=new TextField("ALL");
        textFieldPortEnvoyer.setPrefSize(   80,30);
        //Top's components
        labelTitre.setPadding(new Insets(10));
        labelTitre.setBackground(new Background(new BackgroundFill(Color.RED,  new CornerRadii(1), null)));


        VBox vBox2=new  VBox();//vertical Box
        vBox2.setSpacing(10) ;
        vBox2.setPadding(new Insets(10));
        HBox hBox=new  HBox();//horizental Box
        hBox.setSpacing(10) ;
        hBox.setPadding(new Insets(10));
        hBox.getChildren().addAll(labelHost, textFieldHost, labelPort, textFieldPort,buttonConnecter,btnQuit);
        vBox2.getChildren().addAll(hBox,labelTitre);
        vBox2.setAlignment(Pos.TOP_CENTER);

        //le bloc du millieu
        HBox vBox=new  HBox();//vertical Box
        vBox.setSpacing(10) ;
        vBox.setPadding(new Insets(10));

        ObservableList<String> listModel= FXCollections.observableArrayList();
        //list des reponses
        ListView<String> listView=new ListView<String>(listModel);
      //  vBox.getChildren().add(listView);
        Label labelMessageAfficher=new Label("Message:");
        Label labelResponse=new Label("Response:");

        ObservableList<String> listModel2= FXCollections.observableArrayList();
        //list des messages
        ListView<String> listView2=new ListView<String>(listModel2);
        vBox.getChildren().addAll(labelMessageAfficher,listView2,labelResponse,listView);

//bloc du Bas
        HBox hBox2=new HBox();
        hBox2.setSpacing(10) ;
        hBox2.setPadding(new Insets(10));

        hBox2.getChildren().addAll(labelMessage,textFieldMessage,buttonEnvoyer,labelPortEnvoyer,textFieldPortEnvoyer);

        Button buttonCreateGroup=new Button( "Cree Groupe");
        Button buttonListAmisConnecter=new Button( "liste des amis Connecters");
        Button buttonListGroup =new Button( "liste des groupes");
        Button buttonDeleteGroup=new Button( "supprimer Groupe");
        HBox hBoxGroup=new HBox();
        hBoxGroup.setSpacing(10);
        hBoxGroup.setPadding(new Insets(10));
        hBoxGroup.getChildren().addAll(buttonCreateGroup,buttonListGroup,buttonDeleteGroup,buttonListAmisConnecter);
        hBoxGroup.setAlignment(Pos.TOP_CENTER);

        //hBox2 et hBoxGroup
        VBox vBoxBas=new VBox();
        vBoxBas.getChildren().addAll(hBox2,hBoxGroup);

        borderPane.setBottom(vBoxBas);
        borderPane.setCenter(vBox);
        borderPane.setTop(vBox2);
        Scene scene=new Scene(borderPane,800,600);
        primaryStage.setScene(scene);
        primaryStage.show();
    //pour fermer la session:
        btnQuit.setOnAction((ActionEvent event) -> {
            try {
                new User().DeleteAmisFromSession(user.id);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            primaryStage.close();

        });
    //pour ce connecter au serveur
        buttonConnecter.setOnAction((event) ->{
            String host=textFieldHost.getText();
            try {
                socket = new DatagramSocket(this.port);
                listModel.add("l'utilisateur  "+user.name+" est connecter");


//pour recevoir .....
        byte[] tampon2=new byte[1024];
        DatagramPacket dp2=new DatagramPacket(tampon2,tampon2.length);


                new Thread(()->{
                            while (true){


                                String responce= null;
                                try {
                                    socket.receive(dp2);

                                    responce=new String(dp2.getData(),0,tampon2.length).trim();

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                System.out.println("***** **** "+responce);

                                String resp[] = responce.split(":");
                                String repsender= null;
                                try {

                                    System.out.println("***** la repense est  port "+resp[1].substring(0,4));

                                    repsender = resp[0]+":"+user.getNameByPort(resp[1].substring(0, 4));
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                } catch (ClassNotFoundException e) {
                                    e.printStackTrace();
                                }
                                //ajouter la reponse a la liste dans l'interface du client
                                listModel.add(repsender);


                }     }
                ).start();

            } catch (IOException e) {
                e.printStackTrace();
            }

        } );
        //pour envoyer un message
        buttonEnvoyer.setOnAction((event)->{

            String message=textFieldMessage.getText()+":"+textFieldPortEnvoyer.getText();
            listModel2.add(message);
              int port=5000;
            byte[] tampon=message.getBytes(StandardCharsets.UTF_8);
            InetAddress adresse= null;
            try {
                adresse = InetAddress.getLocalHost();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            try {

                DatagramPacket dp=new DatagramPacket(tampon,tampon.length,adresse,port );
                socket.send(dp);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

//cree un group
        buttonCreateGroup.setOnAction((event)->{
            CreeGroup group=new CreeGroup();
            group.creeG(user.id);

        });
 //supprimer un group
        buttonDeleteGroup.setOnAction((event)->{
            supprimerGroup group=new supprimerGroup();
            group.DeleteG(user.id);

        });
//lister les amis connecters
        buttonListAmisConnecter.setOnAction((event)->{
            Platform.runLater(new Runnable()
            {
                @Override
                public void run()
                {
                    try {

                       AmisConnecter ami= new AmisConnecter();
                       ami.start(new Stage());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            });

        });
//liste des groupes cree par l'utilisateurs
        buttonListGroup.setOnAction((event)->{
            Platform.runLater(new Runnable()
            {
                @Override
                public void run()
                {
                    try {

                          ListGroup lg= new ListGroup(user.id);
                          lg.start(new Stage());

                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            });

        });

    }
    }






