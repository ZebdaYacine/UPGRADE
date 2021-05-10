/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upgrade.UIController;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.WorkingDate;
import upgrade.BackEnd.DateWorkingController;
import static upgrade.UIController.ListWorkingDateUIController.*;

/**
 * FXML Controller class
 *
 * @author Zed-Yacine
 */
public class EditWorkingDateUIController implements Initializable {

    @FXML
    private JFXTextField id;

    @FXML
    private JFXDatePicker date;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void deleteDoUI(ActionEvent event) throws IOException, SQLException {
        int idDate = Integer.parseInt(id.getText());
        String str = DateWorkingController.deleteDateWorking(new WorkingDate(idDate)) + "";
        Options.information(str);
        refrechData();
    }

    @FXML
    private void updateDoUI(ActionEvent event) throws IOException, SQLException {
        if (date.getValue() != null) {
            WorkingDate dte = new WorkingDate(Integer.parseInt(id.getText()),Date.valueOf(date.getValue()));
            String str = DateWorkingController.updateDateWorking(dte) + "";
            Options.information(str);
            refrechData();
        } else {
            Options.information("les champssont vide");
        }
    }

    public void refrechData() {
        try {
            SuperController.refrechWorkingDate(table, Column1, Column2, new WorkingDate());
        } catch (SQLException ex) {
            Logger.getLogger(ListNeedsUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void intiFileds(WorkingDate workingDate) {
        id.setText(workingDate.getId() + "");
        date.setValue(LOCAL_DATE(workingDate.getDateWork().toString()));
    }
    
    private  final LocalDate LOCAL_DATE (String dateString){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate localDate = LocalDate.parse(dateString, formatter);
    return localDate;
}
}
