package com.nathanespejo.blockchaincharityapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main);

        //loadFromDBToMemory();

        EditText usernameText = findViewById(R.id.usernameText);
        EditText passwordText = findViewById(R.id.passwordText);
        final Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(view -> {
            String user = usernameText.getText().toString();
            String pass = passwordText.getText().toString();
            if (user.matches("") || pass.matches("")) {
                //do nothing
                return;
            }

            //Check if username and pass exist as db entry
            //Open new activity
            startActivity(new Intent(MainActivity.this, HomeActivity.class));
        });
    }

    private void loadFromDBToMemory() {
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        sqLiteManager.populateUserListArray();
    }
}