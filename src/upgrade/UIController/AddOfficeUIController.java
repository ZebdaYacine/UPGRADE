/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upgrade.UIController;

import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.*;
import upgrade.BackEnd.*;
import static upgrade.UIController.ListOfficesUIController.*;

/**
 * FXML Controller class
 *
 * @author Zed-Yacine
 */
public class AddOfficeUIController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXTextField name, phone;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void addClient(ActionEvent event) throws IOException, SQLException {
        Office office = new Office(name.getText());
        boolean statusData = office.isDataEmpty(office);
        if (statusData) {
            Options.information(OfficeController.addOffice(office) + "");
            refrechData();
        } else {
            Options.error("les champs sont vides");
        }
    }

    public void refrechData() {
        try {
            SuperController.refrechOffices(table, Column1, Column2, new Office());
        } catch (SQLException ex) {
            Logger.getLogger(AddOfficeUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
