package com.crudproject.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import com.crudproject.models.pwhasher;
import javafx.stage.Stage;

import java.io.IOException;

public class MainWindowController {

    @FXML
    TextField tf_name;
    @FXML
    PasswordField tf_pass;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void login_button_click(ActionEvent event) {
        System.out.println("Login clicked");
        String name = tf_name.getText();
        String password = tf_pass.getText();
        System.out.println("Name: " + name);
        System.out.println("Password: " + password);
        pwhasher pwhasher = new pwhasher();
        boolean verified = pwhasher.checkPassword(name, password);
        if (verified) {
            System.out.println("Password verified");
            try{
                root = FXMLLoader.load(getClass().getResource("/com/crudproject/fxml/UsersWindow.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        else {
            System.out.println("Password not verified");
        }

    }
}
