package com.hesudi.reminderclient.app;

import android.os.AsyncTask;
import android.widget.GridView;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
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
    private static final String m_serverAddress = "localhost:8080";
    String jsonTest = "{data: \\r\\n\t[{name: 'test',\\r\\n\tstatus: 1},\\r\\n\t{name: 'test1',\\r\\n\tstatus: 0},\\r\\n\t{name: 'test2',\\r\\n\tstatus: 0},\\r\\n\t{name: 'test3',\\r\\n\tstatus: 1},\\r\\n\t{name: 'test4',\\r\\n\tstatus: 1},\\r\\n\t{name: 'test5',\\r\\n\tstatus: 0},\\r\\n]}";
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
    private ArrayList<Item> getItems() {
        return jsonDecode(jsonTest/*httpRequest(m_serverAddress)*/);
    }
    private String httpRequest(String url) {
        try {
            return EntityUtils.toString(
                    (new DefaultHttpClient())
                            .execute(new HttpGet(url))
                            .getEntity()
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return httpRequest(url);
    }

    private ArrayList<Item> jsonDecode(String encoded) {
        ArrayList<Item> items = new ArrayList<Item>();
        try {
            JSONArray jsonItems = (new JSONObject(encoded.replace("\\r\\n", " "))).getJSONArray("data");
            for (int i = 0; i < jsonItems.length(); ++i) {
                items.add(new Item(jsonItems.getJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    protected Void doInBackground(List<Item>... lists) {
        ArrayList<Item> items;

        while (true) {
            Random r = new Random();
            try {
                items = getItems();
                Integer randomId = r.nextInt(items.size());
                Item old = items.get(randomId);
                Item replacement = new Item(old.name, r.nextInt(2));
                items.set(randomId, replacement);
                publishProgress(items);
                Thread.sleep(500);
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
