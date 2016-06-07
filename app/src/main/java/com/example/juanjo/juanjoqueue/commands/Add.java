package com.example.juanjo.juanjoqueue.commands;

import android.widget.TextView;

import com.example.juanjo.juanjoqueue.queue.Command;

/**
 * Created by juanjo on 30/11/15.
 */
public class Add implements Command {

    TextView mTextview;
    int id;

    public Add(int id, TextView result) {
        mTextview = result;
        this.id = id;
    }

    @Override
    public void onPrepare() {
        System.out.println("=> onPrepare " + id);
    }

    @Override
    public void onExecute() {
        System.out.println("=> onExecute " + id);

    }

    @Override
    public void onFinalize() {
        System.out.println("=> onFinalize " + id);
        mTextview.setText("onFinalize " + id);
    }
}
