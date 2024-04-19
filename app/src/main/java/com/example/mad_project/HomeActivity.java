package com.example.mad_project;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity {
    private EditText amountEditText, descriptionEditText;
    private TextView balanceTextView, transactionTextView;
    private Button cashIn, cashOut, currency_convertor, investmentButton;
    private double balance = 0.0;
    private StringBuilder transactionHistory = new StringBuilder();
    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String BALANCE_KEY = "balance";
    private static final String TRANSACTION_HISTORY_KEY = "transaction_history";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        amountEditText = findViewById(R.id.amountEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        balanceTextView = findViewById(R.id.balanceTextView);
        transactionTextView = findViewById(R.id.transactionTextView);

        cashIn = findViewById(R.id.cashInButton);
        cashOut = findViewById(R.id.cashOutButton);
        currency_convertor = findViewById(R.id.convertButton);
        investmentButton = findViewById(R.id.investButton);



        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        balance = prefs.getFloat(BALANCE_KEY, 0.0f);
        SharedPreferences prefs1 = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String savedTransactionHistory = prefs.getString(TRANSACTION_HISTORY_KEY, "");
        transactionHistory.append(savedTransactionHistory);
        transactionTextView.setText(transactionHistory.toString());
        updateBalance();

        cashIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amountStr = amountEditText.getText().toString();
                String description = descriptionEditText.getText().toString();

                if (!amountStr.isEmpty()) {
                    double amount = Double.parseDouble(amountStr);
                    balance += amount;
                    updateBalance();
                    String transactionType = "Credit";
                    String transaction ="Type: " + transactionType + "\nAmount: ₹" + amount + "\nDescription: " + description + "\n\n";
                    transactionHistory.append(transaction);
                    updateTransactionHistory();
                    saveBalance();
                } else {
                    Toast.makeText(HomeActivity.this, "Please enter amount", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cashOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amountStr = amountEditText.getText().toString();
                String description = descriptionEditText.getText().toString();

                if (!amountStr.isEmpty()) {
                    double amount = Double.parseDouble(amountStr);
                    balance -= amount;
                    updateBalance();
                    String transactionType = "Debit";
                    String transaction ="Type: " + transactionType + "\nAmount: ₹" + amount + "\nDescription: " + description + "\n\n";
                    transactionHistory.append(transaction);
                    updateTransactionHistory();
                    saveBalance();
                } else {
                    Toast.makeText(HomeActivity.this, "Please enter amount", Toast.LENGTH_SHORT).show();
                }
            }
        });

        investmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInvestmentCalciActivity();
            }
        });

        currency_convertor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCuurecnyConvertor();
            }
        });
    }

    private void updateBalance() {
        balanceTextView.setText(String.format(Locale.getDefault(), "My Balance: ₹%.2f", balance));
        amountEditText.setText("");
        descriptionEditText.setText("");

    }

    private void saveBalance() {
        // Save balance to SharedPreferences
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putFloat(BALANCE_KEY, (float) balance);
        editor.putString(TRANSACTION_HISTORY_KEY, transactionHistory.toString());
        editor.apply();
    }

    private void openInvestmentCalciActivity() {
        Intent intent = new Intent(this, Investment_calci.class);
        startActivity(intent);
    }

    private void openCuurecnyConvertor() {
        Intent intent = new Intent(this, CurrencyConverter.class);
        startActivity(intent);
    }

    private void updateTransactionHistory() {
        transactionTextView.setText(transactionHistory.toString());
    }
}
