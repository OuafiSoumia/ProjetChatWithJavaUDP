package socketThread.AmisConnecterInterface;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import socketThread.entities.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AmisConnecter extends Application{

    private  ObservableList<String> AllGroups=FXCollections.observableArrayList();

    public AmisConnecter() throws SQLException, ClassNotFoundException {
        List<User> allConnectUser=new User().listAmisConnecter();
        for (User all:allConnectUser){
            if (!AllGroups.contains(all.name))
            AllGroups.add(all.name);

        }
    }

    public void start(Stage primaryStage) {


        ListView<String> list = new ListView<String>(AllGroups);
        list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


        FlowPane root = new FlowPane();
        root.getChildren().add(list);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("List Amis CoNNECTER");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}

