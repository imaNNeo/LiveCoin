package com.base.baseproject.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;

import com.base.baseproject.R;
import com.base.baseproject.mvp.activity.enter.EnterActivity;

import java.util.Random;

/**
 * Programmer Iman Khoshabi
 * iman.neofight@gmail.com
 */

public class NotificationHandler {

    public static final byte NOTIFICATION_TYPE_RECEIVED_POINT = 1;

    public static final void showPointReceivedNotify(Context ctx ,String title,String desc){
        int icon = R.mipmap.ic_launcher;
        long when = System.currentTimeMillis();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(ctx);
        builder.setWhen(when);
        builder.setSmallIcon(icon);
        builder.setLights(Color.parseColor("#fffdf907"), 1000, 2500);
        builder.setDefaults(NotificationCompat.DEFAULT_LIGHTS);
        builder.setTicker(title);
        builder.setContentTitle(title);
        builder.setContentText(desc);


        NotificationManager mNotificationManager =
                (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);

        builder.setAutoCancel(true);


        Intent i = new Intent(ctx, EnterActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent contentIntent = PendingIntent.getActivity(ctx,0,i,0);
        builder.setContentIntent(contentIntent);

        builder.setDefaults(Notification.DEFAULT_SOUND);
        builder.setVibrate(new long[]{0});

        Notification notification = builder.build();
        mNotificationManager.notify(new Random().nextInt(), notification);
    }
}