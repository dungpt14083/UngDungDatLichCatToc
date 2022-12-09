package com.example.ungdungdatlichcattoc.fcm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.ungdungdatlichcattoc.MainActivity;
import com.example.ungdungdatlichcattoc.MyAppliation;
import com.example.ungdungdatlichcattoc.R;
import com.example.ungdungdatlichcattoc.activity.LoginActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);
        RemoteMessage.Notification notification = message.getNotification();
        if(notification == null)
        {
            return;
        }
        String strTitle = notification.getTitle();
        String strMesage = notification.getBody();

        sendNotification(strTitle,strMesage);
    }

    private void sendNotification(String strTitle, String strMesage) {
        Intent intent = new Intent(this, LoginActivity.class);
      //  PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder notificationBuild = new NotificationCompat.Builder(this, MyAppliation.CHANNEL_ID)
                .setContentTitle(strTitle)
                .setContentText(strMesage)
                .setSmallIcon(R.drawable.home)
                .setDefaults(Notification.DEFAULT_ALL)
                .setPriority(Notification.PRIORITY_MAX)
                .setAutoCancel(true);
//                .setContentIntent(pendingIntent);
//        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
//        notificationManagerCompat.notify(1,notificationBuild.build());


      Notification notification = notificationBuild.build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if(notificationManager!= null){
            notificationManager.notify(1,notification);
        }
    }
}
