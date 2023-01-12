package socketThread.GroupInterfaces;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import socketThread.entities.group;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

import static javafx.application.Application.launch;

public class ListGroup extends JFrame{
    private  ObservableList<String> AllGroups=FXCollections.observableArrayList();
    public int idG;

    public ListGroup(int id) throws SQLException, ClassNotFoundException {
        this.idG=id;
        List<group> allConnectUser=new group().getInfoGroupUser(idG);
        for (group all:allConnectUser){
            if (!AllGroups.contains(all.getGroupName())){
                AllGroups.add(all.getGroupName());
            }

        }
    }

    public void start(Stage primaryStage) {

        ListView<String> list = new ListView<String>(AllGroups);
        list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


        FlowPane root = new FlowPane();
        root.getChildren().add(list);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Liste des groupes ");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}


