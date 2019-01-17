/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class New1Controller extends HeadOffice {

    /**
     * Initializes the controller class.
     */
    protected int account_no;
    @FXML
    private Button Confirmationbutton;

    @FXML
    private TextField name;

    @FXML
    private ChoiceBox<String> selectbranch;

    @FXML
    private Label brcode;

    @FXML
    private PasswordField PIN;

    @FXML
    private TextField amount;

    @FXML
    private TextField phone;

    @FXML
    private TextField address;

    @FXML
    private TextField CNIC;

    @FXML
    private TextField district;
    
    @FXML
    private Label labelacnumber;

    @FXML
    private Label check;
    
    @FXML
    private Button back;
    
    @FXML
    void getaccountnumber(ActionEvent event) {
        account_no = 10000000 + rnd.nextInt(90000000);
        labelacnumber.setText(Integer.toString(account_no));

    }

    @FXML
    void confirmdetails(ActionEvent event) throws IOException {
        //comfirm butto actions
        Writer output = new BufferedWriter(new FileWriter(path_requests, true));
        //name
        Information.add(name.getText());
        //accountnumber
        Information.add(labelacnumber.getText());
        //branch code
        Information.add(brcode.getText());
//pin

        int Pin = Integer.parseInt(PIN.getText());
//encryption
        Pin = (Pin ^ 957);
//convert and setting pin to encrypt string value
        PIN.setText(Integer.toString(Pin));
//adding info in array list
        Information.add(PIN.getText());
        //amount
        Information.add(amount.getText());
//phone
        Information.add(phone.getText());
//address
        Information.add(address.getText());
        //cnic
        Information.add(CNIC.getText());
        //district
        Information.add(district.getText());
        //province
        Information.add(province.getValue());
        //date
        Information.add(date1);
        
        //if fixed deposit then enter years
        if(selectAccount.getValue().equals("fixeddepositaccount")){
            if(duration.getValue().equals("1year")){
                profit = (Double.parseDouble(Information.get(4))) * (Math.pow((1 + (0.05 / 4)), 4));
                Information.add("1");
                Information.add(Double.toString(profit));
            }else if(duration.getValue().equals("3year")){
                profit = (Double.parseDouble(Information.get(4))) * (Math.pow((1 + (0.0522 / 4)), 4 * 3));
                Information.add("3");
                Information.add(Double.toString(profit));
            }else if(duration.getValue().equals("5year")){
                profit = (Double.parseDouble(Information.get(4))) * (Math.pow((1 + (0.0539 / 4)), 4 * 5));
                Information.add("5");
                Information.add(Double.toString(profit));
            }else if(duration.getValue().equals("10year")){
                profit = (Double.parseDouble(Information.get(4))) * (Math.pow((1 + (0.0635 / 4)), 4 * 10));
                Information.add("10");
                Information.add(Double.toString(profit));
            }
        }
        //select account
        Information.add(selectAccount.getValue());
        output.append("\n");
        for (int i = 0; i < Information.size(); i++) {
            output.append(Information.get(i) + ",");
        }
        output.close();
        check.setText("Your request has been proceeded successfully");
    }

    @FXML
    void getbrcode(ActionEvent event) {
        switch (selectbranch.getValue()) {
            case "North Nazimabad":
                brcode.setText("1605");
                break;
            case "Gulshan-e-Iqbal":
                brcode.setText("2548");
                ;
                break;
            case "Liaquatabad":
                brcode.setText("3749");
                break;
            case "I.I.Chundrigarh(head office)":
                brcode.setText("4535");
                break;
            case "Defence":
                brcode.setText("5302");
                break;
            case "Sharah_e_Faisal Branch":
                brcode.setText("6471");
                break;
        }
    }

    @FXML
    void handledisablityoffda(ActionEvent event) {
        if (selectAccount.getValue().equals("fixeddepositaccount")) {
            duration.setDisable(false);

        } else if ((selectAccount.getValue().equals("savingaccount")) || (selectAccount.getValue().equals("currentaccount")) || (selectAccount.getValue().equals("monthlysavingaccount"))) {
            duration.setDisable(true);
        }

    }

    @FXML
    private ComboBox<String> selectAccount;

    @FXML
    private ComboBox<String> duration;
    ObservableList<String> years = FXCollections.observableArrayList(
            "1year", "3year", "5year", "10year");

    ObservableList<String> AList = FXCollections.observableArrayList(
            "savingaccount", "currentaccount", "monthlysavingaccount", "fixeddepositaccount");
    ObservableList<String> branchlist = FXCollections.observableArrayList(
            "North Nazimabad", "Gulshan-e-Iqbal", "Liaquatabad", "I.I.Chundrigarh(head office)", "Defence", "Sharah_e_Faisal Branch");
    @FXML
    private ChoiceBox<String> province;
    ObservableList<String> prolist = FXCollections.observableArrayList(
            "Sindh", "Punjab", "Baluchistan", "Kheber pakhtun khwan", "FATA");

    public void initialize() {
        // TODO

        province.setItems(prolist);
        selectbranch.setItems(branchlist);
        selectAccount.setItems(AList);
        duration.setItems(years);
        //selectAccount.setDisable(true);
    }
    
    @FXML
    void back(ActionEvent event){
        try {
            Stage stage=(Stage) back.getScene().getWindow();
            stage.close();
            FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("/view/service.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage st = new Stage();
            st.setScene(new Scene(root1));
            st.show();
        } catch (IOException ex) {
            Logger.getLogger(New1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void initializeable() {

    }   
    
}
