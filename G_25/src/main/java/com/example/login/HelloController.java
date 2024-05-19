package com.example.login;

import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HelloController {

    @FXML
    private Button si_LoginBTN;

    @FXML
    private Hyperlink si_forgot;

    @FXML
    private AnchorPane si_loginform;

    @FXML
    private PasswordField si_password;

    @FXML
    private TextField si_username;

    @FXML
    private Button side_createBTN;

    @FXML
    private Button side_alreadyhave;

    @FXML
    private AnchorPane side_form;

    @FXML
    private TextField su_answer;

    @FXML
    private PasswordField su_password;

    @FXML
    private ComboBox<?> su_questions;

    @FXML
    private Button su_signupbtn;

    @FXML
    private AnchorPane su_signupform;

    @FXML
    private TextField su_username;
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Alert alert;


    public void regBTN(){
        if(su_username.getText().isEmpty() || su_password.getText().isEmpty() || su_questions.getSelectionModel().getSelectedItem() == null || su_answer.getText().isEmpty() ){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all the fields");
            alert.showAndWait();
        }
        else{
            String regData = "INSERT INTO employee (username, password, questions, answer)" +"VALUES (?,?,?,?)";
            connect = database.connectDB();
             try{
                 assert connect != null;
                 prepare = connect.prepareStatement(regData);
                 prepare.setString(1, su_username.getText());
                 prepare.setString(2, su_password.getText());
                 prepare.setString(3, su_questions.getSelectionModel().getSelectedItem().toString());
                 prepare.setString(4, su_answer.getText());
                 prepare.executeUpdate();
                 alert = new Alert(Alert.AlertType.INFORMATION);
                 alert.setTitle("Information");
                 alert.setHeaderText(null);
                 alert.setContentText("Successfully registered");
                 alert.showAndWait();

                 su_username.setText("");
                 su_password.setText("");
                 su_questions.getSelectionModel().clearSelection();
                 su_answer.setText("");

                 TranslateTransition slider=new TranslateTransition();

                 slider.setNode(side_form);
                 slider.setToX(0);
                 slider.setDuration(Duration.seconds(.5));
                 slider.setOnFinished((ActionEvent e) -> {
                     side_alreadyhave.setVisible(false);
                     side_createBTN.setVisible(true);


                 });
                 slider.play();


             }catch (Exception e){e.printStackTrace();}
        }
    }
    public void loginBTN() throws IOException {
        if(su_username.getText().isEmpty() || su_password.getText().isEmpty() ){
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mainform.fxml")));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Management");
            stage.setMinHeight(600);
            stage.setMinWidth(1100);
            stage.setScene(scene);
            stage.show();
            si_LoginBTN.getScene().getWindow().hide();
        }
    }



    private String[] questionlist = {"what is your favourite color?", "where were you born?"};
    public void regLquestionList(){
        List<String> listQ=new ArrayList<>();
        for(String data:questionlist){
            listQ.add(data);
        }
        ObservableList  listdata = FXCollections.observableArrayList(listQ);
        su_questions.setItems(listdata);
    }


    public void switchForm(ActionEvent event){
        TranslateTransition slider = new TranslateTransition();
        if(event.getSource() == side_createBTN){
            slider.setNode(side_form);
            slider.setToX(300);
            slider.setDuration(Duration.seconds(.5));
            slider.setOnFinished((ActionEvent e) -> {
                side_alreadyhave.setVisible(true);
                side_createBTN.setVisible(false);
                regLquestionList();


            });
            slider.play();


        } else if(event.getSource() == side_alreadyhave){
            slider.setNode(side_form);
            slider.setToX(0);
            slider.setDuration(Duration.seconds(.5));
            slider.setOnFinished((ActionEvent e) -> {
                side_alreadyhave.setVisible(false);
                side_createBTN.setVisible(true);


            });
            slider.play();
        }
    }
}
