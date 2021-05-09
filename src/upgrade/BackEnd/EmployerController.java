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
import model.Employer;
import model.Office;
import model.WorkingDate;
import static upgrade.UPGRADE.con;

/**
 *
 * @author Zed-Yacine
 */
public class EmployerController {

    public static Results.Rstls addEmployer(Employer empl) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement(""
                    + "insert into employer (firstName,lastName,phone,birthDate,recruitmentDate,socialStatus,"
                    + "deploma,nbrchildren,note,nbrFonrmations,experience,idGrade,idOffice,lastUpgarde) "
                    + "values (?,?,?,?,?,?,?,?,?,?,?,?,?)");
            stm.setString(1, empl.getFirstName());
            stm.setString(2, empl.getLastName());
            stm.setString(3, empl.getPhone());
            stm.setDate(4, (Date) empl.getBirthDate());
            stm.setDate(5, (Date) empl.getRecruitmentDate());
            stm.setString(6, empl.getSocialStatus());
            stm.setString(7, empl.getDeploma());
            stm.setInt(8, empl.getNbrchildren());
            stm.setInt(9, empl.getNote());
            stm.setInt(10, empl.getNbrFonrmations());
            stm.setInt(11, empl.getExperience());
            stm.setInt(12, empl.getIdGrade());
            stm.setInt(13, empl.getIdOffice());
            stm.setDate(14, (Date) empl.getRecruitmentDate());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.DATA_INSERTED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.DATA_NOT_INSERTED;
        }
    }

    public static Results.Rstls deleteEmployer(Employer empl) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement("DELETE FROM "
                    + " employer WHERE id = ?");
            stm.setInt(1, empl.getId());
            stm.executeUpdate();
            upgrade.UPGRADE.alterIdTable(empl.getId(), "employer");
            stm.close();
            return Results.Rstls.DATA_DELETED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.DATA_NOT_DELETED;
        }
    }

    public static Results.Rstls updateEmployer(Employer empl) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement("UPDATE "
                    + " employer SET firstName = ? , lastName = ? , phone = ? , birthDate= ? , recruitmentDate= ?"
                    + " , socialStatus = ? , deploma = ? , nbrchildren = ? , note=? , nbrFonrmations =? ,experience=?  "
                    + " , idGrade = ? , idOffice = ? , lastUpgarde=? "
                    + " WHERE id = ? ");
            stm.setString(1, empl.getFirstName());
            stm.setString(2, empl.getLastName());
            stm.setString(3, empl.getPhone());
            stm.setDate(4, (Date) empl.getBirthDate());
            stm.setDate(5, (Date) empl.getRecruitmentDate());
            stm.setString(6, empl.getSocialStatus());
            stm.setString(7, empl.getDeploma());
            stm.setInt(8, empl.getNbrchildren());
            stm.setInt(9, empl.getNote());
            stm.setInt(10, empl.getNbrFonrmations());
            stm.setInt(11, empl.getExperience());
            stm.setInt(12, empl.getIdGrade());
            stm.setInt(13, empl.getIdOffice());
            stm.setDate(14, (Date) empl.getRecruitmentDate());
            stm.setInt(13, empl.getId());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.DATA_UPDATED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.DATA_NOT_UPDATED;
        }
    }

    public static Results.Rstls updateDisciplineEmployer(int disciplineValue, int idEmpl) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement("UPDATE "
                    + " employer SET discipline = ? WHERE id = ? ");
            stm.setInt(1, disciplineValue);
            stm.setInt(2, idEmpl);
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.DATA_UPDATED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.DATA_NOT_UPDATED;
        }
    }

    private static String selctQuery(Employer empl, String ArgSearch) {
        String query = "";
        switch (ArgSearch) {
            case "office": {
                query = " SELECT * FROM employer,office  where office.id=employer.idOffice and office.name LIKE '" + empl.getOfficeName() + "%'";
                break;
            }
            case "phone": {
                query = "SELECT * FROM employer where phone LIKE '" + empl.getPhone() + "%'";
                break;
            }
            default: {
                query = "SELECT * FROM employer";
            }
        }
        return query;
    }

    public static Object getEmployers(Employer empl, String ArgSearch) {
        String query = selctQuery(empl, ArgSearch);
        System.out.println(query);
        ObservableList<Employer> listEmployers = FXCollections.observableArrayList(new Employer());
        listEmployers.remove(0);
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Employer emp = new Employer();
                emp.setId(rs.getInt("id"));
                emp.setFirstName(rs.getString("firstName"));
                emp.setLastName(rs.getString("lastName"));
                emp.setPhone(rs.getString("phone"));
                emp.setBirthDate(rs.getDate("birthDate"));
                emp.setRecruitmentDate(rs.getDate("recruitmentDate"));
                emp.setSocialStatus(rs.getString("socialStatus"));
                emp.setDeploma(rs.getString("deploma"));
                emp.setNbrchildren(rs.getInt("nbrchildren"));
                emp.setNote(rs.getInt("note"));
                emp.setNbrFonrmations(rs.getInt("nbrFonrmations"));
                emp.setExperience(rs.getInt("experience"));
                emp.setGradeName(upgrade.UPGRADE.getObjectAttFromId(rs.getInt("idGrade"), "name", "grade"));
                emp.setOfficeName(upgrade.UPGRADE.getObjectAttFromId(rs.getInt("idOffice"), "name", "office"));
                listEmployers.add(emp);
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listEmployers;
    }

}
