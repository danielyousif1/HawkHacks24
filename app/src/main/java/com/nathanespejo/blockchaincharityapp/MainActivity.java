package com.nathanespejo.blockchaincharityapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nathanespejo.blockchaincharityapp.dataclasses.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main);


        //Populate charity list
        DatabaseAPI.fetchData("Users");
        DatabaseAPI.fetchData("Charities");
        DatabaseAPI.fetchData("Transactions");

        EditText usernameText = findViewById(R.id.usernameText);
        EditText passwordText = findViewById(R.id.passwordText);
        final Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(view -> {
            String user = usernameText.getText().toString();
            String pass = passwordText.getText().toString();

            for (User.UserData data: DatabaseAPI.userDataList) {
                if ((data.getEmail().matches(user)) && (data.getPassword().matches(pass))) {
                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                    return;
                }
            }

            Toast.makeText(this, "Incorrect login!", Toast.LENGTH_SHORT).show();
        });
    }
}