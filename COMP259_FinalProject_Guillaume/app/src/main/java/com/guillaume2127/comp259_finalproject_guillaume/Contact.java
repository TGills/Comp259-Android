package com.guillaume2127.comp259_finalproject_guillaume;

import android.net.Uri;

/**
 * Created by guillaume2127 on 22/02/2017.
 */
public class Contact {
    private String Name;
    private String Address;
    private String Email;
    private String phoneNumber;
    private Integer ID;

    public Contact(){}

    public Contact(Integer id, String name, String address, String email, String PhoneNumber){
        setName(name);
        setAddress(address);
        setEmail(email);
        setPhoneNumber(PhoneNumber);
        setID(id);
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
    public String getEmail() {
        return Email;
    }
    public void setEmail(String email) {
        Email = email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public Integer getID() {
        return ID;
    }
    public void setID(Integer ID) {
        this.ID = ID;
    }
}
