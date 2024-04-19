package com.example.mad_project;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Investment_calci extends AppCompatActivity {

    private EditText principleEditText, timePeriodEditText, interestRateEditText;
    private Button calculateButton;
    private TextView resultTextView;
    private RadioGroup radioGroup;
    private RadioButton lumpsumRadioButton, sipRadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investment_calci);

        principleEditText = findViewById(R.id.principleEditText);
        timePeriodEditText = findViewById(R.id.timePeriodEditText);
        interestRateEditText = findViewById(R.id.interestRateEditText);
        calculateButton = findViewById(R.id.calculateButton);
        resultTextView = findViewById(R.id.resultTextView);
        radioGroup = findViewById(R.id.radioGroup);
        lumpsumRadioButton = findViewById(R.id.lumpsumRadioButton);
        sipRadioButton = findViewById(R.id.sipRadioButton);

        calculateButton.setBackgroundColor(getResources().getColor(R.color.dark_blue));

        // Add listener to RadioGroup to handle radio button selection
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Clear the result when radio button selection changes
                resultTextView.setText("");
                if (checkedId == R.id.lumpsumRadioButton) {
                    sipRadioButton.setChecked(false);
                } else if (checkedId == R.id.sipRadioButton) {
                    lumpsumRadioButton.setChecked(false);
                }
            }
        });

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateFinalAmount();
            }
        });
    }

    private void calculateFinalAmount() {
        double principle = Double.parseDouble(principleEditText.getText().toString());
        double timePeriod = Double.parseDouble(timePeriodEditText.getText().toString());
        double interestRate;

        if (interestRateEditText.getText().toString().isEmpty()) {
            interestRate = 0.08; // Default interest rate: 8%
        } else {
            interestRate = Double.parseDouble(interestRateEditText.getText().toString());
            interestRate /= 100;
        }

        // Calculate final amount
        double finalAmount;
        if (lumpsumRadioButton.isChecked()) {
            finalAmount = principle * Math.pow((1 + interestRate), timePeriod);
        } else if (sipRadioButton.isChecked()) {
            // Calculate final amount for SIP
            double i = interestRate / 100;
            finalAmount = principle * ((Math.pow((1 + i), timePeriod) - 1) / i);
        } else {
            // No radio button checked, display error message
            resultTextView.setText("Please select Lumpsum or SIP.");
            return;
        }

        // Display the result
        resultTextView.setText(String.format("Final Amount: Rs %.2f", finalAmount));
    }

}