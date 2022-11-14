package com.example.notifyme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button mNotifyButton;
    Button mCancelNotify;
    Button mUpdateNotify;
    private NotificationManager mNotifyManager;
    private static final int NOTIFICATION_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNotifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        mNotifyButton = (Button) findViewById(R.id.notify);
        mNotifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendNotification();
            }
        });

        mUpdateNotify = (Button) findViewById(R.id.update);
        mUpdateNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateNotification();
            }
        });

        mCancelNotify = (Button) findViewById(R.id.cancel);
        mCancelNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelNotification();
            }
        });

        mNotifyButton.setEnabled(true);
        mUpdateNotify.setEnabled(false);
        mCancelNotify.setEnabled(false);
    }

    public void sendNotification() {
        mNotifyButton.setEnabled(false);
        mUpdateNotify.setEnabled(true);
        mCancelNotify.setEnabled(true);

        //pending Intent
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent notificationPendingIntent = PendingIntent.getActivity(this,
                NOTIFICATION_ID, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this)
                .setContentTitle("You've been notified!")
                .setContentText("This is your notification text.")
                .setSmallIcon(R.drawable.ic_android)
                .setContentIntent(notificationPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL);
        Notification myNotification = notifyBuilder.build();
        mNotifyManager.notify(NOTIFICATION_ID, myNotification);
    }

    public void updateNotification() {
        mNotifyButton.setEnabled(false);
        mUpdateNotify.setEnabled(false);
        mCancelNotify.setEnabled(true);

        Bitmap androidImage = BitmapFactory
                .decodeResource(getResources(),R.drawable.mascot_1);

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent notificationPendingIntent = PendingIntent.getActivity(this,
                NOTIFICATION_ID, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this)
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(androidImage)
                        .setBigContentTitle("Notification Updated!"))
                .setSmallIcon(R.drawable.ic_android)
                .setContentIntent(notificationPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setDefaults(NotificationCompat.DEFAULT_ALL);

        Notification myNotification = notifyBuilder.build();
        mNotifyManager.notify(NOTIFICATION_ID, myNotification);

    }
    public void cancelNotification() {
        mNotifyButton.setEnabled(true);
        mUpdateNotify.setEnabled(false);
        mCancelNotify.setEnabled(false);

        mNotifyManager.cancel(NOTIFICATION_ID);
    }
}