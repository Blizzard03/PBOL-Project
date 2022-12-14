/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.Tableviews.Master.User;

import Controller.MainMenu.Main_Menu2Controller;
import Controller.Update.Master.User.UpdateUserController;
import Models.Master.User.UsersModels;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mariq
 */
public class TableDataUserController implements Initializable {

    @FXML
    private TableView<UsersModels> TableViewUser;
    @FXML
    private TextField DataUserSearch;
    @FXML
    private Button FirstButton;
    @FXML
    private Button BeforeButon;
    @FXML
    private Button LastButon;
    @FXML
    private Button AfterButon;
    @FXML
    private Button MainMenuButtoon;
    @FXML
    private Button UpdateButton;
    @FXML
    private Button DeleteButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DataShows();
    }

    @FXML
    private void FirstButtonClick(ActionEvent event) {
        TableViewUser.getSelectionModel().selectFirst();
        TableViewUser.requestFocus();
    }

    @FXML
    private void BeforeButonClick(ActionEvent event) {
        TableViewUser.getSelectionModel().selectAboveCell();
        TableViewUser.requestFocus();
    }

    @FXML
    private void LastButtonClick(ActionEvent event) {
        TableViewUser.getSelectionModel().selectLast();
        TableViewUser.requestFocus();
    }

    @FXML
    private void AfterButonClick(ActionEvent event) {
          TableViewUser.getSelectionModel().selectBelowCell();        
        TableViewUser.requestFocus();

    }

    public void DataShows() {
        ObservableList<UsersModels> data = Main_Menu2Controller.Database_User.Load();
        if (data != null) {
            TableViewUser.getColumns().clear();
            TableViewUser.getItems().clear();
            TableColumn col = new TableColumn("ID_User");
            col.setCellValueFactory(new PropertyValueFactory<UsersModels, String>("ID_User"));
            TableViewUser.getColumns().addAll(col);
            col = new TableColumn("Nama_User");
            col.setCellValueFactory(new PropertyValueFactory<UsersModels, String>("Nama_User"));
            TableViewUser.getColumns().addAll(col);
            col = new TableColumn("Email_User");
            col.setCellValueFactory(new PropertyValueFactory<UsersModels, String>("Email_User"));
            TableViewUser.getColumns().addAll(col);
            col = new TableColumn("Alamat_User");
            col.setCellValueFactory(new PropertyValueFactory<UsersModels, String>("Alamat_User"));
            TableViewUser.getColumns().addAll(col);
            col = new TableColumn("Nomor_Telepon_User");
            col.setCellValueFactory(new PropertyValueFactory<UsersModels, String>("Nomor_Telepon_User"));
            TableViewUser.getColumns().addAll(col);
            col = new TableColumn("Password");
            col.setCellValueFactory(new PropertyValueFactory<UsersModels, String>("Password"));
            TableViewUser.getColumns().addAll(col);
            TableViewUser.setItems(data);
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data kosong", ButtonType.OK);
            a.showAndWait();
            TableViewUser.getScene().getWindow().hide();;
        }
    }

    @FXML
    private void DataUserSearchFinder(KeyEvent event) {
        UsersModels s = new UsersModels();
        String key = DataUserSearch.getText();
       
        if (key !="") {
            ObservableList<UsersModels> data = Main_Menu2Controller.Database_User.CariUser(key, key);
            if (data != null) {
                TableViewUser.getColumns().clear();
                TableViewUser.getItems().clear();
                TableColumn col = new TableColumn("ID_User");
                col.setCellValueFactory(new PropertyValueFactory<UsersModels, String>("ID_User"));
                TableViewUser.getColumns().addAll(col);
                col = new TableColumn("Nama_User");
                col.setCellValueFactory(new PropertyValueFactory<UsersModels, String>("Nama_User"));
                TableViewUser.getColumns().addAll(col);
                col = new TableColumn("Email_User");
                col.setCellValueFactory(new PropertyValueFactory<UsersModels, String>("Email_User"));
                TableViewUser.getColumns().addAll(col);
                col = new TableColumn("Alamat_User");
                col.setCellValueFactory(new PropertyValueFactory<UsersModels, String>("Alamat_User"));
                TableViewUser.getColumns().addAll(col);
                col = new TableColumn("Nomor_Telepon_User");
                col.setCellValueFactory(new PropertyValueFactory<UsersModels, String>("Nomor_Telepon_User"));
                TableViewUser.getColumns().addAll(col);
                col = new TableColumn("Password");
                col.setCellValueFactory(new PropertyValueFactory<UsersModels, String>("Password"));
                TableViewUser.getColumns().addAll(col);
                TableViewUser.setItems(data);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data kosong", ButtonType.OK);
                a.showAndWait();
                TableViewUser.getScene().getWindow().hide();;
            }
        } else {
            DataShows();
        }
    }

    @FXML
    private void MainMenuButtonClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/MainMenu/Main_Menu2.fxml"));
            Parent root = (Parent) loader.load();
            Main_Menu2Controller MainMenu = loader.getController();
            MainMenu.GetUserInformation();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.setTitle("Main Menu");
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();
            MainMenuButtoon.getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void UpdateButtonClick(ActionEvent event) {
        UsersModels ss= new UsersModels();
        ss=TableViewUser.getSelectionModel().getSelectedItem();
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/FXML/Update/Master/User/UpdateUser.fxml"));    
        Parent root = (Parent)loader.load();
        UpdateUserController isidt=(UpdateUserController)loader.getController();
        isidt.execute(ss);                
        Scene scene = new Scene(root);
        Stage stg=new Stage();
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.setResizable(false);
        stg.setIconified(false);
        stg.setScene(scene);
        stg.setTitle("Update User Service");
        stg.showAndWait();
        UpdateButton.getScene().getWindow().hide();
        } catch (IOException e){   e.printStackTrace();   }
        DataShows();  
        UpdateButton.getScene().getWindow().hide();
    }

    @FXML
    private void DeleteButtonClick(ActionEvent event) {
    UsersModels ss= new UsersModels();
        ss=TableViewUser.getSelectionModel().getSelectedItem();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Are you Sure will be delete this data is permanent deleted?", ButtonType.YES, ButtonType.NO);
        a.showAndWait();
        if (a.getResult() == ButtonType.YES) {
            if (Main_Menu2Controller.Database_User.Delete_Data(ss.getID_User())) {
                Alert b = new Alert(Alert.AlertType.INFORMATION, "Data User have been Deleted", ButtonType.OK);
                b.showAndWait();
            } else {
                Alert b = new Alert(Alert.AlertType.ERROR, "Data User Fail to Delete", ButtonType.OK);
                b.showAndWait();
            }
            DataShows();
            FirstButtonClick(event);
        }
        
    }

    
}
