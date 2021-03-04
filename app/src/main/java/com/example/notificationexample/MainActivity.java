package com.example.notificationexample;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.RemoteInput;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.notificationexample.Receiver.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.example.notificationexample.Notification.App.CHANNEL_1_ID;
import static com.example.notificationexample.Notification.App.CHANNEL_2_ID;

public class MainActivity extends AppCompatActivity {


    String message, title;
    Button sendOne, sendTwo;
    EditText etTitle, et_Message;

    NotificationManagerCompat notificationManagerCompat;

    public static List<Message> MESSAGES = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationManagerCompat = NotificationManagerCompat.from(this);


        et_Message = findViewById(R.id.et_message);
        etTitle = findViewById(R.id.editText_ttitle);
        sendOne = findViewById(R.id.sendonchannel1);
        sendTwo = findViewById(R.id.sendonchannel2);


        MESSAGES.add(new Message("Good Morning", "Jim"));
        MESSAGES.add(new Message("Yeah Wake Up Dog", null));
        MESSAGES.add(new Message("Behave Yourself", "Jenny"));


        sendOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                message = et_Message.getText().toString();
                title = etTitle.getText().toString();


                SendOneChannel1(getApplicationContext());


            }
        });


        sendTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                message = et_Message.getText().toString();
                title = etTitle.getText().toString();


                SendOnTwoShit(message, title);


            }
        });

    }


    // managing shit for remote input

    public void SendOneChannel1(Context context) {
        SendOnOneShit(this);


    }

    public static void SendOnOneShit(Context context) {


        // openactivity

        Intent intent = new Intent(context, MainActivity.class);
        // notification only takes in a pendingintent
        // pending intent is a wrapper around the normal intent
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, intent, 0);


        // its the input result of the notification item
        // this remote input is only available in android nougat or higher

        RemoteInput remoteInput = new RemoteInput.Builder("key_text_reply").setLabel("Your answer...").build();


        Intent replyIntent;
        PendingIntent replypending = null;
        // in case of lower api remoteinput wont work so we can just give an option to start the activity instead of replying option

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

            replyIntent = new Intent(context, Service.class);
            replypending = PendingIntent.getBroadcast(context,
                    0, replyIntent, 0);


        } else {
            // we dont have chat activity we can put this into
            // todo add chat activity (PendingIntent.getActivity)
            // todo cancel the notification with notificationManagerCompat.cancel(id);

        }


        // now we will create the action

        NotificationCompat.Action replyAction = new NotificationCompat.Action.Builder(
                R.drawable.reply,
                "Reply",
                replypending).addRemoteInput(remoteInput).build();


        // we can follow same shit for mediaplayer

        NotificationCompat.MessagingStyle messagingStyle =
                new NotificationCompat.MessagingStyle("Me");
        messagingStyle.setConversationTitle("Group Chat"); // only use this with more than 2 people

        // this is looping the messsages
        for (Message chatMessage : MESSAGES) {

            NotificationCompat.MessagingStyle.Message NotificationMessage = new NotificationCompat.MessagingStyle.Message(
                    chatMessage.getText(),
                    chatMessage.getTimeStamp(),
                    chatMessage.getSender()
            );

            messagingStyle.addMessage(NotificationMessage);


        }

        Notification notification = new NotificationCompat.Builder(context, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.oness) // its manadotry
                .setStyle(messagingStyle)
                .addAction(replyAction)
                .setColor(Color.BLUE)
                .setContentIntent(contentIntent) // takes the user to activity
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setAutoCancel(true) // if we click notification goes away
                .build();
        NotificationManagerCompat nMc = NotificationManagerCompat.from(context);
        nMc.notify(1, notification);

    }

    private void SendOnTwoShit(String message, String title) {

        // on android nougat and onwards system automatically's group notification together

        String title1 = "Title 1";
        String message1 = "Message 1";


        String title2 = "Title 2";
        String message2 = "Message 2";


        Notification notification1 = new NotificationCompat.Builder(this, CHANNEL_2_ID)
                .setSmallIcon(R.drawable.two) // its manadotry
                .setContentTitle(title1)
                .setContentText(message1)
                .setPriority(NotificationCompat.PRIORITY_LOW)// it is does not make sound //
                .setGroup("example_group") // TO IDENTIFY THE GROUP OF NOTIFICATION
                .build();



        Notification notification2 = new NotificationCompat.Builder(this, CHANNEL_2_ID)
                .setSmallIcon(R.drawable.two) // its manadotry
                .setContentTitle(title2)
                .setContentText(message2)
                .setPriority(NotificationCompat.PRIORITY_LOW)// it is does not make sound //
                .setGroup("example_group") // TO IDENTIFY THE GROUP OF NOTIFICATION
                .build();



        Notification summaryNotification = new NotificationCompat.Builder(this, CHANNEL_2_ID)
                .setSmallIcon(R.drawable.reply) // its manadotry
                .setStyle(new NotificationCompat.InboxStyle()
                         .addLine(title2 + " " + message2)
                        .addLine(title1 + " " + message1)
                .setBigContentTitle("2 New Message")
                .setSummaryText("user@username.com"))
                .setPriority(NotificationCompat.PRIORITY_LOW)// it is does not make sound //
                .setGroup("example_group") // TO IDENTIFY THE GROUP OF NOTIFICATION
                .setGroupAlertBehavior(NotificationCompat.GROUP_ALERT_CHILDREN) // if our prority is higher then first two will make noise
                .build();


        SystemClock.sleep(2000);

        notificationManagerCompat.notify(2, notification1);
        SystemClock.sleep(2000);

        notificationManagerCompat.notify(3, notification2);

        SystemClock.sleep(2000);

        notificationManagerCompat.notify(4, summaryNotification);


        // for lower api we have to specify the group name so it puts them together

/*
        for (int i = 0; i < 5 ; i++) {

            SystemClock.sleep(2000); // freeze the app creating a short delay
            // in real app we should not do this on main thread because it would freze the whole app

            notificationManagerCompat.notify(i, notification);



        }*/


    }

}
