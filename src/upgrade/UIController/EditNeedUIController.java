/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upgrade.UIController;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.Need;
import model.Office;
import model.Speciality;
import upgrade.BackEnd.NeedController;
import upgrade.BackEnd.OfficeController;
import upgrade.BackEnd.SpecialityController;
import static upgrade.UIController.ListNeedsUIController.*;


/**
 * FXML Controller class
 *
 * @author Zed-Yacine
 */
public class EditNeedUIController implements Initializable {

 
    @FXML
    private JFXTextField  id;
    
    @FXML
    private JFXComboBox CmbOffice, CmbSp;

    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Office> offices = (ObservableList<Office>) OfficeController.getOffices(new Office(""));
        CmbOffice.getItems().clear();
        for (Office office : offices) {
            CmbOffice.getItems().add(office.getName());
        }
        ObservableList<Speciality> sps = (ObservableList<Speciality>) SpecialityController.getSpecialities(new Speciality(""));
        for (Speciality sp : sps) {
            CmbSp.getItems().add(sp.getName());
        }
    }

    @FXML
    private void deleteDoUI(ActionEvent event) throws IOException, SQLException {
        int idNeed = Integer.parseInt(id.getText());
        String str = NeedController.deleteNeed(new Need(idNeed)) + "";
        Options.information(str);
        refrechData();
    }

    @FXML
    private void updateDoUI(ActionEvent event) throws IOException, SQLException {
        String office = (String) CmbOffice.getSelectionModel().getSelectedItem();
        String sp = (String) CmbSp.getSelectionModel().getSelectedItem();
        String idNeed = id.getText();
        if (idNeed != "" && office != "" && sp != "" ) {
            int idOffice = upgrade.UPGRADE.getObjectIdFromName(office, "office");
            int idSpciality = upgrade.UPGRADE.getObjectIdFromName(sp, "speciality");
            int iD= Integer.parseInt(idNeed);
            Need nd = new Need(iD,idOffice,idSpciality);
            String str = NeedController.updateNeed(nd) + "";
            Options.information(str);
            refrechData();
        } else {
            Options.information("les champssont vide");
        }
    }

    public void refrechData() {
        try {
            SuperController.refrechNeed(table, Column1, Column2, Column3,  new Need());
        } catch (SQLException ex) {
            Logger.getLogger(ListNeedsUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void intiFileds(Need need) {
        id.setText(need.getId() + "");
        CmbOffice.setValue(need.getOfficeName());
        CmbSp.setValue(need.getSpecialityName());
    }
}
