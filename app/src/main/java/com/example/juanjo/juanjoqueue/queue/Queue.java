package com.example.juanjo.juanjoqueue.queue;

/**
 * Created by juanjo on 27/11/15.
 */
public interface Queue {

    void prepareQueue();

    long add(Command action);

    void removeMessages();

    void clean();

}
