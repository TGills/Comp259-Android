package com.guillaume2127.comp259_finalproject_guillaume;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guillaume2127 on 22/02/2017.
 */
public class DatabaseHelper  extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Contacts";
    private static final String TABLE_NAME = "contacts";
    private static final String KEY_ID = "_id";
    private static final String KEY_NAME = "name";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_EMAIL = "email";
    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_NAME + " TEXT,"
                + KEY_ADDRESS + " TEXT,"
                + KEY_PHONE + " TEXT,"
                + KEY_EMAIL + " TEXT)" );
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }
    public void createContact(Contact c){
        SQLiteDatabase db = getWritableDatabase();

        String insert = "INSERT or replace INTO " + TABLE_NAME +  "("
                + KEY_NAME +", "
                + KEY_ADDRESS + ", "
                + KEY_PHONE +", "
                + KEY_EMAIL + ") " +
                "VALUES('"
                + c.getName() + "','"
                + c.getAddress() + "','"
                + c.getPhoneNumber() + "','"
                + c.getEmail() +"')" ;
        db.execSQL(insert);
        db.close();
    }
    public Contact getContact(int id){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[]{KEY_ID, KEY_NAME, KEY_ADDRESS, KEY_PHONE, KEY_EMAIL}, KEY_ID + "=?", new String[]{String.valueOf(id)},null,null,null,null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        Contact c = new Contact(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
        db.close();
        cursor.close();

        return c;
    }
    public void deleteContact(Contact c){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + "=?", new String[]{String.valueOf(c.getID())});
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
    public int updateContact(Contact c){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, c.getName());
        values.put(KEY_ADDRESS, c.getAddress());
        values.put(KEY_PHONE, c.getPhoneNumber());
        values.put(KEY_EMAIL, c.getEmail().toString());

        int rowsAffected = db.update(TABLE_NAME, values, KEY_ID + "=?", new String[] {String.valueOf(c.getID())});
        db.close();

        return rowsAffected;
    }
    public List<Contact> getAllContacts(){
        List<Contact> c = new ArrayList<Contact>();

        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ TABLE_NAME, null);

        if(cursor.moveToFirst()){

            do{
                c.add(new Contact(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(5)));
            }
            while(cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return c;
    }






















}
