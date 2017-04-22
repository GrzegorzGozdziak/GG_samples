package com.example.rc.samples;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MyApp.context);

    @BindView(R.id.logout)
    protected FloatingActionButton logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.logout)
    public void logoutForm() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.edit().remove(AppConfig.TOKEN).commit();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();

    }
}
