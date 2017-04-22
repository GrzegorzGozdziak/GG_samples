package com.example.rc.samples;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    public static final String TOKEN = "token";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
//        prefs
//                .edit()
//                .putString(TOKEN, "jakiś token")
//                .commit();
        Intent intent = null;
        if(!prefs.contains(TOKEN)){
            intent = new Intent(this, MainActivity.class);

        } else {
            //Login_Activity
            intent = new Intent(this, LoginActivity.class);

        }
        startActivity(intent);
        finish();


    }
}
