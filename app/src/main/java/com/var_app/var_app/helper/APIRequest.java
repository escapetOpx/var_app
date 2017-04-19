package com.var_app.var_app.helper;

import android.app.AlertDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import com.var_app.var_app.R;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class APIRequest extends AsyncTask<Void, Void, String> {

    private APIResponse apiResponse = null;

    private Context context;
    private RequestBody formBody;
    private String stringURL;

    public APIRequest(Context context, FormEncodingBuilder formBody, String stringURL, APIResponse response) {
        this.context = context;
        this.formBody = formBody.build();
        this.stringURL = stringURL;
        this.apiResponse = response;
    }

    @Override
    protected String doInBackground(Void... params) {

        if (checkInternet()) {

            OkHttpClient client = new OkHttpClient();
            client.setConnectTimeout(4, TimeUnit.SECONDS);

            Request request = new Request.Builder()
                    .url(stringURL)
                    .post(formBody)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    return response.body().string();
                } else {
                    return null;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            showAlertDialog();
            this.cancel(true);
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if (result != null) {
            result = result.trim();
            Log.i(stringURL, result);
            try {
                JSONObject resultJson = new JSONObject(result);
                String status = resultJson.getString("status");
                if (status.equals("success")) {
                    apiResponse.onSuccess(resultJson.getString("data"));
                } else if (status.equals("error")) {
                    apiResponse.onError(resultJson.getString("errno"), resultJson.getString("message"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Log.i(stringURL, "No Result");
    }
    }

    private void showAlertDialog() {
        new AlertDialog.Builder(context).setTitle("No Internet")
                .setMessage("No Internet")
                .setNeutralButton("Close", null)
                .show();
    }

    private boolean checkInternet() {
        ConnectivityManager cManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cManager.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }

    public interface APIResponse {
        void onSuccess(String result);

        void onError(String errno, String error);
    }

}