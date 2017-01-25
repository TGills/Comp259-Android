package com.guillaume2127.travelmilescalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
/*
 * Created by guillaume2127
 */
public class DisplayData extends AppCompatActivity {
    //Global variable for the calculator
    TravelCalculator tc = new TravelCalculator();
    String cityOne;
    String cityTwo;
    String discountCode;
    String distance;
    String ticketPrice;
    String bonusMiles;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_display);
        //Defining the intent to receive data
        Intent intent = getIntent();
        //Assigning the passed over values to variables
        cityOne = intent.getStringExtra("cityOne");
        cityTwo = intent.getStringExtra("cityTwo");
        discountCode = intent.getStringExtra("discountCode");
        //Sending the data to the calculator
        tc.setCityOne(cityOne);
        tc.setCityTwo(cityTwo);
        tc.setTicketDiscount(discountCode);
        //Displaying the data
        showData();
    }
    //Displaying data in text views
    public void showData(){
        //Defining the textViews
        TextView showCityFrom =(TextView) findViewById(R.id.tvFromCity);
        TextView showCityTo =(TextView) findViewById(R.id.tvToCity);
        TextView showDistance =(TextView) findViewById(R.id.tvDistance);
        TextView showTicketPrice =(TextView) findViewById(R.id.tvTicketPrice);
        TextView showBonusMiles =(TextView) findViewById(R.id.tvBonusMiles);
        //Variables for my getters
        distance = String.valueOf(tc.getDistance());
        ticketPrice = String.format("%.2f",(tc.getTicketPrice()));
        bonusMiles = String.valueOf(tc.getBonusMiles());
        //Setting the text of the textViews
        showCityFrom.setText(cityOne);
        showCityTo.setText(cityTwo);
        showDistance.setText(distance + " km");
        showTicketPrice.setText("$" + ticketPrice);
        showBonusMiles.setText(bonusMiles + " km");
    }
    //Return to activity_main
    public void back(View view) {
        //Logging for testing
        Log.d("fromCity: ",cityOne);
        Log.d("toCity: ",cityTwo);
        Log.d("discountCode: ",discountCode);
        Log.d("distance: ",distance);
        Log.d("ticketPrice: ",ticketPrice);
        Log.d("bonusMiles: ",bonusMiles);
        //Defining the intent
        Intent returnData = new Intent(this, MainActivity.class);
        returnData.setAction(Intent.ACTION_SEND_MULTIPLE);
        //Inserting the values to be passed over
        returnData.putExtra("fromCity", cityOne);
        returnData.putExtra("toCity", cityTwo);
        returnData.putExtra("discountCode",discountCode);
        returnData.putExtra("distance",distance);
        returnData.putExtra("ticketPrice",ticketPrice);
        returnData.putExtra("bonusMiles",bonusMiles);
        //Start activity
        startActivity(returnData);
        //finish();
    }
}
