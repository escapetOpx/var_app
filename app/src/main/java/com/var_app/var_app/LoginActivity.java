package com.var_app.var_app;

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
import com.var_app.var_app.helper.AppConfig;
import com.var_app.var_app.helper.APIRequest;
import com.var_app.var_app.helper.UserInfo;

public class LoginActivity extends Activity {
    private static final String TAG = RegisterActivity.class.getSimpleName();
    private Button btnLogin;
    private TextView btnLinkToRegister;
    private EditText inputUsername;
    private EditText inputPassword;
    private ProgressDialog pDialog;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputUsername = (EditText) findViewById(R.id.username);
        inputPassword = (EditText) findViewById(R.id.password);
        btnLogin= (Button) findViewById(R.id.btnLogin);
        btnLinkToRegister = (TextView) findViewById(R.id.btnLinkToRegister);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = inputUsername.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                if (!username.isEmpty() && !password.isEmpty()) {
                    checkLogin(username, password);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Please enter the credentials", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

        btnLinkToRegister.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent i = new Intent(LoginActivity.this,
                        RegisterActivity.class);
                startActivity(i);
                finish();
            }
        });

    }

    private void checkLogin(final String username, final String password){

        pDialog.setMessage("Logging in ...");
        showDialog();

        FormEncodingBuilder formBody = new FormEncodingBuilder()
                .add("user_name", username)
                .add("user_password", password);

        new APIRequest(getApplicationContext(), formBody, AppConfig.URL_LOGIN, new APIRequest.APIResponse() {
            @Override
            public void onSuccess(String result) {
                hideDialog();
                UserInfo.setData(result);
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
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
