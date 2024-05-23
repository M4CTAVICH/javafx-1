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
import javafx.scene.control.cell.PropertyValueFactory;

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
    private TableView<House> Table_h;

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
    private TableColumn<?, ?> availability;

    @FXML
    private Button client_btn;

    @FXML
    private Button client_btn1;

    @FXML
    private Button client_btn2;

    @FXML
    private AnchorPane client_form;

    @FXML
    private TableView<Client> clients_table;

    @FXML
    private TableView<Contract> contract_table;

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
    private Button search_btn;

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
    private TableColumn<?, ?> table_address;

    @FXML
    private TableColumn<?, ?> table_adress1;

    @FXML
    private TableColumn<?, ?> table_adresse;

    @FXML
    private TableColumn<?, ?> table_client;

    @FXML
    private TableColumn<?, ?> table_date;

    @FXML
    private TableColumn<?, ?> table_firstname;

    @FXML
    private TableColumn<?, ?> table_name;

    @FXML
    private TableColumn<?, ?> table_phone;

    @FXML
    private TableColumn<?, ?> table_price;

    @FXML
    private TableColumn<?, ?> table_status;

    @FXML
    private TableColumn<?, ?> table_type;

    @FXML
    private Button update_btn;

    @FXML
    private Button update_c;



    private Alert alert;
    private ObservableList<Client> clientData;
    private ObservableList<Contract> contractData;
    private ObservableList<House> houseData;

    private String[] statusList = {"sold", "rented"};

    private String[] contractList = {"available", "unavailable"};

    private String[] typelist = {"Studio", "F2", "F3", "F4", "F5"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        statuscList();
        contractcList();
        typehlist();
        clientData = FXCollections.observableArrayList();
        table_adresse.setCellValueFactory(new PropertyValueFactory<>("address"));
        table_firstname.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        table_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        table_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));

        clients_table.setItems(clientData);
        contractData = FXCollections.observableArrayList();
        table_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        table_client.setCellValueFactory(new PropertyValueFactory<>("client"));
        table_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        table_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        availability.setCellValueFactory(new PropertyValueFactory<>("availability"));

        contract_table.setItems(contractData);
        houseData = FXCollections.observableArrayList();
        table_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        table_adress1.setCellValueFactory(new PropertyValueFactory<>("address"));
        table_type.setCellValueFactory(new PropertyValueFactory<>("type"));

        Table_h.setItems(houseData);

        // Initialize the ComboBox for house types
        Type_h.setItems(FXCollections.observableArrayList("Studio", "F1", "F2", "F3", "F4"));

        new searchfunction(Table_h, search_bar, houseData);


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
        if (adress_txt.getText().isEmpty() || Client_txt.getText().isEmpty() ||date_txt.getText().isEmpty() || Status_c.getSelectionModel().getSelectedItem() == null || Contract_c.getSelectionModel().getSelectedItem() == null) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all the fields");
            alert.showAndWait();
        } else {
            String address = adress_txt.getText();
            String client = Client_txt.getText();
            String date = date_txt.getText();
            String status = Status_c.getSelectionModel().getSelectedItem();
            String availability = Contract_c.getSelectionModel().getSelectedItem();

            Contract newContract = new Contract(address, client, date, status, availability);
            contractData.add(newContract);

            adress_txt.clear();
            Client_txt.clear();
            date_txt.clear();


            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Contract added successfully");
            alert.showAndWait();
        }
    }
    public void deleteContractBtn() {
        Contract selectedContract = contract_table.getSelectionModel().getSelectedItem();
        if (selectedContract != null) {
            contractData.remove(selectedContract);

            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Contract deleted successfully");
            alert.showAndWait();
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select a contract to delete");
            alert.showAndWait();
        }
    }
    @FXML
    public void updateContractBtn() {
        Contract selectedContract = contract_table.getSelectionModel().getSelectedItem();
        if (selectedContract != null) {
            String address = adress_txt.getText();
            String client = Client_txt.getText();
            String date = date_txt.getText();
            String status = "sold"; // You may need to retrieve this from a ComboBox
            String availability = "available"; // You may need to retrieve this from a ComboBox

            // Update the selected contract
            selectedContract.setAddress(address);
            selectedContract.setClient(client);
            selectedContract.setDate(date);
            selectedContract.setStatus(status);
            selectedContract.setAvailability(availability);

            // Refresh TableView
            contract_table.refresh();

            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Contract updated successfully");
            alert.showAndWait();
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select a contract to update");
            alert.showAndWait();
        }
    }




    public void addHouseBtn() {
        if (price_h.getText().isEmpty() || Adress_h.getText().isEmpty() || Type_h.getSelectionModel().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all the fields");
            alert.showAndWait();
        } else {
            String price = price_h.getText();
            String address = Adress_h.getText();
            String type = Type_h.getSelectionModel().getSelectedItem();

            House newHouse = new House(type, price, address);
            houseData.add(newHouse);

            price_h.clear();
            Adress_h.clear();
            Type_h.getSelectionModel().clearSelection();

            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("House added successfully");
            alert.showAndWait();
        }
    }
    public void deleteHouseBtn() {
        House selectedHouse = Table_h.getSelectionModel().getSelectedItem();
        if (selectedHouse != null) {
            houseData.remove(selectedHouse);

            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("House deleted successfully");
            alert.showAndWait();
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select a house to delete");
            alert.showAndWait();
        }
    }
    public void updateHouseBtn() {
        House selectedHouse = Table_h.getSelectionModel().getSelectedItem();
        if (selectedHouse != null) {
            String price = price_h.getText();
            String address = Adress_h.getText();
            String type = Type_h.getSelectionModel().getSelectedItem();

            if (price.isEmpty() || address.isEmpty() || type == null) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all the fields");
                alert.showAndWait();
            } else {
                selectedHouse.setPrice(price);
                selectedHouse.setAddress(address);
                selectedHouse.setType(type);

                // Refresh TableView
                Table_h.refresh();

                price_h.clear();
                Adress_h.clear();
                Type_h.getSelectionModel().clearSelection();

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("House updated successfully");
                alert.showAndWait();
            }
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select a house to update");
            alert.showAndWait();
        }
    }
    public void searchBtnClicked() {
        searchfunction.triggerSearch();
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
        } else {
            String address = adresse.getText();
            String firstName = firstname.getText();
            String clientName = name.getText();
            String clientPhone = phone.getText();

            Client newClient = new Client(address, firstName, clientName, clientPhone);
            clientData.add(newClient);

            adresse.clear();
            firstname.clear();
            name.clear();
            phone.clear();

            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Client added successfully");
            alert.showAndWait();
        }
    }

    public void updatebtn() {
        Client selectedClient = clients_table.getSelectionModel().getSelectedItem();
        if (selectedClient != null) {
            String address = adresse.getText();
            String firstName = firstname.getText();
            String clientName = name.getText();
            String clientPhone = phone.getText();

            // Update the selected client
            selectedClient.setAddress(address);
            selectedClient.setFirstName(firstName);
            selectedClient.setName(clientName);
            selectedClient.setPhone(clientPhone);

            // Refresh TableView
            clients_table.refresh();

            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Client updated successfully");
            alert.showAndWait();
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select a client to update");
            alert.showAndWait();
        }
    }

    public void deletebtn() {
        Client selectedClient = clients_table.getSelectionModel().getSelectedItem();
        if (selectedClient != null) {
            clientData.remove(selectedClient);

            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Client deleted successfully");
            alert.showAndWait();
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select a client to delete");
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
