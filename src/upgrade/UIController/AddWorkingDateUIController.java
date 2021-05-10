/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upgrade.UIController;

import com.jfoenix.controls.JFXDatePicker;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.*;
import upgrade.BackEnd.*;
import static upgrade.UIController.ListWorkingDateUIController.*;

/**
 * FXML Controller class
 *
 * @author Zed-Yacine
 */
public class AddWorkingDateUIController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXDatePicker date;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void addClient(ActionEvent event) throws IOException, SQLException {
        WorkingDate dte = new WorkingDate(Date.valueOf(date.getValue()));
        boolean statusData = date.getValue()!=null;
        if (statusData) {
            Options.information(DateWorkingController.addDateWorking(dte) + "");
            refrechData();
        } else {
            Options.error("les champs sont vides");
        }
    }

    public void refrechData() {
        try {
            SuperController.refrechWorkingDate(table, Column1, Column2, new WorkingDate());
        } catch (SQLException ex) {
            Logger.getLogger(AddWorkingDateUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
