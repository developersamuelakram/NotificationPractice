package com.example.notificationexample.Notification;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.os.Build;

public class App extends Application {

    public static final String GROUP_1_ID = "group1";
    public static final String GROUP_2_ID = "group2";

    public static final String CHANNEL_1_ID = "Channel1";
    public static final String CHANNEL_2_ID = "Channel2";

    public static final String CHANNEL_3_ID = "Channel3";
    public static final String CHANNEL_4_ID = "Channel4";


    // called before even any activity starts. 
    
    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }

    private void createNotificationChannel() {
        // we have to check if we are on androidoreo
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannelGroup group1 = new NotificationChannelGroup(
                    GROUP_1_ID,
                    "Group 1"
            );


            NotificationChannelGroup group2 = new NotificationChannelGroup(
                    GROUP_2_ID,
                    "Group 2"
            );

            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_1_ID, "Channel 1",
                    NotificationManager.IMPORTANCE_HIGH
            );

            channel.setDescription("This is channel 1");
            channel.setGroup(GROUP_1_ID);


            NotificationChannel channel2 = new NotificationChannel(
                    CHANNEL_2_ID, "Channel 2",
                    NotificationManager.IMPORTANCE_LOW
            );

            channel2.setDescription("This is channel 2");
            channel2.setGroup(GROUP_1_ID);


            // FOR CHANNEL 3 AND 4


            NotificationChannel channeL3 = new NotificationChannel(
                    CHANNEL_3_ID, "Channel 3",
                    NotificationManager.IMPORTANCE_HIGH
            );

            channeL3.setDescription("This is channel 3");
            channeL3.setGroup(GROUP_2_ID);


            NotificationChannel channel4 = new NotificationChannel(
                    CHANNEL_4_ID, "Channel 4",
                    NotificationManager.IMPORTANCE_LOW
            );

            channel4.setDescription("This is channel 4");






            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannelGroup(group1);
            manager.createNotificationChannelGroup(group2);
            manager.createNotificationChannel(channel);
            manager.createNotificationChannel(channel2);
            manager.createNotificationChannel(channeL3);
            manager.createNotificationChannel(channel4);

        }




    }


}
