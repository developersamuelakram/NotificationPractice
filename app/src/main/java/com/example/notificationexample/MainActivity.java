package com.example.notificationexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.notificationexample.Receiver.Service;

import static com.example.notificationexample.Notification.App.CHANNEL_1_ID;
import static com.example.notificationexample.Notification.App.CHANNEL_2_ID;

public class MainActivity extends AppCompatActivity {



    String message, title;
    Button sendOne, sendTwo;
    EditText etTitle, et_Message;

    NotificationManagerCompat notificationManagerCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        notificationManagerCompat = NotificationManagerCompat.from(this);

        et_Message = findViewById(R.id.et_message);
        etTitle = findViewById(R.id.editText_ttitle);
        sendOne = findViewById(R.id.sendonchannel1);
        sendTwo = findViewById(R.id.sendonchannel2);

        sendOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                message = et_Message.getText().toString();
                title = etTitle.getText().toString();


                if (message.isEmpty()) {
                    et_Message.setError("Type Bitch");
                } else if (title.isEmpty()) {

                    etTitle.setError("Type Title Bitch");
                } else {

                    SendOnOneShit(message, title);
                }





            }
        });



        sendTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                message = et_Message.getText().toString();
                title = etTitle.getText().toString();


                if (message.isEmpty()) {
                    et_Message.setError("Type Bitch");
                } else if (title.isEmpty()) {

                    etTitle.setError("Type Title Bitch");
                } else {

                    SendOnTwoShit(message, title);
                }





            }
        });

    }



    private void SendOnOneShit(String message, String title) {


        // openactivity

        Intent intent = new Intent(this, MainActivity.class);
        // notification only takes in a pendingintent
        // pending intent is a wrapper around the normal intent
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent, 0);



        Intent broadcastintent = new Intent(this, Service.class);
        broadcastintent.putExtra("toastMessage", message);
        PendingIntent actionIntent = PendingIntent.getBroadcast(this,
                0,
                broadcastintent,
                PendingIntent.FLAG_UPDATE_CURRENT); // flag means it will update the message


        //putting an image in this shit
        Bitmap picture = BitmapFactory.decodeResource(getResources(), R.drawable.hello);




        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.oness) // its manadotry
                .setContentTitle(title)
                .setContentText(message)
                .setColor(Color.BLUE)
                .setLargeIcon(picture) // optional
                .setStyle(new NotificationCompat.BigTextStyle()
                .bigText(getString(R.string.longtext))
                .setBigContentTitle("Expanded View")
                        .setSummaryText("SummaryText"))
                // this will be different than the collapesed view
                .setContentIntent(contentIntent) // takes the user to activity
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setAutoCancel(true) // if we click notification goes away
                .addAction(R.mipmap.ic_launcher, "Button", actionIntent) // upto three buttons
                .build();

        notificationManagerCompat.notify(1, notification);

    }

    private void SendOnTwoShit(String message, String title) {

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_2_ID)
                .setSmallIcon(R.drawable.two) // its manadotry
                .setContentTitle(title)
                .setContentText(message)
                .setStyle(new NotificationCompat.InboxStyle()
                        .addLine("This is line a")
                        .addLine("This is line b")
                        .addLine("This is line c")
                        .addLine("This is line d")
                        .addLine("This is line e")
                        .addLine("This is line f")
                        .addLine("This is line g") // upto seven lines and then we can group them
                )
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setAutoCancel(true) // if we click notification goes away
                .build();

        notificationManagerCompat.notify(2, notification);

    }
}