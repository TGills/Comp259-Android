package com.guillaume2127.travelmilescalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String cityOne;
    String cityTwo;
    String discountCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    //Populate sCityOne
        Spinner dropdownOne = (Spinner)findViewById(R.id.sCityOne);
        String[] itemsOne = new String[]{"Regina", "Edmonton", "Vancouver"};
        ArrayAdapter<String> adapterOne = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, itemsOne);
        dropdownOne.setAdapter(adapterOne);
    //Populate sCityTwo
        Spinner dropdownTwo = (Spinner)findViewById(R.id.sCityTwo);
        String[] itemsTwo = new String[]{"Regina", "Edmonton", "Vancouver"};
        ArrayAdapter<String> adapterTwo = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, itemsTwo);
        dropdownTwo.setAdapter(adapterTwo);
        showData();
    }
    public void calculate(View view) {
        //Gathering value of sCityOne
        Spinner spinnerOne=(Spinner) findViewById(R.id.sCityOne);
        cityOne = spinnerOne.getSelectedItem().toString();
        //Gathering value of sCityTwo
        Spinner spinnerTwo=(Spinner) findViewById(R.id.sCityTwo);
        cityTwo = spinnerTwo.getSelectedItem().toString();
        //Gathering the discount code
        EditText discount = (EditText) findViewById(R.id.etDiscount);
        discountCode = discount.getText().toString();
        //Defining the intent
        Intent intent = new Intent(this, DisplayData.class);
        intent.setAction(Intent.ACTION_SEND_MULTIPLE);
        //Inserting the values to be passed over
        intent.putExtra("cityOne", cityOne);
        intent.putExtra("cityTwo", cityTwo);
        intent.putExtra("discountCode",discountCode);
        //Start activity
        startActivity(intent);
    }

    public void showData(){
        //Defining the intent to receive data
        Intent returnData = getIntent();
        //Assigning the passed over values to variables
        String returnCityOne = returnData.getStringExtra("fromCity");
        String returnCityTwo = returnData.getStringExtra("toCity");
        String returnDiscountCode = returnData.getStringExtra("discountCode");
        String returnDistance = returnData.getStringExtra("distance");
        String returnTicketPrice = returnData.getStringExtra("ticketPrice");
        String returnBonusMiles = returnData.getStringExtra("bonusMiles");
        //Defining the textViews
        TextView showCityFrom =(TextView) findViewById(R.id.tvFromCity);
        TextView showCityTo =(TextView) findViewById(R.id.tvToCity);
        TextView showDiscountCode = (TextView) findViewById(R.id.tvDiscountCode);
        TextView showDistance =(TextView) findViewById(R.id.tvDistance);
        TextView showTicketPrice =(TextView) findViewById(R.id.tvTicketPrice);
        TextView showBonusMiles =(TextView) findViewById(R.id.tvBonusMiles);
        //Cleaning the interface up
            //If these values are null, the grid layout is going to be hidden
        GridLayout gl = (GridLayout) findViewById(R.id.glData);
        if(returnCityOne == null
                || returnCityTwo == null
                || returnDistance == null
                || returnTicketPrice == null
                || returnBonusMiles == null){
                    gl.setVisibility(View.GONE); }
        //This is in a separate if statement because it is possible that there will be no code
        if(returnDiscountCode == null) {
            showDiscountCode.setVisibility(View.GONE);
        }
        //Setting the text of the textViews
        showCityFrom.setText(returnCityOne);
        showCityTo.setText(returnCityTwo);
        showDiscountCode.setText(returnDiscountCode);
        showDistance.setText(returnDistance + " km");
        showTicketPrice.setText("$" + returnTicketPrice);
        showBonusMiles.setText(returnBonusMiles + " km");
    }
}