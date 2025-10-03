package com.crudproject.controllers;

import com.crudproject.models.User;
import com.crudproject.models.UserRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import com.crudproject.models.pwhasher;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class OverviewWindowController implements Initializable {
    @FXML
    ListView<User> lv_users;
    @FXML
    TextField tf_username;
    @FXML
    TextField tf_user_email;
    @FXML
    TextField tf_user_password;

    private UserRepository userRepository = new UserRepository();


    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initializing OverviewWindowController");
        populate_user_lv();
    }

    private void populate_user_lv() {
        ObservableList<User> users = FXCollections.observableArrayList(userRepository.findAllUsers());
        lv_users.setItems(users);

        lv_users.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                ShowUserDetails(newValue);
            }
        });
    }

    private void ShowUserDetails(User user) {
        tf_username.setText(user.getName());
        tf_user_email.setText(user.getEmail());
        tf_user_password.setText(user.getPassword());
    }

    @FXML
    public void button_add_game_clicked() {

    }
    @FXML
    public void button_remove_game_clicked() {

    }
    @FXML
    public void button_edit_game_clicked() {

    }
    @FXML
    public void button_add_character_clicked() {

    }
    @FXML
    public void button_remove_character_clicked() {

    }
    @FXML
    public void button_edit_character_clicked() {

    }
    @FXML
    public void button_add_user_clicked() {
        String name = tf_username.getText();
        String email = tf_user_email.getText();
        String password = tf_user_password.getText();
        pwhasher pwhasher = new pwhasher();
        pwhasher.hashPassword(name, password, email);
        populate_user_lv();


    }
    @FXML
    public void button_remove_user_clicked() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to remove this user?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            User selectedUser = lv_users.getSelectionModel().getSelectedItem();
            if (selectedUser != null) {
                userRepository.DeleteUser(selectedUser);
                lv_users.getItems().remove(selectedUser);
                ClearUserDetails();
                populate_user_lv();
            }
        }
    }


    public void ClearUserDetails() {
        tf_username.setText("");
        tf_user_email.setText("");
        tf_user_password.setText("");
    }
    @FXML
    public void button_edit_user_clicked() {
        User selectedUser = lv_users.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            selectedUser.setEmail(tf_user_email.getText());
            selectedUser.setPassword(tf_user_password.getText());
            userRepository.UpdateUser(selectedUser);
        }
        else {
            System.out.println("No selected user");
        }
        System.out.println("button_edit_user_clicked");
    }
}
