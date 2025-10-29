package com.crudproject.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import com.crudproject.models.pwhasher;
import javafx.stage.Stage;


public class AddUserWindowController {
    @FXML
    private AnchorPane addUserWindow;

    Stage stage;
    @FXML
    private TextField tb_name;
    @FXML
    private TextField tb_email;
    @FXML
    private TextField tb_password;

    @FXML
    public void btn_add_click(ActionEvent event) {
        String Name = tb_name.getText();
        String Email = tb_email.getText();
        String password = tb_password.getText();
        pwhasher pwhasher = new pwhasher();
        try{
            pwhasher.hashPassword(Name, password, Email);
            stage = (Stage) addUserWindow.getScene().getWindow();
            stage.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }


}
