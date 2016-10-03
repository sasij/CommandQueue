package com.example.juanjo.juanjoqueue;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import com.example.juanjo.juanjoqueue.commands.Add;
import com.example.juanjo.juanjoqueue.queue.Command;
import com.example.juanjo.juanjoqueue.queue.Queue;
import com.example.juanjo.juanjoqueue.queue.WorkerQueue;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

   Queue queue;

   TextView result;
   Random rnd;

   @Override protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      result = (TextView) findViewById(R.id.result);

      queue = new WorkerQueue(Executors.newSingleThreadExecutor(), new Handler());

      rnd = new Random();
      for (int i = 0; i < 1; i++) {
         new Thread(new Runnable() {
            @Override public void run() {
               int t = (int) (rnd.nextDouble() * 1000 + 1);
               int x = (int) (rnd.nextDouble() * 10 + 1);
               int y = (int) (rnd.nextDouble() * 30 + 1);
               int z = (int) (rnd.nextDouble() * 50 + 1);
               try {
                  Thread.sleep(t);
                  queue.add(createAddCommand(x, y, z));
               } catch (InterruptedException e) {
                  e.printStackTrace();
               }
            }
         }).start();
      }
   }

   private Command createAddCommand(final int x, int y, int z) {
      System.out.println("=>" + x);
      System.out.println("=>" + y);
      System.out.println("=>" + z);
      return new Add(Arrays.asList(x, y, z), result);
   }
}

