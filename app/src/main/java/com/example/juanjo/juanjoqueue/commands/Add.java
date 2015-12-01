package com.example.juanjo.juanjoqueue.commands;

import android.widget.TextView;

import com.example.juanjo.juanjoqueue.queue.Command;

/**
 * Created by juanjo on 30/11/15.
 */
public class Add implements Command {

    TextView mTextview;

    public Add(TextView result) {
        mTextview = result;
    }

    @Override
    public void onPrepare() {
        System.out.println("=> onPrepare");
    }

    @Override
    public void onExecute() {
        System.out.println("=> onExecute");

    }

    @Override
    public void onFinalize() {
        System.out.println("=> onFinalize");
        mTextview.setText("onFinalize");
    }
}
