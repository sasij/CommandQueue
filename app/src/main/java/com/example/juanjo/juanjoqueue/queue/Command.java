package com.example.juanjo.juanjoqueue.queue;

import java.util.concurrent.Callable;

/**
 * Created by juanjo on 27/11/15.
 */
public interface Command extends Callable {

   void onPrepare();

   void onFinalize();
}
