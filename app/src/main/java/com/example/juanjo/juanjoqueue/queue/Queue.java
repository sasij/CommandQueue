package com.example.juanjo.juanjoqueue.queue;

/**
 * Created by juanjo on 27/11/15.
 */
public interface Queue<T> {

   boolean add(T action);

   void clean();
}
