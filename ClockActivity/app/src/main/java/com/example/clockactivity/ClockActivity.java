package com.example.clockactivity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ClockActivity extends Activity {
    private TextView tvClock;
    public static final String CLOCK_ACTION="com.jereh.Clock_Action";
    public static  int TIME=30*1000;//倒计时2个小时
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvClock=(TextView)super.findViewById(R.id.tvClock);
        regReceiver();//注册广播
        startService(new Intent(this,ClockService.class));//启动计时服务
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        super.unregisterReceiver(clockReceiver);
        TIME=30*1000;
        Intent intent=new Intent();
        intent.setAction(ClockService.CLOCK_SERVICE_ACTION);
        intent.putExtra("method", "stop");
        super.sendBroadcast(intent);
    }
    private void regReceiver(){
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction(CLOCK_ACTION);
        super.registerReceiver(clockReceiver, intentFilter);
    }
    /**
     *广播接受者，接受来自ClockService（计时服务）的广播，ClockService每隔一秒
     *钟发一次广播
     */
    private BroadcastReceiver clockReceiver=new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {
            changeTime();//改变TextView中的显示时间
        }
    };
    //通过发送广播，控制计时服务
    //继续计时
    public void restart(View view){
        Intent intent=new Intent();
        intent.setAction(ClockService.CLOCK_SERVICE_ACTION);
        intent.putExtra("method", "continue");
        super.sendBroadcast(intent);
    }
    //通过发送广播，控制计时服务
    //暂停计时
    public void pause(View view){
        Intent intent=new Intent();
        intent.setAction(ClockService.CLOCK_SERVICE_ACTION);
        intent.putExtra("method","pause");
        super.sendBroadcast(intent);
    }
    public void back(View view){
            Intent intent=new Intent(ClockActivity.this,MainActivity.class);
            startActivity(intent);
    }
    private void changeTime(){
        String stime="";
        if(TIME==0){
            stime="计时结束";
        }else{
            int hour=TIME/(1000*60*60);
            int minute=TIME%(1000*60*60)/(60*1000);
            int second=(TIME%(1000*60*60))%(60*1000)/1000;
            String shour=""+hour,sminute=""+minute,ssecond=""+second;
            if(hour<=9){
                shour="0"+hour;
            }
            if(minute<=9){
                sminute="0"+minute;
            }
            if (second<=9){
                ssecond="0"+second;
            }
            stime=shour+":"+sminute+":"+ssecond;
        }
        tvClock.setText(stime);
    }

}