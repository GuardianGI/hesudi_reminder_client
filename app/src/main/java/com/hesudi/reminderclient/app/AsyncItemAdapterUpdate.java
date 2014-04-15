package com.hesudi.reminderclient.app;

import android.os.AsyncTask;
import android.view.View;
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
    private static final String m_itemPath = "notifications.json";
    String jsonTest = "{data: \\r\\n\t[{name: 'test',\\r\\n\tstatus: 1},\\r\\n\t{name: 'test1',\\r\\n\tstatus: 0},\\r\\n\t{name: 'test2',\\r\\n\tstatus: 0},\\r\\n\t{name: 'test3',\\r\\n\tstatus: 1},\\r\\n\t{name: 'test4',\\r\\n\tstatus: 1},\\r\\n\t{name: 'test5',\\r\\n\tstatus: 0},\\r\\n]}";
    Boolean noLan = false;
    private ArrayList<Item> getItems() {
        return jsonDecode(noLan ? jsonTest : MyHttpAdapter.httpRequest(m_itemPath));
    }

    private ArrayList<Item> jsonDecode(String encoded) {
        ArrayList<Item> items = new ArrayList<Item>();
        try {
            JSONArray jsonItems = (new JSONArray(encoded.replace("\\r\\n", " ")));
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
                Item replacement = new Item(old.name, r.nextInt(3));
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

        int index = m_gvItems.getFirstVisiblePosition();

        m_gvItems.setAdapter(adapter);
//        try {
//            m_gvItems.smoothScrollToPosition(index);
//        } catch (Exception e) {
//            //ignore and let it scroll to top.... (for SDK version < 8
//        }
    }
}
