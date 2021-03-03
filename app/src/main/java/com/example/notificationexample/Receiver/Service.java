package com.example.notificationexample.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.core.app.RemoteInput;

import com.example.notificationexample.MainActivity;
import com.example.notificationexample.Message;

public class Service extends BroadcastReceiver {


    // without having to open the app we can perform
    // some action

    @Override
    public void onReceive(Context context, Intent intent) {


        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
        if (remoteInput!=null) {

            CharSequence replyText = remoteInput.getCharSequence("key_text_reply"); // new input
            Message answer = new Message(replyText, null); // creating new message
            MainActivity.MESSAGES.add(answer); // adding this message into list

            MainActivity.SendOnOneShit(context); // updating the messages

        }


    }
}
