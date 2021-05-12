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
public class Employer {

    private int id;
    private String firstName;
    private String lastName;
    private String phone;
    private Date birthDate;
    private Date recruitmentDate;
    private String socialStatus;
    private String deploma;
    private int nbrchildren;
    private int note;
    private int nbrFonrmations;
    private int experience;
    private int idGrade;
    private int idOffice;
    private String officeName;
    private String gradeName;

    public Employer(int id) {
        this.id = id;
    }

    public Employer() {
    }

    public Employer(String searchArg) {
        this.phone = searchArg;
        this.officeName = searchArg;
    }
    
    

    public Employer(String firstName, String lastName, String phone, Date birthDate, Date recruitmentDate, String socialStatus, String deploma, int nbrchildren, int note, int nbrFonrmations, int experience, int idGrade, int idOffice) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.birthDate = birthDate;
        this.recruitmentDate = recruitmentDate;
        this.socialStatus = socialStatus;
        this.deploma = deploma;
        this.nbrchildren = nbrchildren;
        this.note = note;
        this.nbrFonrmations = nbrFonrmations;
        this.experience = experience;
        this.idGrade = idGrade;
        this.idOffice = idOffice;
    }

    public Employer(int id, String firstName, String lastName, String phone, Date birthDate, Date recruitmentDate, String socialStatus, String deploma, int nbrchildren, int note, int nbrFonrmations, int experience, String officeName, String gradeName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.birthDate = birthDate;
        this.recruitmentDate = recruitmentDate;
        this.socialStatus = socialStatus;
        this.deploma = deploma;
        this.nbrchildren = nbrchildren;
        this.note = note;
        this.nbrFonrmations = nbrFonrmations;
        this.experience = experience;
        this.officeName = officeName;
        this.gradeName = gradeName;
    }

    public Employer(int id, String firstName, String lastName, String phone, Date birthDate, Date recruitmentDate, String socialStatus, String deploma, int nbrchildren, int note, int nbrFonrmations, int experience, int idGrade, int idOffice) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.birthDate = birthDate;
        this.recruitmentDate = recruitmentDate;
        this.socialStatus = socialStatus;
        this.deploma = deploma;
        this.nbrchildren = nbrchildren;
        this.note = note;
        this.nbrFonrmations = nbrFonrmations;
        this.experience = experience;
        this.idGrade = idGrade;
        this.idOffice = idOffice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getRecruitmentDate() {
        return recruitmentDate;
    }

    public void setRecruitmentDate(Date recruitmentDate) {
        this.recruitmentDate = recruitmentDate;
    }

    public String getSocialStatus() {
        return socialStatus;
    }

    public void setSocialStatus(String socialStatus) {
        this.socialStatus = socialStatus;
    }

    public String getDeploma() {
        return deploma;
    }

    public void setDeploma(String deploma) {
        this.deploma = deploma;
    }

    public int getNbrchildren() {
        return nbrchildren;
    }

    public void setNbrchildren(int nbrchildren) {
        this.nbrchildren = nbrchildren;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public int getNbrFonrmations() {
        return nbrFonrmations;
    }

    public void setNbrFonrmations(int nbrFonrmations) {
        this.nbrFonrmations = nbrFonrmations;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getIdGrade() {
        return idGrade;
    }

    public void setIdGrade(int idGrade) {
        this.idGrade = idGrade;
    }

    public int getIdOffice() {
        return idOffice;
    }

    public void setIdOffice(int idOffice) {
        this.idOffice = idOffice;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public boolean dateIsValid() {
        return true;
    }

    
    
    
}
