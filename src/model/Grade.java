/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Zed-Yacine
 */
public class Grade {
    private int id;
    private String name;
    private String deploma;
    private int note;

    public Grade() {
    }

    public Grade(int id) {
        this.id = id;
    }

    public Grade(String name) {
        this.name = name;
    }
    
    public Grade(String name, String deploma, int note) {
        this.name = name;
        this.deploma = deploma;
        this.note = note;
    }

    public Grade(int id, String name, String deploma, int note) {
        this.id = id;
        this.name = name;
        this.deploma = deploma;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeploma() {
        return deploma;
    }

    public void setDeploma(String deploma) {
        this.deploma = deploma;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }
    
    
}
