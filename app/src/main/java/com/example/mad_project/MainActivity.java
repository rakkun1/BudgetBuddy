package com.example.mad_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText usernameEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        Button loginButton = findViewById(R.id.loginButton);
        Button createAccountButton = findViewById(R.id.createAccountButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if ((username.equals("rakshit123") && password.equals("hello"))||(username.equals("amogh1") && password.equals("bye"))) {
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    // Display error message for invalid credentials
                    Toast.makeText(MainActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open create account activity
                Intent intent = new Intent(MainActivity.this, CreateAccountActivity .class);
                startActivity(intent);
            }
        });
    }
}
