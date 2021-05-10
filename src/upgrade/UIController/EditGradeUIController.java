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
import static upgrade.UIController.ListGradesUIController.*;


/**
 * FXML Controller class
 *
 * @author Zed-Yacine
 */
public class EditGradeUIController implements Initializable {

    @FXML
    private JFXTextField name, id,deploma,note;

  
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void deleteUI(ActionEvent event) throws IOException, SQLException {
        Grade grd = new Grade(Integer.parseInt(id.getText()),name.getText(),deploma.getText(),Integer.parseInt(note.getText()));
        Options.information(GradeController.deleteGrade(grd) + "");
        refrechData();
    }

    @FXML
    private void updateUI(ActionEvent event) throws IOException, SQLException {
        Grade grd = new Grade(Integer.parseInt(id.getText()),name.getText(),deploma.getText(),Integer.parseInt(note.getText()));
        boolean statusData = grd.isDataEmpty(grd);
        if (statusData) {
            Options.information(GradeController.updateGrade(grd) + "");
            refrechData();
        } else {
            Options.error("les champs sont vides");
        }
    }

    private void refrechData() {
        try {
            SuperController.refrechGrades(table, Column1, Column2, Column3, Column4, new Grade());
        } catch (SQLException ex) {
            Logger.getLogger(EditGradeUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void intiFileds(Grade grd) {
        id.setText(grd.getId() + "");
        name.setText(grd.getName());
        deploma.setText(grd.getDeploma());
        note.setText(grd.getNote()+"");
    }
}
