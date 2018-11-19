package com.pywl.ebangbang_rider.utils;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.pywl.ebangbang_rider.R;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by Harry on 2018/11/12.
 */
public class NotificationUtil {

    /**
     * 创建通知
     * @param context 上下文
     */
    public static void createNotification(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "ride_notify";
            String channelName = "接单通知";
            NotificationChannel channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
            builder = new NotificationCompat.Builder(context, channelId);
            builder.setChannelId(channelId);
        } else {
            builder = new NotificationCompat.Builder(context);
        }

        builder.setTicker("new message")
                .setSmallIcon(R.drawable.ic_logo)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_logo))
                .setAutoCancel(true)
                .setWhen(System.currentTimeMillis())
                .setContentTitle("收到一条接单消息")
                .setContentText("您有一条新订单, 请查收");
        notificationManager.notify(1, builder.build());
    }
}
