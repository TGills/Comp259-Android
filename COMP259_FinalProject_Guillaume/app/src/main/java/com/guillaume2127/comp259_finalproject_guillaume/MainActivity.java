package com.guillaume2127.comp259_finalproject_guillaume;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //DATABASE AND ADAPTER OBJECTS
    DatabaseHelper dbHelper;
    ArrayAdapter<Contact> arrayAdapter;

    //SINGLE RECORD INFORMATION IN A LISTVIEW
    List<Contact> ContactArrayList = new ArrayList<Contact>();

    //PET CONTACT DATA ENTRY SCREEN
    Button addContactBTN;
    EditText inputName;
    EditText inputAddress;
    EditText inputEmail;
    EditText inputPhoneNumber;

    Boolean newEntry = true;

    //PET LISTING SCREEN
    ListView contactListView;
    TextView listViewName;
    TextView listViewAddress;
    ImageView listViewEmail;
    TextView listViewPhone;

    int contactIndex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TASK 1: SET UP THE DATABASE
        dbHelper = new DatabaseHelper(getApplicationContext());

        // TASK 2: REFERENCE INPUT UI COMPONENTS FROM THE LAYOUT
        addContactBTN = (Button) findViewById(R.id.addBTN);
        inputName = (EditText) findViewById(R.id.memberName);
        inputAddress = (EditText) findViewById(R.id.memberDetail);
        inputEmail = (EditText) findViewById(R.id.memberDetail);
        inputPhoneNumber = (EditText) findViewById(R.id.memberPhoneNumber);
        contactListView = (ListView) findViewById(R.id.lvContactNames);

        // TASK 5: A PET CAN BE ADDED ONCE USER HAS ENTERED A NAME
        inputName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s,
                                          int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s,
                                      int start, int before, int count) {
                addContactBTN.setEnabled(String.valueOf(
                        inputName.getText()).trim().length() > 0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // TASK 6: LISTENER EVENTS FOR PHOTO SELECTION AND BUTTON
        addContactBTN.setOnClickListener(recordContactInformation);

        //TASK 7: POPULATE THE DATABASE
        if (dbHelper.getContactsCount() != 0)
            ContactArrayList.addAll(dbHelper.getAllContacts());

        populateList();










    }

    //*********** ADD CONTACT RECORD TO THE DATABASE *******
    private final View.OnClickListener recordContactInformation =
            new View.OnClickListener() {

                public void onClick(View v) {
                    Contact c = new Contact(
                            dbHelper.getContactsCount(),
                            String.valueOf(inputName.getText().toString()),
                            String.valueOf(inputAddress.getText().toString()),
                            String.valueOf(inputEmail.getText().toString()),
                            String.valueOf(inputPhoneNumber.getText().toString()));

                    if (!contactExists(c)) {
                        dbHelper.createContact(c);
                        ContactArrayList.add(c);
                        arrayAdapter.notifyDataSetChanged();
                        Toast.makeText(getApplicationContext(),
                                inputName.getText().toString()
                                        + " has been added.",
                                Toast.LENGTH_SHORT).show();
                        newEntry = true;
                        onResume();
                        return;
                    }
                    Toast.makeText(getApplicationContext(),
                            String.valueOf(inputName.getText())
                                    + "has already been added. Use another name",
                            Toast.LENGTH_LONG).show();
                }
            };


    //**************CONTEXT MENU : DELETE A PET

    public void onCreateContextMenu(ContextMenu menu, View view,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);

        menu.setHeaderIcon(R.drawable.ic_launcher);
        menu.setHeaderTitle("Contact ...");
        menu.add(Menu.NONE, 1, Menu.NONE, "Delete Contact");
    }

    public boolean onContextItemSelected(MenuItem item) {
        dbHelper.deleteContact(
                ContactArrayList.get(contactIndex));
        ContactArrayList.remove(contactIndex);
        arrayAdapter.notifyDataSetChanged();

        return super.onContextItemSelected(item);
    }

    private boolean contactExists(Contact member) {
        String first = member.getPhoneNumber();
        int contactCount = ContactArrayList.size();

        for (int i = 0; i < contactCount; i++) {
            if (first.compareToIgnoreCase(
                    ContactArrayList.get(i).getPhoneNumber()) == 0)
                return true;
        }
        return false;
    }





    //activity_main.xml
    public void newContact(View view) {




    }
    //new_contact.xml
    public void createContact(View view) {




    }
    //view_contact.xml
    public void editContact(View view) {

    }
    public void deleteContact(View view) {

    }
    public void returnToMain(View view) {

    }
}
