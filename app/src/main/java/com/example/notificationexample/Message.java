package com.example.notificationexample;

public class Message {

    CharSequence text;
    long timeStamp;
    CharSequence sender;

    public Message(CharSequence text,  CharSequence sender) {
        this.text = text;
        this.sender = sender;
        timeStamp = System.currentTimeMillis();

    }


    public CharSequence getText() {
        return text;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public CharSequence getSender() {
        return sender;
    }
}
