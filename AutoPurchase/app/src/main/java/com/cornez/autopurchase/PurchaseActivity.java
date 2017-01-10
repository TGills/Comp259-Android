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
        setContentView(R.layout.purchase_layout);
        try {
            StartActivityForResult();
            onActivityResult();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    private void StartActivityForResult(){
        try {
            Intent intent = new Intent(this, LoanSummaryActivity.class);
            intent.setAction(Intent.ACTION_SEND_MULTIPLE);
            Integer radioId = loanTermRG.getCheckedRadioButtonId();
            RadioButton term = (RadioButton) findViewById(radioId);
            String termLength = term.getText().toString();
            String price = findViewById(R.id.editText1).toString();
            String downPayment = findViewById(R.id.editText2).toString();
            intent.putExtra("Price", price);
            intent.putExtra("DownPayment", downPayment);
            intent.putExtra("LoanTerm", termLength);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    private void onActivityResult(){
        try{
            Intent intent = getIntent();
            String totalCost = intent.getStringExtra("totalCost");
            String borrowedAmount = intent.getStringExtra("borrowedAmount");
            String interestAmount = intent.getStringExtra("interestAmount");
            TextView TotalCost = (TextView) findViewById(R.id.tvTotalCost);
            TextView BorrowedAmount = (TextView) findViewById(R.id.tvBorrowedAmount);
            TextView InterestAmount = (TextView) findViewById(R.id.tvInterestAmount);
            if(totalCost == null || borrowedAmount == null || interestAmount == null){
                TotalCost.setText("");
                BorrowedAmount.setText("");
               InterestAmount.setText("");
            }
            else {
                TotalCost.setText(totalCost);
                BorrowedAmount.setText(borrowedAmount);
                InterestAmount.setText(interestAmount);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        try{
            // Inflate the menu;
            getMenuInflater().inflate(R.menu.my, menu);
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
