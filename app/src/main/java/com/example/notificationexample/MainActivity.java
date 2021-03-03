package com.example.notificationexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.oness) // its manadotry
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setAutoCancel(true) // if we click notification goes away
                .build();

        notificationManagerCompat.notify(1, notification);

    }

    private void SendOnTwoShit(String message, String title) {

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_2_ID)
                .setSmallIcon(R.drawable.two) // its manadotry
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setAutoCancel(true) // if we click notification goes away
                .build();

        notificationManagerCompat.notify(2, notification);

    }
}