package com.cornez.COMP259_FinalProject_Guillaume;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;


public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "petManager";
    private static final String TABLE_NAME = "contacts";
    private static final String KEY_ID = "_id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DETAIL = "detail";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_IMAGEURI = "imageUri";

    public DBHelper(Context context){
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_NAME + " TEXT,"
                + KEY_DETAIL + " TEXT,"
                + KEY_PHONE + " TEXT,"
                + KEY_IMAGEURI + " TEXT)" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    public void createContact(Pet pet){
        SQLiteDatabase db = getWritableDatabase();

        String insert = "INSERT or replace INTO " + TABLE_NAME +  "("
                + KEY_NAME +", "
                + KEY_DETAIL + ", "
                + KEY_PHONE +", "
                + KEY_IMAGEURI + ") " +
                "VALUES('"
                + pet.getName() + "','"
                + pet.getDetails() + "','"
                + pet.getPhone() + "','"
                + pet.getPhotoURI() +"')" ;
        db.execSQL(insert);
        db.close();
    }



    public Pet getContact(int id){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[]{KEY_ID, KEY_NAME, KEY_DETAIL, KEY_PHONE, KEY_IMAGEURI}, KEY_ID + "=?", new String[]{String.valueOf(id)},null,null,null,null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        Pet pet = new Pet(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), Uri.parse(cursor.getString(4)));
        db.close();
        cursor.close();

        return pet;
    }


    public void deleteContact(Pet pet){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + "=?", new String[]{String.valueOf(pet.getId())});
        db.close();
    }

    public int getContactsCount(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ TABLE_NAME, null);
        int count = cursor.getCount();
        db.close();
        cursor.close();

        return count;
    }


    public int updateContact(Pet pet){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, pet.getName());
        values.put(KEY_DETAIL, pet.getDetails());
        values.put(KEY_PHONE, pet.getPhone());
        values.put(KEY_IMAGEURI, pet.getPhotoURI().toString());

        int rowsAffected = db.update(TABLE_NAME, values, KEY_ID + "=?", new String[] {String.valueOf(pet.getId())});
        db.close();

        return rowsAffected;
    }

    public List<Pet> getAllContacts(){
        List<Pet> allPets = new ArrayList<Pet>();

        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ TABLE_NAME, null);

        if(cursor.moveToFirst()){

            do{
                allPets.add(new Pet(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), Uri.parse(cursor.getString(5))));
            }
            while(cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return allPets;
    }

}
