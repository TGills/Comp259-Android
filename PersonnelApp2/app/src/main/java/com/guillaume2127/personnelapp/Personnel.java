package com.guillaume2127.personnelapp;

import java.util.Date;

/*
 * Created by guillaume2127 on 11/23/2016.
 */
public class Personnel extends MainActivity{
    private int PersonnelID;
    private int PictureID;
    private String Name;
    private String Address;
    private String Phone;
    private String Email;
    private String Position;
    private String supervisorName;
    private String Role;
    private String Birthdate;
    private int Age;
    private Boolean Married;

    public Personnel(int PersonnelID, int PictureID, String Name, String Address, String Phone, String Email,
                     String Position, String supervisorName, String Role, String Birthdate, int Age, Boolean Married){
        setPersonnelID(PersonnelID);
        setPictureID(PictureID);
        setName(Name);
        setAddress(Address);
        setPhone(Phone);
        setEmail(Email);
        setPosition(Position);
        setSupervisorName(supervisorName);
        setRole(Role);
        setBirthdate(Birthdate);
        setAge(Age);
        setMarried(Married);
    }

    public int getPersonnelID() {
        return PersonnelID;
    }
    public void setPersonnelID(int personnelID) {
        PersonnelID = personnelID;
    }
    public int getPictureID() {
        return PictureID;
    }
    public void setPictureID(int pictureID) {
        PictureID = pictureID;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public String getAddress() {
        return Address;
    }
    public void setAddress(String address) {
        Address = address;
    }
    public String getPhone() {
        return Phone;
    }
    public void setPhone(String phone) {
        Phone = phone;
    }
    public String getEmail() {
        return Email;
    }
    public void setEmail(String email) {
        Email = email;
    }
    public String getPosition() {
        return Position;
    }
    public void setPosition(String positon) {
        Position = positon;
    }
    public String getSupervisorName() {
        return supervisorName;
    }
    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }
    public String getRole() {
        return Role;
    }
    public void setRole(String role) {
        Role = role;
    }
    public String getBirthdate() {
        return Birthdate;
    }
    public void setBirthdate(String birthdate) {
        Birthdate = birthdate;
    }
    public int getAge() {
        return Age;
    }
    public void setAge(int age) {
        Age = age;
    }
    public Boolean getMarried() {
        return Married;
    }
    public void setMarried(Boolean married) {
        Married = married;
    }
}
