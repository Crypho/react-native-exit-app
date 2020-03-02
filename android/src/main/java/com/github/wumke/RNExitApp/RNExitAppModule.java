package com.github.wumke.RNExitApp;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RNExitAppModule extends ReactContextBaseJavaModule {

    ReactApplicationContext reactContext;
    AlarmManager alarmManager;

    public RNExitAppModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
        alarmManager = (AlarmManager) reactContext.getSystemService(Context.ALARM_SERVICE);
    }

    @Override
    public String getName() {
        return "RNExitApp";
    }

    @ReactMethod
    public void exitApp() {
        // We send app to background first before killing it to avoid issue with Android Q 
        // when we try to invoke the app form lock screen (the send event never be called!)
        Intent i = new Intent();
        i.setAction(Intent.ACTION_MAIN);
        i.addCategory(Intent.CATEGORY_HOME);
        this.reactContext.startActivity(i);

        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
