package com.example.juanjo.juanjoqueue;

import android.os.Handler;
import com.juanjo.queue.UIExecutor;

/**
 * Created by juanjo on 16/10/16.
 */

public class UIExecutorImp implements UIExecutor {
   private final Handler uithread;

   public UIExecutorImp(Handler uithread) {
      this.uithread = uithread;
   }

   @Override public void execute(Runnable runnable) {
      uithread.post(runnable);
   }
}
