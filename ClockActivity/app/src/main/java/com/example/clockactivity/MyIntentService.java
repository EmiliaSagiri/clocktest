package com.example.clockactivity;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import java.net.MalformedURLException;
import java.net.URL;

public class MyIntentService extends IntentService {
    public static final String TAG = "MyIntentService";
    public MyIntentService() {
        super(TAG);

    }

    protected void onHandleIntent(Intent intent) {
        Log.d(TAG,"id是"+Thread.currentThread().getId());


    }

   public void onDestoy(){
        super .onDestroy();
        Log.d(TAG,"3");
   }
}