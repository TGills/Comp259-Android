package com.guillaume2127.travelmilescalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
/*
 * Created by guillaume2127
 */
public class DisplayData extends AppCompatActivity {
    //Global variable for the calculator
    TravelCalculator tc = new TravelCalculator();
    //Global cityOne
    String cityOne;
    String cityTwo;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_display);
        //Defining the intent to receive data
        Intent intent = getIntent();
        //Assigning the passed over values to variables
        cityOne = intent.getStringExtra("cityOne");
        cityTwo = intent.getStringExtra("cityTwo");
        //Sending the data to the calculator
        tc.setCityOne(cityOne);
        tc.setCityTwo(cityTwo);
        //Displaying the data
        showData();
    }
    //Displaying data in text views
    public void showData(){
        //Defining the textViews
        TextView cityFrom =(TextView) findViewById(R.id.tvFromCity);
        TextView cityTo =(TextView) findViewById(R.id.tvToCity);
        TextView distance =(TextView) findViewById(R.id.tvDistance);
        TextView ticketPrice =(TextView) findViewById(R.id.tvTicketPrice);
        TextView bonusMiles =(TextView) findViewById(R.id.tvBonusMiles);
        //Variables for my getters
        String getDistance = String.valueOf(tc.getDistance());
        String getTicketPrice = String.format("%.2f",(tc.getTicketPrice()));
        String getBonusMiles = String.valueOf(tc.getBonusMiles());
        //Setting the text of the textViews
        cityFrom.setText(cityOne);
        cityTo.setText(cityTwo);
        distance.setText(getDistance + " km");
        ticketPrice.setText("$" + getTicketPrice);
        bonusMiles.setText(getBonusMiles + " km");
    }
    //Return to activity_main
    public void back(View view) {
        finish();
    }
}
