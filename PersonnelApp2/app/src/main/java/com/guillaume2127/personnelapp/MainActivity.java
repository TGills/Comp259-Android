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

public class MainActivity extends AppCompatActivity {
    Personnel personnel;
    String birthDate;

    //Creating global variables for the textviews and whatnot
     TextView PersonnelID = (TextView) findViewById(R.id.tvPersonnelID);
     TextView PictureID = (TextView) findViewById(R.id.tvPictureID);
     TextView Name = (TextView) findViewById(R.id.tvName);
     TextView Address = (TextView) findViewById(R.id.tvAddress);
     TextView Phone = (TextView) findViewById(R.id.tvPhone);
     TextView Email = (TextView) findViewById(R.id.tvEmail);
     TextView Position = (TextView) findViewById(R.id.tvPosition);
     TextView supervisorName = (TextView) findViewById(R.id.tvSupervisorName);
     TextView Role = (TextView) findViewById(R.id.tvRole);
     TextView Birthdate = (TextView) findViewById(R.id.tvBirthdate);;
     TextView Age = (TextView) findViewById(R.id.tvAge);
     TextView Married = (TextView) findViewById(R.id.tvMarried);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //MainActivity pd = new MainActivity();
        InitData();
        /*
        PersonnelID = (TextView) findViewById(R.id.tvPersonnelID);
        PictureID = (TextView) findViewById(R.id.tvPictureID);
        Name = (TextView) findViewById(R.id.tvName);
        Address = (TextView) findViewById(R.id.tvAddress);
        Phone = (TextView) findViewById(R.id.tvPhone);
        Email = (TextView) findViewById(R.id.tvEmail);
        Position = (TextView) findViewById(R.id.tvPosition);
        supervisorName = (TextView) findViewById(R.id.tvSupervisorName);
        Role = (TextView) findViewById(R.id.tvRole);
        Age = (TextView) findViewById(R.id.tvAge);
        Married = (TextView) findViewById(R.id.tvMarried);
        */
        //PersonnelID
        PersonnelID.setText(String.valueOf(personnel.getPersonnelID()));
        //PictureID
        PictureID.setText(String.valueOf(personnel.getPictureID()));
        //Name
        Name.setText(personnel.getName());
        Name.addTextChangedListener(twName);
        //Address
        Address.setText(personnel.getAddress());
        Address.addTextChangedListener(twAddress);
        //Phone Number
        Phone.setText(personnel.getPhone());
        Phone.addTextChangedListener(twPhone);
        //Email address
        Email.setText(personnel.getEmail());
        Email.addTextChangedListener(twEmail);
        //Position
        Position.setText(personnel.getPosition());
        Position.addTextChangedListener(twPosition);
        //Supervisor Name
        supervisorName.setText(personnel.getSupervisorName());
        supervisorName.addTextChangedListener(twSupervisorName);
        //Role
        Role.setText(personnel.getRole());
        Role.addTextChangedListener(twRole);
        //Birth date
        Birthdate.setText(personnel.getBirthdate());
        Birthdate.addTextChangedListener(twBirthDate);
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
    public void InitData(){
        personnel = new Personnel(1234,223,"John Doe", "OverTheHedge, 111", "306-123-5555",
                "jdoe@generic.ca", "Clerk", "Mr. Smith", "Staff", "06-15-1994" , Age(birthDate), TRUE);
    }
    public int Age(String by){
        int age = 0;
        if(birthDate == null){
            age = 0;
        }
        else {
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
        return age;
    }

    private TextWatcher twName = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }
        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }
        @Override
        public void afterTextChanged(Editable editable) {
            personnel.setName(String.valueOf(Name));
        }
    };
    private TextWatcher twAddress = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }
        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }
        @Override
        public void afterTextChanged(Editable editable) {
            personnel.setAddress(String.valueOf(Address));
        }
    };
    private TextWatcher twPhone = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }
        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }
        @Override
        public void afterTextChanged(Editable editable) {
            personnel.setPhone(String.valueOf(Phone));
        }
    };
    private TextWatcher twEmail = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }
        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }
        @Override
        public void afterTextChanged(Editable editable) {
            personnel.setEmail(String.valueOf(Email));
        }
    };
    private TextWatcher twPosition = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }
        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }
        @Override
        public void afterTextChanged(Editable editable) {
            personnel.setPosition(String.valueOf(Position));
        }
    };
    private TextWatcher twSupervisorName = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }
        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }
        @Override
        public void afterTextChanged(Editable editable) {
            personnel.setSupervisorName(String.valueOf(supervisorName));
        }
    };
    private TextWatcher twRole = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }
        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }
        @Override
        public void afterTextChanged(Editable editable) {
            personnel.setRole(String.valueOf(Role));
        }
    };
    private TextWatcher twBirthDate = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }
        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            Age(birthDate);
            InitData();
            personnel.setBirthdate(String.valueOf(Birthdate));
        }
        @Override
        public void afterTextChanged(Editable editable) {
            Age(birthDate);
            InitData();
            personnel.setBirthdate(String.valueOf(Birthdate));
            //Age();
            //personnel.setBirthdate(String.valueOf(Birthdate));

        }
    };


}