package com.example.juanjo.juanjoqueue.commands;

import android.widget.TextView;
import com.example.juanjo.juanjoqueue.queue.Command;
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
      System.out.println("=> onFinalize " + items);
      mTextview.setText("onFinalize " + items);
   }

   @Override public Command call() throws Exception {
      System.out.println("=> onExecute " + items);
      return this;
   }

   public TextView getmTextview() {
      return mTextview;
   }

   public List<Integer> getItems() {
      return items;
   }
}
