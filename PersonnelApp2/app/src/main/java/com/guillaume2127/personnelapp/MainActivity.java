package com.guillaume2127.personnelapp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ImageView;
import android.widget.TextView;
import java.text.ParseException;
import java.util.Date;
import static java.lang.Boolean.TRUE;
import static java.lang.Boolean.valueOf;
import java.*;

public class MainActivity extends AppCompatActivity {
    Personnel personnel;
    String birthDate;

    //Creating global variables for the textviews and whatnot
     TextView PersonnelID;
     TextView PictureID;
     TextView Name;
     TextView Address;
     TextView Phone;
     TextView Email;
     TextView Position;
     TextView supervisorName;
     TextView Role;
     TextView Birthdate;
     TextView Age;
     TextView Married;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //MainActivity pd = new MainActivity();
        InitData();
        PersonnelID = (TextView) findViewById(R.id.tvPersonnelID);
        PictureID = (TextView) findViewById(R.id.tvPictureID);
        Name = (TextView) findViewById(R.id.tvName);
        Address = (TextView) findViewById(R.id.tvAddress);
        Phone = (TextView) findViewById(R.id.tvPhone);
        Email = (TextView) findViewById(R.id.tvEmail);
        Position = (TextView) findViewById(R.id.tvPosition);
        supervisorName = (TextView) findViewById(R.id.tvSupervisorName);
        Role = (TextView) findViewById(R.id.tvRole);
        Birthdate = (TextView) findViewById(R.id.tvBirthdate);
        Age = (TextView) findViewById(R.id.tvAge);
        Married = (TextView) findViewById(R.id.tvMarried);
        Output();
    }
    public void InitData(){
        personnel = new Personnel(1234,223,"John Doe", "OverTheHedge, 111", "306-123-5555",
                "jdoe@generic.ca", "Clerk", "Mr. Smith", "Staff", "06-15-1994" , Age(birthDate), TRUE);
    }
    public void Output(){
        //PersonnelID
        PersonnelID.setText(String.valueOf(personnel.getPersonnelID()));
        //PictureID
        PictureID.setText(String.valueOf(personnel.getPictureID()));
        //Name
        Name.setText(personnel.getName());
        Name.addTextChangedListener(textWatcher);
        //Address
        Address.setText(personnel.getAddress());
        Address.addTextChangedListener(textWatcher);
        //Phone Number
        Phone.setText(personnel.getPhone());
        Phone.addTextChangedListener(textWatcher);
        //Email address
        Email.setText(personnel.getEmail());
        Email.addTextChangedListener(textWatcher);
        //Position
        Position.setText(personnel.getPosition());
        Position.addTextChangedListener(textWatcher);
        //Supervisor Name
        supervisorName.setText(personnel.getSupervisorName());
        supervisorName.addTextChangedListener(textWatcher);
        //Role
        Role.setText(personnel.getRole());
        Role.addTextChangedListener(textWatcher);
        //Birth date
        Birthdate.setText(personnel.getBirthdate());
        Birthdate.addTextChangedListener(textWatcher);
        //Age
        Age.setText(String.valueOf(personnel.getAge()));
        //Married
        if(personnel.getMarried() == TRUE){
            Married.setText("Yes");
        }
        else{
            Married.setText("No");
        }
        //Image
        ImageView ivPictureProfile= (ImageView) findViewById(R.id.ivPictureProfile);
        ivPictureProfile.setImageResource(R.drawable.profilepicture);
    }
    public int Age(String by){
        int age = 0;
        if(Birthdate != null){
            try {
                //Gathering the YEAR from a calendar that grabs the date right now
                Calendar dateNow = Calendar.getInstance();
                int year = dateNow.get(Calendar.YEAR);
                //Converting the date into a calendar so that I may grab the year
                Calendar bYear = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("DD-MM-yyyy");
                Date sd = df.parse(by);
                bYear.setTime(sd);
                int birthYear = bYear.get(Calendar.YEAR);
                //Doing the calculation for age
                age = year - birthYear;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        else {
            age = 9999;
        }
        return age;
    }


    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }
        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }
        @Override
        public void afterTextChanged(Editable editable) {
           //Use this one
                PersonnelID.removeTextChangedListener(textWatcher);
                PictureID.removeTextChangedListener(textWatcher);
                Name.removeTextChangedListener(textWatcher);
                Address.removeTextChangedListener(textWatcher);
                Phone.removeTextChangedListener(textWatcher);
                Email.removeTextChangedListener(textWatcher);
                Position.removeTextChangedListener(textWatcher);
                supervisorName.removeTextChangedListener(textWatcher);
                Role.removeTextChangedListener(textWatcher);
                Birthdate.removeTextChangedListener(textWatcher);
                Married.removeTextChangedListener(textWatcher);
                int PID = Integer.parseInt(PersonnelID.getText().toString());
                int PicID = Integer.parseInt(PictureID.getText().toString());
                String NAME = Name.getText().toString();
                String ADDRESS = Address.getText().toString();
                String PHONE = Phone.getText().toString();
                String EMAIL = Email.getText().toString();
                String POSITION = Position.getText().toString();
                String SNAME = supervisorName.getText().toString();
                String ROLE = Role.getText().toString();
                String BDATE = Birthdate.getText().toString();
                boolean MARRIED = Boolean.parseBoolean((Married.getText().toString()));

                personnel = new Personnel(PID, PicID, NAME, ADDRESS, PHONE, EMAIL, POSITION,
                        SNAME, ROLE, BDATE, Age(BDATE), MARRIED);
                Output();


        }
    };

}