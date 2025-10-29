package com.crudproject.controllers;

import com.crudproject.models.User;
import com.crudproject.models.UserRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.Console;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class UsersWindowController implements Initializable {

    @FXML
    private TextField tb_filter;
    @FXML
    private ListView<User> ListView_users;



    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        RefreshListBox();
    }
    private void RefreshListBox(){
        UserRepository userRepository = new UserRepository();
        ObservableList<User> users = FXCollections.observableArrayList(
            userRepository.findAllUsers()
                );
        ListView_users.setItems(users);
    }
    @FXML
    private void btn_add_click(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/crudproject/fxml/AddUserWindow.fxml"));
            Parent root = loader.load();

            Stage owner = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Stage dialog = new Stage();
            dialog.initOwner(owner);
            dialog.initModality(Modality.WINDOW_MODAL);     // blocks owner
            dialog.setTitle("Add User");
            dialog.setScene(new Scene(root));
            dialog.showAndWait();
            RefreshListBox();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    private void btn_delete_click(ActionEvent event) {
        User selectedUser = ListView_users.getSelectionModel().getSelectedItem();
        if(selectedUser == null){
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Deletion");
        alert.setHeaderText("Are you sure you want to delete this user?");
        alert.setContentText("This action cannot be undone.");

// Show the alert and wait for the user’s response
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            UserRepository userRepository = new UserRepository();
            userRepository.DeleteUser(selectedUser);
            RefreshListBox();
        }
    }
    @FXML
    private void btn_edit_click(ActionEvent event) {
        System.out.println("pressed button");
        try {

            User selectedUser = ListView_users.getSelectionModel().getSelectedItem();
            if(selectedUser == null){
                return;
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/crudproject/fxml/EditUserWindow.fxml"));
            Parent root = loader.load();
            // Get the controller of the Edit window
            EditUserWindowController controller = loader.getController();
            controller.setUser(selectedUser); // Pass the selected user

            Stage owner = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Stage dialog = new Stage();
            dialog.initOwner(owner);
            dialog.initModality(Modality.WINDOW_MODAL);
            dialog.setTitle("Edit User");
            dialog.setScene(new Scene(root));

            dialog.showAndWait();

        // After closing, refresh list
            RefreshListBox();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
