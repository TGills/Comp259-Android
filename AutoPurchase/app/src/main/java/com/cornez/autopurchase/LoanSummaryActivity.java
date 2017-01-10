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
    TextView monthlyPayET = (TextView) findViewById(R.id.textView2);
    TextView loanReportET = (TextView) findViewById(R.id.textView3);
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuto = new Auto();
        setContentView(R.layout.loansummary_layout);
        buildLoanReport();
        // PASS DATA
        monthlyPayET.setText(monthlyPayment);
        loanReportET.setText(loanReport);
        returnData();
    }
    private void returnData(){
        Intent intent = new Intent(this, PurchaseActivity.class);
        intent.setAction(Intent.ACTION_SEND_MULTIPLE);
        String TotalCost = String.valueOf(mAuto.totalCost());
        String BorrowedAmount = String.valueOf(mAuto.borrowedAmount());
        String InterestAmount = String.valueOf(mAuto.interestAmount());
        intent.putExtra("totalCost", TotalCost);
        intent.putExtra("borrowedAmount", BorrowedAmount);
        intent.putExtra("interestAmount", InterestAmount);
    }
    private void buildLoanReport() {
        Intent intent = getIntent();
        Integer DownPayment = Integer.parseInt(intent.getStringExtra("DownPayment"));
        Integer Price = Integer.parseInt(intent.getStringExtra("Price"));
        String LoanTerm = intent.getStringExtra("LoanTerm");
        mAuto.setPrice(Price);
        mAuto.setDownPayment(DownPayment);
        mAuto.setLoanTerm(LoanTerm);
        //TASK 1: CONSTRUCT THE MONTHLY PAYMENT
        Resources res = getResources();
        monthlyPayment = res.getString(R.string.report_line1) + String.format("%.02f", mAuto.monthlyPayment());
        //TASK 2: CONSTRUCT THE LOAN REPORT
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
    public void goDataEntry(View view) {
        finish();
    }
}
