package com.example.rmontoya.comparison.thread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class ComparisonThread extends Thread {

    public static final String THREAD_RESULT = "thread";
    private Handler uiHandler;

    public ComparisonThread(Handler handler) {
        this.uiHandler = handler;
    }

    @Override
    public void run() {
        try {
            for (int counter = 1; counter <= 10; counter++) {
                Thread.sleep(1000);
                uiHandler.sendMessage(buildThreadMessage(counter));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Message buildThreadMessage(int counter) {
        Message threadMessage = new Message();
        Bundle bundle = new Bundle();
        bundle.putInt(THREAD_RESULT, counter);
        threadMessage.setData(bundle);
        return threadMessage;
    }

}
