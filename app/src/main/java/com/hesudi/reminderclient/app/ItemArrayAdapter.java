package com.hesudi.reminderclient.app;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

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
        final Item item = getItem(i);
        if (null == view) {
            LayoutInflater aInflater = LayoutInflater.from(Main.getInstance());
            v = aInflater.inflate(R.layout.item, viewGroup, false);
        }
        TextView txtName = (TextView) v.findViewById(R.id.txtName);
        Button btnSnooze = (Button) v.findViewById(R.id.btnSnooze);

        btnSnooze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.snooze();
            }
        });

        txtName.setText(item.name);
        int strokeColor = 0;
        int bgColor = (i % 2 == 0) ? R.color.bg : R.color.bgAlt;
        switch (item.status) {
            case 0:
                strokeColor = R.color.green;
                break;
            case 1:
                strokeColor = R.color.yellow;
                break;
            case 2:
                strokeColor = R.color.red;
                break;
        }
        GradientDrawable bg = (GradientDrawable)v.getBackground();
        bg.setStroke(10, v.getResources().getColor(strokeColor));
        bg.setColor(v.getResources().getColor(bgColor));
        return v;
    }
    Random r = new Random();
}
