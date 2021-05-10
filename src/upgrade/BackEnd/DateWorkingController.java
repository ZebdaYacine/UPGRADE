/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upgrade.BackEnd;

import com.mysql.jdbc.PreparedStatement;
import java.sql.Date;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;
import static upgrade.UPGRADE.con;

/**
 *
 * @author Zed-Yacine
 */
public class DateWorkingController {

    public static Results.Rstls addDateWorking(WorkingDate dte) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement(""
                    + "insert into workingDate (dateWorke) values (?)");
            stm.setDate(1, (Date) dte.getDateWork());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.DATA_INSERTED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.DATA_NOT_INSERTED;
        }
    }

    public static Results.Rstls updateDateWorking(WorkingDate dte) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement("UPDATE "
                    + " workingDate SET dateWorke = ?  WHERE id = ? ");
            stm.setDate(1, (Date) dte.getDateWork());
            stm.setInt(2, dte.getId());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.DATA_UPDATED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.DATA_NOT_UPDATED;
        }
    }

    public static Results.Rstls deleteDateWorking(WorkingDate dte) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement("DELETE FROM "
                    + " workingDate WHERE id = ?");
            stm.setInt(1, dte.getId());
            stm.executeUpdate();
            upgrade.UPGRADE.alterIdTable(dte.getId(), "workingDate");
            stm.close();
            return Results.Rstls.DATA_DELETED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.DATA_NOT_DELETED;
        }
    }

    public static Object getDateWorking(WorkingDate dte) {
        String query;
        if (dte.getDateWork()== null) {
            query = "SELECT * FROM workingDate";
        } else {
            query = "SELECT * FROM workingDate where dateWorke Like'" + dte.getDateWork() + "%'";
        }
        ObservableList<WorkingDate> listDates = FXCollections.observableArrayList(new WorkingDate());
        listDates.remove(0);
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                WorkingDate dt = new WorkingDate();
                dt.setId(rs.getInt("id"));
                dt.setDateWork(rs.getDate("dateWorke"));
                listDates.add(dt);
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listDates;
    }

}
