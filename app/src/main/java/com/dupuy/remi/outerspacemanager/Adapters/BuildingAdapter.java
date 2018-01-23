package com.dupuy.remi.outerspacemanager.Adapters;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dupuy.remi.outerspacemanager.Models.Building;
import com.dupuy.remi.outerspacemanager.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by lledent on 23/01/2018.
 */

public class BuildingAdapter extends ArrayAdapter<Building> {
    private final Context context;
    private final List<Building> values;
    public BuildingAdapter(Context context, List<Building> values) {
        super(context, R.layout.building_adapter, values);
        this.context = context;
        this.values = values;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.building_adapter, parent, false);
        TextView building_name = (TextView) rowView.findViewById(R.id.building_name);
        building_name.setText(values.get(position).getName());

        ImageView image = (ImageView) rowView.findViewById(R.id.image_building);
        Glide.with(rowView).load(values.get(position).getImageUrl()).into(image);
        return rowView;
    }
}