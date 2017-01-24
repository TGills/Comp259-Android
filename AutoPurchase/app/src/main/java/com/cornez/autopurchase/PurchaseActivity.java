package com.cornez.autopurchase;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

public class PurchaseActivity extends Activity {
    // LAYOUT INPUT REFERENCES
    private EditText carPriceET;
    private EditText downPayET;
    private RadioGroup loanTermRG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Setting the view to purchase_layout.xml
        setContentView(R.layout.purchase_layout);
        //Method to output data recieved from LoanSummaryActivity.java
        onActivityResult();
    }

    private void StartActivityForResult(){
        try {
            //Defining the new intent
            Intent intent = new Intent(this, LoanSummaryActivity.class);
            intent.setAction(Intent.ACTION_SEND_MULTIPLE);
            //Gathering the value of the selected radio button
            RadioGroup loanTermRG = (RadioGroup) findViewById(R.id.radioGroup1);
            Integer radioId = loanTermRG.getCheckedRadioButtonId();
            RadioButton term = (RadioButton) findViewById(radioId);
            String termLength = term.getText().toString();
            //Gathering the price value
            String price = findViewById(R.id.editText1).toString();
            //Gathering the down payment value
            String downPayment = findViewById(R.id.editText2).toString();
            //Entering the gathered data into the intent
            intent.putExtra("Price", price);
            intent.putExtra("DownPayment", downPayment);
            intent.putExtra("LoanTerm", termLength);
            //Start loan activity
            startActivity(intent);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    private void onActivityResult(){
        try{
            //Defining the intent
            Intent intent = getIntent();
            //Assigning the passed over values to variables
            String totalCost = intent.getStringExtra("totalCost");
            String borrowedAmount = intent.getStringExtra("borrowedAmount");
            String interestAmount = intent.getStringExtra("interestAmount");
            //Variables for the textviews
            TextView TotalCost = (TextView) findViewById(R.id.tvTotalCost);
            TextView BorrowedAmount = (TextView) findViewById(R.id.tvBorrowedAmount);
            TextView InterestAmount = (TextView) findViewById(R.id.tvInterestAmount);
            //Tried to ensure a null exception was not thrown
            if(totalCost == null || borrowedAmount == null || interestAmount == null){
                //Should be blank
                TotalCost.setText("");
                BorrowedAmount.setText("");
                InterestAmount.setText("");
            }
            else {
                //Outputting the values to the textviews
                TotalCost.setText(totalCost);
                BorrowedAmount.setText(borrowedAmount);
                InterestAmount.setText(interestAmount);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    //On click for button
    public void activateLoanSummary(View view) {
        try {
            //Method to send data to LoanSummaryActivity.java
            StartActivityForResult();
            setContentView(R.layout.loansummary_layout);
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

}