package com.example.notificationexample.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class Service extends BroadcastReceiver {


    // without having to open the app we can perform
    // some action

    @Override
    public void onReceive(Context context, Intent intent) {

        String message = intent.getStringExtra("toastMessage");
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();


    }
}
