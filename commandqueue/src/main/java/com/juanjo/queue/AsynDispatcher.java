package com.juanjo.queue;

import java.util.concurrent.Executors;

/**
 * Created with ♥
 *
 * @author Juanjo
 */
public class AsynDispatcher<T extends Command> {

    private Queue<T> queue;

    public AsynDispatcher(UIExecutor uiThread) {
        queue = new WorkerQueue<T>(Executors.newSingleThreadExecutor(), uiThread);
    }

    public void dispatch(final T command) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                queue.add(command);
            }
        }).start();
    }

    public void clean() {
        queue.clean();
    }
}
