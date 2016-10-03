package com.example.juanjo.juanjoqueue.queue;

import android.os.Handler;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by juanjo on 3/10/16.
 */

public class WorkerQueue<T extends Command> implements Queue<T> {

   private volatile BlockingQueue<T> queue = new LinkedBlockingQueue<>();
   private ExecutorService executorService;
   private Handler uiThread;
   private boolean inAction;

   public WorkerQueue(ExecutorService executorService, Handler uiThread) {
      this.executorService = executorService;
      this.uiThread = uiThread;
   }

   @Override public synchronized boolean add(T action) {
      try {
         queue.put(action);
         scheduleNext();
      } catch (InterruptedException e) {
         throw new RuntimeException("Task scheduling was interrupted!");
      }
      return true;
   }

   @Override public void clean() {
      queue.clear();
      queue = null;
   }

   private synchronized void scheduleNext() throws InterruptedException {
      if (!inAction && !queue.isEmpty()) {
         inAction = true;
         processNext(queue.take());
      }
   }

   private void processNext(final T task) {
      new Thread(new Runnable() {
         @Override public void run() {
            Future<T> future = null;
            try {
               future = executorService.submit(task);
               while (!future.isDone()) ;
            } finally {
               try {
                  future.get().onFinalize();
                  inAction = false;
                  scheduleNext();
               } catch (Exception e) {
                  e.printStackTrace();
               }
            }
         }
      }).start();
   }
}
