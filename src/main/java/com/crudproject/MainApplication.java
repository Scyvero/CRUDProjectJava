package com.crudproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.crudproject.models.User;
import com.crudproject.models.databasehandler;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.crudproject.models.pwhasher;


import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/MainWindow.fxml"));
        if(root == null){
            System.out.println("Root is null");
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
        //pwhasher pwhasher = new pwhasher();
        //pwhasher.hashPassword("sil", "test123", "example@example.com");



    }
}