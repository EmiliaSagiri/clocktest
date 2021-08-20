package com.example.clockactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

    }
    public void broad_ljj(View view){

    }
    public void service_ljj(View view){
         Intent intent = new Intent(MainActivity.this,ClockActivity.class)   ;
         startActivity(intent);
    }
}