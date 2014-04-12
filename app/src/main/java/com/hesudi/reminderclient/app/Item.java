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

    public Item(JSONObject jsonItem) {
        try {
            name = jsonItem.getString("name");
            status = jsonItem.getInt("status");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Item(String name, Integer status) {
        this.name = name;
        this.status = status;
    }
}
