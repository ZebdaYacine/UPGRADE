/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upgrade.UIController;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.sun.deploy.uitoolkit.impl.fx.ui.FXAboutDialog;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.util.Callback;
import model.Employer;
import model.Grade;
import model.Office;
import upgrade.BackEnd.EmployerController;
import upgrade.BackEnd.GradeController;
import upgrade.BackEnd.OfficeController;
import static upgrade.UIController.ListEmployersUIController.*;

/**
 * FXML Controller class
 *
 * @author Zed-Yacine
 */
public class AddEmployerUIController implements Initializable {

    @FXML
    private JFXComboBox CmbOffice, CmbGrade, statusSCmb;

    @FXML
    private JFXDatePicker dateN, dateR;

    @FXML
    private JFXTextField Fname, Lname, phone, nbrChildren, deploma, formations, experience, note;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Office> offices = (ObservableList<Office>) OfficeController.getOffices(new Office(""));
        CmbOffice.getItems().clear();
        for (Office office : offices) {
            CmbOffice.getItems().add(office.getName());
        }
        ObservableList<Grade> grades = (ObservableList<Grade>) GradeController.getGrade(new Grade());
        for (Grade grd : grades) {
            CmbGrade.getItems().add(grd.getName());
        }
        ObservableList<String> listStatusSocial = FXCollections.observableArrayList("Marié", "célibataire");
        for (String str : listStatusSocial) {
            statusSCmb.getItems().add(str);
        }
    }

    private int getInt(String str) {
        return Integer.parseInt(str);
    }

    @FXML
    private void addEmployer(ActionEvent event) throws IOException, SQLException {
        String grade = (String) CmbGrade.getSelectionModel().getSelectedItem();
        String office = (String) CmbOffice.getSelectionModel().getSelectedItem();
        int idOffice = upgrade.UPGRADE.getObjectIdFromName(office, "office", "");
        int idGrade = upgrade.UPGRADE.getObjectIdFromName(grade, "grade", "");
        Employer empl = new Employer(Fname.getText(), Lname.getText(), phone.getText(),
                Date.valueOf(dateN.getValue()), Date.valueOf(dateR.getValue()),
                statusSCmb.getSelectionModel().getSelectedItem().toString(),
                deploma.getText(), getInt(nbrChildren.getText()), getInt(note.getText()), getInt(formations.getText()),
                getInt(experience.getText()), idGrade,idOffice);
        if (empl.dateIsValid()) {
            Options.information(EmployerController.addEmployer(empl) + "");
            refrechData();
        }
    }

    public void refrechData() {
        try {
            SuperController.refrechEmployers(table, Column1, Column2, Column3, Column4, Column5, Column6,
                    Column7, Column8, Column9, Column10, Column11,
                    Column12, Column13, Column14, new Employer(), "");
        } catch (SQLException ex) {
            Logger.getLogger(ListEmployersUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
