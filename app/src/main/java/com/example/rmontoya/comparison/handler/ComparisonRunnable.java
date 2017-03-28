package com.example.rmontoya.comparison.handler;

import com.example.rmontoya.comparison.listener.HandlerResultListener;

public class ComparisonRunnable implements Runnable {

    HandlerResultListener listener;

    public ComparisonRunnable(HandlerResultListener handlerResultListener) {
        this.listener = handlerResultListener;
    }

    @Override
    public void run() {
        try {
            for (int counter = 1; counter <= 10; counter++) {
                Thread.sleep(1000);
                listener.onHandlerResultListener(counter);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
