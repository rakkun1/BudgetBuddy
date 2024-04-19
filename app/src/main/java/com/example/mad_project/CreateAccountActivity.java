package com.example.mad_project;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CreateAccountActivity extends AppCompatActivity {
    private EditText nameEditText, newUsernameEditText, newPasswordEditText;
    private DBHandler dbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        nameEditText = findViewById(R.id.nameEditText);
        newUsernameEditText = findViewById(R.id.newUsernameEditText);
        newPasswordEditText = findViewById(R.id.newPasswordEditText);
        Button submitButton = findViewById(R.id.submitButton);
        dbHandler = new DBHandler(CreateAccountActivity.this);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String newUsername = newUsernameEditText.getText().toString();
                String newPassword = newPasswordEditText.getText().toString();

                // Dummy implementation for creating account (Replace with actual implementation)
                if (!name.isEmpty() && !newUsername.isEmpty() && !newPassword.isEmpty()) {
                    // Account created successfully
                    Toast.makeText(CreateAccountActivity.this, "Account created successfully", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    // Display error message for incomplete fields
                    Toast.makeText(CreateAccountActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }
                dbHandler.addNewUser(name, newUsername, newPassword);

            }
        });
    }
}
