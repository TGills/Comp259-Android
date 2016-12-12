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
public class MainActivity extends AppCompatActivity {
    Personnel personnel;
    String birthDate = "06-15-1994";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainActivity pd = new MainActivity();
        InitData();

        final TextView PersonnelID = (TextView) findViewById(R.id.tvPersonnelID);
        PersonnelID.setText(String.valueOf(personnel.getPersonnelID()));
        final TextView PictureID = (TextView) findViewById(R.id.tvPictureID);
        PictureID.setText(String.valueOf(personnel.getPictureID()));
        final TextView Name = (TextView) findViewById(R.id.tvName);
        Name.setText(personnel.getName());
        final TextView Address = (TextView) findViewById(R.id.tvAddress);
        Address.setText(personnel.getAddress());
        final TextView Phone = (TextView) findViewById(R.id.tvPhone);
        Phone.setText(personnel.getPhone());
        final TextView Email = (TextView) findViewById(R.id.tvEmail);
        Email.setText(personnel.getEmail());
        final TextView Position = (TextView) findViewById(R.id.tvPosition);
        Position.setText(personnel.getPosition());
        final TextView supervisorName = (TextView) findViewById(R.id.tvSupervisorName);
        supervisorName.setText(personnel.getSupervisorName());
        final TextView Role = (TextView) findViewById(R.id.tvRole);
        Role.setText(personnel.getRole());
        final TextView Birthdate = (TextView) findViewById(R.id.tvBirthdate);
        Birthdate.setText(personnel.getBirthdate());
        final TextView Age = (TextView) findViewById(R.id.tvAge);
        Age.setText(String.valueOf(personnel.getAge()));
        final TextView Married = (TextView) findViewById(R.id.tvMarried);
        if(personnel.getMarried() == TRUE){
            Married.setText("Yes");
        }
        else{
            Married.setText("No");
        }
        ImageView ivPictureProfile= (ImageView) findViewById(R.id.ivPictureProfile);
        ivPictureProfile.setImageResource(R.drawable.profilepicture);


    }
    public void InitData(){
        personnel = new Personnel(1234,223,"John Doe", "OverTheHedge, 111", "306-123-5555",
                "jdoe@generic.ca", "Clerk", "Mr. Smith", "Staff", birthDate , Age(), TRUE);
    }
    public int Age(){
        int age = 0;
        try {
            //Gathering the YEAR from a calendar that grabs the date right now
            Calendar dateNow = Calendar.getInstance();
            int year = dateNow.get(Calendar.YEAR);
            //Converting the date into a calendar so that I may grab the year
            Calendar bYear = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("DD-MM-yyyy");
            Date sd = df.parse(birthDate);
            bYear.setTime(sd);
            int birthYear = bYear.get(Calendar.YEAR);
            //Doing the calculation for age
            age = year - birthYear;
        }
        catch(ParseException e){
            e.printStackTrace();
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

        }
    };










}
