package com.guillaume2127.comp259_finalproject_guillaume2127;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by guillaume2127 on 24/02/2017.
 */
public class contact_details extends AppCompatActivity {

    ListView ContactListView;
    ImageView listViewPhoto;
    TextView listViewID;
    TextView listViewName;
    TextView listViewAddress;
    TextView listViewEmail;
    TextView listViewPhone;

    DBHelper dbHelper;
    ArrayAdapter<Contact> arrayAdapter;

    List<Contact> ContactArrayList = new ArrayList<Contact>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        String contactID;
        Intent i = getIntent();
        contactID = i.getStringExtra("ID");
        Integer id = Integer.parseInt(contactID);

        if (dbHelper.getContactsCount() != 0){
            ContactArrayList.addAll(dbHelper.getAContact(id));
        }
        populateList();
    }

    private void populateList() {
        arrayAdapter = new ContactListAdapter();
        ContactListView.setAdapter(arrayAdapter);
    }
    private class ContactListAdapter extends ArrayAdapter<Contact> {
        public ContactListAdapter() {
            super(getApplicationContext(),
                    R.layout.listview_item, ContactArrayList);
        }
        @Override
        public View getView(int position, View view, ViewGroup parent) {
            if (view == null)
                view = getLayoutInflater().inflate(R.layout.listview_item, parent, false);

            Contact currentContact = ContactArrayList.get(position);
            listViewID = (TextView)
                    view.findViewById(R.id.textViewID);
            listViewName = (TextView)
                    view.findViewById(R.id.textViewName);
            listViewAddress = (TextView)
                    view.findViewById(R.id.textViewAddress);
            listViewEmail = (TextView)
                    view.findViewById(R.id.textViewEmail);
            listViewPhone = (TextView)
                    view.findViewById(R.id.textViewPhone);

            listViewID.setText(currentContact.getID());
            listViewName.setText(currentContact.getName());
            listViewAddress.setText(currentContact.getAddress());
            listViewEmail.setText(currentContact.getEmail());
            listViewPhone.setText(currentContact.getPhoneNumber());

            return view;
        }
    }
}





