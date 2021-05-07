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
public class WorkingDate {
    
    private int id;
    private Date dateWork;

    public WorkingDate() {
    }

    public WorkingDate(int id) {
        this.id = id;
    }
    
    
    public WorkingDate(Date dateWork) {
        this.dateWork = dateWork;
    }

    public WorkingDate(int id, Date dateWork) {
        this.id = id;
        this.dateWork = dateWork;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateWork() {
        return dateWork;
    }

    public void setDateWork(Date dateWork) {
        this.dateWork = dateWork;
    }
    
}
