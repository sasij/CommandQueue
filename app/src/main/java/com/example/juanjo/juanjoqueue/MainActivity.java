package com.example.juanjo.juanjoqueue;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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
      Button button = (Button) findViewById(R.id.button);
      button.setOnClickListener(new View.OnClickListener() {
         @Override public void onClick(View v) {
            createTask();
         }
      });
      queue = new WorkerQueue(Executors.newSingleThreadExecutor(), new Handler());
   }

   private Command createAddCommand(final int x, int y, int z) {
      System.out.println("=>" + x);
      return new Add(Arrays.asList(x, y, z), result);
   }

   private void createTask() {
      rnd = new Random();
      int x = (int) (rnd.nextDouble() * 10 + 1);
      int y = (int) (rnd.nextDouble() * 30 + 1);
      int z = (int) (rnd.nextDouble() * 50 + 1);
      queue.add(createAddCommand(x, y, z));
   }
}

