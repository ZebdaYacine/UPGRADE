/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upgrade.UIController;

import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.*;
import upgrade.BackEnd.*;
import static upgrade.UIController.ListWorkUIController.*;

/**
 * FXML Controller class
 *
 * @author Zed-Yacine
 */
public class AddWorkUIController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXComboBox nameCmb, dateCmb, statusCmb;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<WorkingDate> dtes = (ObservableList<WorkingDate>) DateWorkingController.getDateWorking(new WorkingDate());
        dateCmb.getItems().clear();
        for (WorkingDate dte : dtes) {
            dateCmb.getItems().add(dte.getDateWork());
        }
        ObservableList<Employer> empls = (ObservableList<Employer>) EmployerController.getEmployers(new Employer(), "");
        for (Employer empl : empls) {
            nameCmb.getItems().add(empl.getFirstName() + "  " + empl.getLastName());
        }
        
        ObservableList<String> status
                = FXCollections.observableArrayList(
                        "présent",
                        "absent"
                );
        for (String sts : status) {
            statusCmb.getItems().add(sts);
        }
    }

    @FXML
    private void addClient(ActionEvent event) throws IOException, SQLException {
        String[]  fullName=nameCmb.getSelectionModel().getSelectedItem().toString().split("  ",2);
        String fName=fullName[0];
        String lName=fullName[1];
        String dte=dateCmb.getSelectionModel().getSelectedItem().toString();
        String status=statusCmb.getSelectionModel().getSelectedItem().toString();
        int idEmpl=upgrade.UPGRADE.getEmplyoerIdFromFullName(fName, lName);
        int idDate= upgrade.UPGRADE.getIdFromDate(java.sql.Date.valueOf(dte));
        Work wrk = new Work(idEmpl,idDate,status);
        if (!status.isEmpty() && !dte.isEmpty() && fullName.length!=0) {
            Options.information(WorkController.addWork(wrk) + "");
            refrechData();
        } else {
            Options.error("les champs sont vides");
        }
    }

    public void refrechData() {
        try {
            SuperController.refrechWork(table, Column1, Column2, Column3, Column4,Column5, new Work());
        } catch (SQLException ex) {
            Logger.getLogger(AddWorkUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
