package com.example.rc.samples;

import android.app.Application;
import android.content.Context;

/**
 * Created by RENT on 2017-04-22.
 */

public class MyApp extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
}
