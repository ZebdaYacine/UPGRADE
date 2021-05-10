/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upgrade.UIController;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
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
public class AddNeedUIController implements Initializable {

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
    private void addNeed(ActionEvent event) throws IOException, SQLException {
        String sp = (String) CmbSp.getSelectionModel().getSelectedItem();
        String office = (String) CmbOffice.getSelectionModel().getSelectedItem();
        if (sp != "" && office != "") {
            int idOffice = upgrade.UPGRADE.getObjectIdFromName(office, "office");
            int idSp = upgrade.UPGRADE.getObjectIdFromName(sp, "speciality");
            Need nd = new Need(idSp, idOffice);
            String str = NeedController.addNeed(nd) + "";
            Options.information(str);
            refrechData();
        } else {
            Options.information("les champssont vide");
        }
    }

    public void refrechData() {
        try {
            SuperController.refrechNeed(table, Column1, Column2, Column3, new Need());
        } catch (SQLException ex) {
            Logger.getLogger(ListNeedsUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
