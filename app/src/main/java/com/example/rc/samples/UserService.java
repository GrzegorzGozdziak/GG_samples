package com.example.rc.samples;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by RENT on 2017-04-22.
 */

public class UserService {

    public static final String LOGIN = "/login";

    private Gson gson = new GsonBuilder().create();

    private static final UserService ourInstance = new UserService();

    private SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MyApp.context);

    public static UserService getInstance() {
        return ourInstance;
    }

    private UserService() {
    }

    public Boolean login(LoginModel loginModels) {

        boolean isLogIn = false;

        String result = loginRequest(AppConfig.HOST + LOGIN, loginModels);

        if(result != null) {
            isLogIn = true;
            LoginModel loginModel = gson.fromJson(result, LoginModel.class);
            prefs.edit().putString(AppConfig.TOKEN, loginModel.getToken()).commit();

        }

        return isLogIn;
    }

    public String loginRequest(String requestURL, LoginModel model) {

        URL url;
        String response = "";
        try {
            url = new URL(requestURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            InputStream in = null;
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setDoInput(true);
            conn.setDoOutput(true);


            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(gson.toJson(model));

            writer.flush();
            writer.close();
            os.close();
            int responseCode = conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {
//                String line;
//                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//                while ((line = br.readLine()) != null) {
//                    response += line;
//                }
                InputStream in = new BufferedInputStream(conn.getInputStream());
                response = IOUtils.toString(in, "UTF-8");
            } else {
                InputStream in = new BufferedInputStream(conn.getErrorStream());
//                response = IOUtils.toString(in, "UTF-8");
                response = null;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

}
