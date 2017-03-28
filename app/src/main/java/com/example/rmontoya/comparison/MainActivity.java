package com.example.rmontoya.comparison;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rmontoya.comparison.async.ComparisonAsync;
import com.example.rmontoya.comparison.handler.ComparisonRunnable;
import com.example.rmontoya.comparison.thread.ComparisonThread;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final String HANDLER_THREAD_NAME = "new thread";

    Button startButton;
    TextView asyncCounterTextView;
    TextView threadCounterTextView;
    TextView handlerCounterTextView;

    private Handler counterHandler;
    private ComparisonThread thread;
    private ComparisonAsync async;
    private Handler uiHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setHandler();
        setViews();
    }

    private void setHandler() {
        uiHandler = new Handler(getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                Bundle bundle = msg.getData();
                if (bundle.containsKey(ComparisonThread.THREAD_RESULT)) {
                    int counter = (int) bundle.get(ComparisonThread.THREAD_RESULT);
                    threadCounterTextView.setText(String.valueOf(counter));
                } else if (bundle.containsKey(ComparisonRunnable.RUNNABLE_RESULT)) {
                    int counter = (int) bundle.get(ComparisonRunnable.RUNNABLE_RESULT);
                    handlerCounterTextView.setText(String.valueOf(counter));
                } else if (bundle.containsKey(ComparisonAsync.ASYNC_RESULT)) {
                    int counter = (int) bundle.get(ComparisonAsync.ASYNC_RESULT);
                    asyncCounterTextView.setText(String.valueOf(counter));
                }
            }
        };
    }

    private void setViews() {
        asyncCounterTextView = (TextView) findViewById(R.id.counter_async);
        threadCounterTextView = (TextView) findViewById(R.id.counter_thread);
        handlerCounterTextView = (TextView) findViewById(R.id.counter_handler);
        startButton = (Button) findViewById(R.id.start_button);
        startButton.setOnClickListener(this);
    }

    private void startThreadCounter() {
        thread = new ComparisonThread(uiHandler);
        thread.start();
    }

    private void startAsyncCounter() {
        async = new ComparisonAsync(uiHandler);
        async.execute();
    }

    private void startHandlerCounter() {
        HandlerThread handlerThread = new HandlerThread(HANDLER_THREAD_NAME);
        handlerThread.start();
        ComparisonRunnable runnable = new ComparisonRunnable(uiHandler);
        counterHandler = new Handler(handlerThread.getLooper());
        counterHandler.post(runnable);
    }

    @Override
    public void onClick(View view) {
        startHandlerCounter();
        startAsyncCounter();
        startThreadCounter();
        startButton.setEnabled(false);
    }

}