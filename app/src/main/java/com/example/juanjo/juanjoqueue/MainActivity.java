package com.example.juanjo.juanjoqueue;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.juanjo.juanjoqueue.commands.Add;
import com.example.juanjo.juanjoqueue.queue.Command;
import com.example.juanjo.juanjoqueue.queue.HandlerQueue;
import com.example.juanjo.juanjoqueue.queue.Queue;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Queue queue;

    TextView result;
    Random rnd;

    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = (TextView) findViewById(R.id.result);

        queue = new HandlerQueue(new Handler());
        queue.prepareQueue();

        rnd = new Random();

        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int x = (int) (rnd.nextDouble() * 10 + 1);
                    if (x % 2 == 0) {
                        queue.add(createAddCommand(x));
                    }
                }
            }).start();
        }
    }

    private Command createAddCommand(final int number) {

        System.out.println("=>" + number);
        return new Add(number, result);
    }

//    private Command remove(final double number) {
//        return new Command() {
//            @Override
//            public void onPrepare() {
//                //nothing to do
//            }
//
//            @Override
//            public void onExecute() {
//                System.out.println("=> Remove:" + number + " to " + total);
//                total -= number;
//            }
//
//            @Override
//            public void onFinalize() {
//                System.out.println("=> Finalize Remove:" + number + " to " + total);
////                result.setText(total);
//
//            }
//        };
//    }
}
