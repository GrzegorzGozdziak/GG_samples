package com.example.rc.samples;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.email)
    protected EditText email;

    @BindView(R.id.password)
    protected EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

    }

    @OnClick(R.id.submit)
    public void submitForm() {
        new AsyncTask<LoginModel, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(LoginModel... loginModels) {
                return UserService.getInstance().login(loginModels[0] );
            }

            @Override
            protected void onPostExecute(Boolean isLogged) {
                if(isLogged){
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    showConnectionProblemsAlert();
                }
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            }
        }
                .execute(new LoginModel(email.getText().toString(), password.getText().toString()));

    }

    private void showConnectionProblemsAlert() {
        new AlertDialog.Builder(this)
                .setTitle("Ojojoj")
                .setMessage("Problem z połączeniem?")
                .setCancelable(true)
                .setNegativeButton("Ustawienia", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    }
                })
                .setPositiveButton("Rozumiem", null)
                .show();
    }
}
