package com.example.juanjo.juanjoqueue.queue;

/**
 * Created by juanjo on 27/11/15.
 */
public interface Command {

    void onPrepare();

    void onExecute();

    void onFinalize();
}
