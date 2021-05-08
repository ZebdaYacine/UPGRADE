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
public class Work {

    private int id;
    private int idEmployer;
    private int idWorkingDate;
    private String status;
    private Date workingDate;
    private String fName;
    private String Lname;

    public Work() {
    }

    public Work(int id) {
        this.id = id;
    }

    public Work(int idEmployer, int idWorkingDate, String status) {
        this.idEmployer = idEmployer;
        this.idWorkingDate = idWorkingDate;
        this.status = status;
    }

    public Work(int id, int idEmployer, int idWorkingDate, String status) {
        this.id = id;
        this.idEmployer = idEmployer;
        this.idWorkingDate = idWorkingDate;
        this.status = status;
    }

    public Work(int id, String status, Date workingDate, String fName, String Lname) {
        this.id = id;
        this.status = status;
        this.workingDate = workingDate;
        this.fName = fName;
        this.Lname = Lname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEmployer() {
        return idEmployer;
    }

    public void setIdEmployer(int idEmployer) {
        this.idEmployer = idEmployer;
    }

    public int getIdWorkingDate() {
        return idWorkingDate;
    }

    public void setIdWorkingDate(int idWorkingDate) {
        this.idWorkingDate = idWorkingDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getWorkingDate() {
        return workingDate;
    }

    public void setWorkingDate(Date workingDate) {
        this.workingDate = workingDate;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getLname() {
        return Lname;
    }

    public void setLname(String Lname) {
        this.Lname = Lname;
    }
    
    
}
