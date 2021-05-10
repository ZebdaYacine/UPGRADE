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
public class EditOfficeUIController implements Initializable {

    @FXML
    private JFXTextField name, id;

  
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void deleteClientUI(ActionEvent event) throws IOException, SQLException {
        Office office = new Office(Integer.parseInt(id.getText()),name.getText());
        Options.information(OfficeController.deleteOffice(office) + "");
        refrechData();
    }

    @FXML
    private void updateClientUI(ActionEvent event) throws IOException, SQLException {
        Office office = new Office(Integer.parseInt(id.getText()),name.getText());
        boolean statusData = office.isDataEmpty(office);
        if (statusData) {
            Options.information(OfficeController.updateOffice(office) + "");
            refrechData();
        } else {
            Options.error("les champs sont vides");
        }
    }

    private void refrechData() {
        try {
            SuperController.refrechOffices(table, Column1, Column2, new Office());
        } catch (SQLException ex) {
            Logger.getLogger(EditOfficeUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void intiFileds(Office office) {
        id.setText(office.getId() + "");
        name.setText(office.getName());
    }
}
