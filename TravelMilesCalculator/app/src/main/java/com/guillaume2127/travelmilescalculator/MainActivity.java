package com.guillaume2127.travelmilescalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    String cityOne;
    String cityTwo;

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
    }
    public void calculate(View view) {
        //Gathering value of sCityOne
        Spinner spinnerOne=(Spinner) findViewById(R.id.sCityOne);
        cityOne = spinnerOne.getSelectedItem().toString();
        //Gathering value of sCityTwo
        Spinner spinnerTwo=(Spinner) findViewById(R.id.sCityTwo);
        cityTwo = spinnerTwo.getSelectedItem().toString();
        //Defining the intent
        Intent intent = new Intent(this, DisplayData.class);
        intent.setAction(Intent.ACTION_SEND_MULTIPLE);
        //Inserting the values to be passed over
        intent.putExtra("cityOne", cityOne);
        intent.putExtra("cityTwo", cityTwo);
        //Start activity
        startActivity(intent);
    }



}

