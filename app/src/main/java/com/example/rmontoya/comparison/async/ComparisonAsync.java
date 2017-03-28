package com.example.rmontoya.comparison.async;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class ComparisonAsync extends AsyncTask<Void, Void, Void> {

    public static final String ASYNC_RESULT = "async";
    private Handler uiHandler;

    public ComparisonAsync(Handler handler) {
        this.uiHandler = handler;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            for (int counter = 1; counter <= 10; counter++) {
                Thread.sleep(1000);
                uiHandler.sendMessage(buildAsyncMessage(counter));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Message buildAsyncMessage(int counter) {
        Message threadMessage = new Message();
        Bundle bundle = new Bundle();
        bundle.putInt(ASYNC_RESULT, counter);
        threadMessage.setData(bundle);
        return threadMessage;
    }

}