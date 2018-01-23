package com.dupuy.remi.outerspacemanager.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dupuy.remi.outerspacemanager.Models.Building;
import com.dupuy.remi.outerspacemanager.Models.Ship;
import com.dupuy.remi.outerspacemanager.R;

import java.util.List;

/**
 * Created by lledent on 23/01/2018.
 */

public class ShipAdapter extends ArrayAdapter<Ship> {
    private final Context context;
    private final List<Ship> values;
    public ShipAdapter(Context context, List<Ship> values) {
        super(context, R.layout.building_adapter, values);
        this.context = context;
        this.values = values;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.building_adapter, parent, false);
        TextView ship_name = (TextView) rowView.findViewById(R.id.ship_name);
        ship_name.setText(values.get(position).getName());

        return rowView;
    }
}