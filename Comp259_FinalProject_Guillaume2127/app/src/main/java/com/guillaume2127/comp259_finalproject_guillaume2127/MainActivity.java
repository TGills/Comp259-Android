package com.guillaume2127.comp259_finalproject_guillaume2127;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

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
    ListView ContactListView;
    ImageView listViewPhoto;
    TextView listViewID;
    TextView listViewName;
    TextView listViewAddress;
    TextView listViewEmail;
    TextView listViewPhone;

    TabHost tabHost;
    int contactIndex;
    int currentID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // TASK 1: SET UP THE DATABASE
        dbHelper = new DBHelper(getApplicationContext());
        // TASK 2: REFERENCE INPUT UI COMPONENTS FROM THE LAYOUT
        addContactBTN = (Button) findViewById(R.id.addBTN);
        inputName = (EditText) findViewById(R.id.memberName);
        inputAddress = (EditText) findViewById(R.id.memberAddress);
        inputEmail = (EditText) findViewById(R.id.memberEmail);
        inputPhoneNumber = (EditText) findViewById(R.id.memberPhoneNumber);
        ContactListView = (ListView) findViewById(R.id.listView);
        inputPhotoId = (ImageView) findViewById(R.id.memberPhoto);
        noContactImage = inputPhotoId.getDrawable();
        //TASK 3: SET UP TABS
        registerForContextMenu(ContactListView);
        ContactListView.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent,
                                                   View view, int position, long id) {
                        contactIndex = position;
                        //get currentContact ID
                        Contact currentContact = (Contact) view.getTag();
                        currentID = currentContact.getID();



                        return false;
                    }
                });
        // TASK 4: CREATE ACTION TABS: ADD Contact INFORMATION
        tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();
        TabHost.TabSpec tabSpec = tabHost.newTabSpec("add Contact information");
        tabSpec.setContent(R.id.tabInfo);
        tabSpec.setIndicator("add Contact information");
        tabHost.addTab(tabSpec);
        tabSpec = tabHost.newTabSpec("view all Contacts");
        tabSpec.setContent(R.id.tabList);
        tabSpec.setIndicator("view all Contacts");
        tabHost.addTab(tabSpec);
        // TASK 5: A CONTACT CAN BE ADDED ONCE USER HAS ENTERED A NAME
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
        inputPhotoId.setOnClickListener(getPhotoFromGallery);
        addContactBTN.setOnClickListener(recordContactInformation);
        //TASK 7: POPULATE THE DATABASE
        if (dbHelper.getContactsCount() != 0)
            ContactArrayList.addAll(dbHelper.getAllContacts());
        populateList();
    }
    //******* ACTIVATE AN INTENT TO CHOOSE A PHOTO FROM THE PHOTO GALLERY
    private final View.OnClickListener getPhotoFromGallery =
            new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Contact Image"), 1);
                }
            };
    //*********** ADD Contact RECORD TO THE DATABASE *******
    private final View.OnClickListener recordContactInformation = new View.OnClickListener() {
                public void onClick(View v) {
                    Contact contact = new Contact(
                            dbHelper.getContactsCount(), //ID
                            String.valueOf(inputName.getText().toString()), //Name
                            String.valueOf(inputAddress.getText().toString()), //Address
                            String.valueOf(inputEmail.getText().toString()), //Email
                            String.valueOf(inputPhoneNumber.getText().toString()), //Phone
                            defaultImage);
                    Log.d("ID: ", String.valueOf(dbHelper.getContactsCount()));
                    Log.d("Name: ", String.valueOf(inputName.getText().toString()));
                    Log.d("Address: ",  String.valueOf(inputAddress.getText().toString()));
                    Log.d("Email: ", String.valueOf(inputEmail.getText().toString()));
                    Log.d("PhoneNumber: ", String.valueOf(inputPhoneNumber.getText().toString()));

                    if (!contactExists(contact)) {
                        dbHelper.createContact(contact);
                        ContactArrayList.add(contact);
                        arrayAdapter.notifyDataSetChanged();
                        Toast.makeText(getApplicationContext(),
                                inputName.getText().toString() + " has been added.",
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
    //**************CONTEXT MENU : DELETE A Contact, EDIT A Contact
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);
        menu.setHeaderIcon(R.drawable.ic_launcher);
        menu.setHeaderTitle("Contact ...");
        menu.add(Menu.NONE, 1, Menu.NONE, "Delete Contact");
        menu.add(Menu.NONE, 1, Menu.NONE, "Edit Contact");
        menu.add(Menu.NONE, 1, Menu.NONE, "Contact Details");
    }
    //Delete Contact
    public boolean onContextItemSelected(MenuItem item) {
            if (item.getTitle() == "Delete Contact") {
                dbHelper.deleteContact(ContactArrayList.get(currentID));
                ContactArrayList.remove(currentID);
                arrayAdapter.notifyDataSetChanged();
            }
            else if (item.getTitle() == "Edit Contact") {
                setContentView(R.layout.edit_contact);
            }
            else if(item.getTitle() == "Contact Details"){
                Intent i = new Intent(this, contact_details.class);
                i.setAction(Intent.ACTION_SEND_MULTIPLE);
                int position = 0;
                String selectedFromList = String.valueOf(currentID);
                Log.d("ID: ", selectedFromList);
                i.putExtra("ID", selectedFromList);
                startActivity(i);
                setContentView(R.layout.contact_details);
            }
        return super.onContextItemSelected(item);
    }
    public void deleteContact(MenuItem item){

    }

    private boolean contactExists(Contact member) {
        String first = member.getPhoneNumber();
        int contactCount = ContactArrayList.size();
        Log.d("contactCount: ", String.valueOf(contactCount));
        for (int i = 0; i <= (contactCount - 1); i++) {                                              //IMPORTANT
            if (first.compareToIgnoreCase(ContactArrayList.get(i).getPhoneNumber()) == 0)
                return true;
        }
        return false;
    }
    // INTENT RETURNS A PHOTO SELECTED FROM THE PHOTO GALLERY
    public void onActivityResult(int reqCode,
                                 int resCode, Intent data) {
        if (resCode == RESULT_OK) {
            if (reqCode == 1) {
                newEntry = false;
                defaultImage = data.getData();
                inputPhotoId.setImageURI(data.getData());
            }
        }
    }
    private void populateList() {
        arrayAdapter = new ContactListAdapter();
        ContactListView.setAdapter(arrayAdapter);
    }
    //Updating a contact
    public void contactUpdate(View view) {
        EditText updateName = (EditText) findViewById(R.id.etName);
        EditText updateAddress = (EditText) findViewById(R.id.etAddress);
        EditText updateEmail = (EditText) findViewById(R.id.etEmail);
        EditText updatePhoneNumber = (EditText) findViewById(R.id.etPhoneNumber);

        Contact contact = new Contact(
                dbHelper.getContactsCount(), //ID
                String.valueOf(updateName.getText().toString()), //Name
                String.valueOf(updateAddress.getText().toString()), //Address
                String.valueOf(updateEmail.getText().toString()), //Email
                String.valueOf(updatePhoneNumber.getText().toString()), //Phone
                defaultImage);

        dbHelper.updateContact(contact);
        setContentView(R.layout.activity_main);
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
            listViewName = (TextView)
                    view.findViewById(R.id.textViewName);
            listViewAddress = (TextView)
                    view.findViewById(R.id.textViewAddress);
            listViewEmail = (TextView)
                    view.findViewById(R.id.textViewEmail);
            listViewPhone = (TextView)
                    view.findViewById(R.id.textViewPhone);
            listViewPhoto = (ImageView)
                    view.findViewById(R.id.memberPhoto);
            //listViewID.setText(currentContact.getID());
            listViewName.setText(currentContact.getName());
            listViewAddress.setText(currentContact.getAddress());
            listViewEmail.setText(currentContact.getEmail());
            listViewPhone.setText(currentContact.getPhoneNumber());
            listViewPhoto.setImageURI(Uri.parse(String.valueOf(currentContact.getImageURL())));

            view.setTag(currentContact);

            return view;
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        //CLEAR OUT CONTACT INFORMATION IF IT IS A NEW ENTRY
        if (newEntry) {
            inputName.setText("");
            inputAddress.setText("");
            inputEmail.setText("");
            inputPhoneNumber.setText("");
            inputPhotoId.setImageDrawable(noContactImage);
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}