package com.example.login;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;

public class mainformcontroller implements Initializable {

    @FXML
    private Button Add_h;

    @FXML
    private TextField Adress_h;

    @FXML
    private TextField Client_txt;

    @FXML
    private ComboBox<String> Contract_c;

    @FXML
    private AnchorPane Contract_form;

    @FXML
    private Button Delete_h;

    @FXML
    private ComboBox<String> Status_c;

    @FXML
    private TableView<?> Table_h;

    @FXML
    private ComboBox<String> Type_h;

    @FXML
    private Button Update_h;

    @FXML
    private Button add_btn;

    @FXML
    private Button add_c;

    @FXML
    private TextField adress_txt;

    @FXML
    private TextField adresse;

    @FXML
    private Button client_btn;

    @FXML
    private Button client_btn1;

    @FXML
    private Button client_btn11;

    @FXML
    private Button client_btn2;

    @FXML
    private AnchorPane client_form;

    @FXML
    private TableView<?> clients_table;

    @FXML
    private TableView<?> contract_table;

    @FXML
    private TextField date_txt;

    @FXML
    private Button delete_btn;

    @FXML
    private Button delete_c;

    @FXML
    private AnchorPane estate_form;

    @FXML
    private TextField firstname;

    @FXML
    private AnchorPane house_manp;

    @FXML
    private Button logout_btn;

    @FXML
    private AnchorPane main_form;

    @FXML
    private AnchorPane mainform;

    @FXML
    private TextField name;

    @FXML
    private TextField phone;

    @FXML
    private TextField price_h;

    @FXML
    private TextField search_bar;

    @FXML
    private TableColumn<?, ?> table_adresse;

    @FXML
    private TableColumn<?, ?> table_firstname;

    @FXML
    private TableColumn<?, ?> table_name;

    @FXML
    private TableColumn<?, ?> table_phone;

    @FXML
    private Button update_btn;

    @FXML
    private Button update_c;

    private Alert alert;

    private String[] statusList = {"sold", "rented"};

    private String[] contractList = {"available", "unavailable"};

    private String[] typelist = {"Studio", "F2", "F3", "F4", "F5"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        statuscList();
        contractcList();
        typehlist();
    }

    public void statuscList() {
        List<String> statusL = new ArrayList<>();
        for (String data : statusList) {
            statusL.add(data);
        }
        ObservableList<String> listData = FXCollections.observableArrayList(statusL);
        Status_c.setItems(listData);
    }

    public void contractcList() {
        List<String> contractL = new ArrayList<>();
        for (String data : contractList) {
            contractL.add(data);
        }
        ObservableList<String> listData = FXCollections.observableArrayList(contractL);
        Contract_c.setItems(listData);
    }

    public void typehlist() {
        List<String> typeL = new ArrayList<>();
        for (String data : typelist) {
            typeL.add(data);
        }
        ObservableList<String> listData = FXCollections.observableArrayList(typeL);
        Type_h.setItems(listData);
    }

    public void addBTN1() {
        if (adress_txt.getText().isEmpty() || Client_txt.getText().isEmpty() || date_txt.getText().isEmpty() || Status_c.getSelectionModel().getSelectedItem() == null || Contract_c.getSelectionModel().getSelectedItem() == null) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all the fields");
            alert.showAndWait();
        } else if(adress_txt.getText().isEmpty() || Client_txt.getText().isEmpty() || date_txt.getText().isEmpty() || Status_c.getSelectionModel().getSelectedItem() == "sold" || Contract_c.getSelectionModel().getSelectedItem() == null){

            String proprietor = "Propriétaire";
            String tenant = Client_txt.getText();
            String address = adress_txt.getText();
            String startDate = date_txt.getText();
            String endDate = "31/12/2024";
            String monthlyRent = "1000";

            contractgenerator.generateRentalContract(proprietor, tenant, address, startDate, endDate, monthlyRent);

            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("sales contract generated successfully");
            alert.showAndWait();
        } else if  (adress_txt.getText().isEmpty() || Client_txt.getText().isEmpty() || date_txt.getText().isEmpty() || Status_c.getSelectionModel().getSelectedItem() == "rented" || Contract_c.getSelectionModel().getSelectedItem() == null) {

            String proprietor = "Propriétaire";
            String tenant = Client_txt.getText();
            String address = adress_txt.getText();
            String startDate = date_txt.getText();
            String endDate = "31/12/2024";
            String monthlyRent = "1000";

            contractgenerator.generateRentalContract(proprietor, tenant, address, startDate, endDate, monthlyRent);

            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Rental contract generated successfully");
            alert.showAndWait();
        }
    }

    public void addBTN3() {
        if (price_h.getText().isEmpty() || Adress_h.getText().isEmpty() || Type_h.getSelectionModel().getSelectedItem() == null) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all the fields");
            alert.showAndWait();
        }
    }

    public void logout() {
        try {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Logout");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get().equals(ButtonType.OK)) {
                logout_btn.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setTitle("Management1");
                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addbtn() {
        if (adresse.getText().isEmpty() || firstname.getText().isEmpty() || name.getText().isEmpty() || phone.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all the fields");
            alert.showAndWait();
        }
    }

    public void updatebtn() {
        if (adresse.getText().isEmpty() || firstname.getText().isEmpty() || name.getText().isEmpty() || phone.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all the fields");
            alert.showAndWait();
        }
    }

    public void deletebtn() {
        if (adresse.getText().isEmpty() || firstname.getText().isEmpty() || name.getText().isEmpty() || phone.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all the fields");
            alert.showAndWait();
        }
    }


    public void clientbtn() {
        client_form.setVisible(true);
        Contract_form.setVisible(false);
        estate_form.setVisible(false);

    }

    public void contractbtn() {
        client_form.setVisible(false);
        Contract_form.setVisible(true);
        estate_form.setVisible(false);

    }

    public void estatebtn() {
        client_form.setVisible(false);
        Contract_form.setVisible(false);
        estate_form.setVisible(true);

    }



}
