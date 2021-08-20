package com.example.clockactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class demo extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
    }
    public void Intent_ljj(View view){
        switch (view.getId()) {
            case R.id.START:
                Log.d("demo", "6");
                Intent intentSercice = new Intent(this, MyIntentService.class);
                startService(intentSercice);
                break;

            default:
                break;
        }

    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.int_ljj:
                Intent intent =new Intent();
                break;
            case R.id.String_ljj:
                break;
            case R.id.Boolean_ljj:
                break;
            case R.id.ArrayList:
                break;
             default:
                 break;
        }
    }

}