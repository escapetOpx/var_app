package com.var_app.var_app;

/**
 * Created by escape on 4/5/2017 AD.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.FormEncodingBuilder;
import com.var_app.var_app.helper.APIRequest;
import com.var_app.var_app.helper.AppConfig;


public class RegisterActivity extends Activity {
    private static final String TAG = RegisterActivity.class.getSimpleName();
    private Button btnRegister;
    private TextView btnLinkToLogin;
    private EditText inputUserName;
    private EditText inputFullName;
    private EditText inputPassword;
    private EditText inputTel;
    private EditText inputEmail;

    private ProgressDialog pDialog;
   /* private SessionManager session;
    private SQLiteHandler db;*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

          inputUserName = (EditText) findViewById(R.id.inUsername);
          inputFullName = (EditText) findViewById(R.id.inName);
          inputEmail = (EditText) findViewById(R.id.inEmail);
          inputPassword = (EditText) findViewById(R.id.inPassword);
          inputTel = (EditText) findViewById(R.id.inTel);
          btnRegister = (Button) findViewById(R.id.btnRegister);
          btnLinkToLogin = (TextView) findViewById(R.id.btnToLogin);

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        // Session manager
        //session = new SessionManager(getApplicationContext());

        // SQLite database handler
        //db = new SQLiteHandler(getApplicationContext());

        // Check if user is already logged in or not
        /*if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(RegisterActivity.this,
                    MainActivity.class);
            startActivity(intent);
            finish();
        }*/

        // Register Button Click event
        btnRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String username = inputUserName.getText().toString().trim();
                String name = inputFullName.getText().toString().trim();
                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();
                String tel = inputTel.getText().toString().trim();

                if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty()
                        && !username.isEmpty()&& !tel.isEmpty()) {
                    registerUser(name, username, email, password, tel);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Please enter your details!", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

        btnLinkToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        // Link to Login Screen
//        btnLinkToLogin.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View view) {
//                Intent i = new Intent(getApplicationContext(),
//                        LoginActivity.class);
//                startActivity(i);
//                finish();
//            }
//        });

    }

    /**
     * Function to store user in MySQL database will post params(tag, name,
     * email, password) to register url
     * */
    private void registerUser(final String name, final String username, final String email,
                              final String password, final String tel) {
        // Tag used to cancel the request

        pDialog.setMessage("Registering ...");
        showDialog();

        FormEncodingBuilder formBody = new FormEncodingBuilder()
                .add("user_firstname", name)
                .add("user_name", username)
                .add("user_email", email)
                .add("user_password", password)
                .add("user_tel", tel);

        new APIRequest(getApplicationContext(), formBody, AppConfig.URL_REGISTER, new APIRequest.APIResponse() {
            @Override
            public void onSuccess(String result) {
                hideDialog();
                Toast.makeText(getApplicationContext(), "User successfully registered. Try login now!", Toast.LENGTH_LONG).show();

                // Launch login activity
                Intent intent = new Intent(
                        RegisterActivity.this,
                        LoginActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onError(String error) {
                hideDialog();
                Toast.makeText(getApplicationContext(), "Error " + error, Toast.LENGTH_LONG).show();

            }
        }).execute();
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
