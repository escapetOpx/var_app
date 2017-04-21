
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
import com.var_app.var_app.helper.APIRequest;
import com.var_app.var_app.helper.AppConfig;
import com.var_app.var_app.helper.UserInfo;
import org.json.JSONException;
import org.json.JSONObject;




public class BarcodeActivity extends Activity {

    private static final String TAG = RegisterActivity.class.getSimpleName();
    private Button barbutton;
    private TextView btnLinkToRegister;
    private EditText inputBarcode;

    private ProgressDialog pDialog;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cal_bar);

        /*inputBarcode = (EditText) findViewById(R.id.wet_bar);

        barbutton= (Button) findViewById(R.id.barbutton);
        // btnLinkToRegister = (TextView) findViewById(R.id.btnLinkToRegister);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        barbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bar_code = inputBarcode.getText().toString().trim();


                if (!bar_code.isEmpty()) {
                    checkLogin(bar_code);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Please enter the credentials", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });


        private void checkLogin ( final String username, final String password){

            pDialog.setMessage("Logging in ...");
            showDialog();

            FormEncodingBuilder formBody = new FormEncodingBuilder()
                    .add("barcoe", bar_code)
                    .add("user_password", password);

            new APIRequest(getApplicationContext(), formBody, AppConfig.URL_BARCODE, new APIRequest.APIResponse() {
                @Override
                public void onResponse(String barcode_response) {
                    //Log.d(TAG, "Register Response: " + barcode_response.toString());
                    // hideDialog();

                    try {

                        JSONObject jObj = new JSONObject(barcode_response);
                        String status = jObj.getString("status");

                        if (status.equals("success")) {
                            JSONObject dataObj = jObj.getJSONObject("info_barcode");
                            String band1 = dataObj.getString("band");
                            String weight1 = dataObj.getString("weight");
                            String size1 = dataObj.getString("size");
                            String type_name = dataObj.getString("trashtype_name");
                            String price_kg = dataObj.getString("priceperkg");


                            // Launch login activity
                            Intent intent = new Intent(
                                    BarcodeActivity.this,
                                    LoginActivity.class);
                            startActivity(intent);
                            finish();
                        } else {

                            // Error occurred in registration. Get the error
                            // message
                            String errorMsg = jObj.getString("error_msg");
                            Toast.makeText(getApplicationContext(),
                                    errorMsg, Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

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
            );
        }*/
    }
}


