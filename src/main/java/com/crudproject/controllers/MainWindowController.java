package com.crudproject.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import com.crudproject.models.pwhasher;

public class MainWindowController {

    @FXML
    TextField tf_name;
    @FXML
    PasswordField tf_pass;

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
        }
        else {
            System.out.println("Password not verified");
        }

    }
}
