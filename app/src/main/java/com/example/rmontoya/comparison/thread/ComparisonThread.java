package com.example.rmontoya.comparison.thread;

import com.example.rmontoya.comparison.listener.ThreadResultListener;

public class ComparisonThread extends Thread {

    public static final String THREAD_RESULT = "thread";
    private ThreadResultListener listener;

    public ComparisonThread(ThreadResultListener listener) {
        this.listener = listener;
    }

    @Override
    public void run() {
        try {
            for (int counter = 1; counter <= 10; counter++) {
                Thread.sleep(1000);
                listener.onThreadResult(counter);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
