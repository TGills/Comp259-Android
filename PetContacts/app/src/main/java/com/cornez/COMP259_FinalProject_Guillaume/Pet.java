package com.cornez.COMP259_FinalProject_Guillaume;


import android.net.Uri;

class Pet {
    private int _id;
     private String name;
     private String detail;
     private String phone;
     private Uri photoURI;

     public Pet(int id, String nm, String det, String ph, Uri iURI){
         _id = id;
         name = nm;
         detail = det;
         phone = ph;
         photoURI = iURI;
     }
     public int getId(){
         return _id;
     }

     public String getName(){
         return name;
     }

     public String getDetails(){
         return detail;
     }

     public String getPhone(){
         return phone;
     }

     public Uri getPhotoURI(){
         return photoURI;
     }

 }
