package com.example.juanjo.juanjoqueue.commands;

import android.widget.TextView;
import com.juanjo.queue.Command;
import java.util.List;

/**
 * Created by juanjo on 30/11/15.
 */
public class Add implements Command {

   TextView mTextview;
   List<Integer> items;

   public Add(List<Integer> items, TextView result) {
      mTextview = result;
      this.items = items;
   }

   @Override public void onPrepare() {
      System.out.println("=> onPrepare " + items);
   }

   @Override public void onFinalize() {
      System.out.println("=> onFinalize " + items.get(0));
      mTextview.setText("onFinalize " + items.get(0));
   }

   @Override public Command call() throws Exception {
      System.out.println("=> onExecute " + items.get(0));
      Thread.sleep(2000);
      return this;
   }

   public TextView getTextview() {
      return mTextview;
   }

   public List<Integer> getItems() {
      return items;
   }
}
