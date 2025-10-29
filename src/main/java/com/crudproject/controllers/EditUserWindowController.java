package com.crudproject.controllers;

import com.crudproject.models.User;
import com.crudproject.models.UserRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EditUserWindowController {
    @FXML
    private AnchorPane EditUserWindow;
    Stage stage;

    @FXML
    private TextField tb_name;
    @FXML
    private TextField tb_email;
    private User CurrentUser;
    public void setUser(User user) {
        this.CurrentUser = user;

        // Fill text fields with user data
        tb_name.setText(user.getName());
        tb_email.setText(user.getEmail());
    }
    public void btn_edit_click(ActionEvent actionEvent) {
        try{
            String name = tb_name.getText();
            String email = tb_email.getText();
            CurrentUser.setName(name);
            CurrentUser.setEmail(email);


            UserRepository userRepository = new UserRepository();
            userRepository.UpdateUser(CurrentUser);
            stage = (Stage) EditUserWindow.getScene().getWindow();
            stage.close();
        }
        catch(Exception e){
            System.out.println(e);
        }


    }


}
