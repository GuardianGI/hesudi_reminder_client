package com.hesudi.reminderclient.app;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Martin on 11-4-2014.
 */
public class Item {
    public String name;
    public Integer status;
    public Integer id;

    private String m_deletePath = "/notifications/%d.json";

    public Item(JSONObject jsonItem) {
        try {
            name = jsonItem.getString("text");
            status = jsonItem.getInt("status") - 1;
            id = jsonItem.getInt("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Item(String name, Integer status) {
        this.name = name;
        this.status = status;
    }

    public void snooze() {
        MyHttpAdapter.httpRequest(String.format(m_deletePath, id), null);
    }
}
