package com.guillaume2127.guillaume_comp259_finalproject;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //DATABASE AND ADAPTER OBJECTS
    DBHelper dbHelper;
    ArrayAdapter<Contact> arrayAdapter;

    //SINGLE RECORD INFORMATION IN A LISTVIEW
    List<Contact> ContactArrayList = new ArrayList<Contact>();

    //Contact CONTACT DATA ENTRY SCREEN
    Button addContactBTN;
    ImageView inputPhotoId;
    EditText inputName;
    EditText inputAddress;
    EditText inputEmail;
    EditText inputPhoneNumber;
    Drawable noContactImage;
    Uri defaultImage = Uri.parse("android.resource://com.cornez.petcontacts/drawable/none.png");

    Boolean newEntry = true;

    //CONTACT LISTING SCREEN
    ListView ContactListView = (ListView) findViewById(R.id.listViewContacts);
    TextView listViewID;
    TextView listViewName;


    TabHost tabHost;
    int contactIndex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TASK 1: SET UP THE DATABASE
        dbHelper = new DBHelper(getApplicationContext());


        //TASK 2: POPULATE THE DATABASE AND LISTVIEW
        if (dbHelper.getContactsCount() != 0)
            ContactArrayList.addAll(dbHelper.getAllContacts());
        populateList();




        //On click event for listview items
        ContactListView.setOnItemClickListener(new MainActivity() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this, ContactDetails.class);
                i.putExtra("Name", );
                startActivity(i);
            }
        });
    }

    //Populating the list
    private void populateList() {
        arrayAdapter = new ContactListAdapter();
        ContactListView.setAdapter(arrayAdapter);
    }
    private class ContactListAdapter extends ArrayAdapter<Contact> {
        public ContactListAdapter() {
            super(getApplicationContext(), R.layout.activity_main, ContactArrayList);
        }
        @Override
        public View getView(int position, View view, ViewGroup parent) {
            if (view == null) {
                view = getLayoutInflater().inflate(R.layout.activity_main, parent, false);
            }
            Contact currentContact = ContactArrayList.get(position);
            listViewName = (TextView) view.findViewById(R.id.tvName);
            listViewName.setText(currentContact.getName());
            return view;
        }
    }





    //Switch to new contact activity
    public void addNewContact(View view) {
        setContentView(R.layout.new_contact);
    }
}


