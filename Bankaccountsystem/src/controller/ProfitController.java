/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class ProfitController extends HeadOffice implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField brcode;

    @FXML
    private TextField acnumber;

    @FXML
    private TextField title;
    
    @FXML
    private PasswordField pin;

    @FXML
    private Label check;
    
    @FXML
    private Button back;
    
    private String path2=path_monsaving;
    
    @FXML
    void titleofaccount(ActionEvent event) {

    }

    @FXML
    void accountnumber(ActionEvent event) {

    }

    @FXML
    void branchcode(ActionEvent event) {

    }
    
    @FXML
    void pinnumber(ActionEvent event) {

    }
    
    @FXML
    void submit(ActionEvent event) {
        try {
            readBalance(title.getText(), Double.parseDouble(acnumber.getText()), Double.parseDouble(brcode.getText()), Integer.parseInt(pin.getText()), path2);
        } catch (IOException ex) {
            System.out.println("IOException");
        }
        if (bal1.equals("0")) {
            check.setText("Incorrect Information");
        } else {
            String split[] = date1.split(",");
            if (Double.parseDouble(split[0]) >= day && Double.parseDouble(split[2]) > year){
                profit = (Double.parseDouble(bal1) / 100000) * 400;
                check.setText(Double.toString(profit));
                try {
                    updateDate(title.getText(), Double.parseDouble(acnumber.getText()), Double.parseDouble(brcode.getText()), Integer.parseInt(pin.getText()), path2);
                } catch (FileNotFoundException a) {
                    System.out.println("File Not Found");
                } catch (IOException ex) {
                    Logger.getLogger(ProfitController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (Double.parseDouble(split[0]) >= day && Double.parseDouble(split[2]) == year && Double.parseDouble(split[1]) > month){
                profit = (Double.parseDouble(bal1) / 100000) * 400;
                check.setText(Double.toString(profit));
                try {
                    updateDate(title.getText(), Double.parseDouble(acnumber.getText()), Double.parseDouble(brcode.getText()), Integer.parseInt(pin.getText()), path2);
                } catch (FileNotFoundException a) {
                    System.out.println("File Not Found");
                } catch (IOException ex) {
                    Logger.getLogger(ProfitController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else check.setText("Date doesn't exceeded");
        }
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
            Logger.getLogger(ProfitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
