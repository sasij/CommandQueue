package com.example.juanjo.juanjoqueue.queue;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by juanjo on 27/11/15.
 */
public class HandlerQueue extends HandlerThread implements Queue {

    private static final String TAG = HandlerQueue.class.getSimpleName();
    //    private static BlockingQueue<Command> sPoolWorkQueue =
//            new ArrayBlockingQueue<Command>(10);
    private static java.util.Queue<Command> sPoolWorkQueue = new ConcurrentLinkedQueue<>();


    private boolean inAction;
    private Handler responseHandler;
    private Handler workerHandler;

    public HandlerQueue(Handler resposeHandler) {
        super(TAG);
        this.responseHandler = resposeHandler;
        start();
    }

    @Override
    public long add(Command action) {
        synchronized (this) {
            Command command = sPoolWorkQueue.poll();
            command.onPrepare();
            workerHandler.obtainMessage(0, command).sendToTarget();
        }
        return 0L;
    }


    @Override
    public void prepareQueue() {

        workerHandler = new Handler(getLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(final Message msg) {
                synchronized (this) {
                    final Command command = (Command) msg.obj;
                    try {
                        command.onExecute();
                    } finally {
                        responseHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                command.onFinalize();
                                inAction = false;
                                scheduleNext();
                            }
                        });
                    }
                    return false;
                }
            }
        });
    }

    @Override
    public void removeMessages() {

    }

    @Override
    public void clean() {
        sPoolWorkQueue = null;
        responseHandler = null;
        workerHandler = null;
    }

    private synchronized void scheduleNext() {

        if (!inAction && !sPoolWorkQueue.isEmpty()) {
            inAction = true;
            Command command = sPoolWorkQueue.poll();
            workerHandler.obtainMessage(0, command).sendToTarget();
        }
    }
}