package com.cornez.autopurchase;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class LoanSummaryActivity extends Activity {
    Auto mAuto;
    String loanReport;
    String monthlyPayment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            mAuto = new Auto();
            setContentView(R.layout.loansummary_layout);  //Setting the view to loansummary_layout.xml

            initData();         //Initializing the data
            buildLoanReport();  //Method to build and display the data
            displayData();      //Method to display the data
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

    private void initData(){
        try {
            //Receiving the intent
            Intent intent = getIntent();
            //Assigning the sent info to variables
            Double Price = Double.parseDouble(intent.getStringExtra("Price"));
            Double DownPayment = Double.parseDouble(intent.getStringExtra("DownPayment"));
            String LoanTerm = intent.getStringExtra("LoanTerm");
            mAuto.setPrice(Price);             //Sending the "price" to Auto.java
            mAuto.setDownPayment(DownPayment); //Sending the "down payment" to Auto.java
            mAuto.setLoanTerm(LoanTerm);       //Sending the "loan term" to Auto.java
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    private void buildLoanReport() {
        try {
            //Developing the monthly payment
            Resources res = getResources();
            monthlyPayment = res.getString(R.string.report_line1) + String.format("%.02f", mAuto.monthlyPayment());
            //Generating the report with all information
            loanReport = res.getString(R.string.report_line6) + String.format("%10.02f", mAuto.getPrice());
            loanReport += res.getString(R.string.report_line7) + String.format("%10.02f", mAuto.getDownPayment());
            loanReport += res.getString(R.string.report_line9) + String.format("%18.02f", mAuto.taxAmount());
            loanReport += res.getString(R.string.report_line10) + String.format("%18.02f", mAuto.totalCost());
            loanReport += res.getString(R.string.report_line11) + String.format("%12.02f", mAuto.borrowedAmount());
            loanReport += res.getString(R.string.report_line12) + String.format("%12.02f", mAuto.interestAmount());
            loanReport += "\n\n" + res.getString(R.string.report_line8) + " " + mAuto.getLoanTerm() + " years.";
            loanReport += "\n\n" + res.getString(R.string.report_line2);
            loanReport += res.getString(R.string.report_line3);
            loanReport += res.getString(R.string.report_line4);
            loanReport += res.getString(R.string.report_line5);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    private void displayData(){
        try {
            //Variables for the textviews
            TextView monthlyPayET = (TextView) findViewById(R.id.textView2);
            TextView loanReportET = (TextView) findViewById(R.id.textView3);
            //Displaying the information
            monthlyPayET.setText(monthlyPayment);
            loanReportET.setText(loanReport);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /*
     private void returnData(){
        //Defining the intent that sends data to PurchaseActivity.java
        Intent intent = new Intent(this, PurchaseActivity.class);
        intent.setAction(Intent.ACTION_SEND_MULTIPLE);
        //Gathering the values from Auto.java
        String TotalCost = String.valueOf(mAuto.totalCost());
        String BorrowedAmount = String.valueOf(mAuto.borrowedAmount());
        String InterestAmount = String.valueOf(mAuto.interestAmount());
        //Putting the information back into an intent to be sent to PurchaseActivity.java
        intent.putExtra("totalCost", TotalCost);
        intent.putExtra("borrowedAmount", BorrowedAmount);
        intent.putExtra("interestAmount", InterestAmount);
        startActivity(intent);
        setContentView(R.layout.purchase_layout);
     }
     */

    public void returnData(View view) {
        try {
            //Defining the intent that sends data to PurchaseActivity.java
            Intent intent = new Intent(this, PurchaseActivity.class);
            intent.setAction(Intent.ACTION_SEND_MULTIPLE);
            //Gathering the values from Auto.java
            String TotalCost = String.valueOf(mAuto.totalCost());
            String BorrowedAmount = String.valueOf(mAuto.borrowedAmount());
            String InterestAmount = String.valueOf(mAuto.interestAmount());
            //Putting the information back into an intent to be sent to PurchaseActivity.java
            intent.putExtra("totalCost", TotalCost);
            intent.putExtra("borrowedAmount", BorrowedAmount);
            intent.putExtra("interestAmount", InterestAmount);
            startActivity(intent);
            setContentView(R.layout.purchase_layout);

            finish();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /*
    public void goDataEntry(View view) {
        returnData();

        finish();
    }
    */
}