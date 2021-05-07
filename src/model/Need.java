/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author Zed-Yacine
 */
public class Need {

    private int id;
    private int idOffice;
    private int idSpeciality;
    private String officeName;
    private String specialityName;

    public Need() {
    }

    public Need(int id) {
        this.id = id;
    }

    public Need(int idSpeciality, int idOffice) {
        this.idSpeciality = idSpeciality;
        this.idOffice = idOffice;
    }

    public Need(int id, int idOffice, int idSpeciality) {
        this.id = id;
        this.idOffice = idOffice;
        this.idSpeciality = idSpeciality;
    }

    public Need(int id, String officeName, String specialityName) {
        this.id = id;
        this.officeName = officeName;
        this.specialityName = specialityName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdOffice() {
        return idOffice;
    }

    public void setIdOffice(int idOffice) {
        this.idOffice = idOffice;
    }

    public int getIdSpeciality() {
        return idSpeciality;
    }

    public void setIdSpeciality(int idSpeciality) {
        this.idSpeciality = idSpeciality;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getSpecialityName() {
        return specialityName;
    }

    public void setSpecialityName(String specialityName) {
        this.specialityName = specialityName;
    }
    
}
