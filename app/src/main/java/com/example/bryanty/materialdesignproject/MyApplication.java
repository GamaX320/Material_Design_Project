package com.example.bryanty.materialdesignproject;

import android.app.Application;
import android.content.Context;

/**
 * Created by BRYANTY on 08/05/2015.
 */
public class MyApplication extends Application {
    private static MyApplication myApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication= this;
    }

    public static MyApplication getInstance(){
        return myApplication;
    }

    public static Context getAppContext(){
        return myApplication.getApplicationContext();
    }
}
