package com.example.ungdungdatlichcattoc;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class MyAppliation extends Application {
    public static final String CHANNEL_ID = "push_notification_id";


    @Override
    public void onCreate() {
        super.onCreate();
        createChannelNotiffication();
    }

    private void createChannelNotiffication() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,"PushNotification", NotificationManager.IMPORTANCE_HIGH);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }
}
