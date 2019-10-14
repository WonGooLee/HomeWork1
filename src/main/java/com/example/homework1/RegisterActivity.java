package com.example.homework1;

import android.app.Activity;

import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;


public class RegisterActivity extends Activity {

//  수정
    final static private String URL = "http://1.209.175.113/phpmyadmin/Register.php";

    private Map<String, String> parameters;

    public RegisterActivity(String userID, String userPassword, String userName,
                           int userPhone, String userAddress, Response.Listener<String> listener){
//        super(Request.Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userID", userID);
        parameters.put("userPassword", userPassword);
        parameters.put("userName", userName);
        parameters.put("userPhone", userPhone + "");
        parameters.put("userAddress", userAddress);

    }

    public Map<String, String> getParameters(){
        return parameters;
    }
}
