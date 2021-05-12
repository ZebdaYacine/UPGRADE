/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upgrade.BackEnd;

import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;
import static upgrade.UPGRADE.con;

/**
 *
 * @author Zed-Yacine
 */
public class NeedController {

    public static Results.Rstls addNeed(Need need) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement(""
                    + "insert into need (idOffice,idSpeciality) values (?,?)");
            stm.setInt(1, need.getIdOffice());
            stm.setInt(2, need.getIdSpeciality());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.DATA_INSERTED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.DATA_NOT_INSERTED;
        }
    }

    public static Results.Rstls updateNeed(Need need) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement("UPDATE "
                    + " need SET idOffice = ? , idSpeciality= ? WHERE id = ? ");
            stm.setInt(1, need.getIdOffice());
            stm.setInt(2, need.getIdSpeciality());
            stm.setInt(3, need.getId());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.DATA_UPDATED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.DATA_NOT_UPDATED;
        }
    }

    public static Results.Rstls deleteNeed(Need need) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement("DELETE FROM "
                    + " need WHERE id = ?");
            stm.setInt(1, need.getId());
            stm.executeUpdate();
            upgrade.UPGRADE.alterIdTable(need.getId(), "need");
            stm.close();
            return Results.Rstls.DATA_DELETED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.DATA_NOT_DELETED;
        }
    }

    public static Object getNeed(Need need) {
        String query;
        if (need.getOfficeName() == null) {
            query = "SELECT * FROM need";
        } else {
            query = "SELECT * FROM need where idOffice = " +upgrade.UPGRADE.getObjectIdFromName(need.getOfficeName(),"office","");
        }
        ObservableList<Need> listNeed = FXCollections.observableArrayList(new Need());
        listNeed.remove(0);
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Need ned = new Need();
                ned.setId(rs.getInt("id"));
                ned.setOfficeName(upgrade.UPGRADE.getObjectAttFromId(rs.getInt("idOffice"), "name","office"));
                ned.setSpecialityName(upgrade.UPGRADE.getObjectAttFromId(rs.getInt("idSpeciality"), "name","speciality"));
                listNeed.add(ned);
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listNeed;
    }

}
