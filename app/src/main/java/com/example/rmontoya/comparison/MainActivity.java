package com.example.rmontoya.comparison;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rmontoya.comparison.async.ComparisonAsync;
import com.example.rmontoya.comparison.handler.ComparisonRunnable;
import com.example.rmontoya.comparison.listener.AsyncResultListener;
import com.example.rmontoya.comparison.listener.HandlerResultListener;
import com.example.rmontoya.comparison.listener.ThreadResultListener;
import com.example.rmontoya.comparison.thread.ComparisonThread;

public class MainActivity extends AppCompatActivity implements AsyncResultListener,
        ThreadResultListener, HandlerResultListener, View.OnClickListener {

    private final String HANDLER_THREAD_NAME = "new thread";
    Button startButton;

    TextView asyncCounterTextView;
    TextView threadCounterTextView;
    TextView handlerCounterTextView;

    private Handler handler;
    private ComparisonThread thread;
    private ComparisonAsync async;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViews();
    }

    private void setViews() {
        asyncCounterTextView = (TextView) findViewById(R.id.counter_async);
        threadCounterTextView = (TextView) findViewById(R.id.counter_thread);
        handlerCounterTextView = (TextView) findViewById(R.id.counter_handler);
        startButton = (Button) findViewById(R.id.start_button);
        startButton.setOnClickListener(this);
    }

    private void startThreadCounter() {
        thread = new ComparisonThread(this);
        thread.start();
    }

    private void startAsyncCounter() {
        async = new ComparisonAsync(this);
        async.execute();
    }

    private void startHandlerCounter() {
        HandlerThread handlerThread = new HandlerThread(HANDLER_THREAD_NAME);
        handlerThread.start();
        ComparisonRunnable runnable = new ComparisonRunnable(this);
        handler = new Handler(handlerThread.getLooper());
        handler.post(runnable);
    }

    @Override
    public void onClick(View view) {
        startHandlerCounter();
        startAsyncCounter();
        startThreadCounter();
        startButton.setEnabled(false);
    }

    @Override
    public void onAsyncResult(final Integer result) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                asyncCounterTextView.setText(String.valueOf(result));
            }
        });
    }

    @Override
    public void onThreadResult(final Integer result) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                threadCounterTextView.setText(String.valueOf(result));
            }
        });
    }

    @Override
    public void onHandlerResultListener(final Integer result) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                handlerCounterTextView.setText(String.valueOf(result));
            }
        });
    }

}
