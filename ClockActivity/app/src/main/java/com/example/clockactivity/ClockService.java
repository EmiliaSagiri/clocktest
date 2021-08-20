package com.example.clockactivity;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class ClockService extends Service {
    public static final String CLOCK_SERVICE_ACTION="clock_service_actoin";
    public static final String TAG = "ClockaService";
    private boolean controllOpt=true;
    public ClockService() { }
    @Override
    public   void onCreate(){
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction(CLOCK_SERVICE_ACTION);
        //在service中注册广播（serviceController）,接受来自ClockActivity中
//的广播信息，实现对计时服务的控制（暂停、继续）
        super.registerReceiver(serviceController,intentFilter);
        Log.d(TAG,"1");
    }
    @Override
    public int onStartCommand(Intent intent,int flags,int startId){
        countTime();//执行计时功能
        Log.d(TAG,"2");
        return Service.START_STICKY;
    }
    //实现计时功能，每隔一秒减少总时间并ClockActivity发送广播
    private void countTime(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Intent intent= new Intent(ClockActivity.CLOCK_ACTION);
                while(controllOpt){
                    try {
                        Thread.sleep(1000);
                        if(ClockActivity.TIME<=0){
                            sendBroadcast(intent);
                            stopSelf();
                            break;
                        }
                        ClockActivity.TIME-=1000;
                        sendBroadcast(intent);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    //广播接受者，接受来自ClockActivity的广播以便暂停、继续、停止广播
    private BroadcastReceiver serviceController=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String method=intent.getStringExtra("method");
            switch (method){
                case "pause":
                    controllOpt=false;
                    break;
                case "continue":
                    controllOpt=true;
                    countTime();
                    break;
                case "stop":
                    controllOpt=false;
                    stopSelf();
                    break;
            }
        }
    };
    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    public void onDestroy(){
        super.unregisterReceiver(serviceController);
        Log.d(TAG,"3");
    }
}