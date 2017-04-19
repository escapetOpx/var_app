package com.var_app.var_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.var_app.var_app.helper.UserInfo;

public class MainActivity extends Activity {

    private TextView txtName;
    private TextView txtEmail;
    private Button btnLogout;
    private Button cal1Button, cal2Button;

    /*private SQLiteHandler db;
    private SessionManager session;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtName = (TextView) findViewById(R.id.name);
        txtEmail = (TextView) findViewById(R.id.email);
        cal1Button = (Button) findViewById(R.id.cal1butt);
        cal2Button = (Button) findViewById(R.id.cal2butt);
        //  btnLogout = (Button) findViewById(R.id.btnLogout);

//        // SqLite database handler
//        db = new SQLiteHandler(getApplicationContext());
//
//        // session manager
//        session = new SessionManager(getApplicationContext());
//
//        if (!session.isLoggedIn()) {
//            logoutUser();
//        }
//
//        // Fetching user details from sqlite
//        HashMap<String, String> user = db.getUserDetails();
//
//        String name = user.get("name");
//        String email = user.get("email");
//
//        // Displaying the user details on the screen
        txtName.setText(UserInfo.getFullName());
        txtEmail.setText(UserInfo.getEmail());

        cal1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CalculatorActivity.class);
                startActivity(intent);
            }
        });

        cal2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(MainActivity.this, ScanerBarcode.class);
                //startActivity(intent);
            }
        });

        // Logout button click event
 /*       btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });
    }

    *//**
         * Logging out the user. Will set isLoggedIn flag to false in shared
         * preferences Clears the user data from sqlite users table
         * *//*
    private void logoutUser() {
//        session.setLogin(false);

        db.deleteUsers();

        // Launching the login activity
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }*/
    }
}