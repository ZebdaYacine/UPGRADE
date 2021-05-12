/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upgrade.BackEnd;

import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Work;
import model.WorkingDate;
import static upgrade.UPGRADE.con;

/**
 *
 * @author Zed-Yacine
 */
public class WorkController {

    private static void CalculatDesciplineVlaue(Work work) {
        Date dte = upgrade.UPGRADE.getDateFromId(work.getIdEmployer(), "recruitmentDate", "employer");
        int allDaysNbr = getNbrAllDays(dte);
        float desciplineValue = (float) 0.0;
        if (allDaysNbr > 0) {
            desciplineValue = ((float)getNbrPresences(work.getIdEmployer()) / (float)allDaysNbr) * 100;
            EmployerController.updateDisciplineEmployer((int) desciplineValue, work.getIdEmployer());
        }
    }

    public static Results.Rstls addWork(Work work) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement(""
                    + "insert into work (idEmployer,idWorkingDate,status) values (?,?,?)");
            stm.setInt(1, work.getIdEmployer());
            stm.setInt(2, work.getIdWorkingDate());
            stm.setString(3, work.getStatus());
            stm.executeUpdate();
            CalculatDesciplineVlaue(work);
            return Results.Rstls.DATA_INSERTED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.DATA_NOT_INSERTED;
        }
    }

    public static Results.Rstls updateWork(Work work) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement("UPDATE "
                    + " work SET idEmployer = ? , idWorkingDate=? , status =?  WHERE id = ? ");
            stm.setInt(1, work.getIdEmployer());
            stm.setInt(2, work.getIdWorkingDate());
            stm.setString(3, work.getStatus());
            stm.setInt(4, work.getId());
            stm.executeUpdate();
            CalculatDesciplineVlaue(work);
            return Results.Rstls.DATA_UPDATED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.DATA_NOT_UPDATED;
        }
    }

    public static Results.Rstls deleteWork(Work work) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement("DELETE FROM "
                    + " work WHERE id = ?");
            stm.setInt(1, work.getId());
            stm.executeUpdate();
            upgrade.UPGRADE.alterIdTable(work.getId(), "work");
            stm.close();
            return Results.Rstls.DATA_DELETED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.DATA_NOT_DELETED;
        }
    }

    public static Object getWork(Work work) {
        String query;
        if (work.getWorkingDate() == null) {
            query = "SELECT * FROM work";
        } else {
            Date dte=work.getWorkingDate();
            query = "SELECT * FROM work where idWorkingDate ='" + upgrade.UPGRADE.getIdFromDate(dte) + "'";
        }
        ObservableList<Work> listWork = FXCollections.observableArrayList(new Work());
        listWork.remove(0);
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Work wrk = new Work();
                wrk.setId(rs.getInt("id"));
                wrk.setLname(upgrade.UPGRADE.getObjectAttFromId(rs.getInt("idEmployer"), "lastName", "employer"));
                wrk.setFname(upgrade.UPGRADE.getObjectAttFromId(rs.getInt("idEmployer"), "firstName", "employer"));
                wrk.setStatus(rs.getString("status"));
                wrk.setWorkingDate(upgrade.UPGRADE.getDateFromId(rs.getInt("idWorkingDate"), "dateWorke", "workingDate"));
                listWork.add(wrk);
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listWork;
    }

    public static int getNbrPresences(int idEmployer) {
        String query = "select count(*) from work  where status='present' and idEmployer=" + idEmployer;
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt("count(*)");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public static int getNbrAllDays(Date dte) {
        String query = "SELECT count(*) as allDays FROM upgrade.workingdate where   dateWorke >='" + dte.toString() + "'";
        int nbrAllDays = 0;
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                nbrAllDays = rs.getInt("allDays");
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return nbrAllDays;
    }

}
