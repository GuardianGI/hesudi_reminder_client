package com.hesudi.reminderclient.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Martin on 11-4-2014.
 */
public class ItemArrayAdapter extends ArrayAdapter<Item> implements ListAdapter {
    public ItemArrayAdapter(List<Item> items, Context context, int resource) {
        super(context, resource);
        for (int i = 0; i < items.size(); ++i) {
            Item n = items.get(i);
            n.id = i;
            add(n);
        }
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        Item item = getItem(i);
        if (null == view) {
            LayoutInflater aInflater = LayoutInflater.from(Main.getInstance());
            v = aInflater.inflate(R.layout.item, viewGroup, false);
        }
        TextView txtName = (TextView) v.findViewById(R.id.txtName);

        txtName.setText(item.name);
        switch (item.status) {
            case 0:
                v.setBackgroundColor(v.getResources().getColor(R.color.green));
                break;
            case 1:
                v.setBackgroundColor(v.getResources().getColor(R.color.red));
                break;
        }
        return v;
    }
}
