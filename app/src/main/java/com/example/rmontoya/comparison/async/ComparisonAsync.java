package com.example.rmontoya.comparison.async;

import android.os.AsyncTask;

import com.example.rmontoya.comparison.listener.AsyncResultListener;

public class ComparisonAsync extends AsyncTask<Void, Void, Void> {

    private AsyncResultListener listener;

    public ComparisonAsync(AsyncResultListener resultListener) {
        this.listener = resultListener;
    }


    @Override
    protected Void doInBackground(Void... voids) {
        try {
            for (int counter = 1; counter <= 10; counter++) {
                Thread.sleep(1000);
                listener.onAsyncResult(counter);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

}