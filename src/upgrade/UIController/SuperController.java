/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upgrade.UIController;

import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import model.*;
import upgrade.BackEnd.*;

/**
 *
 * @author Zed-Yacine
 */
public class SuperController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public static void refrechOffices(TableView table, TableColumn Column1, TableColumn Column2,
            Office office)
            throws SQLException {
        ObservableList<Office> offices = (ObservableList<Office>) OfficeController.getOffices(office);
        Column1.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );
        Column2.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        table.setItems(offices);
    }

    public static void refrechSpecialites(TableView table, TableColumn Column1, TableColumn Column2,
            Speciality sp)
            throws SQLException {
        ObservableList<Speciality> spcialites = (ObservableList<Speciality>) SpecialityController.getSpecialities(sp);
        Column1.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );
        Column2.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        table.setItems(spcialites);
    }

    public static void refrechNeed(TableView table, TableColumn Column1, TableColumn Column2, TableColumn Column3,
            Need need)
            throws SQLException {
        ObservableList<Need> nds = (ObservableList<Need>) NeedController.getNeed(need);
        Column1.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );
        Column2.setCellValueFactory(
                new PropertyValueFactory<>("officeName")
        );
        Column3.setCellValueFactory(
                new PropertyValueFactory<>("specialityName")
        );
        table.setItems(nds);
    }

    public static void refrechWorkingDate(TableView table, TableColumn Column1, TableColumn Column2,
            WorkingDate dte)
            throws SQLException {
        ObservableList<WorkingDate> days = (ObservableList<WorkingDate>) DateWorkingController.getDateWorking(dte);
        Column1.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );
        Column2.setCellValueFactory(
                new PropertyValueFactory<>("dateWork")
        );
        table.setItems(days);
    }

    public static void refrechGrades(TableView table, TableColumn Column1, TableColumn Column2,
            TableColumn Column3, TableColumn Column4, Grade grade)
            throws SQLException {
        ObservableList<Grade> grades = (ObservableList<Grade>) GradeController.getGrade(grade);
        Column1.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );
        Column2.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        Column3.setCellValueFactory(
                new PropertyValueFactory<>("deploma")
        );
        Column4.setCellValueFactory(
                new PropertyValueFactory<>("note")
        );
        table.setItems(grades);
    }

    public static void refrechWork(TableView table, TableColumn Column1, TableColumn Column2,
            TableColumn Column3, TableColumn Column4, TableColumn Column5, Work work)
            throws SQLException {
        ObservableList<Work> works = (ObservableList<Work>) WorkController.getWork(work);
        Column1.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );
        Column2.setCellValueFactory(
                new PropertyValueFactory<>("Fname")
        );
        Column3.setCellValueFactory(
                new PropertyValueFactory<>("Lname")
        );
        Column4.setCellValueFactory(
                new PropertyValueFactory<>("workingDate")
        );
        Column5.setCellValueFactory(
                new PropertyValueFactory<>("status")
        );
        table.setItems(works);
    }

    public static void refrechEmployers(TableView table, TableColumn Column1, TableColumn Column2,
            TableColumn Column3, TableColumn Column4, TableColumn Column5,
             TableColumn Column6, TableColumn Column7,
            TableColumn Column8, TableColumn Column9, TableColumn Column10,
             TableColumn Column11, TableColumn Column12,
            TableColumn Column13, TableColumn Column14, TableColumn Column15,TableColumn Column16,
             Employer empl, String searchArg,int key)
            throws SQLException {
        ObservableList<Employer> empls = (ObservableList<Employer>) EmployerController.getEmployers(empl, searchArg);
        Column1.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );
        Column2.setCellValueFactory(
                new PropertyValueFactory<>("firstName")
        );
        Column3.setCellValueFactory(
                new PropertyValueFactory<>("lastName")
        );
        Column4.setCellValueFactory(
                new PropertyValueFactory<>("phone")
        );
        Column5.setCellValueFactory(
                new PropertyValueFactory<>("birthDate")
        );
        Column6.setCellValueFactory(
                new PropertyValueFactory<>("recruitmentDate")
        );
        Column7.setCellValueFactory(
                new PropertyValueFactory<>("socialStatus")
        );
        Column8.setCellValueFactory(
                new PropertyValueFactory<>("nbrchildren")
        );
        Column9.setCellValueFactory(
                new PropertyValueFactory<>("nbrFonrmations")
        );
        Column10.setCellValueFactory(
                new PropertyValueFactory<>("experience")
        );
        Column11.setCellValueFactory(
                new PropertyValueFactory<>("deploma")
        );
        Column12.setCellValueFactory(
                new PropertyValueFactory<>("note")
        );
        Column13.setCellValueFactory(
                new PropertyValueFactory<>("gradeName")
        );
        Column14.setCellValueFactory(
                new PropertyValueFactory<>("officeName")
        );
        Column15.setCellValueFactory(
                new PropertyValueFactory<>("lastUpgardeDate")
        );
        Column16.setCellValueFactory(
                new PropertyValueFactory<>("descipline")
        );
        if (key==1) {
            table.setItems(empls);
            table.setRowFactory(new Callback<TableView<Employer>, TableRow<Employer>>() {
                @Override
                public TableRow<Employer> call(TableView param) {
                    return new TableRow<Employer>() {
                        protected void updateItem(Employer empl, boolean b) {
                            super.updateItem(empl, b);
                            if (empl != null) {
                                String currentDate=upgrade.UPGRADE.getCurrentDate();
                               double period= java.sql.Date.valueOf(currentDate).getTime()-
                                       java.sql.Date.valueOf(empl.getLastUpgardeDate().toString()).getTime();
                                period = (period / 86400000);
                                if (period>= 900) {
                                    setStyle("-fx-background-color: #2CE93B;");
                                    return;
                                }
                                setStyle("-fx-background-color: #ffffff;");
                            } else {
                                setStyle("-fx-background-color: #ffffff;");
                            }
                        }
                    };
                }
            });
        }
        table.setItems(empls);
    }

}
