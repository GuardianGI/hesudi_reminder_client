package com.hesudi.reminderclient.app;

import android.os.AsyncTask;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Martin on 11-4-2014.
 */
public class AsyncItemAdapterUpdate extends AsyncTask<List<Item>, List<Item>, Void> {
    private GridView m_gvItems;
    public AsyncItemAdapterUpdate(GridView gvItems) {
        m_gvItems = gvItems;
    }

    private ArrayList<Item> getSampleData() {
        ArrayList<Item> items = new ArrayList<Item>();
        items.add(new Item("Go to bathroom", 0));
        items.add(new Item("eat", 1));
        items.add(new Item("go outside", 0));
        items.add(new Item("sleep", 0));
        items.add(new Item("feed the cat", 1));
        items.add(new Item("pet the cat", 0));
        return items;
    }
    @Override
    protected Void doInBackground(List<Item>... lists) {
        ArrayList<Item> items = getSampleData();
        while (true) {
            Random r = new Random();
            try {
                Integer randomId = r.nextInt(items.size());
                Item old = items.get(randomId);
                Item replacement = new Item(old.name, r.nextInt(2));
                items.set(randomId, replacement);
                publishProgress(items);
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    protected void onProgressUpdate(List<Item>... updatedItems) {
        ItemArrayAdapter adapter = new ItemArrayAdapter(
                updatedItems[updatedItems.length - 1],
                Main.getInstance(),
                R.layout.item);
        m_gvItems.setAdapter(adapter);
    }
}
