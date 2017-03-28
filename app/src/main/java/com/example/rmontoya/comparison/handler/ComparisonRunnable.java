package com.example.rmontoya.comparison.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class ComparisonRunnable implements Runnable {

    public static final String RUNNABLE_RESULT = "runnable";
    private Handler uiHandler;

    public ComparisonRunnable(Handler handler) {
        this.uiHandler = handler;
    }

    @Override
    public void run() {
        try {
            for (int counter = 1; counter <= 10; counter++) {
                Thread.sleep(1000);
                uiHandler.sendMessage(buildRunnableMessage(counter));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Message buildRunnableMessage(int counter) {
        Message threadMessage = new Message();
        Bundle bundle = new Bundle();
        bundle.putInt(RUNNABLE_RESULT, counter);
        threadMessage.setData(bundle);
        return threadMessage;
    }
}
