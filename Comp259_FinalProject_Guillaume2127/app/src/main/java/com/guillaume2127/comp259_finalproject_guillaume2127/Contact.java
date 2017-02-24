package com.guillaume2127.comp259_finalproject_guillaume2127;

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
    private Uri imageURL;

    public Contact(){}
    public Contact(int i, String string, String cursorString, String s, Uri parse){}

    public Contact(Integer id, String name, String address, String email, String PhoneNumber, Uri ImageURL){
        setName(name);
        setAddress(address);
        setEmail(email);
        setPhoneNumber(PhoneNumber);
        setID(id);
        setImageURL(ImageURL);
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
    public Uri getImageURL() {
        return imageURL;
    }
    public void setImageURL(Uri imageURL) {
        this.imageURL = imageURL;
    }



}

