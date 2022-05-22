package com.example.lafamilledesanimaux.models;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.TaskStackBuilder;

import com.example.lafamilledesanimaux.R;
import com.example.lafamilledesanimaux.views.Login;
import com.example.lafamilledesanimaux.views.Menu;

public class NotificationReceiver extends BroadcastReceiver {

    public static String notification_id = "1";
    public static String channel_id = "channel1";
    public static String message_extra = "messageextra";

    @Override
    public void onReceive(Context context, Intent intent) {
        // Build notification based on Intent
        Notification notification = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.splash)
                .setContentTitle("Here's the title")
                .setContentText("Here's the text")
                .build();
        // Show notification
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(1, notification);
    }

    public void showNotification(Context context, String title, String body, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        // Intent intent = new Intent(context, Login.class);
        int notificationId = 1;
        String channelId = "channel1";
        String channelName = "Channel";
        int importance = NotificationManager.IMPORTANCE_HIGH;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notifchanel = new NotificationChannel(channelId, channelName, importance);
            notificationManager.createNotificationChannel(notifchanel);
        }

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(body);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addNextIntent(intent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT );
        mBuilder.setContentIntent(resultPendingIntent);

        notificationManager.notify(notificationId, mBuilder.build());
    }
}
