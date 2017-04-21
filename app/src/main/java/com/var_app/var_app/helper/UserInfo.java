package com.var_app.var_app.helper;

import android.util.Log;

import com.squareup.okhttp.FormEncodingBuilder;

import org.json.JSONException;
import org.json.JSONObject;

public class UserInfo {

    private static final String TAG = "UserInfo";

    private static String userId, email, fullName, phone, point;
    private static OnChangeListener onChangeListener;

    public UserInfo(String jsonString) {
        setData(jsonString);
    }

    public static void setData(String jsonResult) {

        try {
            JSONObject obj = new JSONObject(jsonResult);

            userId      = tryGetString(obj,"user_id");
            email       = tryGetString(obj,"user_email");
            fullName    = tryGetString(obj,"user_firstname");
            phone       = tryGetString(obj,"user_tel");
            point       = tryGetString(obj,"user_point");

            if (onChangeListener != null) {
                onChangeListener.onChange();
            }
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
        }

    }

    private static String tryGetString(JSONObject jsonObject, String tag) {
        try {
            return jsonObject.getString(tag);
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
            return null;
        }
    }

    // Getter

    public static FormEncodingBuilder getUserIDBody() {
        return new FormEncodingBuilder()
                .add("user_id", userId);
    }

    public static String getUserId() {
        return userId;
    }

    public static String getEmail() {
        return email;
    }

    public static String getFullName() {
        return fullName;
    }

    public static void setFullName(String fullName) {
        UserInfo.fullName = fullName;
    }

    public static void setEmail(String email) {
        UserInfo.email = email;
    }

    public static String getPoint() {
        return point;
    }

    public static void setPoint(String point) {
        UserInfo.point = point;
    }

    // Setter

    public static String getPhone() {
        return phone;
    }

    public static void setPhone(String phone) {
        UserInfo.phone = phone;
    }

    // Interface

    public static void setOnChangeListener(OnChangeListener o){
        onChangeListener = o;
    }

    public interface OnChangeListener {
        void onChange();
    }
}


